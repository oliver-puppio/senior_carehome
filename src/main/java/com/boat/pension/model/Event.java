package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    private Integer id;

    private Integer seniorid;

    private String name;

    private Integer deviceid;

    private Integer instid;

    private Integer type;

    private Date starttime;

    private Integer state;

    private Date entertime;

    private Date endtime;

    private String handler;

    private String location;

    private String resulttext;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getInstid() {
        return instid;
    }

    public void setInstid(Integer instid) {
        this.instid = instid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getEntertime() {
        return entertime;
    }

    public void setEntertime(Date entertime) {
        this.entertime = entertime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResulttext() {
        return resulttext;
    }

    public void setResulttext(String resulttext) {
        this.resulttext = resulttext;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", seniorid=").append(seniorid);
        sb.append(", name=").append(name);
        sb.append(", deviceid=").append(deviceid);
        sb.append(", instid=").append(instid);
        sb.append(", type=").append(type);
        sb.append(", starttime=").append(starttime);
        sb.append(", state=").append(state);
        sb.append(", entertime=").append(entertime);
        sb.append(", endtime=").append(endtime);
        sb.append(", handler=").append(handler);
        sb.append(", location=").append(location);
        sb.append(", resulttext=").append(resulttext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}