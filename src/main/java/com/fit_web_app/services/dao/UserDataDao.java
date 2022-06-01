package com.fit_web_app.services.dao;

import com.fit_web_app.models.entity.UsersDataEntity;

import java.util.List;

public interface UserDataDao {
    void saveBMI(UsersDataEntity usersDataEntity);

    List<UsersDataEntity> getMyBMI(Integer userId);
}
