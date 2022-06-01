package com.fit_web_app.services.bl;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.UsersDataEntity;
import com.fit_web_app.models.entity.UsersEntity;
import com.fit_web_app.services.dao.UserDataDao;
import com.fit_web_app.services.dao.UserDataDaoImpl;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class UserDataBlImpl implements UserDataBl {
    private final UserDataDao userDataDao;

    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public UserDataBlImpl() {
        userDataDao = new UserDataDaoImpl();
    }

    @Override
    public void saveBMI(Integer userId, Integer height, Integer weight, Date date) {
        userDataDao.saveBMI(new UsersDataEntity(height, weight, date, new UsersEntity(userId)));
    }

    @Override
    public List<UsersDataEntity> getMyBMI(Integer userId) throws DevException {
        if (userId == null) {
            throw new DevException(messages.getString("errorEmptyUserId"));
        }

        return userDataDao.getMyBMI(userId);
    }
}
