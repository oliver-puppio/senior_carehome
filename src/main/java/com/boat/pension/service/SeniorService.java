package com.boat.pension.service;

import com.boat.pension.dto.*;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Institution;
import com.boat.pension.model.Senior;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface SeniorService {
    /**
     * 根据养老院id获取老人列表
     * @param instId 机构id
     * @return 老人列表
     */
    SeniorDTO listAllByInstid(Integer instId);
    /**
     * 根据养老院id获取管理的社区的老人列表
     * @param instId 机构
     * @return 分组老人列表
     */
    List<SeniorDTO> listAllinCommunityByInstid(Integer instId);
    /**
     * 老人入住
     * @param seniorParam 老人参数
     * @return 影响记录数
     */
    int addSenior(Integer instId, SeniorParam seniorParam);

    /**
     * 老人离院
     * @param id 老人id
     * @return 影响记录数
     */
    int delete(Integer id);
    /**
     * 修改指定老人信息
     * @param id,seniorParam 老人id，老人参数
     * @return 影响记录数
     */
    int update(Integer id, SeniorParam seniorParam);
    /**
     * 根据老人id获取老人
     * @param seniorId 老人id
     * @return 老人
     */
    JSONObject getSeniorBySeniorId(Integer seniorId);
    /**
     * 根据老人id获取监测日志
     */

    /**
     * 更换床位
     * @param seniorid，bedid 老人id，床位id
     * @return 影响记录数
     */
    int bind(Integer seniorid, Integer bedid);

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

