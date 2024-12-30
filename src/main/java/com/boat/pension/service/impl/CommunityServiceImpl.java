package com.boat.pension.service.impl;

import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.dto.CommunityParam;
import com.boat.pension.mapper.BedMapper;
import com.boat.pension.mapper.EventMapper;
import com.boat.pension.mapper.InstitutionMapper;
import com.boat.pension.mapper.RoomMapper;
import com.boat.pension.model.*;
import com.boat.pension.service.CommunityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired(required = false)
    private InstitutionMapper communityMapper;

    @Autowired(required = false)
    private RoomMapper roomMapper;

    @Autowired(required = false)
    private BedMapper bedMapper;

    @Autowired(required = false)
    private EventMapper eventMapper;


    @Override
    public List<Institution> listAllByInstid(Integer instId) {
        InstitutionExample example = new InstitutionExample();
        example.createCriteria().andAffEqualTo(instId);
        return communityMapper.selectByExample(example);
    }


    @Override
    public int addCommunity(Integer instId, CommunityParam communityParam) {
        Institution community = new Institution();
        BeanUtils.copyProperties(communityParam, community);
        community.setAff(instId);
        return communityMapper.insert(community);
    }

    //    未测试
    @Override
    public int update(Integer id,CommunityParam communityParam){
        Institution community = communityMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(communityParam, community);

//        更新养老院时是否会影响其他实体  会影响房间!
        Room room = new Room();
        room.setInstname(community.getName());
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
                b.setLocation(community.getName() + " " + r.getBuildingname() + " " + r.getNum() + " " + b.getNum());
                bedMapper.updateByPrimaryKeySelective(b);
            }
        }

        //会影响event的location
        EventExample eventExample = new EventExample();
        eventExample.createCriteria().andInstidEqualTo(id);
        List<Event> events = eventMapper.selectByExample(eventExample);
        for (Event e:  events) {
            e.setLocation(community.getName() + " " + e.getLocation().split("\\s+")[1] + " " + e.getLocation().split("\\s+")[2] + " " + e.getLocation().split("\\s+")[3]);
            eventMapper.updateByPrimaryKeySelective(e);
        }

        //TODO:影响日志

        return communityMapper.updateByPrimaryKeySelective(community);
    }

    @Override
    public int delete(Integer id) {
        int count = communityMapper.deleteByPrimaryKey(id);
        return count;
    }
}
