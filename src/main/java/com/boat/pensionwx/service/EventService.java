package com.boat.pensionwx.service;

import com.boat.pension.model.Wechatuser;
import com.boat.pensionwx.dto.EventDTO;

import java.util.List;

public interface EventService {
    /**
     * 根据机构id获取异常事件列表
     * @param user 机构参数
     * @return 异常事件列表
     */
    List<EventDTO> listAllByUser(Wechatuser user);

    /**
     * 根据机构id获取异常事件列表
     * @param user 机构参数
     * @return 异常事件列表
     */
    List<EventDTO> listAllActiveByUser(Wechatuser user);
    /**
     * 护工确认接收异常事件
     * @param eventid,workerid 异常事件id，护工id
     * @return 影响记录数
     */
    int confirm(Integer eventid, Integer workerid);
    /**
     * 护工提交异常事件处理结果
     * @param eventid,resultText,handler 异常事件id，护工id
     * @return 影响记录数
     */
    int finish(Integer eventid, String resultText);
}