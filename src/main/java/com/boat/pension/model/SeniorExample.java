package com.boat.pension.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeniorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SeniorExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInstidIsNull() {
            addCriterion("instID is null");
            return (Criteria) this;
        }

        public Criteria andInstidIsNotNull() {
            addCriterion("instID is not null");
            return (Criteria) this;
        }

        public Criteria andInstidEqualTo(Integer value) {
            addCriterion("instID =", value, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidNotEqualTo(Integer value) {
            addCriterion("instID <>", value, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidGreaterThan(Integer value) {
            addCriterion("instID >", value, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidGreaterThanOrEqualTo(Integer value) {
            addCriterion("instID >=", value, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidLessThan(Integer value) {
            addCriterion("instID <", value, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidLessThanOrEqualTo(Integer value) {
            addCriterion("instID <=", value, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidIn(List<Integer> values) {
            addCriterion("instID in", values, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidNotIn(List<Integer> values) {
            addCriterion("instID not in", values, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidBetween(Integer value1, Integer value2) {
            addCriterion("instID between", value1, value2, "instid");
            return (Criteria) this;
        }

        public Criteria andInstidNotBetween(Integer value1, Integer value2) {
            addCriterion("instID not between", value1, value2, "instid");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andIdnumIsNull() {
            addCriterion("IDNum is null");
            return (Criteria) this;
        }

        public Criteria andIdnumIsNotNull() {
            addCriterion("IDNum is not null");
            return (Criteria) this;
        }

        public Criteria andIdnumEqualTo(String value) {
            addCriterion("IDNum =", value, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumNotEqualTo(String value) {
            addCriterion("IDNum <>", value, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumGreaterThan(String value) {
            addCriterion("IDNum >", value, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumGreaterThanOrEqualTo(String value) {
            addCriterion("IDNum >=", value, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumLessThan(String value) {
            addCriterion("IDNum <", value, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumLessThanOrEqualTo(String value) {
            addCriterion("IDNum <=", value, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumLike(String value) {
            addCriterion("IDNum like", value, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumNotLike(String value) {
            addCriterion("IDNum not like", value, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumIn(List<String> values) {
            addCriterion("IDNum in", values, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumNotIn(List<String> values) {
            addCriterion("IDNum not in", values, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumBetween(String value1, String value2) {
            addCriterion("IDNum between", value1, value2, "idnum");
            return (Criteria) this;
        }

        public Criteria andIdnumNotBetween(String value1, String value2) {
            addCriterion("IDNum not between", value1, value2, "idnum");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andHeartIsNull() {
            addCriterion("heart is null");
            return (Criteria) this;
        }

        public Criteria andHeartIsNotNull() {
            addCriterion("heart is not null");
            return (Criteria) this;
        }

        public Criteria andHeartEqualTo(Integer value) {
            addCriterion("heart =", value, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartNotEqualTo(Integer value) {
            addCriterion("heart <>", value, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartGreaterThan(Integer value) {
            addCriterion("heart >", value, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartGreaterThanOrEqualTo(Integer value) {
            addCriterion("heart >=", value, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartLessThan(Integer value) {
            addCriterion("heart <", value, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartLessThanOrEqualTo(Integer value) {
            addCriterion("heart <=", value, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartIn(List<Integer> values) {
            addCriterion("heart in", values, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartNotIn(List<Integer> values) {
            addCriterion("heart not in", values, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartBetween(Integer value1, Integer value2) {
            addCriterion("heart between", value1, value2, "heart");
            return (Criteria) this;
        }

        public Criteria andHeartNotBetween(Integer value1, Integer value2) {
            addCriterion("heart not between", value1, value2, "heart");
            return (Criteria) this;
        }

        public Criteria andBreathIsNull() {
            addCriterion("breath is null");
            return (Criteria) this;
        }

        public Criteria andBreathIsNotNull() {
            addCriterion("breath is not null");
            return (Criteria) this;
        }

        public Criteria andBreathEqualTo(Integer value) {
            addCriterion("breath =", value, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathNotEqualTo(Integer value) {
            addCriterion("breath <>", value, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathGreaterThan(Integer value) {
            addCriterion("breath >", value, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathGreaterThanOrEqualTo(Integer value) {
            addCriterion("breath >=", value, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathLessThan(Integer value) {
            addCriterion("breath <", value, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathLessThanOrEqualTo(Integer value) {
            addCriterion("breath <=", value, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathIn(List<Integer> values) {
            addCriterion("breath in", values, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathNotIn(List<Integer> values) {
            addCriterion("breath not in", values, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathBetween(Integer value1, Integer value2) {
            addCriterion("breath between", value1, value2, "breath");
            return (Criteria) this;
        }

        public Criteria andBreathNotBetween(Integer value1, Integer value2) {
            addCriterion("breath not between", value1, value2, "breath");
            return (Criteria) this;
        }

        public Criteria andRegistertimeIsNull() {
            addCriterion("registerTime is null");
            return (Criteria) this;
        }

        public Criteria andRegistertimeIsNotNull() {
            addCriterion("registerTime is not null");
            return (Criteria) this;
        }

        public Criteria andRegistertimeEqualTo(Date value) {
            addCriterion("registerTime =", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeNotEqualTo(Date value) {
            addCriterion("registerTime <>", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeGreaterThan(Date value) {
            addCriterion("registerTime >", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("registerTime >=", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeLessThan(Date value) {
            addCriterion("registerTime <", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeLessThanOrEqualTo(Date value) {
            addCriterion("registerTime <=", value, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeIn(List<Date> values) {
            addCriterion("registerTime in", values, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeNotIn(List<Date> values) {
            addCriterion("registerTime not in", values, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeBetween(Date value1, Date value2) {
            addCriterion("registerTime between", value1, value2, "registertime");
            return (Criteria) this;
        }

        public Criteria andRegistertimeNotBetween(Date value1, Date value2) {
            addCriterion("registerTime not between", value1, value2, "registertime");
            return (Criteria) this;
        }

        public Criteria andCont1IsNull() {
            addCriterion("cont1 is null");
            return (Criteria) this;
        }

        public Criteria andCont1IsNotNull() {
            addCriterion("cont1 is not null");
            return (Criteria) this;
        }

        public Criteria andCont1EqualTo(String value) {
            addCriterion("cont1 =", value, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1NotEqualTo(String value) {
            addCriterion("cont1 <>", value, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1GreaterThan(String value) {
            addCriterion("cont1 >", value, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1GreaterThanOrEqualTo(String value) {
            addCriterion("cont1 >=", value, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1LessThan(String value) {
            addCriterion("cont1 <", value, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1LessThanOrEqualTo(String value) {
            addCriterion("cont1 <=", value, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1Like(String value) {
            addCriterion("cont1 like", value, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1NotLike(String value) {
            addCriterion("cont1 not like", value, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1In(List<String> values) {
            addCriterion("cont1 in", values, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1NotIn(List<String> values) {
            addCriterion("cont1 not in", values, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1Between(String value1, String value2) {
            addCriterion("cont1 between", value1, value2, "cont1");
            return (Criteria) this;
        }

        public Criteria andCont1NotBetween(String value1, String value2) {
            addCriterion("cont1 not between", value1, value2, "cont1");
            return (Criteria) this;
        }

        public Criteria andCtel1IsNull() {
            addCriterion("cTel1 is null");
            return (Criteria) this;
        }

        public Criteria andCtel1IsNotNull() {
            addCriterion("cTel1 is not null");
            return (Criteria) this;
        }

        public Criteria andCtel1EqualTo(String value) {
            addCriterion("cTel1 =", value, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1NotEqualTo(String value) {
            addCriterion("cTel1 <>", value, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1GreaterThan(String value) {
            addCriterion("cTel1 >", value, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1GreaterThanOrEqualTo(String value) {
            addCriterion("cTel1 >=", value, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1LessThan(String value) {
            addCriterion("cTel1 <", value, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1LessThanOrEqualTo(String value) {
            addCriterion("cTel1 <=", value, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1Like(String value) {
            addCriterion("cTel1 like", value, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1NotLike(String value) {
            addCriterion("cTel1 not like", value, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1In(List<String> values) {
            addCriterion("cTel1 in", values, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1NotIn(List<String> values) {
            addCriterion("cTel1 not in", values, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1Between(String value1, String value2) {
            addCriterion("cTel1 between", value1, value2, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCtel1NotBetween(String value1, String value2) {
            addCriterion("cTel1 not between", value1, value2, "ctel1");
            return (Criteria) this;
        }

        public Criteria andCont2IsNull() {
            addCriterion("cont2 is null");
            return (Criteria) this;
        }

        public Criteria andCont2IsNotNull() {
            addCriterion("cont2 is not null");
            return (Criteria) this;
        }

        public Criteria andCont2EqualTo(String value) {
            addCriterion("cont2 =", value, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2NotEqualTo(String value) {
            addCriterion("cont2 <>", value, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2GreaterThan(String value) {
            addCriterion("cont2 >", value, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2GreaterThanOrEqualTo(String value) {
            addCriterion("cont2 >=", value, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2LessThan(String value) {
            addCriterion("cont2 <", value, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2LessThanOrEqualTo(String value) {
            addCriterion("cont2 <=", value, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2Like(String value) {
            addCriterion("cont2 like", value, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2NotLike(String value) {
            addCriterion("cont2 not like", value, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2In(List<String> values) {
            addCriterion("cont2 in", values, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2NotIn(List<String> values) {
            addCriterion("cont2 not in", values, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2Between(String value1, String value2) {
            addCriterion("cont2 between", value1, value2, "cont2");
            return (Criteria) this;
        }

        public Criteria andCont2NotBetween(String value1, String value2) {
            addCriterion("cont2 not between", value1, value2, "cont2");
            return (Criteria) this;
        }

        public Criteria andCtel2IsNull() {
            addCriterion("cTel2 is null");
            return (Criteria) this;
        }

        public Criteria andCtel2IsNotNull() {
            addCriterion("cTel2 is not null");
            return (Criteria) this;
        }

        public Criteria andCtel2EqualTo(String value) {
            addCriterion("cTel2 =", value, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2NotEqualTo(String value) {
            addCriterion("cTel2 <>", value, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2GreaterThan(String value) {
            addCriterion("cTel2 >", value, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2GreaterThanOrEqualTo(String value) {
            addCriterion("cTel2 >=", value, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2LessThan(String value) {
            addCriterion("cTel2 <", value, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2LessThanOrEqualTo(String value) {
            addCriterion("cTel2 <=", value, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2Like(String value) {
            addCriterion("cTel2 like", value, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2NotLike(String value) {
            addCriterion("cTel2 not like", value, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2In(List<String> values) {
            addCriterion("cTel2 in", values, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2NotIn(List<String> values) {
            addCriterion("cTel2 not in", values, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2Between(String value1, String value2) {
            addCriterion("cTel2 between", value1, value2, "ctel2");
            return (Criteria) this;
        }

        public Criteria andCtel2NotBetween(String value1, String value2) {
            addCriterion("cTel2 not between", value1, value2, "ctel2");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}