package com.fit_web_app.services.bl;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.UsersDataEntity;

import java.util.Date;
import java.util.List;

public interface UserDataBl {
    void saveBMI(Integer userId, Integer height, Integer weight, Date date);

    List<UsersDataEntity> getMyBMI(Integer userId) throws DevException;
}
