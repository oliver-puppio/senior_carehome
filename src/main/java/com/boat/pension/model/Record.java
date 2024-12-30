package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    private Integer id;

    private Integer deviceid;

    private Date realtime;

    private Integer heart;

    private Integer breath;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public Date getRealtime() {
        return realtime;
    }

    public void setRealtime(Date realtime) {
        this.realtime = realtime;
    }

    public Integer getHeart() {
        return heart;
    }

    public void setHeart(Integer heart) {
        this.heart = heart;
    }

    public Integer getBreath() {
        return breath;
    }

    public void setBreath(Integer breath) {
        this.breath = breath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deviceid=").append(deviceid);
        sb.append(", realtime=").append(realtime);
        sb.append(", heart=").append(heart);
        sb.append(", breath=").append(breath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}