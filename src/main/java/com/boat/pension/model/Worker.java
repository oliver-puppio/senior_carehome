package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Worker implements Serializable {
    private Integer id;

    private Integer instid;

    private String num;

    private String name;

    private String gender;

    private String idnum;

    private String tel;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", instid=").append(instid);
        sb.append(", num=").append(num);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", idnum=").append(idnum);
        sb.append(", tel=").append(tel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}