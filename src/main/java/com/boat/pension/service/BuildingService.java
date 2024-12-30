package com.boat.pension.service;


import com.boat.pension.dto.BuildingParam;
import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.model.Building;

import java.util.List;

/**
 * 楼栋管理Service
 * Created by hxy on 2022/7/1.
 */
public interface BuildingService {
    /**
     * 获取所有楼栋
     * @param
     * @return 楼栋列表
     */
    List<Building> listAllBuilding(int instID);

    /**
     * 新增楼栋
     * @param buildingParam 楼栋参数
     * @return 影响记录数
     */
    int addBuilding(Integer instId, BuildingParam buildingParam);

    /**
     * 删除指定楼栋
     * @param id 楼栋id
     * @return 影响记录数
     */
    int delete(Integer id);
}
