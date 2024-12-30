package com.boat.pensionwx.dto;

import com.boat.pension.model.Event;
import com.boat.pensionwx.util.TimeUtil;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//@ApiModel("异常事件")
public class EventDTO {
    //    @ApiModelProperty("事件编号")
    private Integer id;
    //    @ApiModelProperty("老人id")
    private Integer seniorID;
    //    @ApiModelProperty("老人姓名")
    private Integer deviceID;
    private String name;
    private String gender;
    //    @ApiModelProperty("老人位置")
    private String location;
    //    @ApiModelProperty("异常类型：1表示心率异常，2呼吸异常，3表示离床未归")
    private int type;
    //    @ApiModelProperty("处理状态：0表示待处理，1表示处理中，2表示已完成")
    private int state;
    //    @ApiModelProperty("异常开始时间")
    private String startTime;
    private String enterTime;
    private String endTime;
    //    @ApiModelProperty("护工介入所画时间")
//    private String enterDuration;
//    @ApiModelProperty("异常持续时间")
//    private String duration;
//    @ApiModelProperty("处理结果")
    private String resultText;
    //    @ApiModelProperty("处理人")
    private String handler;

    public EventDTO(Event event,String g) {
        id = event.getId();
        seniorID = event.getSeniorid();
        deviceID=event.getDeviceid();
        name = event.getName();
        gender=g;
        location = event.getLocation();
        type = event.getType();
        state = event.getState();
        startTime = TimeUtil.dateTime2String(event.getStarttime());
        if (state > 0 && event.getEntertime() != null) {
            enterTime = TimeUtil.dateTime2String(event.getEntertime());
            handler = event.getHandler();
            if (state > 1 && event.getEndtime() != null) {
                endTime = TimeUtil.dateTime2String(event.getEndtime());
                resultText = event.getResulttext();
            }
        }
    }

}
