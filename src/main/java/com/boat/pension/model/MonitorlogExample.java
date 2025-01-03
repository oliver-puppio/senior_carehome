package com.boat.pension.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MonitorlogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MonitorlogExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andSenioridIsNull() {
            addCriterion("seniorID is null");
            return (Criteria) this;
        }

        public Criteria andSenioridIsNotNull() {
            addCriterion("seniorID is not null");
            return (Criteria) this;
        }

        public Criteria andSenioridEqualTo(Integer value) {
            addCriterion("seniorID =", value, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridNotEqualTo(Integer value) {
            addCriterion("seniorID <>", value, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridGreaterThan(Integer value) {
            addCriterion("seniorID >", value, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridGreaterThanOrEqualTo(Integer value) {
            addCriterion("seniorID >=", value, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridLessThan(Integer value) {
            addCriterion("seniorID <", value, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridLessThanOrEqualTo(Integer value) {
            addCriterion("seniorID <=", value, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridIn(List<Integer> values) {
            addCriterion("seniorID in", values, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridNotIn(List<Integer> values) {
            addCriterion("seniorID not in", values, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridBetween(Integer value1, Integer value2) {
            addCriterion("seniorID between", value1, value2, "seniorid");
            return (Criteria) this;
        }

        public Criteria andSenioridNotBetween(Integer value1, Integer value2) {
            addCriterion("seniorID not between", value1, value2, "seniorid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNull() {
            addCriterion("deviceID is null");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNotNull() {
            addCriterion("deviceID is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceidEqualTo(Integer value) {
            addCriterion("deviceID =", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotEqualTo(Integer value) {
            addCriterion("deviceID <>", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThan(Integer value) {
            addCriterion("deviceID >", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("deviceID >=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThan(Integer value) {
            addCriterion("deviceID <", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThanOrEqualTo(Integer value) {
            addCriterion("deviceID <=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIn(List<Integer> values) {
            addCriterion("deviceID in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotIn(List<Integer> values) {
            addCriterion("deviceID not in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidBetween(Integer value1, Integer value2) {
            addCriterion("deviceID between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotBetween(Integer value1, Integer value2) {
            addCriterion("deviceID not between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andLogdateIsNull() {
            addCriterion("logDate is null");
            return (Criteria) this;
        }

        public Criteria andLogdateIsNotNull() {
            addCriterion("logDate is not null");
            return (Criteria) this;
        }

        public Criteria andLogdateEqualTo(Date value) {
            addCriterionForJDBCDate("logDate =", value, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("logDate <>", value, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateGreaterThan(Date value) {
            addCriterionForJDBCDate("logDate >", value, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("logDate >=", value, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateLessThan(Date value) {
            addCriterionForJDBCDate("logDate <", value, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("logDate <=", value, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateIn(List<Date> values) {
            addCriterionForJDBCDate("logDate in", values, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("logDate not in", values, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("logDate between", value1, value2, "logdate");
            return (Criteria) this;
        }

        public Criteria andLogdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("logDate not between", value1, value2, "logdate");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("startTime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("startTime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterion("startTime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterion("startTime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterion("startTime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("startTime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterion("startTime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("startTime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterion("startTime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterion("startTime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterion("startTime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("startTime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andHeartarrIsNull() {
            addCriterion("heartArr is null");
            return (Criteria) this;
        }

        public Criteria andHeartarrIsNotNull() {
            addCriterion("heartArr is not null");
            return (Criteria) this;
        }

        public Criteria andHeartarrEqualTo(String value) {
            addCriterion("heartArr =", value, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrNotEqualTo(String value) {
            addCriterion("heartArr <>", value, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrGreaterThan(String value) {
            addCriterion("heartArr >", value, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrGreaterThanOrEqualTo(String value) {
            addCriterion("heartArr >=", value, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrLessThan(String value) {
            addCriterion("heartArr <", value, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrLessThanOrEqualTo(String value) {
            addCriterion("heartArr <=", value, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrLike(String value) {
            addCriterion("heartArr like", value, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrNotLike(String value) {
            addCriterion("heartArr not like", value, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrIn(List<String> values) {
            addCriterion("heartArr in", values, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrNotIn(List<String> values) {
            addCriterion("heartArr not in", values, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrBetween(String value1, String value2) {
            addCriterion("heartArr between", value1, value2, "heartarr");
            return (Criteria) this;
        }

        public Criteria andHeartarrNotBetween(String value1, String value2) {
            addCriterion("heartArr not between", value1, value2, "heartarr");
            return (Criteria) this;
        }

        public Criteria andBreatharrIsNull() {
            addCriterion("breathArr is null");
            return (Criteria) this;
        }

        public Criteria andBreatharrIsNotNull() {
            addCriterion("breathArr is not null");
            return (Criteria) this;
        }

        public Criteria andBreatharrEqualTo(String value) {
            addCriterion("breathArr =", value, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrNotEqualTo(String value) {
            addCriterion("breathArr <>", value, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrGreaterThan(String value) {
            addCriterion("breathArr >", value, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrGreaterThanOrEqualTo(String value) {
            addCriterion("breathArr >=", value, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrLessThan(String value) {
            addCriterion("breathArr <", value, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrLessThanOrEqualTo(String value) {
            addCriterion("breathArr <=", value, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrLike(String value) {
            addCriterion("breathArr like", value, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrNotLike(String value) {
            addCriterion("breathArr not like", value, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrIn(List<String> values) {
            addCriterion("breathArr in", values, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrNotIn(List<String> values) {
            addCriterion("breathArr not in", values, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrBetween(String value1, String value2) {
            addCriterion("breathArr between", value1, value2, "breatharr");
            return (Criteria) this;
        }

        public Criteria andBreatharrNotBetween(String value1, String value2) {
            addCriterion("breathArr not between", value1, value2, "breatharr");
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