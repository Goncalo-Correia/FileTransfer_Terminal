package org.musicsource.codezillas.server.services;

import org.musicsource.codezillas.server.persistence.daos.UserDao;
import org.musicsource.codezillas.server.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(Integer id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return userDao.saveOrUpdate(user);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userDao.findAll();
    }
}
