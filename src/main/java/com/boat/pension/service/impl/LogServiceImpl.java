package com.boat.pension.service.impl;

import com.boat.pension.mapper.*;
import com.boat.pension.model.*;
import com.boat.pension.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired(required = false)
    MonitorlogMapper monitorlogMapper;
    
    @Autowired(required = false)
    RoomMapper roomMapper;
    
    @Autowired(required = false)
    BedMapper bedMapper;

    @Autowired(required = false)
    RecordMapper recordMapper;

    public int create(Integer instId){
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andInstidEqualTo(instId);
        List<Bed> beds = new ArrayList<>();
        for (Room r: roomMapper.selectByExample(roomExample)) {
            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andRoomidEqualTo(r.getId());
            for (Bed b: bedMapper.selectByExample(bedExample)) {
                beds.add(b);
            }
        }

        for (Bed b: beds) {
            RecordExample recordExample = new RecordExample();
            recordExample.createCriteria().andDeviceidEqualTo(b.getDeviceid());
            recordExample.setOrderByClause("realtime asc");
            List<Record> recordList = recordMapper.selectByExample(recordExample);

            if(recordList.size() > 0){
                List<Integer> heartArr = new ArrayList<>();
                List<Integer> breathArr = new ArrayList<>();
                int flag = 0;
                int heart = 0;
                int breath = 0;
                int rate = 90;//压缩比
                for (Record j: recordList) {
                    heart += j.getHeart();
                    breath += j.getBreath();
                    flag += 1;

                    if(flag == rate){
                        heartArr.add(heart/rate);
                        breathArr.add(breath/rate);
                        heart = breath = flag = 0;
                    }
                }

                Monitorlog log = new Monitorlog();
                log.setLogdate(new Date());
                log.setHeartarr(heartArr.toString());
                log.setBreatharr(breathArr.toString());
                log.setSeniorid(b.getSeniorid());
                log.setDeviceid(b.getDeviceid());
                log.setLocation(b.getLocation());
                log.setStarttime(recordList.get(0).getRealtime());
                log.setEndtime(recordList.get(recordList.size()-1).getRealtime());
                monitorlogMapper.insert(log);
            }
        }
        return 1;
    }
}
