package com.fit_web_app.models.dto;

import java.util.Date;

public class UserDataDTO {
    private Date date;
    private Integer height;
    private Integer weight;

    public UserDataDTO(Date date, Integer height, Integer weight) {
        this.date = date;
        this.height = height;
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
