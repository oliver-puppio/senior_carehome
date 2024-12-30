package com.boat.pension.service;


import com.boat.pension.dto.CarehomeParam;
import com.boat.pension.model.Admin;
import com.boat.pension.model.Institution;

/**
 * 养老院管理Service
 * Created by hxy on 2022/7/1.
 */
public interface CarehomeService {
    /**
     * 根据养老院id获取养老院
     * @param carehomeId 养老院id
     * @return 养老院
     */
    Institution getCarehomeByCarehomeId(Integer carehomeId);

    /**
     * 新增养老院
     * @param carehomeParam 养老院参数
     * @return 影响记录数
     */
    int addCarehome(CarehomeParam carehomeParam);

    /**
     * 修改指定养老院信息
     * @param id,carehomeParam 养老院id,养老院参数
     * @return 影响记录数
     */
    int update(Integer id, CarehomeParam carehomeParam);

    /**
     * 删除指定养老院
     * @param id 养老院id
     * @return 影响记录数
     */
    int delete(Integer id);
}
