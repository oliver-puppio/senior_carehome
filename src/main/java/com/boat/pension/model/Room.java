package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Room implements Serializable {
    private Integer id;

    private Integer instid;

    private String instname;

    private Integer wokerid;

    private String num;

    private String buildingname;

    private Integer floor;

    private Integer size;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstid() {
        return instid;
    }

    public void setInstid(Integer instid) {
        this.instid = instid;
    }

    public String getInstname() {
        return instname;
    }

    public void setInstname(String instname) {
        this.instname = instname;
    }

    public Integer getWokerid() {
        return wokerid;
    }

    public void setWokerid(Integer wokerid) {
        this.wokerid = wokerid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", instid=").append(instid);
        sb.append(", instname=").append(instname);
        sb.append(", wokerid=").append(wokerid);
        sb.append(", num=").append(num);
        sb.append(", buildingname=").append(buildingname);
        sb.append(", floor=").append(floor);
        sb.append(", size=").append(size);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}