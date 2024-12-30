package com.boat.pension.service.impl;

import com.boat.pension.dto.CommunityParam;
import com.boat.pension.dto.RoomDTO;
import com.boat.pension.dto.WorkerParam;
import com.boat.pension.mapper.AdminMapper;
import com.boat.pension.mapper.InstitutionMapper;
import com.boat.pension.mapper.RoomMapper;
import com.boat.pension.mapper.WorkerMapper;
import com.boat.pension.model.*;
import com.boat.pension.service.RecordService;
import com.boat.pension.service.RoomService;
import com.boat.pension.service.WorkerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired(required = false)
    private WorkerMapper workerMapper;

    @Autowired(required = false)
    private RoomMapper roomMapper;

    @Autowired
    private InstitutionMapper institutionMapper;
    @Autowired
    RoomService roomService;
    @Override
    public List<Worker> listAllByInstid(Integer instId) {
        WorkerExample example = new WorkerExample();
        example.createCriteria().andInstidEqualTo(instId);
        return workerMapper.selectByExample(example);
    }

    @Override
    public Worker getWorkerByWorkerId(Integer workerId) {
        return workerMapper.selectByPrimaryKey(workerId);
    }

    @Override
    public JSONArray listRoomByWorkerId(Integer workerid, Integer instId) {
        // 1. 查到护工绑定的所有房间
        RoomExample example = new RoomExample();
        example.createCriteria().andWokeridEqualTo(workerid);
        List<Room> roomList = roomMapper.selectByExample(example);
        JSONArray result = new JSONArray();
        if (roomList.size() == 0) {
            return result;
        }
        // 2. 将房间按所属机构分成不同组
        JSONArray roomsInCommunities = new JSONArray();
        Map<Integer, List<Room>> roomsByInstitution = roomList.stream().collect(Collectors.groupingBy(Room::getInstid));
        Iterator<Map.Entry<Integer, List<Room>>> rbiIterator = roomsByInstitution.entrySet().iterator();
        // 3. 遍历所有机构，根据机构id获取机构基本信息；
        // 同时根据机构id是否为养老院本身区分院内院外：若为院内，则将其放在result的第一个属性中；否则放在第二个属性里
        while (rbiIterator.hasNext()) {
            Map.Entry<Integer, List<Room>> entry = rbiIterator.next();
            Institution institution = institutionMapper.selectByPrimaryKey(entry.getKey());
            JSONObject insObj = JSONObject.fromObject(institution);
            JSONArray current_rooms = JSONArray.fromObject(entry.getValue());//将List<Room>转化成JSONArray格式
            JSONArray temp_rooms=new JSONArray();
            for (Object obj : current_rooms) {
                JSONObject room = JSONObject.fromObject(obj); //获取房间列表中的item
                room.put("bedState", roomService.getRoomBedState((Integer) room.get("id")));//给item新增房间床位状态属性
                temp_rooms.add(room);
            }
            insObj.put("room_list", temp_rooms);
            if (entry.getKey() == instId) {
                result.add(insObj);
            } else {
                roomsInCommunities.add(insObj);
            }
        }
        result.add(roomsInCommunities);
        return result;
    }
    //    未测试
    @Override
    public int addWorker(Integer instId, WorkerParam workerParam) {
        Worker worker = new Worker();
        BeanUtils.copyProperties(workerParam, worker);
        worker.setInstid(instId);
        return workerMapper.insert(worker);
    }

    //    未测试
    @Override
    public int update(Integer id, WorkerParam workerParam) {
        Worker worker = workerMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(workerParam, worker);
//        worker.setInstid(id);
        return workerMapper.updateByPrimaryKeySelective(worker);
    }

    //    未测试
    @Override
    public int delete(Integer id) {
        int count = workerMapper.deleteByPrimaryKey(id);
        return count;
    }

    //    未测试
    @Override
    public int addRoom(Integer workerid, Integer roomid) {
        Room room = roomMapper.selectByPrimaryKey(roomid);
        if (room.getWokerid() != null)
            return 0;//表示该房间已有护工
        room.setWokerid(workerid);
        return roomMapper.updateByPrimaryKeySelective(room);
    }


    @Override
    public int delRoom(Integer workerid, Integer roomid) {
        Room room = roomMapper.selectByPrimaryKey(roomid);
        if (room.getWokerid() != workerid)
            return 0;//表示关系不成立
        room.setWokerid(null);
        return roomMapper.updateByPrimaryKey(room);//不能用selective
    }
}
