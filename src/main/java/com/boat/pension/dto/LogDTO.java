package com.boat.pension.dto;

import com.boat.pension.model.Monitorlog;
import com.boat.pension.util.TimeUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.sf.json.JSONObject;

@Data
@ApiModel("老人健康日志")
public class LogDTO {
    private String logDate;
    private JSONObject heartArr;
    private JSONObject breathArr;
//    private JSONObject stateArr;

    public LogDTO(Monitorlog log) {
        logDate = TimeUtil.date2String(log.getLogdate());
        heartArr = JSONObject.fromObject(log.getHeartarr());
        breathArr = JSONObject.fromObject(log.getBreatharr());
//        stateArr = JSONObject.fromObject(log.getStatearr());
    }
}
