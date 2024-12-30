package com.boat.pensionwx.service.impl;

import com.boat.pension.mapper.BedMapper;
import com.boat.pension.mapper.RoomMapper;
import com.boat.pension.mapper.SeniorMapper;
import com.boat.pension.mapper.WorkerMapper;
import com.boat.pension.model.*;
import com.boat.pensionwx.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    BedMapper bedMapper;

    @Autowired
    SeniorMapper seniorMapper;

    @Autowired
    WorkerMapper workerMapper;

    @Override
    public List<Integer> selectSeniorsID(int id) {
        //获取床位上所有的老人ID
        List<Integer> seniorsID=new ArrayList<>();
        //获取护工负责的所有房间
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andWokeridEqualTo(id);
        List<Room> roomList = roomMapper.selectByExample(roomExample);
        if(roomList.size()>0){
            List<Integer> roomIDList = new ArrayList<>();
            roomList.forEach(
                    room -> {
                        roomIDList.add(room.getId());
                    }
            );

            //获取护工负责房间内的所有床位
            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andRoomidIn(roomIDList).andSenioridIsNotNull();//有老人、有房号
            List<Bed> bedList=bedMapper.selectByExample(bedExample);
            if(bedList.size()>0){
                for (Bed bed:bedList
                ) {
                    seniorsID.add(bed.getSeniorid());
                }
            }
        }
        return seniorsID;
    }

    @Override
    public int verifyByPhone(Wechatuser user) {
        String phone=user.getTel();
        WorkerExample example=new WorkerExample();
        example.createCriteria().andTelEqualTo(phone);
        List<Worker> workers=workerMapper.selectByExample(example);
        if(workers.size()==0)
            return -1;
        else
            return workers.get(0).getId();
    }
}
