package com.fit_web_app;

import com.fit_web_app.configuration.AbstractDao;
import com.fit_web_app.models.entity.UsersEntity;
import com.fit_web_app.services.bl.UserBl;
import com.fit_web_app.services.bl.UserBlImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserTest {
    private static UserBl userBl;

    @BeforeAll
    public static void init() {
        AbstractDao.init();
        userBl = new UserBlImpl();
    }

    @Test
    public void getUserTest() {
        Assertions.assertNotNull(userBl.getUserByLogin("administrator"));
    }

    @Test
    public void insertAndDeleteUserTest() throws Exception {
        UsersEntity usersEntity = userBl.registerUser("test10", "administrator", "test@devs.com", 1);
        Assertions.assertNotNull(userBl.getUserByLogin(usersEntity.getLogin()));
        userBl.deleteUser(usersEntity.getId());
        Assertions.assertNull(userBl.getUserByLogin(usersEntity.getLogin()));
    }

    @Test
    public void checkUserExistThrowsExceptionTest() throws Exception {
        UsersEntity usersEntity = userBl.registerUser("test", "test", "test@devs.com", 2);
        Assertions.assertThrows(Exception.class, () -> userBl.registerUser("test", "test", "test@devs.com", 2));
        userBl.deleteUser(usersEntity.getId());
        Assertions.assertNull(userBl.getUserByLogin(usersEntity.getLogin()));
    }

    @Test
    public void getAvailableRolesTest() {
        Assertions.assertEquals(2, userBl.getAvailableRoles().size());
    }

    @Test
    public void getAllUsersTest() {
        List<UsersEntity> test = userBl.getAllUsers();
        System.out.println(test.size());
    }
}