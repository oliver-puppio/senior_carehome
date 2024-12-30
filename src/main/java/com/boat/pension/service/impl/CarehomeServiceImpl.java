package com.boat.pension.service.impl;

import cn.hutool.core.util.StrUtil;
import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.mapper.*;
import com.boat.pension.model.*;
import com.boat.pension.service.CarehomeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarehomeServiceImpl implements CarehomeService {
    @Autowired(required = false)
    private InstitutionMapper carehomeMapper;

    @Autowired(required = false)
    private RoomMapper roomMapper;

    @Autowired(required = false)
    private BedMapper bedMapper;

    @Autowired(required = false)
    private EventMapper eventMapper;

    @Override
    public Institution getCarehomeByCarehomeId(Integer carehomeId) {
        return carehomeMapper.selectByPrimaryKey(carehomeId);
    }

    @Override
    public int addCarehome(CarehomeParam carehomeParam) {
        Institution carehome = new Institution();
        BeanUtils.copyProperties(carehomeParam, carehome);
        return carehomeMapper.insert(carehome);
    }


    @Override
    public int update(Integer id,CarehomeParam carehomeParam){
        Institution carehome = carehomeMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(carehomeParam, carehome);

//        更新养老院时是否会影响其他实体  会影响房间!
        Room room = new Room();
        room.setInstname(carehome.getName());
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andInstidEqualTo(id);
        roomMapper.updateByExampleSelective(room,roomExample);

        //会影响床位的location
        List<Room> rooms = roomMapper.selectByExample(roomExample);
        for (Room r : rooms) {
            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andRoomidEqualTo(r.getId());
            List<Bed> addBed = bedMapper.selectByExample(bedExample);
            for (Bed b: addBed) {
                b.setLocation(carehome.getName() + " " + r.getBuildingname() + " " + r.getNum() + " " + b.getNum());
                bedMapper.updateByPrimaryKeySelective(b);
            }
        }

        //会影响event的location
        EventExample eventExample = new EventExample();
        eventExample.createCriteria().andInstidEqualTo(id);
        List<Event> events = eventMapper.selectByExample(eventExample);
        for (Event e:  events) {
            e.setLocation(carehome.getName() + " " + e.getLocation().split("\\s+")[1] + " " + e.getLocation().split("\\s+")[2] + " " + e.getLocation().split("\\s+")[3]);
            eventMapper.updateByPrimaryKeySelective(e);
        }

        return carehomeMapper.updateByPrimaryKeySelective(carehome);
    }

    @Override
    public int delete(Integer id) {
        int count = carehomeMapper.deleteByPrimaryKey(id);
        return count;
    }

}
