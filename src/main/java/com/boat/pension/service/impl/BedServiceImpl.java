package com.boat.pension.service.impl;

//import com.boat.pension.dto.BedParam;
import com.boat.pension.dto.BedDeviceDTO;
import com.boat.pension.mapper.BedMapper;
import com.boat.pension.mapper.DeviceMapper;
import com.boat.pension.mapper.InstitutionMapper;
import com.boat.pension.mapper.RoomMapper;
import com.boat.pension.model.*;
import com.boat.pension.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BedServiceImpl implements BedService {
    @Autowired(required = false)
    private BedMapper bedMapper;

    @Autowired(required = false)
    private DeviceMapper deviceMapper;

    @Autowired(required = false)
    private RoomMapper roomMapper;

    @Autowired(required = false)
    private InstitutionMapper institutionMapper;


    @Override
    public int delete(Integer id) {
        if(bedMapper.selectByPrimaryKey(id).getSeniorid() != null){
            return 0;
        }
        int count = bedMapper.deleteByPrimaryKey(id);
        return count;
    }


    @Override
    public int addDevice(BedDeviceDTO bedDeviceDTO){
        Bed bed = getBedByBedId(bedDeviceDTO.getBedid());

        //会影响设备状态!
        Device device = deviceMapper.selectByPrimaryKey(bedDeviceDTO.getDeviceid());
        //先判断该设备是否已经绑定
        if(device.getState() == 1 || bed.getDeviceid() != null)
            return 0;
        device.setState(1);//已绑定

        bed.setDeviceid(bedDeviceDTO.getDeviceid());
        deviceMapper.updateByPrimaryKeySelective(device);
        return bedMapper.updateByPrimaryKeySelective(bed);
    }

    //    未测试
    @Override
    public int deleteDevice(BedDeviceDTO bedDeviceDTO){
        if(bedDeviceDTO.getDeviceid() == null && bedDeviceDTO.getBedid() != null){//床位页面解绑
            Bed bed = getBedByBedId(bedDeviceDTO.getBedid());
            //会影响设备状态!
            Device device = deviceMapper.selectByPrimaryKey(bed.getDeviceid());
            device.setState(0);//解绑
            bed.setDeviceid(null);
            deviceMapper.updateByPrimaryKey(device);
            return bedMapper.updateByPrimaryKey(bed);//不可以使用selective
        }
        else if(bedDeviceDTO.getDeviceid() != null && bedDeviceDTO.getBedid() == null){//设备页面解绑
            Device device = deviceMapper.selectByPrimaryKey(bedDeviceDTO.getDeviceid());
            device.setState(0);//解绑

            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andDeviceidEqualTo(bedDeviceDTO.getDeviceid());
            Bed bed = bedMapper.selectByExample(bedExample).get(0);

            bed.setDeviceid(null);
            bedMapper.updateByPrimaryKey(bed);
            deviceMapper.updateByPrimaryKey(device);
            return deviceMapper.updateByPrimaryKey(device);//不可以使用selective
        }
        else
            return 0;
    }

    public Bed getBedByBedId(Integer bedId) {
        return bedMapper.selectByPrimaryKey(bedId);
    }

    @Override
    public List<Bed> listNoBindByInstid(Integer id){
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andInstidEqualTo(id);
        List<Room> rooms = roomMapper.selectByExample(roomExample);
        List<Bed> beds = new ArrayList<>();
        for (Room r: rooms) {
            BedExample example = new BedExample();
            example.createCriteria().andRoomidEqualTo(r.getId());
            for (Bed b: bedMapper.selectByExample(example)) {
                if(b.getDeviceid() == null){
                    beds.add(b);
                }
            }

        }
        return beds;
    }

    @Override
    public List<Bed> listNoSeniorByInstid(Integer id){
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andInstidEqualTo(id);
        List<Room> rooms = roomMapper.selectByExample(roomExample);
        List<Bed> beds = new ArrayList<>();
        for (Room r: rooms) {
            BedExample example = new BedExample();
            example.createCriteria().andRoomidEqualTo(r.getId());
            for (Bed b: bedMapper.selectByExample(example)) {
                if(b.getSeniorid() == null){
                    beds.add(b);
                }
            }

        }
        return beds;
    }
}
