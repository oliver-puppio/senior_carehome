package com.boat.pension.model;

import java.io.Serializable;

public class Institution implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution.id
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution.name
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution.loc
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private String loc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution.leader
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private String leader;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution.tel
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private String tel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution.aff
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private Integer aff;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table institution
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institution.id
     *
     * @return the value of institution.id
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institution.id
     *
     * @param id the value for institution.id
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institution.name
     *
     * @return the value of institution.name
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institution.name
     *
     * @param name the value for institution.name
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institution.loc
     *
     * @return the value of institution.loc
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public String getLoc() {
        return loc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institution.loc
     *
     * @param loc the value for institution.loc
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institution.leader
     *
     * @return the value of institution.leader
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public String getLeader() {
        return leader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institution.leader
     *
     * @param leader the value for institution.leader
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setLeader(String leader) {
        this.leader = leader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institution.tel
     *
     * @return the value of institution.tel
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institution.tel
     *
     * @param tel the value for institution.tel
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column institution.aff
     *
     * @return the value of institution.aff
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public Integer getAff() {
        return aff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column institution.aff
     *
     * @param aff the value for institution.aff
     *
     * @mbg.generated Mon Jul 25 21:36:27 CST 2022
     */
    public void setAff(Integer aff) {
        this.aff = aff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table institution
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