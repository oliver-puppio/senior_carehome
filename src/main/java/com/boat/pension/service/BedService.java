package com.boat.pension.service;

//import com.boat.pension.dto.BedParam;
import com.boat.pension.dto.BedDeviceDTO;
import com.boat.pension.model.Bed;

import java.util.List;

/**
 * 床位管理Service
 * Created by hxy on 2022/7/1.
 */
public interface BedService {
    /**
     * 删除指定房间
     * @param id 床位id
     * @return 影响记录数
     */
    int delete(Integer id);

    /**
     * 绑定设备
     * @param bedDeviceDTO 包含id,deviceid 床位id 设备id
     * @return 影响记录数
     */
    int addDevice(BedDeviceDTO bedDeviceDTO);

    /**
     * 解除设备
     * @param bedDeviceDTO 包含id,deviceid，分别是床位id 设备id
     * @return 影响记录数
     */
    int deleteDevice(BedDeviceDTO bedDeviceDTO);

    /**
     * 返回未绑定设备的床位列表
     * @param id 机构id
     * @return 床位列表
     */
    List<Bed> listNoBindByInstid(Integer id);

    /**
     * 返回未入住的床位列表
     * @param id 机构id
     * @return 床位列表
     */
    List<Bed> listNoSeniorByInstid(Integer id);


}
