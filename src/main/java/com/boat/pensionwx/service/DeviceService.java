package com.boat.pensionwx.service;

import com.boat.pensionwx.dto.LogDTO;
import com.boat.pensionwx.dto.RealTimeRecordDTO;

public interface DeviceService {
    /***
     * 按设备id获取实时监控信息
     * @param deviceID 设备id
     * @return 实时监控信息
     */
    RealTimeRecordDTO realtimeRecord(int deviceID);
}
