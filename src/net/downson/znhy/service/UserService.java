package net.downson.znhy.service;

import net.downson.znhy.bean.User;
import net.downson.znhy.dao.UserDao;

import java.sql.SQLException;

public class UserService {
    public User login(String userName, String password) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.login(userName, password);
       return user;
    }
}
