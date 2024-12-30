package com.boat.pension.service;

import com.boat.pension.dto.WorkerParam;
import com.boat.pension.model.Worker;
import net.sf.json.JSONArray;

import java.util.List;

public interface WorkerService {
    /**
     * 根据养老院id获取护工列表
     *
     * @param instId 机构id
     * @return 护工列表
     */
    List<Worker> listAllByInstid(Integer instId);

    /**
     * 根据护工id获取护工
     *
     * @param workerId 护工参数
     * @return 护工
     */
    Worker getWorkerByWorkerId(Integer workerId);

    /**
     * 根据护工id获取护工监管的房间列表
     *
     * @param id 护工id
     * @return 房间列表
     */
    JSONArray listRoomByWorkerId(Integer id, Integer instId);

    /**
     * 添加护工
     *
     * @param workerParam 护工参数
     * @return 影响记录数
     */
    int addWorker(Integer instId, WorkerParam workerParam);

    /**
     * 删除护工
     *
     * @param id 护工参数
     * @return 影响记录数
     */
    int delete(Integer id);


    /**
     * 修改指定护工信息
     *
     * @param id,workerParam 护工id，护工参数
     * @return 影响记录数
     */
    int update(Integer id, WorkerParam workerParam);

    /**
     * 添加房间
     *
     * @param workerid,roomid 护工id，房间id
     * @return 影响记录数
     */
    int addRoom(Integer workerid, Integer roomid);

    /**
     * 删除房间
     *
     * @param workerid,roomid 护工id，房间id
     * @return 影响记录数
     */
    int delRoom(Integer workerid, Integer roomid);
}
