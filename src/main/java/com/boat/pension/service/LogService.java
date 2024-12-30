package com.boat.pension.service;

import com.boat.pension.model.Monitorlog;
import com.boat.pension.model.Record;

import java.util.List;

public interface LogService {
    /**
     * 停止监测后生成日志
     * @param
     * @return 日志列表
     */
    int create(Integer instId);
}
