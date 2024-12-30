package com.boat.pensionwx.service.impl;

import com.boat.pension.mapper.BedMapper;
import com.boat.pension.mapper.EventMapper;
import com.boat.pension.mapper.MonitorlogMapper;
import com.boat.pension.mapper.SeniorMapper;
import com.boat.pension.model.*;
import com.boat.pensionwx.dto.EventDTO;
import com.boat.pensionwx.dto.LogDTO;
import com.boat.pensionwx.dto.SeniorHealthDetailDTO;
import com.boat.pensionwx.service.SeniorService;
import com.boat.pensionwx.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeniorServiceImpl implements SeniorService {

    @Autowired
    SeniorMapper seniorMapper;
    @Autowired
    BedMapper bedMapper;
    @Autowired
    EventMapper eventMapper;
    @Autowired
    MonitorlogMapper monitorlogMapper;

    @Override
    public SeniorHealthDetailDTO detail(int id) {
        Senior senior = seniorMapper.selectByPrimaryKey(id);
        BedExample example = new BedExample();
        example.createCriteria().andSenioridEqualTo(id);
        List<Bed> bedList = bedMapper.selectByExample(example);
        if (bedList.size() > 0) {
            Bed bed = bedMapper.selectByExample(example).get(0);
            return new SeniorHealthDetailDTO(senior, bed);
        } else {
            return null;
        }
    }

    @Override
    public LogDTO log(int id, String date) {
        Date logDate = TimeUtil.string2Date(date);
        MonitorlogExample example = new MonitorlogExample();
        example.createCriteria().andSenioridEqualTo(id).andLogdateLessThanOrEqualTo(logDate);
        example.setOrderByClause("logDate desc");
        List<Monitorlog> logs = monitorlogMapper.selectByExample(example);
        if (logs.size() > 0)
            return new LogDTO(logs.get(0), logs.size());
        else
            return null;
    }

    @Override
    public LogDTO log(int id) {
        MonitorlogExample example = new MonitorlogExample();
        example.setOrderByClause("logDate desc");
        example.createCriteria().andSenioridEqualTo(id);
        List<Monitorlog> logs = monitorlogMapper.selectByExample(example);
        if (logs.size() > 0)
            return new LogDTO(logs.get(0), logs.size());
        else
            return null;
    }

    @Override
    public List<EventDTO> event(int id) {
        EventExample example = new EventExample();
        example.setOrderByClause("startTime desc");
        example.createCriteria().andSenioridEqualTo(id);
        List<Event> events = eventMapper.selectByExample(example);
        Senior senior= seniorMapper.selectByPrimaryKey(id);
        String gender=senior.getGender();
        List<EventDTO> result = new ArrayList<>();
        if (events.size() == 0)
            return null;
        for (Event event : events
        ) {
            result.add(new EventDTO(event,gender));
        }
        return result;
    }
}
