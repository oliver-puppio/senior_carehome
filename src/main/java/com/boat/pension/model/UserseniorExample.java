package com.boat.pension.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserseniorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserseniorExample() {
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

        public Criteria andUseridIsNull() {
            addCriterion("userID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userID not between", value1, value2, "userid");
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

        public Criteria andBindtimeIsNull() {
            addCriterion("bindTime is null");
            return (Criteria) this;
        }

        public Criteria andBindtimeIsNotNull() {
            addCriterion("bindTime is not null");
            return (Criteria) this;
        }

        public Criteria andBindtimeEqualTo(Date value) {
            addCriterion("bindTime =", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeNotEqualTo(Date value) {
            addCriterion("bindTime <>", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeGreaterThan(Date value) {
            addCriterion("bindTime >", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bindTime >=", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeLessThan(Date value) {
            addCriterion("bindTime <", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeLessThanOrEqualTo(Date value) {
            addCriterion("bindTime <=", value, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeIn(List<Date> values) {
            addCriterion("bindTime in", values, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeNotIn(List<Date> values) {
            addCriterion("bindTime not in", values, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeBetween(Date value1, Date value2) {
            addCriterion("bindTime between", value1, value2, "bindtime");
            return (Criteria) this;
        }

        public Criteria andBindtimeNotBetween(Date value1, Date value2) {
            addCriterion("bindTime not between", value1, value2, "bindtime");
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