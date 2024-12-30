package com.boat.pension.service.impl;

import com.boat.pension.dto.EventParam;
import com.boat.pension.dto.HealthRepo;
import com.boat.pension.dto.RealTimeRecordDTO;
import com.boat.pension.mapper.BedMapper;
import com.boat.pension.mapper.DeviceMapper;
import com.boat.pension.mapper.RecordMapper;
import com.boat.pension.mapper.SeniorMapper;
import com.boat.pension.model.*;
import com.boat.pension.service.EventService;
import com.boat.pension.service.RecordService;
import com.boat.pension.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordMapper recordMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    SeniorMapper seniorMapper;

    @Autowired
    BedMapper bedMapper;

    @Autowired
    EventService eventService;

    @Override
    public List<RealTimeRecordDTO> realtimeRecord(Integer instID) {
        try {
            DeviceExample deviceExample = new DeviceExample();
            deviceExample.createCriteria().andInstidEqualTo(instID);
//            List<Integer> deviceID = new ArrayList<>();
            List<Device> devices = deviceMapper.selectByExample(deviceExample);
            List<RealTimeRecordDTO> realTimeRecordDTOS = new ArrayList<>();
            for (Device d: devices) {
                RecordExample example = new RecordExample();
                example.setOrderByClause("realtime desc");
                example.createCriteria().andDeviceidEqualTo(d.getDeviceid());
                if(recordMapper.selectByExample(example).size() > 0){

                    List<Record> list = recordMapper.selectByExample(example);
                    if(list.size()>0){
                    Record record = list.get(0);
                    BedExample bedExample = new BedExample();
                    bedExample.createCriteria().andDeviceidEqualTo(d.getDeviceid());
                    List<Bed> bedList = bedMapper.selectByExample(bedExample);
                    if(bedList.size()>0){
                        Bed bed = bedList.get(0);
                        realTimeRecordDTOS.add(new RealTimeRecordDTO(record,bed.getSeniorid()));}
                    }



                }

            }

            return realTimeRecordDTOS;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HealthRepo> realtimeHealthCheck(Integer instId) {
        // 1. 根据机构id获取所有绑定老人和设备的床位（获取所有设备）
        // 2. 对于每一个床位，联表查询查到老人和设备实体（根据设备查找对应的床位和老人）
        // 3. 根据设备实体，查询record表，按照时间顺序获取前10条数据，若数据量小于十条则跳过
        // 4. 计算出这10条记录的心率、呼吸均值，并根据前面关联的实体，生成健康监测报告对象
        // 5. 根据HealthRepo中的flag属性，判断老人是否出现异常，若是，则调用Event服务生成异常报告。

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andInstidEqualTo(instId).andStateEqualTo(1);
        List<Device> deviceList = deviceMapper.selectByExample(deviceExample);
        List<HealthRepo> realtimeRepos = new ArrayList<>();
        for (Device d : deviceList) {
            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andSenioridIsNotNull().andDeviceidEqualTo(d.getDeviceid());
            List<Bed> bedList = bedMapper.selectByExample(bedExample);
            if (bedList.size() > 0) {
                Bed bed = bedList.get(0);
                Senior senior = seniorMapper.selectByPrimaryKey(bed.getSeniorid());
                Calendar cal=Calendar.getInstance();
                cal.add(Calendar.MINUTE,-1);
                Date time= cal.getTime();
                RecordExample example = new RecordExample();
                example.setOrderByClause("realtime desc");
                example.createCriteria().andDeviceidEqualTo(d.getDeviceid()).andRealtimeGreaterThan(time);
                List<Record> recordList = recordMapper.selectByExample(example);
                if (recordList.size() > 0) {
                    List<Record> counting_list;
                    if (recordList.size() >= 10)
                        counting_list = recordList.subList(0, 10);
                    else
                        counting_list = recordList;
                    Integer h = 0, b = 0;
                    for (Record record : counting_list) {
                        h += record.getHeart();
                        b += record.getBreath();
                    }
                    h = h / counting_list.size();
                    b = b / counting_list.size();
                    HealthRepo repo = new HealthRepo(bed, senior, h, b);
                    realtimeRepos.add(repo);
                    if (repo.getFlag() > 0) {
                        EventParam eventParam = new EventParam();
                        eventParam.setDeviceid(d.getDeviceid());
                        eventParam.setSeniorid(senior.getId());
                        eventParam.setType(repo.getFlag());
                        eventService.addEvent(d.getInstid(), eventParam);
                    }
                }
            }
        }
        return realtimeRepos;
    }


    @Override
    public List<Record> create(){
        List<Record> records = new ArrayList<>();
        Date start = TimeUtil.string2Date("2022-07-20 21:00:00");
//        long timestamp = start.getTime();
        List<Integer> devices = new ArrayList<>();
//        DeviceExample deviceExample = new DeviceExample();
//        deviceExample.createCriteria().andInstidEqualTo(instId);
//        for (Device d: deviceMapper.selectByExample(deviceExample)) {
//            devices.add(d.getDeviceid());
//        }
        devices.add(4);
        devices.add(5);
        for (Integer id: devices) {
            int count = 6*60*8;
            for (int i = 0; i < count; i++) {
                int heart = (int)(Math.random()*100 + 0);
                int breath = (int)(Math.random()*100 + 0);
                Record r = new Record();
                r.setBreath(breath);
                r.setHeart(heart);
                r.setDeviceid(id);
                r.setRealtime(start);
                records.add(r);
            }
        }
        return records;

    }
}
