package com.boat.pension.service.impl;

import com.boat.pension.dto.BuildingParam;
import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.mapper.BuildingMapper;
import com.boat.pension.mapper.RoomMapper;
import com.boat.pension.model.*;
import com.boat.pension.service.BuildingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired(required = false)
    private BuildingMapper buildingMapper;

    @Autowired(required = false)
    private RoomMapper roomMapper;

    @Override
    public List<Building> listAllBuilding(int instID) {
        BuildingExample example=new BuildingExample();
        example.createCriteria().andInstidEqualTo(instID);
        return buildingMapper.selectByExample(example);
    }

    @Override
    public int addBuilding(Integer instId, BuildingParam buildingParam) {
        BuildingExample buildingExample = new BuildingExample();
        buildingExample.createCriteria().andInstidEqualTo(instId);
        List<Building> buildings = buildingMapper.selectByExample(buildingExample);
        for (Building b: buildings) {
            if(b.getName() == buildingParam.getName())
                return 0;
        }
        Building building = new Building();
        BeanUtils.copyProperties(buildingParam, building);
        building.setInstid(instId);
        return buildingMapper.insert(building);
    }

    @Override
    public int delete(Integer id) {
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andInstidEqualTo(buildingMapper.selectByPrimaryKey(id).getInstid()).andBuildingnameEqualTo(buildingMapper.selectByPrimaryKey(id).getName());
        List<Room> rooms = roomMapper.selectByExample(roomExample);
        if(rooms.size() != 0){//当本楼栋还有房间时，删除失败
            return 0;
        }
        int count = buildingMapper.deleteByPrimaryKey(id);
        return count;
    }
}
