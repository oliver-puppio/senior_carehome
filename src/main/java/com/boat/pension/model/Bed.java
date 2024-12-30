package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Bed implements Serializable {
    private Integer id;

    private Integer num;

    private Integer roomid;

    private String roomnum;

    private Integer seniorid;

    private Integer deviceid;

    private String location;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", num=").append(num);
        sb.append(", roomid=").append(roomid);
        sb.append(", roomnum=").append(roomnum);
        sb.append(", seniorid=").append(seniorid);
        sb.append(", deviceid=").append(deviceid);
        sb.append(", location=").append(location);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}