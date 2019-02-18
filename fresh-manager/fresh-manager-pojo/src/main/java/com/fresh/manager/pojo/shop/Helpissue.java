package com.fresh.manager.pojo.shop;

public class Helpissue {
    /**
     * 
     */
    private Integer id;

    /**
     * 问题分类
     */
    private Short typeId;

    /**
     * 
     */
    private String question;

    /**
     * 
     */
    private String answer;

    /**
     * 排序
     */
    private Short sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getTypeId() {
        return typeId;
    }

    public void setTypeId(Short typeId) {
        this.typeId = typeId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }
}