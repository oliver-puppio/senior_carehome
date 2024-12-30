package com.boat.pensionwx.dto;

import com.boat.pension.model.Monitorlog;
import com.boat.pensionwx.util.TimeUtil;
//import io.swagger.annotations.ApiModel;
import lombok.Data;
import net.sf.json.JSONObject;

@Data
//@ApiModel("老人健康日志")
public class LogDTO {
    private String logDate;
    private JSONObject heartArr;
    private JSONObject breathArr;

    private String startTime;

    private String endTime;
    private int num;

    public LogDTO(Monitorlog log, int size) {
        logDate = TimeUtil.date2String(log.getLogdate());
        heartArr = JSONObject.fromObject("{ heart: "  + log.getHeartarr().toString() + "}");
        breathArr = JSONObject.fromObject("{ breath: " + log.getBreatharr().toString() + "}");
        if(log.getStarttime()!=null)
            startTime = TimeUtil.dateTime2String(log.getStarttime());
        if(log.getEndtime()!=null)
            endTime = TimeUtil.dateTime2String(log.getEndtime());
        num = size;
    }
}
