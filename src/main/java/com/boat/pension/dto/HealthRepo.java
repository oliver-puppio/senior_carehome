package com.boat.pension.dto;

import com.boat.pension.model.Bed;
import com.boat.pension.model.Senior;
import com.boat.pension.util.TimeUtil;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

@Data
public class HealthRepo {
    Integer seniorID;
    Integer deviceID;
    String name;
    String location;
    // 记录状态，0表示正常，1表示数据异常，2表示老人离床
    Integer flag;
    Integer mean_heart;
    Integer mean_breath;
    String timestamp;

    public HealthRepo(Bed bed, Senior senior, Integer mh, Integer mb) {
        seniorID = bed.getSeniorid();
        deviceID = bed.getDeviceid();
        name = senior.getName();
        location = bed.getLocation();
        mean_heart = mh;
        mean_breath = mb;
        if (mh == 0 && mb == 0) {
            flag = 2;
        }
        Integer his_heart = senior.getHeart();
        Integer his_breath = senior.getBreath();
        if (isNormal(mh, his_heart, 10) && isNormal(mb, his_breath, 5)) {
            flag = 0;
        } else {
            flag = 1;
        }
        timestamp = TimeUtil.dateTime2String(new Date());
    }

    private boolean isNormal(Integer individual, Integer mean, Integer error) {
        return Math.abs(individual - mean) <= error;
    }
}

