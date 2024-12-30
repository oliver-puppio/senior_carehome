package com.boat.pension.model;

import java.io.Serializable;

public class Bed implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.id
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.num
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private Integer num;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.roomID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private Integer roomid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.roomNum
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private String roomnum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.seniorID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private Integer seniorid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.deviceID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private Integer deviceid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.location
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private String location;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bed
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.id
     *
     * @return the value of bed.id
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.id
     *
     * @param id the value for bed.id
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.num
     *
     * @return the value of bed.num
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.num
     *
     * @param num the value for bed.num
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.roomID
     *
     * @return the value of bed.roomID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public Integer getRoomid() {
        return roomid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.roomID
     *
     * @param roomid the value for bed.roomID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.roomNum
     *
     * @return the value of bed.roomNum
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public String getRoomnum() {
        return roomnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.roomNum
     *
     * @param roomnum the value for bed.roomNum
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.seniorID
     *
     * @return the value of bed.seniorID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public Integer getSeniorid() {
        return seniorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.seniorID
     *
     * @param seniorid the value for bed.seniorID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setSeniorid(Integer seniorid) {
        this.seniorid = seniorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.deviceID
     *
     * @return the value of bed.deviceID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public Integer getDeviceid() {
        return deviceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.deviceID
     *
     * @param deviceid the value for bed.deviceID
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.location
     *
     * @return the value of bed.location
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.location
     *
     * @param location the value for bed.location
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bed
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
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