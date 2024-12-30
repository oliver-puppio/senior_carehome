package com.boat.pension.dto;

import com.boat.pension.model.Event;
import com.boat.pension.model.Worker;
import com.boat.pension.util.TimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("异常事件")
public class EventDTO {
    @ApiModelProperty("事件编号")
    private int id;
    @ApiModelProperty("老人姓名")
    private String name;
    @ApiModelProperty("老人ID")
    private Integer seniorID;
    @ApiModelProperty("老人位置")
    private String location;
    @ApiModelProperty("异常类型：1表示数据异常，0表示离床未归")
    private int type;
    @ApiModelProperty("处理状态：0表示待处理，1表示处理中，2表示已完成")
    private int state;
    @ApiModelProperty("异常开始时间")
    private String startTime;
    @ApiModelProperty("处理人姓名")
    private String handler;
    @ApiModelProperty("护工联姓名")
    private String workerName;
    @ApiModelProperty("护工联系方式")
    private String workerTel;
    @ApiModelProperty("护工介入所花时间")
    private String enterDuration;
    @ApiModelProperty("异常持续时间")
    private String duration;
    @ApiModelProperty("处理结果")
    private String resultText;


    public EventDTO(Event event, Worker worker) {
        id = event.getId();
        name = event.getName();
        seniorID = event.getSeniorid();
        location = event.getLocation();
        type = event.getType();
        state = event.getState();
        startTime = TimeUtil.dateTime2String(event.getStarttime());
        if (state > 0 && event.getEntertime() != null) {
            enterDuration = TimeUtil.difference(event.getEntertime(), event.getStarttime());
            handler = event.getHandler();
            if (state > 1 && event.getEndtime() != null) {
                duration = TimeUtil.difference(event.getEndtime(), event.getStarttime());
                resultText = event.getResulttext();
            }
        }
        if(worker != null){
            workerName = worker.getName();
            workerTel = worker.getTel();
        }

    }

}

