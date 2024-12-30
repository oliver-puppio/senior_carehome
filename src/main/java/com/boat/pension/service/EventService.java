package com.boat.pension.service;

import com.boat.pension.dto.EventDTO;
import com.boat.pension.dto.EventParam;
import com.boat.pension.dto.RoomParam;
import com.boat.pension.model.Event;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EventService {
    /**
     * 新增异常事件
     * @param eventParam 异常事件参数
     * @return 影响记录数
     */
    int addEvent(Integer instId, EventParam eventParam);
    /**
     * 根据机构id获取异常事件列表
     * @param id 机构参数
     * @return 异常事件列表
     */
    List<EventDTO> listAllByInstid(Integer id);
    /**
     * 根据机构id获取已处理的异常事件列表
     * @param id 机构参数
     * @return 异常事件列表
     */
    List<EventDTO> listFinishedByInstid(Integer id);
    /**
     * 根据机构id获取未处理的异常事件列表
     * @param id 机构参数
     * @return 异常事件列表
     */
    List<EventDTO> listUnfinishedByInstid(Integer id);
    /**
     * 护工确认接收异常事件
     * @param eventid,adminid 异常事件id，护工id
     * @return 影响记录数
     */
    int confirm(Integer eventid, Integer workerid);
    /**
     * 护工提交异常事件处理结果
     * @param eventid,resultText 异常事件id，处理结果
     * @return 影响记录数
     */
    int finish(Integer eventid, String resultText);
}
