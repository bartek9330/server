package com.fit_web_app.services.dao;

import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.RolesEntity;
import com.fit_web_app.models.entity.UsersEntity;

import javax.persistence.NoResultException;
import java.util.List;

public interface UserDao {
    UsersEntity getUserByLogin(String login) throws NoResultException;

    UsersEntity registerUser(UsersEntity usersEntity);

    void deleteUser(int userId) throws DevException;

    RolesEntity getUserRole(int roleId);

    List<RolesEntity> getAvailableRoles();

    List<UsersEntity> getAllUsers();

    boolean checkUserIsAdministrator(String userName);
}
