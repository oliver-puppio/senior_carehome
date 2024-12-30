package com.boat.pension.service.impl;

import com.boat.pension.controller.TestController;
import com.boat.pension.dto.DeviceDTO;
import com.boat.pension.dto.RealTimeRecordDTO;
import com.boat.pension.mapper.BedMapper;
import com.boat.pension.mapper.DeviceMapper;
import com.boat.pension.mapper.RecordMapper;
import com.boat.pension.model.*;
import com.boat.pension.service.DeviceService;
import com.boat.pension.util.MyTimerTask;
import com.boat.pension.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired(required = false)
    private DeviceMapper deviceMapper;

    @Autowired(required = false)
    private BedMapper bedMapper;

    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Override
    public List<DeviceDTO> listAllByInstid(Integer instId) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andInstidEqualTo(instId).andStateNotEqualTo(2);//未禁用的
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        if (devices.size() > 0) {
            for (Device d : devices) {
                BedExample bedExample = new BedExample();
                bedExample.createCriteria().andDeviceidEqualTo(d.getDeviceid());
                List<Bed> bedList = bedMapper.selectByExample(bedExample);
                if (bedList.size() > 0) {
                    deviceDTOS.add(new DeviceDTO(d, bedList.get(0).getLocation()));
                } else {
                    deviceDTOS.add(new DeviceDTO(d, "无"));
                }
            }
        }
        return deviceDTOS;
    }

    @Override
    public List<Device> listAllByInstidAndState(int instId, int state) {
        DeviceExample example = new DeviceExample();
        example.createCriteria().andInstidEqualTo(instId).andStateEqualTo(state);
        return deviceMapper.selectByExample(example);
    }

    @Override
    public int addDevice(Integer instid, Integer id) {
        Device device = new Device();
        device.setInstid(instid);
        device.setDeviceid(id);
        device.setState(0);
        device.setRegistertime(new Date());
        return deviceMapper.insert(device);
    }

    public int updateState(Integer id, int state) {
        Device device = deviceMapper.selectByPrimaryKey(id);
        if (device.getState() == 1) {//将绑定设备禁用
            BedExample example = new BedExample();
            example.createCriteria().andDeviceidEqualTo(device.getDeviceid());
            List<Bed> bedList = bedMapper.selectByExample(example);
            if (bedList.size() > 0) {//如果设备绑定了床位，则需要将对应床位解绑
                Bed bed = bedList.get(0);
                bed.setDeviceid(null);
                System.out.println(bed);
                bedMapper.updateByPrimaryKey(bed);
            }
        }
        device.setState(state);
        System.out.println(device);
        return deviceMapper.updateByPrimaryKeySelective(device);
    }

    @Override
    public RealTimeRecordDTO realtimeRecord(int deviceID) {
        try {
            RecordExample example = new RecordExample();
            example.setOrderByClause("realtime desc");
            example.createCriteria().andDeviceidEqualTo(deviceID);
            Record record = recordMapper.selectByExample(example).get(0);

            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andDeviceidEqualTo(deviceID);
            Bed bed = bedMapper.selectByExample(bedExample).get(0);

            return new RealTimeRecordDTO(record, bed.getSeniorid());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public int monitor(Admin admin){
//        TestController testController = new TestController();
//        Record r = testController.create(admin);
//        return
//    }
}
