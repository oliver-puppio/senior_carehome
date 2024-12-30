package com.boat.pension.service;

import com.boat.pension.dto.HealthRepo;
import com.boat.pension.dto.RealTimeRecordDTO;
import com.boat.pension.model.Record;

import java.util.List;

public interface RecordService {
    /**
     * 根据养老院id获取监测数据列表
     * @param instId 机构id
     * @return 设备列表
     */
    List<RealTimeRecordDTO> realtimeRecord(Integer instId);

    /**
     * 生成监测数据
     * @param
     * @return 设备列表
     */
    List<Record> create();

    /***
     * 根据养老院id获取健康检查报告
     * @param instId
     * @return
     */
    List<HealthRepo> realtimeHealthCheck(Integer instId);
}
