package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Wechatuser implements Serializable {
    private Integer id;

    private String openid;

    private String tel;

    private Integer isworker;

    private Integer workerid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getIsworker() {
        return isworker;
    }

    public void setIsworker(Integer isworker) {
        this.isworker = isworker;
    }

    public Integer getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Integer workerid) {
        this.workerid = workerid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openid=").append(openid);
        sb.append(", tel=").append(tel);
        sb.append(", isworker=").append(isworker);
        sb.append(", workerid=").append(workerid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}