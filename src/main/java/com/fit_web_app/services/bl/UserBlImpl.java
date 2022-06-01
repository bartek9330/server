package com.fit_web_app.services.bl;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.RolesEntity;
import com.fit_web_app.models.entity.UsersEntity;
import com.fit_web_app.services.dao.UserDao;
import com.fit_web_app.services.dao.UserDaoImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.NoResultException;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class UserBlImpl implements UserBl {
    private final UserDao userDao;

    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public UserBlImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public UsersEntity getUserByLogin(String login, String authorization) throws DevException {
        try {
            if (authorization == null || authorization.trim().isEmpty()) {
                throw new DevException(messages.getString("errorEmptyToken"));
            }

            String payload = new String(Base64.getDecoder().decode(authorization.split("\\.")[1]));

            if (!payload.substring(payload.indexOf("sub\":") + 6, payload.indexOf("\",\"exp")).equals(login)) {
                throw new DevException(messages.getString("errorWrongToken"));
            }

            return userDao.getUserByLogin(login);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UsersEntity getUserByLogin(String login) {
        try {
            return userDao.getUserByLogin(login);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UsersEntity registerUser(String login, String password, String email, int roleId) throws Exception {
        if (!checkUserExist(login)) {
            throw new DevException(messages.getString("errorUserExist"));
        }

        if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            throw new DevException(messages.getString("errorEmptyDataRegisterForm"));
        }

        return userDao.registerUser(new UsersEntity(login, BCrypt.hashpw(password, BCrypt.gensalt(12)), email, new RolesEntity(roleId)));
    }

    private boolean checkUserExist(String login) {
        return getUserByLogin(login) == null;
    }

    @Override
    public RolesEntity getRole(int roleId) {
        return userDao.getUserRole(roleId);
    }


    @Override
    public void deleteUser(int userId) throws DevException {
        userDao.deleteUser(userId);
    }

    @Override
    public List<RolesEntity> getAvailableRoles() {
        return userDao.getAvailableRoles();
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean checkUserIsAdministrator(String userName) {
        return userDao.checkUserIsAdministrator(userName);
    }
}
