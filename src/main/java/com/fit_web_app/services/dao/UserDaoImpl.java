package com.fit_web_app.services.dao;

import com.fit_web_app.configuration.AbstractDao;
import com.fit_web_app.exceptions.DevException;
import com.fit_web_app.models.entity.RolesEntity;
import com.fit_web_app.models.entity.UsersEntity;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {
    @Override
    public UsersEntity getUserByLogin(String login) throws NoResultException {
        Query query = entityManager.createQuery("select u from UsersEntity u where lower(u.login) = lower(:login)");
        query.setParameter("login", login);

        return singleResult(query);
    }

    @Override
    public UsersEntity registerUser(UsersEntity usersEntity) {
        return save(usersEntity);
    }

    @Override
    public void deleteUser(int userId) throws DevException {
        delete(entityManager.find(UsersEntity.class, userId));
    }

    @Override
    public RolesEntity getUserRole(int roleId) {
        return entityManager.find(RolesEntity.class, roleId);
    }

    @Override
    public List<RolesEntity> getAvailableRoles() {
        Query query = entityManager.createQuery("select r from RolesEntity r");

        return listResult(query);
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        Query query = entityManager.createQuery("select new com.fit_web_app.models.dto.UserDTO(u.id, u.login, u.email, u.rolesEntity.id) from UsersEntity u");

        return listResult(query);
    }

    @Override
    public boolean checkUserIsAdministrator(String userName) {
        Query query = entityManager.createQuery("select 1 from UsersEntity u where lower(u.login) = lower(:userName) and u.rolesEntity.id = 1");
        query.setParameter("userName", userName);

        return singleResult(query) == null;
    }
}
