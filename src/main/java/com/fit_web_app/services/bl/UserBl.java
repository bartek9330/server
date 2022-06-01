package com.fit_web_app.services.bl;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.RolesEntity;
import com.fit_web_app.models.entity.UsersEntity;

import java.util.List;

public interface UserBl {
    UsersEntity getUserByLogin(String login, String authorization) throws DevException;

    UsersEntity getUserByLogin(String login);

    UsersEntity registerUser(String login, String password, String email, int roleId) throws Exception;

    RolesEntity getRole(int roleId);

    void deleteUser(int userId) throws DevException;

    List<RolesEntity> getAvailableRoles();

    List<UsersEntity> getAllUsers();

    boolean checkUserIsAdministrator(String userName);
}
