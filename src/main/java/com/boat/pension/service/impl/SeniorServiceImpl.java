package com.boat.pension.service.impl;

import com.boat.pension.dto.EventDTO;
import com.boat.pension.dto.LogDTO;
import com.boat.pension.dto.SeniorDTO;
import com.boat.pension.dto.SeniorParam;
import com.boat.pension.mapper.*;
import com.boat.pension.model.*;
import com.boat.pension.service.DeviceService;
import com.boat.pension.service.SeniorService;
import com.boat.pension.util.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeniorServiceImpl implements SeniorService {
    @Autowired(required = false)
    private SeniorMapper seniorMapper;

    @Autowired(required = false)
    private BedMapper bedMapper;

    @Autowired(required = false)
    private InstitutionMapper institutionMapper;

    @Autowired(required = false)
    EventMapper eventMapper;
    @Autowired(required = false)
    MonitorlogMapper monitorlogMapper;

    @Autowired(required = false)
    RoomMapper roomMapper;

    @Autowired(required = false)
    WorkerMapper workerMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public SeniorDTO listAllByInstid(Integer instId) {

        SeniorExample example = new SeniorExample();
        example.createCriteria().andInstidEqualTo(instId);
        JSONArray seniors = new JSONArray();
        for(Senior s : seniorMapper.selectByExample(example)){
            JSONObject senior = new JSONObject();

            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andSenioridEqualTo(s.getId());

            senior.put("seniorID",s.getId());
            senior.put("seniorName",s.getName());
            senior.put("gender",s.getGender());
            List<Bed> bedList=bedMapper.selectByExample(bedExample);
            senior.put("registerTime",TimeUtil.date2String(s.getRegistertime()));
            senior.put("tel",s.getTel());
            if(bedList.size()>0){
                Bed bed=bedList.get(0);
                senior.put("seniorLoc",bed.getLocation());
                Worker worker = workerMapper.selectByPrimaryKey(roomMapper.selectByPrimaryKey(bed.getRoomid()).getWokerid());
                if(worker!=null){
                    senior.put("worker",worker.getName());
                    senior.put("workerTel",worker.getTel());
                }
            }else {
                senior.put("seniorLoc","未选择房间");
            }
            seniors.add(senior);
        }
        return new SeniorDTO(institutionMapper.selectByPrimaryKey(instId),seniors);
    }

    @Override
    public List<SeniorDTO> listAllinCommunityByInstid(Integer instId) {
        List<SeniorDTO> seniorDTOS = new ArrayList<>();
        InstitutionExample example = new InstitutionExample();
        example.createCriteria().andAffEqualTo(instId);
        List<Institution> communities = institutionMapper.selectByExample(example);//找到了养老院下的社区
        for (Institution community : communities) {
            SeniorExample seniorExample = new SeniorExample();
            seniorExample.createCriteria().andInstidEqualTo(community.getId());
            JSONArray seniors = new JSONArray();
            for(Senior s : seniorMapper.selectByExample(seniorExample)) {
                JSONObject senior = new JSONObject();

                BedExample bedExample = new BedExample();
                bedExample.createCriteria().andSenioridEqualTo(s.getId());

                senior.put("seniorID", s.getId());
                senior.put("seniorName", s.getName());
                senior.put("gender", s.getGender());
                senior.put("seniorLoc", bedMapper.selectByExample(bedExample).get(0).getLocation());
                senior.put("registerTime", TimeUtil.date2String(s.getRegistertime()));
                senior.put("tel", s.getTel());

                Worker worker = workerMapper.selectByPrimaryKey(roomMapper.selectByPrimaryKey(bedMapper.selectByExample(bedExample).get(0).getRoomid()).getWokerid());

                senior.put("worker", worker.getName());
                senior.put("workerTel", worker.getTel());

                seniors.add(senior);
            }
            seniorDTOS.add(new SeniorDTO(community,seniors));
        }
        return seniorDTOS;
    }

    @Override
    public int addSenior(Integer instId, SeniorParam seniorParam) {
        Senior senior = new Senior();
        BeanUtils.copyProperties(seniorParam, senior);
        senior.setInstid(instId);
        //入住时有时间
        Date utildate = new Date();
        senior.setRegistertime(utildate);
        int count = seniorMapper.insert(senior);

        if(seniorParam.getFlag() == 0){

            //老人入住时选择床位，则床位也需要修改
            //前端只显示未入住的床位，因此此处不用判断所选择的床位是否未入住
            Bed bed = bedMapper.selectByPrimaryKey(seniorParam.getBedid());
            bed.setSeniorid(senior.getId());
            bedMapper.updateByPrimaryKeySelective(bed);

        }
        else {
            //和设备绑定，如果该房间没有老人入住过，则添加房间
            RoomExample roomExample = new RoomExample();
            roomExample.createCriteria().andInstidEqualTo(instId).andBuildingnameEqualTo(seniorParam.getBuildingname()).andNumEqualTo(seniorParam.getNum());
            List<Room> rooms = roomMapper.selectByExample(roomExample);
            Bed bed = new Bed();
            if (rooms.size() == 0) {//该房间没有老人入住过
                Room room = new Room();
                room.setInstid(instId);
                room.setInstname(institutionMapper.selectByPrimaryKey(instId).getName());
                room.setBuildingname(seniorParam.getBuildingname());
                room.setSize(10);
                room.setNum(seniorParam.getNum());
                room.setFloor(1);

                roomMapper.insert(room);
                //新增床位
                bed.setNum(1);
                bed.setRoomid(room.getId());

            } else {//已有老人入住过，只新增床位
                BedExample bedExample = new BedExample();
                bedExample.createCriteria().andRoomidEqualTo(rooms.get(0).getId());
                bed.setNum(bedMapper.selectByExample(bedExample).size() + 1);
                bed.setRoomid(rooms.get(0).getId());
            }

            bed.setLocation(institutionMapper.selectByPrimaryKey(instId).getName() + " " + seniorParam.getBuildingname() + " " + seniorParam.getNum() + " " + bed.getNum());
            bed.setRoomnum(seniorParam.getNum());
            bed.setSeniorid(senior.getId());
            bed.setDeviceid(seniorParam.getDeviceid());
            int res=bedMapper.insert(bed);
            if(res>0){
                Device d= deviceMapper.selectByPrimaryKey(seniorParam.getDeviceid());
                d.setState(1);
                deviceMapper.updateByPrimaryKeySelective(d);
            }
        }
        return count;
    }


    @Override
    public int update(Integer id, SeniorParam seniorParam) {
        Senior senior = seniorMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(seniorParam, senior);
        return seniorMapper.updateByPrimaryKeySelective(senior);
    }


    @Override
    public int delete(Integer id) {
        Senior senior = seniorMapper.selectByPrimaryKey(id);
        if (senior == null)
            return 0;
        senior.setInstid(null);
        int count = seniorMapper.updateByPrimaryKeySelective(senior);
        //老人离院时解绑床位，则床位也需要修改
        BedExample example = new BedExample();
        example.createCriteria().andSenioridEqualTo(id);
        Bed bed = bedMapper.selectByExample(example).get(0);
        if (bed != null) {
            bed.setSeniorid(null);
            bedMapper.updateByPrimaryKeySelective(bed);
        }
        return count;
    }

    @Override
    public JSONObject getSeniorBySeniorId(Integer seniorId) {
        JSONObject detail = JSONObject.fromObject(seniorMapper.selectByPrimaryKey(seniorId));

        BedExample bedExample = new BedExample();
        bedExample.createCriteria().andSenioridEqualTo(seniorId);
        if(bedMapper.selectByExample(bedExample).size() > 0){
            Bed bed = bedMapper.selectByExample(bedExample).get(0);

            Worker worker = workerMapper.selectByPrimaryKey(roomMapper.selectByPrimaryKey(bed.getRoomid()).getWokerid());
            detail.put("workername",worker.getName());
            detail.put("workertel",worker.getTel());
            detail.put("location",bed.getLocation());
        }

        return detail;
    }


    @Override
    public int bind(Integer seniorid, Integer bedid) {
        //解绑
        BedExample example = new BedExample();
        example.createCriteria().andSenioridEqualTo(seniorid);
        List<Bed> beds = bedMapper.selectByExample(example);
        if(beds.size() != 0){
            Bed bed = beds.get(0);
            bed.setSeniorid(null);
            bedMapper.updateByPrimaryKey(bed);
        }

        //绑定
        Bed newbed = bedMapper.selectByPrimaryKey(bedid);
        if(newbed == null)
            return 0;
        newbed.setSeniorid(seniorid);
        int count2 = bedMapper.updateByPrimaryKeySelective(newbed);

        return count2;

    }

    @Override
    public LogDTO log(int id, String date) {
        Date logDate = TimeUtil.string2Date(date);
        MonitorlogExample example = new MonitorlogExample();
        example.setOrderByClause("logDate desc");
        example.createCriteria().andSenioridEqualTo(id).andLogdateEqualTo(logDate);
        List<Monitorlog> logs = monitorlogMapper.selectByExample(example);
        if (logs.size() > 0)
            return new LogDTO(logs.get(0));
        else
            return null;
    }

    @Override
    public LogDTO log(int id) {
        MonitorlogExample example = new MonitorlogExample();
        example.setOrderByClause("logDate desc");
        example.createCriteria().andSenioridEqualTo(id);
        List<Monitorlog> logs = monitorlogMapper.selectByExample(example);
        if (logs.size() > 0)
            return new LogDTO(logs.get(0));
        else
            return null;
    }

    @Override
    public List<EventDTO> event(int id) {
        EventExample example = new EventExample();
        example.setOrderByClause("startTime desc");
        example.createCriteria().andSenioridEqualTo(id);
        List<Event> events = eventMapper.selectByExample(example);
        List<EventDTO> result = new ArrayList<>();
        if (events.size() == 0)
            return null;
        for (Event event : events
        ) {
            result.add(new EventDTO(event,null));
        }
        return result;
    }
}
