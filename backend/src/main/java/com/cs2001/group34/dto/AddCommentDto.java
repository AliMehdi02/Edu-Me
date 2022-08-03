package com.cs2001.group34.dto;

public class AddCommentDto {

    private Integer guideId;
    private String comment;
    private Integer userId;

    public AddCommentDto() {
    }

    public AddCommentDto(Integer guideId, String comment, Integer userId) {
        this.guideId = guideId;
        this.comment = comment;
        this.userId = userId;
    }

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
