package com.boat.pension.service.impl;

import com.boat.pension.dto.BedDTO;
import com.boat.pension.dto.RoomDTO;
import com.boat.pension.dto.RoomParam;
import com.boat.pension.mapper.*;
import com.boat.pension.model.*;
import com.boat.pension.service.CommunityService;
import com.boat.pension.service.RoomService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired(required = false)
    private RoomMapper roomMapper;

    @Autowired(required = false)
    private InstitutionMapper carehomeMapper;

    @Autowired(required = false)
    private BedMapper bedMapper;

    @Autowired(required = false)
    private WorkerMapper workerMapper;

    @Autowired
    private SeniorMapper seniorMapper;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public JSONArray getRoomBedState(Integer roomId) {
        JSONArray result = new JSONArray();
        BedExample example = new BedExample();
        example.setOrderByClause("num asc");
        example.createCriteria().andRoomidEqualTo(roomId);
        for (Bed b : bedMapper.selectByExample(example)) {
            JSONObject bed = new JSONObject();
            bed.put("id", b.getId());
            bed.put("num", b.getNum());
            bed.put("state", b.getSeniorid() != null);
            result.add(bed);
        }
        return result;
    }


    @Override
    public JSONObject getRoomDetailByRoomId(Integer roomId) {
        Room room = roomMapper.selectByPrimaryKey(roomId);
        Worker worker = workerMapper.selectByPrimaryKey(room.getWokerid());
        BedExample bedexample = new BedExample();
        bedexample.setOrderByClause("num asc");
        bedexample.createCriteria().andRoomidEqualTo(room.getId());
        List<BedDTO> bedDTOList = new ArrayList<>();
        for (Bed b : bedMapper.selectByExample(bedexample)) {
            Senior senior = seniorMapper.selectByPrimaryKey(b.getSeniorid());
            bedDTOList.add(new BedDTO(b, senior));
        }
        JSONObject result = new JSONObject();
        result.put("room", room);
        result.put("workername", worker.getName());
        result.put("workertel",worker.getTel());
        result.put("bedList", bedDTOList);
        return result;
    }


    @Override
    public List<RoomDTO> listAllByInstId(Integer instId, Integer flag) {
        RoomExample example = new RoomExample();
        example.createCriteria().andInstidEqualTo(instId);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : roomMapper.selectByExample(example)) {
            BuildingExample buildingExample = new BuildingExample();
            buildingExample.createCriteria().andInstidEqualTo(instId).andNameEqualTo(r.getBuildingname());

            BedExample bedexample = new BedExample();
            bedexample.createCriteria().andRoomidEqualTo(r.getId());
            JSONArray bedStates = new JSONArray();
            for (Bed b : bedMapper.selectByExample(bedexample)) {
                JSONObject bed = new JSONObject();
                bed.put("id",b.getId());
                bed.put("num",b.getNum());
                bed.put("state",b.getSeniorid() == null ? 0 : 1);
                bedStates.add(bed);
            }
            if(flag == 0){
                int num = 0;
                for(Bed b:bedMapper.selectByExample(bedexample)){
                    num = num + (b.getSeniorid() == null? 0:1);
                }

                if(num < r.getSize()){
                    roomDTOS.add(new RoomDTO(buildingMapper.selectByExample(buildingExample).get(0),r, bedStates, workerMapper.selectByPrimaryKey(r.getWokerid())));
                }
            }

            else{
List<Building> buildingList=buildingMapper.selectByExample(buildingExample);
if(buildingList.size()>0){

    roomDTOS.add(new RoomDTO(buildingList.get(0),r, bedStates, workerMapper.selectByPrimaryKey(r.getWokerid())));
}
            }

        }
        return roomDTOS;
    }

    @Override
    public JSONArray listAllInCommunityByInstId(Integer instId) {
        JSONArray result = new JSONArray();
        List<Institution> communities = communityService.listAllByInstid(instId);
        for (Institution community : communities
        ) {
            JSONObject c = JSONObject.fromObject(community);
//            List<RoomDTO> rooms = listAllByInstId(community.getId(),1);//每个社区的房间   社区没有楼栋
            RoomExample example = new RoomExample();
            example.createCriteria().andInstidEqualTo(community.getId());
//            List<RoomDTO> roomDTOS = new ArrayList<>();
            JSONArray jsonArray = new JSONArray();
            List<Room> rooms = roomMapper.selectByExample(example);
            for (Room r:rooms){
                JSONObject detail = new JSONObject();
                detail.put("buildingname",r.getBuildingname());
                detail.put("id",r.getId());
                detail.put("num",r.getNum());
                detail.put("size",r.getSize());
                detail.put("workerid",r.getWokerid());
                detail.put("workername",workerMapper.selectByPrimaryKey(r.getWokerid()).getName());
                detail.put("workertel",workerMapper.selectByPrimaryKey(r.getWokerid()).getTel());

                jsonArray.add(detail);
            }
            c.put("instid", community.getId());
            c.put("nums", rooms.size());
            c.put("room_list", jsonArray);
            result.add(c);
        }
        return result;
    }



    @Override
    public int addRoom(Admin admin, RoomParam roomParam) {
        Room room = new Room();
        BeanUtils.copyProperties(roomParam, room);
        room.setInstname(carehomeMapper.selectByPrimaryKey(admin.getInstid()).getName());
        room.setBuildingname(buildingMapper.selectByPrimaryKey(roomParam.getBuildingid()).getName());
        int count = roomMapper.insert(room);
        if (count == 0)
            return 0;

        //根据房间的容量添加相应数量的床位
        for (int i = 0; i < roomParam.getSize(); i++) {
            Bed bed = new Bed();
            bed.setNum(i+1);
            String location = carehomeMapper.selectByPrimaryKey(admin.getInstid()).getName() + " " + room.getBuildingname() + " " + roomParam.getNum() + " " + (i+1);
            bed.setLocation(location);
            bed.setRoomid(room.getId());
            bed.setRoomnum(room.getNum());
            bedMapper.insert(bed);
        }
        return 1;
    }


    @Override
    public int update(Integer id, RoomParam roomParam) {
        Room room = roomMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(roomParam, room);
        return roomMapper.updateByPrimaryKeySelective(room);
    }


    @Override
    public int delete(Integer id) {
        int count = roomMapper.deleteByPrimaryKey(id);
        return count;
    }
}
