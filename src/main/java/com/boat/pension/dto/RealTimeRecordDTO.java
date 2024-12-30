package com.boat.pension.dto;

import com.boat.pension.model.Record;
import com.boat.pension.util.TimeUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("实时监测记录")
public class RealTimeRecordDTO {
    private Integer seniorID;
    private Integer deviceID;
    private int heart_rate;
    private int breath_frequency;
//    private int bed_state;

    public RealTimeRecordDTO(Record record, Integer id) {
        seniorID = id;
        heart_rate = record.getHeart();
        breath_frequency = record.getBreath();
        deviceID = record.getDeviceid();
//        bed_state = record.getState();
    }
}
