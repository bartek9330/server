package com.fit_web_app.services.dao;

import com.fit_web_app.configuration.AbstractDao;
import com.fit_web_app.models.entity.UsersDataEntity;

import javax.persistence.Query;
import java.util.List;

public class UserDataDaoImpl extends AbstractDao implements UserDataDao {
    @Override
    public void saveBMI(UsersDataEntity usersDataEntity) {
        save(usersDataEntity);
    }

    @Override
    public List<UsersDataEntity> getMyBMI(Integer userId) {
        Query query = entityManager.createQuery("select new com.fit_web_app.models.dto.UserDataDTO(u.date, u.heightCm, u.weightG) from UsersDataEntity u where u.userEntity.id = :userId order by u.date DESC");
        query.setParameter("userId", userId);

        return listResult(query);
    }
}
