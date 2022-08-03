package com.cs2001.group34.dto;


public class LoginDto {
    private Integer uID;
    private Boolean validated;
    private String t;

    public LoginDto(Integer uID, Boolean validated) {
        this.uID = uID;
        this.validated = validated;
    }

    public String getToken() {
        return t;
    }

    public void setToken(String token) {
        this.t = token;
    }

    public Integer getUserId() {
        return uID;
    }

    public void setUserId(Integer userId) {
        this.uID = userId;
    }

    public Boolean getValid() {
        return validated;
    }

    public void setValid(Boolean valid) {
        validated = valid;
    }
}
