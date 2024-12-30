package com.boat.pension.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Senior implements Serializable {
    private Integer id;

    private Integer instid;

    private String pic;

    private String name;

    private String gender;

    private String idnum;

    private String tel;

    private Integer heart;

    private Integer breath;

    private Date registertime;

    private String cont1;

    private String ctel1;

    private String cont2;

    private String ctel2;

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getCont1() {
        return cont1;
    }

    public void setCont1(String cont1) {
        this.cont1 = cont1;
    }

    public String getCtel1() {
        return ctel1;
    }

    public void setCtel1(String ctel1) {
        this.ctel1 = ctel1;
    }

    public String getCont2() {
        return cont2;
    }

    public void setCont2(String cont2) {
        this.cont2 = cont2;
    }

    public String getCtel2() {
        return ctel2;
    }

    public void setCtel2(String ctel2) {
        this.ctel2 = ctel2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", instid=").append(instid);
        sb.append(", pic=").append(pic);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", idnum=").append(idnum);
        sb.append(", tel=").append(tel);
        sb.append(", heart=").append(heart);
        sb.append(", breath=").append(breath);
        sb.append(", registertime=").append(registertime);
        sb.append(", cont1=").append(cont1);
        sb.append(", ctel1=").append(ctel1);
        sb.append(", cont2=").append(cont2);
        sb.append(", ctel2=").append(ctel2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}