package com.sandman.film.dao.mysql.film.model.auto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public FilmExample() {
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

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
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

        public Criteria andFilmNameIsNull() {
            addCriterion("film_name is null");
            return (Criteria) this;
        }

        public Criteria andFilmNameIsNotNull() {
            addCriterion("film_name is not null");
            return (Criteria) this;
        }

        public Criteria andFilmNameEqualTo(String value) {
            addCriterion("film_name =", value, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameNotEqualTo(String value) {
            addCriterion("film_name <>", value, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameGreaterThan(String value) {
            addCriterion("film_name >", value, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameGreaterThanOrEqualTo(String value) {
            addCriterion("film_name >=", value, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameLessThan(String value) {
            addCriterion("film_name <", value, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameLessThanOrEqualTo(String value) {
            addCriterion("film_name <=", value, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameLike(String value) {
            addCriterion("film_name like", value, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameNotLike(String value) {
            addCriterion("film_name not like", value, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameIn(List<String> values) {
            addCriterion("film_name in", values, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameNotIn(List<String> values) {
            addCriterion("film_name not in", values, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameBetween(String value1, String value2) {
            addCriterion("film_name between", value1, value2, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmNameNotBetween(String value1, String value2) {
            addCriterion("film_name not between", value1, value2, "filmName");
            return (Criteria) this;
        }

        public Criteria andFilmUrlIsNull() {
            addCriterion("film_url is null");
            return (Criteria) this;
        }

        public Criteria andFilmUrlIsNotNull() {
            addCriterion("film_url is not null");
            return (Criteria) this;
        }

        public Criteria andFilmUrlEqualTo(String value) {
            addCriterion("film_url =", value, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlNotEqualTo(String value) {
            addCriterion("film_url <>", value, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlGreaterThan(String value) {
            addCriterion("film_url >", value, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlGreaterThanOrEqualTo(String value) {
            addCriterion("film_url >=", value, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlLessThan(String value) {
            addCriterion("film_url <", value, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlLessThanOrEqualTo(String value) {
            addCriterion("film_url <=", value, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlLike(String value) {
            addCriterion("film_url like", value, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlNotLike(String value) {
            addCriterion("film_url not like", value, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlIn(List<String> values) {
            addCriterion("film_url in", values, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlNotIn(List<String> values) {
            addCriterion("film_url not in", values, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlBetween(String value1, String value2) {
            addCriterion("film_url between", value1, value2, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andFilmUrlNotBetween(String value1, String value2) {
            addCriterion("film_url not between", value1, value2, "filmUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlIsNull() {
            addCriterion("magnet_url is null");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlIsNotNull() {
            addCriterion("magnet_url is not null");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlEqualTo(String value) {
            addCriterion("magnet_url =", value, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlNotEqualTo(String value) {
            addCriterion("magnet_url <>", value, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlGreaterThan(String value) {
            addCriterion("magnet_url >", value, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlGreaterThanOrEqualTo(String value) {
            addCriterion("magnet_url >=", value, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlLessThan(String value) {
            addCriterion("magnet_url <", value, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlLessThanOrEqualTo(String value) {
            addCriterion("magnet_url <=", value, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlLike(String value) {
            addCriterion("magnet_url like", value, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlNotLike(String value) {
            addCriterion("magnet_url not like", value, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlIn(List<String> values) {
            addCriterion("magnet_url in", values, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlNotIn(List<String> values) {
            addCriterion("magnet_url not in", values, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlBetween(String value1, String value2) {
            addCriterion("magnet_url between", value1, value2, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andMagnetUrlNotBetween(String value1, String value2) {
            addCriterion("magnet_url not between", value1, value2, "magnetUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlIsNull() {
            addCriterion("thunder_url is null");
            return (Criteria) this;
        }

        public Criteria andThunderUrlIsNotNull() {
            addCriterion("thunder_url is not null");
            return (Criteria) this;
        }

        public Criteria andThunderUrlEqualTo(String value) {
            addCriterion("thunder_url =", value, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlNotEqualTo(String value) {
            addCriterion("thunder_url <>", value, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlGreaterThan(String value) {
            addCriterion("thunder_url >", value, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlGreaterThanOrEqualTo(String value) {
            addCriterion("thunder_url >=", value, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlLessThan(String value) {
            addCriterion("thunder_url <", value, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlLessThanOrEqualTo(String value) {
            addCriterion("thunder_url <=", value, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlLike(String value) {
            addCriterion("thunder_url like", value, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlNotLike(String value) {
            addCriterion("thunder_url not like", value, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlIn(List<String> values) {
            addCriterion("thunder_url in", values, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlNotIn(List<String> values) {
            addCriterion("thunder_url not in", values, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlBetween(String value1, String value2) {
            addCriterion("thunder_url between", value1, value2, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andThunderUrlNotBetween(String value1, String value2) {
            addCriterion("thunder_url not between", value1, value2, "thunderUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlIsNull() {
            addCriterion("pan_url is null");
            return (Criteria) this;
        }

        public Criteria andPanUrlIsNotNull() {
            addCriterion("pan_url is not null");
            return (Criteria) this;
        }

        public Criteria andPanUrlEqualTo(String value) {
            addCriterion("pan_url =", value, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlNotEqualTo(String value) {
            addCriterion("pan_url <>", value, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlGreaterThan(String value) {
            addCriterion("pan_url >", value, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pan_url >=", value, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlLessThan(String value) {
            addCriterion("pan_url <", value, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlLessThanOrEqualTo(String value) {
            addCriterion("pan_url <=", value, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlLike(String value) {
            addCriterion("pan_url like", value, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlNotLike(String value) {
            addCriterion("pan_url not like", value, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlIn(List<String> values) {
            addCriterion("pan_url in", values, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlNotIn(List<String> values) {
            addCriterion("pan_url not in", values, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlBetween(String value1, String value2) {
            addCriterion("pan_url between", value1, value2, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanUrlNotBetween(String value1, String value2) {
            addCriterion("pan_url not between", value1, value2, "panUrl");
            return (Criteria) this;
        }

        public Criteria andPanPasswordIsNull() {
            addCriterion("pan_password is null");
            return (Criteria) this;
        }

        public Criteria andPanPasswordIsNotNull() {
            addCriterion("pan_password is not null");
            return (Criteria) this;
        }

        public Criteria andPanPasswordEqualTo(String value) {
            addCriterion("pan_password =", value, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordNotEqualTo(String value) {
            addCriterion("pan_password <>", value, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordGreaterThan(String value) {
            addCriterion("pan_password >", value, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("pan_password >=", value, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordLessThan(String value) {
            addCriterion("pan_password <", value, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordLessThanOrEqualTo(String value) {
            addCriterion("pan_password <=", value, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordLike(String value) {
            addCriterion("pan_password like", value, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordNotLike(String value) {
            addCriterion("pan_password not like", value, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordIn(List<String> values) {
            addCriterion("pan_password in", values, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordNotIn(List<String> values) {
            addCriterion("pan_password not in", values, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordBetween(String value1, String value2) {
            addCriterion("pan_password between", value1, value2, "panPassword");
            return (Criteria) this;
        }

        public Criteria andPanPasswordNotBetween(String value1, String value2) {
            addCriterion("pan_password not between", value1, value2, "panPassword");
            return (Criteria) this;
        }

        public Criteria andFilmImageIsNull() {
            addCriterion("film_image is null");
            return (Criteria) this;
        }

        public Criteria andFilmImageIsNotNull() {
            addCriterion("film_image is not null");
            return (Criteria) this;
        }

        public Criteria andFilmImageEqualTo(String value) {
            addCriterion("film_image =", value, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageNotEqualTo(String value) {
            addCriterion("film_image <>", value, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageGreaterThan(String value) {
            addCriterion("film_image >", value, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageGreaterThanOrEqualTo(String value) {
            addCriterion("film_image >=", value, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageLessThan(String value) {
            addCriterion("film_image <", value, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageLessThanOrEqualTo(String value) {
            addCriterion("film_image <=", value, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageLike(String value) {
            addCriterion("film_image like", value, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageNotLike(String value) {
            addCriterion("film_image not like", value, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageIn(List<String> values) {
            addCriterion("film_image in", values, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageNotIn(List<String> values) {
            addCriterion("film_image not in", values, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageBetween(String value1, String value2) {
            addCriterion("film_image between", value1, value2, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmImageNotBetween(String value1, String value2) {
            addCriterion("film_image not between", value1, value2, "filmImage");
            return (Criteria) this;
        }

        public Criteria andFilmActorIsNull() {
            addCriterion("film_actor is null");
            return (Criteria) this;
        }

        public Criteria andFilmActorIsNotNull() {
            addCriterion("film_actor is not null");
            return (Criteria) this;
        }

        public Criteria andFilmActorEqualTo(String value) {
            addCriterion("film_actor =", value, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorNotEqualTo(String value) {
            addCriterion("film_actor <>", value, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorGreaterThan(String value) {
            addCriterion("film_actor >", value, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorGreaterThanOrEqualTo(String value) {
            addCriterion("film_actor >=", value, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorLessThan(String value) {
            addCriterion("film_actor <", value, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorLessThanOrEqualTo(String value) {
            addCriterion("film_actor <=", value, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorLike(String value) {
            addCriterion("film_actor like", value, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorNotLike(String value) {
            addCriterion("film_actor not like", value, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorIn(List<String> values) {
            addCriterion("film_actor in", values, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorNotIn(List<String> values) {
            addCriterion("film_actor not in", values, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorBetween(String value1, String value2) {
            addCriterion("film_actor between", value1, value2, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmActorNotBetween(String value1, String value2) {
            addCriterion("film_actor not between", value1, value2, "filmActor");
            return (Criteria) this;
        }

        public Criteria andFilmTypeIsNull() {
            addCriterion("film_type is null");
            return (Criteria) this;
        }

        public Criteria andFilmTypeIsNotNull() {
            addCriterion("film_type is not null");
            return (Criteria) this;
        }

        public Criteria andFilmTypeEqualTo(Integer value) {
            addCriterion("film_type =", value, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeNotEqualTo(Integer value) {
            addCriterion("film_type <>", value, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeGreaterThan(Integer value) {
            addCriterion("film_type >", value, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("film_type >=", value, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeLessThan(Integer value) {
            addCriterion("film_type <", value, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeLessThanOrEqualTo(Integer value) {
            addCriterion("film_type <=", value, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeIn(List<Integer> values) {
            addCriterion("film_type in", values, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeNotIn(List<Integer> values) {
            addCriterion("film_type not in", values, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeBetween(Integer value1, Integer value2) {
            addCriterion("film_type between", value1, value2, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("film_type not between", value1, value2, "filmType");
            return (Criteria) this;
        }

        public Criteria andFilmTimeIsNull() {
            addCriterion("film_time is null");
            return (Criteria) this;
        }

        public Criteria andFilmTimeIsNotNull() {
            addCriterion("film_time is not null");
            return (Criteria) this;
        }

        public Criteria andFilmTimeEqualTo(String value) {
            addCriterion("film_time =", value, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeNotEqualTo(String value) {
            addCriterion("film_time <>", value, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeGreaterThan(String value) {
            addCriterion("film_time >", value, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeGreaterThanOrEqualTo(String value) {
            addCriterion("film_time >=", value, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeLessThan(String value) {
            addCriterion("film_time <", value, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeLessThanOrEqualTo(String value) {
            addCriterion("film_time <=", value, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeLike(String value) {
            addCriterion("film_time like", value, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeNotLike(String value) {
            addCriterion("film_time not like", value, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeIn(List<String> values) {
            addCriterion("film_time in", values, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeNotIn(List<String> values) {
            addCriterion("film_time not in", values, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeBetween(String value1, String value2) {
            addCriterion("film_time between", value1, value2, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmTimeNotBetween(String value1, String value2) {
            addCriterion("film_time not between", value1, value2, "filmTime");
            return (Criteria) this;
        }

        public Criteria andFilmClarityIsNull() {
            addCriterion("film_clarity is null");
            return (Criteria) this;
        }

        public Criteria andFilmClarityIsNotNull() {
            addCriterion("film_clarity is not null");
            return (Criteria) this;
        }

        public Criteria andFilmClarityEqualTo(String value) {
            addCriterion("film_clarity =", value, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityNotEqualTo(String value) {
            addCriterion("film_clarity <>", value, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityGreaterThan(String value) {
            addCriterion("film_clarity >", value, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityGreaterThanOrEqualTo(String value) {
            addCriterion("film_clarity >=", value, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityLessThan(String value) {
            addCriterion("film_clarity <", value, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityLessThanOrEqualTo(String value) {
            addCriterion("film_clarity <=", value, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityLike(String value) {
            addCriterion("film_clarity like", value, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityNotLike(String value) {
            addCriterion("film_clarity not like", value, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityIn(List<String> values) {
            addCriterion("film_clarity in", values, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityNotIn(List<String> values) {
            addCriterion("film_clarity not in", values, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityBetween(String value1, String value2) {
            addCriterion("film_clarity between", value1, value2, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmClarityNotBetween(String value1, String value2) {
            addCriterion("film_clarity not between", value1, value2, "filmClarity");
            return (Criteria) this;
        }

        public Criteria andFilmAreaIsNull() {
            addCriterion("film_area is null");
            return (Criteria) this;
        }

        public Criteria andFilmAreaIsNotNull() {
            addCriterion("film_area is not null");
            return (Criteria) this;
        }

        public Criteria andFilmAreaEqualTo(String value) {
            addCriterion("film_area =", value, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaNotEqualTo(String value) {
            addCriterion("film_area <>", value, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaGreaterThan(String value) {
            addCriterion("film_area >", value, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaGreaterThanOrEqualTo(String value) {
            addCriterion("film_area >=", value, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaLessThan(String value) {
            addCriterion("film_area <", value, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaLessThanOrEqualTo(String value) {
            addCriterion("film_area <=", value, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaLike(String value) {
            addCriterion("film_area like", value, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaNotLike(String value) {
            addCriterion("film_area not like", value, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaIn(List<String> values) {
            addCriterion("film_area in", values, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaNotIn(List<String> values) {
            addCriterion("film_area not in", values, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaBetween(String value1, String value2) {
            addCriterion("film_area between", value1, value2, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmAreaNotBetween(String value1, String value2) {
            addCriterion("film_area not between", value1, value2, "filmArea");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorIsNull() {
            addCriterion("film_director is null");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorIsNotNull() {
            addCriterion("film_director is not null");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorEqualTo(String value) {
            addCriterion("film_director =", value, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorNotEqualTo(String value) {
            addCriterion("film_director <>", value, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorGreaterThan(String value) {
            addCriterion("film_director >", value, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorGreaterThanOrEqualTo(String value) {
            addCriterion("film_director >=", value, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorLessThan(String value) {
            addCriterion("film_director <", value, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorLessThanOrEqualTo(String value) {
            addCriterion("film_director <=", value, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorLike(String value) {
            addCriterion("film_director like", value, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorNotLike(String value) {
            addCriterion("film_director not like", value, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorIn(List<String> values) {
            addCriterion("film_director in", values, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorNotIn(List<String> values) {
            addCriterion("film_director not in", values, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorBetween(String value1, String value2) {
            addCriterion("film_director between", value1, value2, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmDirectorNotBetween(String value1, String value2) {
            addCriterion("film_director not between", value1, value2, "filmDirector");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageIsNull() {
            addCriterion("film_language is null");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageIsNotNull() {
            addCriterion("film_language is not null");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageEqualTo(String value) {
            addCriterion("film_language =", value, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageNotEqualTo(String value) {
            addCriterion("film_language <>", value, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageGreaterThan(String value) {
            addCriterion("film_language >", value, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("film_language >=", value, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageLessThan(String value) {
            addCriterion("film_language <", value, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageLessThanOrEqualTo(String value) {
            addCriterion("film_language <=", value, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageLike(String value) {
            addCriterion("film_language like", value, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageNotLike(String value) {
            addCriterion("film_language not like", value, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageIn(List<String> values) {
            addCriterion("film_language in", values, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageNotIn(List<String> values) {
            addCriterion("film_language not in", values, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageBetween(String value1, String value2) {
            addCriterion("film_language between", value1, value2, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmLanguageNotBetween(String value1, String value2) {
            addCriterion("film_language not between", value1, value2, "filmLanguage");
            return (Criteria) this;
        }

        public Criteria andFilmDescIsNull() {
            addCriterion("film_desc is null");
            return (Criteria) this;
        }

        public Criteria andFilmDescIsNotNull() {
            addCriterion("film_desc is not null");
            return (Criteria) this;
        }

        public Criteria andFilmDescEqualTo(String value) {
            addCriterion("film_desc =", value, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescNotEqualTo(String value) {
            addCriterion("film_desc <>", value, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescGreaterThan(String value) {
            addCriterion("film_desc >", value, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescGreaterThanOrEqualTo(String value) {
            addCriterion("film_desc >=", value, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescLessThan(String value) {
            addCriterion("film_desc <", value, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescLessThanOrEqualTo(String value) {
            addCriterion("film_desc <=", value, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescLike(String value) {
            addCriterion("film_desc like", value, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescNotLike(String value) {
            addCriterion("film_desc not like", value, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescIn(List<String> values) {
            addCriterion("film_desc in", values, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescNotIn(List<String> values) {
            addCriterion("film_desc not in", values, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescBetween(String value1, String value2) {
            addCriterion("film_desc between", value1, value2, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmDescNotBetween(String value1, String value2) {
            addCriterion("film_desc not between", value1, value2, "filmDesc");
            return (Criteria) this;
        }

        public Criteria andFilmGoldIsNull() {
            addCriterion("film_gold is null");
            return (Criteria) this;
        }

        public Criteria andFilmGoldIsNotNull() {
            addCriterion("film_gold is not null");
            return (Criteria) this;
        }

        public Criteria andFilmGoldEqualTo(Integer value) {
            addCriterion("film_gold =", value, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldNotEqualTo(Integer value) {
            addCriterion("film_gold <>", value, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldGreaterThan(Integer value) {
            addCriterion("film_gold >", value, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("film_gold >=", value, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldLessThan(Integer value) {
            addCriterion("film_gold <", value, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldLessThanOrEqualTo(Integer value) {
            addCriterion("film_gold <=", value, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldIn(List<Integer> values) {
            addCriterion("film_gold in", values, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldNotIn(List<Integer> values) {
            addCriterion("film_gold not in", values, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldBetween(Integer value1, Integer value2) {
            addCriterion("film_gold between", value1, value2, "filmGold");
            return (Criteria) this;
        }

        public Criteria andFilmGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("film_gold not between", value1, value2, "filmGold");
            return (Criteria) this;
        }

        public Criteria andBuyCountIsNull() {
            addCriterion("buy_count is null");
            return (Criteria) this;
        }

        public Criteria andBuyCountIsNotNull() {
            addCriterion("buy_count is not null");
            return (Criteria) this;
        }

        public Criteria andBuyCountEqualTo(Integer value) {
            addCriterion("buy_count =", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotEqualTo(Integer value) {
            addCriterion("buy_count <>", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountGreaterThan(Integer value) {
            addCriterion("buy_count >", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_count >=", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLessThan(Integer value) {
            addCriterion("buy_count <", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLessThanOrEqualTo(Integer value) {
            addCriterion("buy_count <=", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountIn(List<Integer> values) {
            addCriterion("buy_count in", values, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotIn(List<Integer> values) {
            addCriterion("buy_count not in", values, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountBetween(Integer value1, Integer value2) {
            addCriterion("buy_count between", value1, value2, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_count not between", value1, value2, "buyCount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
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