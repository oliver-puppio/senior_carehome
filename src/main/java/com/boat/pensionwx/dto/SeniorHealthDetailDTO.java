package com.boat.pensionwx.dto;

import com.boat.pension.model.Bed;
import com.boat.pension.model.Senior;
//import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
//@ApiModel("老人基本健康信息")
public class SeniorHealthDetailDTO {
    private int id;
    private String name;
    private String gender;
    private int deviceID;
    private String location;
    private int heart_rate;
    private int breath_frequency;

    /***
     * 构建老人健康信息
     * @param senior 老人实体
     * @param bed 床位实体
     */
    public SeniorHealthDetailDTO(Senior senior, Bed bed) {
        id = senior.getId();
        name = senior.getName();
        gender = senior.getGender();
        //TODO:根据老人历史数据生成平均心率、呼吸
        heart_rate = senior.getHeart() == null ? 0 : senior.getHeart();
        breath_frequency = senior.getBreath() == null ? 0 : senior.getBreath();
        location = bed.getLocation();
        deviceID = bed.getDeviceid();
    }
}
