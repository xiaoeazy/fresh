package com.fresh.api.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupcolonelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupcolonelExample() {
        oredCriteria = new ArrayList<Criteria>();
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

        public Criteria andColonelGroupIdIsNull() {
            addCriterion("colonel_group_id is null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdIsNotNull() {
            addCriterion("colonel_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdEqualTo(Integer value) {
            addCriterion("colonel_group_id =", value, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdNotEqualTo(Integer value) {
            addCriterion("colonel_group_id <>", value, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdGreaterThan(Integer value) {
            addCriterion("colonel_group_id >", value, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("colonel_group_id >=", value, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdLessThan(Integer value) {
            addCriterion("colonel_group_id <", value, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("colonel_group_id <=", value, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdIn(List<Integer> values) {
            addCriterion("colonel_group_id in", values, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdNotIn(List<Integer> values) {
            addCriterion("colonel_group_id not in", values, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("colonel_group_id between", value1, value2, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("colonel_group_id not between", value1, value2, "colonelGroupId");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeIsNull() {
            addCriterion("colonel_group_type is null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeIsNotNull() {
            addCriterion("colonel_group_type is not null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeEqualTo(Short value) {
            addCriterion("colonel_group_type =", value, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeNotEqualTo(Short value) {
            addCriterion("colonel_group_type <>", value, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeGreaterThan(Short value) {
            addCriterion("colonel_group_type >", value, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("colonel_group_type >=", value, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeLessThan(Short value) {
            addCriterion("colonel_group_type <", value, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeLessThanOrEqualTo(Short value) {
            addCriterion("colonel_group_type <=", value, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeIn(List<Short> values) {
            addCriterion("colonel_group_type in", values, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeNotIn(List<Short> values) {
            addCriterion("colonel_group_type not in", values, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeBetween(Short value1, Short value2) {
            addCriterion("colonel_group_type between", value1, value2, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupTypeNotBetween(Short value1, Short value2) {
            addCriterion("colonel_group_type not between", value1, value2, "colonelGroupType");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnIsNull() {
            addCriterion("colonel_group_sn is null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnIsNotNull() {
            addCriterion("colonel_group_sn is not null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnEqualTo(String value) {
            addCriterion("colonel_group_sn =", value, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnNotEqualTo(String value) {
            addCriterion("colonel_group_sn <>", value, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnGreaterThan(String value) {
            addCriterion("colonel_group_sn >", value, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnGreaterThanOrEqualTo(String value) {
            addCriterion("colonel_group_sn >=", value, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnLessThan(String value) {
            addCriterion("colonel_group_sn <", value, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnLessThanOrEqualTo(String value) {
            addCriterion("colonel_group_sn <=", value, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnLike(String value) {
            addCriterion("colonel_group_sn like", value, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnNotLike(String value) {
            addCriterion("colonel_group_sn not like", value, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnIn(List<String> values) {
            addCriterion("colonel_group_sn in", values, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnNotIn(List<String> values) {
            addCriterion("colonel_group_sn not in", values, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnBetween(String value1, String value2) {
            addCriterion("colonel_group_sn between", value1, value2, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupSnNotBetween(String value1, String value2) {
            addCriterion("colonel_group_sn not between", value1, value2, "colonelGroupSn");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameIsNull() {
            addCriterion("colonel_group_name is null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameIsNotNull() {
            addCriterion("colonel_group_name is not null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameEqualTo(String value) {
            addCriterion("colonel_group_name =", value, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameNotEqualTo(String value) {
            addCriterion("colonel_group_name <>", value, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameGreaterThan(String value) {
            addCriterion("colonel_group_name >", value, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("colonel_group_name >=", value, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameLessThan(String value) {
            addCriterion("colonel_group_name <", value, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameLessThanOrEqualTo(String value) {
            addCriterion("colonel_group_name <=", value, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameLike(String value) {
            addCriterion("colonel_group_name like", value, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameNotLike(String value) {
            addCriterion("colonel_group_name not like", value, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameIn(List<String> values) {
            addCriterion("colonel_group_name in", values, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameNotIn(List<String> values) {
            addCriterion("colonel_group_name not in", values, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameBetween(String value1, String value2) {
            addCriterion("colonel_group_name between", value1, value2, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andColonelGroupNameNotBetween(String value1, String value2) {
            addCriterion("colonel_group_name not between", value1, value2, "colonelGroupName");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeIsNull() {
            addCriterion("group_start_time is null");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeIsNotNull() {
            addCriterion("group_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeEqualTo(Date value) {
            addCriterion("group_start_time =", value, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeNotEqualTo(Date value) {
            addCriterion("group_start_time <>", value, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeGreaterThan(Date value) {
            addCriterion("group_start_time >", value, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("group_start_time >=", value, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeLessThan(Date value) {
            addCriterion("group_start_time <", value, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("group_start_time <=", value, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeIn(List<Date> values) {
            addCriterion("group_start_time in", values, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeNotIn(List<Date> values) {
            addCriterion("group_start_time not in", values, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeBetween(Date value1, Date value2) {
            addCriterion("group_start_time between", value1, value2, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("group_start_time not between", value1, value2, "groupStartTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeIsNull() {
            addCriterion("group_end_time is null");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeIsNotNull() {
            addCriterion("group_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeEqualTo(Date value) {
            addCriterion("group_end_time =", value, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeNotEqualTo(Date value) {
            addCriterion("group_end_time <>", value, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeGreaterThan(Date value) {
            addCriterion("group_end_time >", value, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("group_end_time >=", value, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeLessThan(Date value) {
            addCriterion("group_end_time <", value, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("group_end_time <=", value, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeIn(List<Date> values) {
            addCriterion("group_end_time in", values, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeNotIn(List<Date> values) {
            addCriterion("group_end_time not in", values, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeBetween(Date value1, Date value2) {
            addCriterion("group_end_time between", value1, value2, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("group_end_time not between", value1, value2, "groupEndTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeIsNull() {
            addCriterion("group_delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeIsNotNull() {
            addCriterion("group_delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeEqualTo(Date value) {
            addCriterion("group_delivery_time =", value, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeNotEqualTo(Date value) {
            addCriterion("group_delivery_time <>", value, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeGreaterThan(Date value) {
            addCriterion("group_delivery_time >", value, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("group_delivery_time >=", value, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeLessThan(Date value) {
            addCriterion("group_delivery_time <", value, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("group_delivery_time <=", value, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeIn(List<Date> values) {
            addCriterion("group_delivery_time in", values, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeNotIn(List<Date> values) {
            addCriterion("group_delivery_time not in", values, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("group_delivery_time between", value1, value2, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("group_delivery_time not between", value1, value2, "groupDeliveryTime");
            return (Criteria) this;
        }

        public Criteria andGroupAddressIsNull() {
            addCriterion("group_address is null");
            return (Criteria) this;
        }

        public Criteria andGroupAddressIsNotNull() {
            addCriterion("group_address is not null");
            return (Criteria) this;
        }

        public Criteria andGroupAddressEqualTo(String value) {
            addCriterion("group_address =", value, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressNotEqualTo(String value) {
            addCriterion("group_address <>", value, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressGreaterThan(String value) {
            addCriterion("group_address >", value, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressGreaterThanOrEqualTo(String value) {
            addCriterion("group_address >=", value, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressLessThan(String value) {
            addCriterion("group_address <", value, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressLessThanOrEqualTo(String value) {
            addCriterion("group_address <=", value, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressLike(String value) {
            addCriterion("group_address like", value, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressNotLike(String value) {
            addCriterion("group_address not like", value, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressIn(List<String> values) {
            addCriterion("group_address in", values, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressNotIn(List<String> values) {
            addCriterion("group_address not in", values, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressBetween(String value1, String value2) {
            addCriterion("group_address between", value1, value2, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andGroupAddressNotBetween(String value1, String value2) {
            addCriterion("group_address not between", value1, value2, "groupAddress");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeIsNull() {
            addCriterion("pick_up_time is null");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeIsNotNull() {
            addCriterion("pick_up_time is not null");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeEqualTo(Date value) {
            addCriterion("pick_up_time =", value, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeNotEqualTo(Date value) {
            addCriterion("pick_up_time <>", value, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeGreaterThan(Date value) {
            addCriterion("pick_up_time >", value, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pick_up_time >=", value, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeLessThan(Date value) {
            addCriterion("pick_up_time <", value, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeLessThanOrEqualTo(Date value) {
            addCriterion("pick_up_time <=", value, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeIn(List<Date> values) {
            addCriterion("pick_up_time in", values, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeNotIn(List<Date> values) {
            addCriterion("pick_up_time not in", values, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeBetween(Date value1, Date value2) {
            addCriterion("pick_up_time between", value1, value2, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andPickUpTimeNotBetween(Date value1, Date value2) {
            addCriterion("pick_up_time not between", value1, value2, "pickUpTime");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentIsNull() {
            addCriterion("colonel_group_content is null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentIsNotNull() {
            addCriterion("colonel_group_content is not null");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentEqualTo(String value) {
            addCriterion("colonel_group_content =", value, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentNotEqualTo(String value) {
            addCriterion("colonel_group_content <>", value, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentGreaterThan(String value) {
            addCriterion("colonel_group_content >", value, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentGreaterThanOrEqualTo(String value) {
            addCriterion("colonel_group_content >=", value, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentLessThan(String value) {
            addCriterion("colonel_group_content <", value, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentLessThanOrEqualTo(String value) {
            addCriterion("colonel_group_content <=", value, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentLike(String value) {
            addCriterion("colonel_group_content like", value, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentNotLike(String value) {
            addCriterion("colonel_group_content not like", value, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentIn(List<String> values) {
            addCriterion("colonel_group_content in", values, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentNotIn(List<String> values) {
            addCriterion("colonel_group_content not in", values, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentBetween(String value1, String value2) {
            addCriterion("colonel_group_content between", value1, value2, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andColonelGroupContentNotBetween(String value1, String value2) {
            addCriterion("colonel_group_content not between", value1, value2, "colonelGroupContent");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Boolean value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Boolean value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Boolean value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Boolean value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Boolean value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Boolean> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Boolean> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Boolean value1, Boolean value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("state not between", value1, value2, "state");
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