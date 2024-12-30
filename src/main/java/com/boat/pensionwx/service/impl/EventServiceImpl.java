package com.boat.pensionwx.service.impl;

import com.boat.pension.mapper.*;
import com.boat.pension.model.*;
import com.boat.pensionwx.dto.EventDTO;
import com.boat.pensionwx.service.EventService;
import com.boat.pensionwx.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    WxUserService wxUserService;

    @Override
    public List<EventDTO> listAllByUser(Wechatuser user) {
        List<Integer> seniorIDList = wxUserService.seniorIDList(user);
        if (seniorIDList.size()==0){
            return null;
        }
        EventExample example = new EventExample();
        example.setOrderByClause("startTime DESC");
        if (user.getIsworker() == 0) {//如果用户不是护工
            example.createCriteria().andSenioridIn(seniorIDList).andStateEqualTo(2);//只显示已完成的异常事件
            System.out.println("The user isn't a worker");
        }else {
            example.createCriteria().andSenioridIn(seniorIDList);
        }
        List<Event> eventList = eventMapper.selectByExample(example);
        System.out.println(eventList);
        List<EventDTO> eventDTOList = new ArrayList<>();
        if (eventList.size() > 0) {
            for (Event e : eventList
            ) {
                String gender=seniorMapper.selectByPrimaryKey(e.getSeniorid()).getGender();
                eventDTOList.add(new EventDTO(e,gender));
            }
            return eventDTOList;
        } else {
            return null;
        }
    }

    @Override
    public List<EventDTO> listAllActiveByUser(Wechatuser user) {
        List<Integer> seniorIDList = wxUserService.seniorIDList(user);
        EventExample example = new EventExample();
        example.setOrderByClause("startTime DESC");
        if (user.getIsworker() == 0 || seniorIDList.size()==0) {//如果用户不是护工或者seniorIDList长度为0
            return null;
        }else {
            example.createCriteria().andSenioridIn(seniorIDList).andStateNotEqualTo(2);
        }
        List<Event> eventList = eventMapper.selectByExample(example);
        System.out.println(eventList);
        List<EventDTO> eventDTOList = new ArrayList<>();
        if (eventList.size() > 0) {
            for (Event e : eventList
            ) {
                String gender=seniorMapper.selectByPrimaryKey(e.getSeniorid()).getGender();
                eventDTOList.add(new EventDTO(e,gender));
            }
            return eventDTOList;
        } else {
            return null;
        }
    }

    @Override
    public int confirm(Integer eventid, Integer workerid) {
        Event event = eventMapper.selectByPrimaryKey(eventid);
        if (event == null) {
            System.out.println("Event doesn't exist");
            return 0;
        }
        event.setEntertime(new Date());//异常确认时间
        String handler;//获得处理人姓名
        Admin worker = adminMapper.selectByPrimaryKey(workerid);
        if (worker == null) {
            Worker worker1 = workerMapper.selectByPrimaryKey(workerid);
            if (worker1 == null)
                handler = "未知";
            else
                handler = "护工：" + worker1.getName();
        } else
            handler = worker.getCname();
        event.setHandler(handler);
        event.setState(1);
        System.out.println("Event entity:");
        System.out.println(event);
        return eventMapper.updateByPrimaryKeySelective(event);
    }

    @Override
    public int finish(Integer eventid, String resultText) {
        Event event = eventMapper.selectByPrimaryKey(eventid);
        if (event == null) {
            return 0;
        }
        event.setState(2);
        Date endTime = new Date();//异常结束时间
        event.setEndtime(endTime);
        event.setResulttext(resultText);
        System.out.println("Event entity:");
        System.out.println(event);
        return eventMapper.updateByPrimaryKeySelective(event);
    }
}