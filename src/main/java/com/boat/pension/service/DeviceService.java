package com.boat.pension.service;

import com.boat.pension.dto.DeviceDTO;
import com.boat.pension.dto.RealTimeRecordDTO;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Device;

import java.util.List;

public interface DeviceService {
    /**
     * 根据养老院id获取设备列表
     * @param instId 机构id
     * @return 设备列表
     */
    List<DeviceDTO> listAllByInstid(Integer instId);

    /***
     * 根据养老院id和设备状态获取设备列表
     * @param instId 机构id
     * @param state 设备状态，0、1、2分别表示未绑定、启用、禁用
     * @return
     */
    List<Device> listAllByInstidAndState(int instId,int state);
    /**
     * 新增设备
     * @param id 设备id
     * @return 影响记录数
     */
    int addDevice(Integer instid,Integer id);
    /**
     * 改变设备状态,0为禁用，1为启用
     * @param id,flag 设备id, 禁用/启用
     * @return 影响记录数
     */
    int updateState(Integer id, int state);

    /***
     * 按设备id获取实时监控信息
     * @param deviceID 设备id
     * @return 实时监控信息
     */
    RealTimeRecordDTO realtimeRecord(int deviceID);
//
//    int monitor(Admin admin);
}
