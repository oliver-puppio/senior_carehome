package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Monitorlog implements Serializable {
    private Integer id;

    private Integer seniorid;

    private Integer deviceid;

    private Date logdate;

    private String location;

    private Date starttime;

    private Date endtime;

    private String heartarr;

    private String breatharr;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeniorid() {
        return seniorid;
    }

    public void setSeniorid(Integer seniorid) {
        this.seniorid = seniorid;
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public Date getLogdate() {
        return logdate;
    }

    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getHeartarr() {
        return heartarr;
    }

    public void setHeartarr(String heartarr) {
        this.heartarr = heartarr;
    }

    public String getBreatharr() {
        return breatharr;
    }

    public void setBreatharr(String breatharr) {
        this.breatharr = breatharr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", seniorid=").append(seniorid);
        sb.append(", deviceid=").append(deviceid);
        sb.append(", logdate=").append(logdate);
        sb.append(", location=").append(location);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", heartarr=").append(heartarr);
        sb.append(", breatharr=").append(breatharr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}