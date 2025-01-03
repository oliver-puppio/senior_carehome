package com.boat.pension.model;

import java.util.ArrayList;
import java.util.List;

public class RoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoomExample() {
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

        public Criteria andInstnameIsNull() {
            addCriterion("instName is null");
            return (Criteria) this;
        }

        public Criteria andInstnameIsNotNull() {
            addCriterion("instName is not null");
            return (Criteria) this;
        }

        public Criteria andInstnameEqualTo(String value) {
            addCriterion("instName =", value, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameNotEqualTo(String value) {
            addCriterion("instName <>", value, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameGreaterThan(String value) {
            addCriterion("instName >", value, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameGreaterThanOrEqualTo(String value) {
            addCriterion("instName >=", value, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameLessThan(String value) {
            addCriterion("instName <", value, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameLessThanOrEqualTo(String value) {
            addCriterion("instName <=", value, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameLike(String value) {
            addCriterion("instName like", value, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameNotLike(String value) {
            addCriterion("instName not like", value, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameIn(List<String> values) {
            addCriterion("instName in", values, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameNotIn(List<String> values) {
            addCriterion("instName not in", values, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameBetween(String value1, String value2) {
            addCriterion("instName between", value1, value2, "instname");
            return (Criteria) this;
        }

        public Criteria andInstnameNotBetween(String value1, String value2) {
            addCriterion("instName not between", value1, value2, "instname");
            return (Criteria) this;
        }

        public Criteria andWokeridIsNull() {
            addCriterion("wokerID is null");
            return (Criteria) this;
        }

        public Criteria andWokeridIsNotNull() {
            addCriterion("wokerID is not null");
            return (Criteria) this;
        }

        public Criteria andWokeridEqualTo(Integer value) {
            addCriterion("wokerID =", value, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridNotEqualTo(Integer value) {
            addCriterion("wokerID <>", value, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridGreaterThan(Integer value) {
            addCriterion("wokerID >", value, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridGreaterThanOrEqualTo(Integer value) {
            addCriterion("wokerID >=", value, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridLessThan(Integer value) {
            addCriterion("wokerID <", value, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridLessThanOrEqualTo(Integer value) {
            addCriterion("wokerID <=", value, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridIn(List<Integer> values) {
            addCriterion("wokerID in", values, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridNotIn(List<Integer> values) {
            addCriterion("wokerID not in", values, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridBetween(Integer value1, Integer value2) {
            addCriterion("wokerID between", value1, value2, "wokerid");
            return (Criteria) this;
        }

        public Criteria andWokeridNotBetween(Integer value1, Integer value2) {
            addCriterion("wokerID not between", value1, value2, "wokerid");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andBuildingnameIsNull() {
            addCriterion("buildingName is null");
            return (Criteria) this;
        }

        public Criteria andBuildingnameIsNotNull() {
            addCriterion("buildingName is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingnameEqualTo(String value) {
            addCriterion("buildingName =", value, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameNotEqualTo(String value) {
            addCriterion("buildingName <>", value, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameGreaterThan(String value) {
            addCriterion("buildingName >", value, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameGreaterThanOrEqualTo(String value) {
            addCriterion("buildingName >=", value, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameLessThan(String value) {
            addCriterion("buildingName <", value, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameLessThanOrEqualTo(String value) {
            addCriterion("buildingName <=", value, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameLike(String value) {
            addCriterion("buildingName like", value, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameNotLike(String value) {
            addCriterion("buildingName not like", value, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameIn(List<String> values) {
            addCriterion("buildingName in", values, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameNotIn(List<String> values) {
            addCriterion("buildingName not in", values, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameBetween(String value1, String value2) {
            addCriterion("buildingName between", value1, value2, "buildingname");
            return (Criteria) this;
        }

        public Criteria andBuildingnameNotBetween(String value1, String value2) {
            addCriterion("buildingName not between", value1, value2, "buildingname");
            return (Criteria) this;
        }

        public Criteria andFloorIsNull() {
            addCriterion("floor is null");
            return (Criteria) this;
        }

        public Criteria andFloorIsNotNull() {
            addCriterion("floor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEqualTo(Integer value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(Integer value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(Integer value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(Integer value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(Integer value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<Integer> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<Integer> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(Integer value1, Integer value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(Integer value1, Integer value2) {
            addCriterion("floor not between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Integer value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Integer value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Integer value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Integer value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Integer value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Integer> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Integer> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Integer value1, Integer value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("size not between", value1, value2, "size");
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