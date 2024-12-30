package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Institution implements Serializable {
    private Integer id;

    private String name;

    private String loc;

    private String leader;

    private String tel;

    private Integer aff;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAff() {
        return aff;
    }

    public void setAff(Integer aff) {
        this.aff = aff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", loc=").append(loc);
        sb.append(", leader=").append(leader);
        sb.append(", tel=").append(tel);
        sb.append(", aff=").append(aff);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}