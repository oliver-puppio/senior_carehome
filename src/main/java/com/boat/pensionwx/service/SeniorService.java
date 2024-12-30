package com.boat.pensionwx.service;

import com.boat.pensionwx.dto.EventDTO;
import com.boat.pensionwx.dto.LogDTO;
import com.boat.pensionwx.dto.SeniorHealthDetailDTO;
import net.sf.json.JSONObject;

import java.util.List;

public interface SeniorService {
    /***
     * 获取老人的基本健康信息
     * @param id 老人编号
     * @return 健康信息
     */
    SeniorHealthDetailDTO detail(int id);

    /***
     * 指定日期获取老人的健康日志
     * @param id 老人编号
     * @param date 指定日期
     * @return 健康日志
     */
    LogDTO log(int id, String date);

    /***
     * 获取老人最近一天的的健康日志
     * @param id 老人编号
     * @return 健康日志
     */
    LogDTO log(int id);

    /***
     * 获取老人的异常事件列表
     * @param id 老人编号
     * @return 异常事件列表
     */
    List<EventDTO> event(int id);
}
