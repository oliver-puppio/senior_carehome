package com.boat.pensionwx.dto;

import com.boat.pension.model.Record;
import com.boat.pensionwx.util.TimeUtil;
//import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
//@ApiModel("实时监测记录")
public class RealTimeRecordDTO {
    private int deviceID;
    private String realtime;
    private int heart_rate;
    private int breath_frequency;

    public RealTimeRecordDTO(Record record) {
        deviceID = record.getDeviceid();
        realtime = TimeUtil.time2String(record.getRealtime());
        heart_rate = record.getHeart();
        breath_frequency = record.getBreath();
    }
}
