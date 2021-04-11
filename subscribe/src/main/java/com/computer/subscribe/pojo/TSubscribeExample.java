package com.computer.subscribe.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSubscribeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public TSubscribeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andApplicantIsNull() {
            addCriterion("applicant is null");
            return (Criteria) this;
        }

        public Criteria andApplicantIsNotNull() {
            addCriterion("applicant is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantEqualTo(Long value) {
            addCriterion("applicant =", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotEqualTo(Long value) {
            addCriterion("applicant <>", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantGreaterThan(Long value) {
            addCriterion("applicant >", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantGreaterThanOrEqualTo(Long value) {
            addCriterion("applicant >=", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLessThan(Long value) {
            addCriterion("applicant <", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantLessThanOrEqualTo(Long value) {
            addCriterion("applicant <=", value, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantIn(List<Long> values) {
            addCriterion("applicant in", values, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotIn(List<Long> values) {
            addCriterion("applicant not in", values, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantBetween(Long value1, Long value2) {
            addCriterion("applicant between", value1, value2, "applicant");
            return (Criteria) this;
        }

        public Criteria andApplicantNotBetween(Long value1, Long value2) {
            addCriterion("applicant not between", value1, value2, "applicant");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNull() {
            addCriterion("reviewer is null");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNotNull() {
            addCriterion("reviewer is not null");
            return (Criteria) this;
        }

        public Criteria andReviewerEqualTo(Long value) {
            addCriterion("reviewer =", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotEqualTo(Long value) {
            addCriterion("reviewer <>", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThan(Long value) {
            addCriterion("reviewer >", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThanOrEqualTo(Long value) {
            addCriterion("reviewer >=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThan(Long value) {
            addCriterion("reviewer <", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThanOrEqualTo(Long value) {
            addCriterion("reviewer <=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerIn(List<Long> values) {
            addCriterion("reviewer in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotIn(List<Long> values) {
            addCriterion("reviewer not in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerBetween(Long value1, Long value2) {
            addCriterion("reviewer between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotBetween(Long value1, Long value2) {
            addCriterion("reviewer not between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusIsNull() {
            addCriterion("subscribe_status is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusIsNotNull() {
            addCriterion("subscribe_status is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusEqualTo(Integer value) {
            addCriterion("subscribe_status =", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusNotEqualTo(Integer value) {
            addCriterion("subscribe_status <>", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusGreaterThan(Integer value) {
            addCriterion("subscribe_status >", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("subscribe_status >=", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusLessThan(Integer value) {
            addCriterion("subscribe_status <", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("subscribe_status <=", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusIn(List<Integer> values) {
            addCriterion("subscribe_status in", values, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusNotIn(List<Integer> values) {
            addCriterion("subscribe_status not in", values, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusBetween(Integer value1, Integer value2) {
            addCriterion("subscribe_status between", value1, value2, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("subscribe_status not between", value1, value2, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andRoomNumIsNull() {
            addCriterion("room_num is null");
            return (Criteria) this;
        }

        public Criteria andRoomNumIsNotNull() {
            addCriterion("room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNumEqualTo(Integer value) {
            addCriterion("room_num =", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotEqualTo(Integer value) {
            addCriterion("room_num <>", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumGreaterThan(Integer value) {
            addCriterion("room_num >", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_num >=", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumLessThan(Integer value) {
            addCriterion("room_num <", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("room_num <=", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumIn(List<Integer> values) {
            addCriterion("room_num in", values, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotIn(List<Integer> values) {
            addCriterion("room_num not in", values, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("room_num between", value1, value2, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("room_num not between", value1, value2, "roomNum");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeIsNull() {
            addCriterion("application_start_time is null");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeIsNotNull() {
            addCriterion("application_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeEqualTo(Date value) {
            addCriterion("application_start_time =", value, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeNotEqualTo(Date value) {
            addCriterion("application_start_time <>", value, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeGreaterThan(Date value) {
            addCriterion("application_start_time >", value, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("application_start_time >=", value, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeLessThan(Date value) {
            addCriterion("application_start_time <", value, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("application_start_time <=", value, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeIn(List<Date> values) {
            addCriterion("application_start_time in", values, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeNotIn(List<Date> values) {
            addCriterion("application_start_time not in", values, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeBetween(Date value1, Date value2) {
            addCriterion("application_start_time between", value1, value2, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andApplicationStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("application_start_time not between", value1, value2, "applicationStartTime");
            return (Criteria) this;
        }

        public Criteria andUseIntervalIsNull() {
            addCriterion("use_interval is null");
            return (Criteria) this;
        }

        public Criteria andUseIntervalIsNotNull() {
            addCriterion("use_interval is not null");
            return (Criteria) this;
        }

        public Criteria andUseIntervalEqualTo(Integer value) {
            addCriterion("use_interval =", value, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalNotEqualTo(Integer value) {
            addCriterion("use_interval <>", value, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalGreaterThan(Integer value) {
            addCriterion("use_interval >", value, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_interval >=", value, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalLessThan(Integer value) {
            addCriterion("use_interval <", value, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalLessThanOrEqualTo(Integer value) {
            addCriterion("use_interval <=", value, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalIn(List<Integer> values) {
            addCriterion("use_interval in", values, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalNotIn(List<Integer> values) {
            addCriterion("use_interval not in", values, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalBetween(Integer value1, Integer value2) {
            addCriterion("use_interval between", value1, value2, "useInterval");
            return (Criteria) this;
        }

        public Criteria andUseIntervalNotBetween(Integer value1, Integer value2) {
            addCriterion("use_interval not between", value1, value2, "useInterval");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNull() {
            addCriterion("handle_time is null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNotNull() {
            addCriterion("handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeEqualTo(Date value) {
            addCriterion("handle_time =", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotEqualTo(Date value) {
            addCriterion("handle_time <>", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThan(Date value) {
            addCriterion("handle_time >", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("handle_time >=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThan(Date value) {
            addCriterion("handle_time <", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThanOrEqualTo(Date value) {
            addCriterion("handle_time <=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIn(List<Date> values) {
            addCriterion("handle_time in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotIn(List<Date> values) {
            addCriterion("handle_time not in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeBetween(Date value1, Date value2) {
            addCriterion("handle_time between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotBetween(Date value1, Date value2) {
            addCriterion("handle_time not between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateIsNull() {
            addCriterion("apply_use_date is null");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateIsNotNull() {
            addCriterion("apply_use_date is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateEqualTo(Date value) {
            addCriterion("apply_use_date =", value, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateNotEqualTo(Date value) {
            addCriterion("apply_use_date <>", value, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateGreaterThan(Date value) {
            addCriterion("apply_use_date >", value, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_use_date >=", value, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateLessThan(Date value) {
            addCriterion("apply_use_date <", value, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateLessThanOrEqualTo(Date value) {
            addCriterion("apply_use_date <=", value, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateIn(List<Date> values) {
            addCriterion("apply_use_date in", values, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateNotIn(List<Date> values) {
            addCriterion("apply_use_date not in", values, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateBetween(Date value1, Date value2) {
            addCriterion("apply_use_date between", value1, value2, "applyUseDate");
            return (Criteria) this;
        }

        public Criteria andApplyUseDateNotBetween(Date value1, Date value2) {
            addCriterion("apply_use_date not between", value1, value2, "applyUseDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_subscribe
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 11 13:28:41 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_subscribe
     *
     * @mbggenerated Sun Apr 11 13:28:41 CST 2021
     */
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