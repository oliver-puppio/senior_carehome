package com.boat.pension.service.impl;

import com.boat.pension.dto.EventDTO;
import com.boat.pension.dto.EventParam;
import com.boat.pension.mapper.*;
import com.boat.pension.model.*;
import com.boat.pension.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired(required = false)
    private EventMapper eventMapper;

    @Autowired(required = false)
    WorkerMapper workerMapper;
    @Autowired(required = false)
    AdminMapper adminMapper;

    @Autowired(required = false)
    SeniorMapper seniorMapper;
    @Autowired(required = false)
    BedMapper bedMapper;

    @Autowired(required = false)
    RoomMapper roomMapper;

    @Override
    public int addEvent(Integer instId, EventParam eventParam) {
        //若异常事件表中存在未处理完且设备id等于传入参数的事件，则该待创建事件应该被丢弃
        EventExample eventExample=new EventExample();
        eventExample.createCriteria().andStateNotEqualTo(2).andDeviceidEqualTo(eventParam.getDeviceid());
        EventExample.Criteria c2=eventExample.createCriteria();
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MINUTE,-1);
        Date time= cal.getTime();
        c2.andDeviceidEqualTo(eventParam.getDeviceid()).andStateEqualTo(2).andEndtimeGreaterThan(time);
        eventExample.or(c2);
        List<Event> eventList=eventMapper.selectByExample(eventExample);
        if(eventList.size()>0){
            return 0;
        }

        Event event = new Event();
        BeanUtils.copyProperties(eventParam, event);
        event.setStarttime(new Date());
        event.setName(seniorMapper.selectByPrimaryKey(event.getSeniorid()).getName());
        event.setState(0);
        event.setInstid(instId);

        BedExample example = new BedExample();
        example.createCriteria().andSenioridEqualTo(event.getSeniorid());
        event.setLocation(bedMapper.selectByExample(example).get(0).getLocation());
        //todo:如果我们设计了消息机制，那么得考虑给护工发信息了。
        return eventMapper.insert(event);
    }

    @Override
    public List<EventDTO> listAllByInstid(Integer instId) {
        EventExample eventExample = new EventExample();
        eventExample.setOrderByClause("startTime desc");
        eventExample.createCriteria().andInstidEqualTo(instId);

        List<Event> events = eventMapper.selectByExample(eventExample);
        List<EventDTO> eventDTOS = new ArrayList<>();
        for (Event e : events) {
            //老人->床位->房间->护工
            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andSenioridEqualTo(e.getSeniorid());
            Bed bed = bedMapper.selectByExample(bedExample).get(0);
            Worker worker = workerMapper.selectByPrimaryKey(roomMapper.selectByPrimaryKey(bed.getRoomid()).getWokerid());

            eventDTOS.add(new EventDTO(e,worker));
        }

        return eventDTOS;
    }

    @Override
    public List<EventDTO> listFinishedByInstid(Integer instId) {
        EventExample eventExample = new EventExample();
        eventExample.setOrderByClause("startTime desc");
        eventExample.createCriteria().andInstidEqualTo(instId).andStateEqualTo(2);
        List<Event> events = eventMapper.selectByExample(eventExample);

        List<EventDTO> eventDTOS = new ArrayList<>();
        for (Event e : events) {
            //老人->床位->房间->护工
            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andSenioridEqualTo(e.getSeniorid());
            Bed bed = bedMapper.selectByExample(bedExample).get(0);
            Worker worker = workerMapper.selectByPrimaryKey(roomMapper.selectByPrimaryKey(bed.getRoomid()).getWokerid());

            eventDTOS.add(new EventDTO(e,worker));
        }

        return eventDTOS;
    }

    @Override
    public List<EventDTO> listUnfinishedByInstid(Integer instId) {
        EventExample eventExample = new EventExample();
        eventExample.setOrderByClause("startTime desc");
        eventExample.createCriteria().andInstidEqualTo(instId).andStateNotEqualTo(2);

        List<Event> events = eventMapper.selectByExample(eventExample);
        List<EventDTO> eventDTOS = new ArrayList<>();
        for (Event e : events) {
            //老人->床位->房间->护工
            BedExample bedExample = new BedExample();
            bedExample.createCriteria().andSenioridEqualTo(e.getSeniorid());
            Bed bed = bedMapper.selectByExample(bedExample).get(0);
            Worker worker = workerMapper.selectByPrimaryKey(roomMapper.selectByPrimaryKey(bed.getRoomid()).getWokerid());

            eventDTOS.add(new EventDTO(e,worker));
        }

        return eventDTOS;
    }

    @Override
    public int confirm(Integer eventid, Integer adminid){
        Event event = eventMapper.selectByPrimaryKey(eventid);
        event.setEntertime(new Date());//异常确认时间
        String handler;//获得处理人姓名
        Admin worker = adminMapper.selectByPrimaryKey(adminid);
        if (worker==null){
            Worker worker1=workerMapper.selectByPrimaryKey(adminid);
            if (worker1==null)
                handler="未知";
            else
                handler="护工："+worker1.getName();
        }   else
            handler = "管理员";
        event.setHandler(handler);
        return eventMapper.updateByPrimaryKeySelective(event);
    }

    @Override
    public int finish(Integer eventid, String resultText){
        Event event = eventMapper.selectByPrimaryKey(eventid);
        event.setState(1);
        Date endTime = new Date();//异常结束时间
        event.setEndtime(endTime);
        event.setResulttext(resultText);
        return eventMapper.updateByPrimaryKeySelective(event);
    }
}
