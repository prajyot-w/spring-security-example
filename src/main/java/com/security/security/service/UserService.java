package com.security.security.service;

import com.security.security.model.dao.UserDao;
import com.security.security.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void add(User user){
        userDao.add(user);

        user.getRoles().forEach(role -> {
            userDao.addRoleMap(user.getId(), role.getId());
        });
    }

    public void update(User user) {
        userDao.update(user);
        userDao.removeRoleMap(user);
        user.getRoles().forEach(role -> {
            userDao.addRoleMap(user.getId(), role.getId());
        });
    }

    public void delete(User user){
        userDao.removeRoleMap(user);
        userDao.delete(user);
    }

    public List<User> getAll(){
        List<User> users = userDao.getAll();
        users.forEach(user -> {
            user.setRoles(userDao.getRolesByUserId(user.getId()));
            user.setPassword(null);
        });
        return users;
    }

    public User getById(int id){
        User user = userDao.getById(id);
        user.setRoles(userDao.getRolesByUserId(user.getId()));
        return user;
    }

    public User getByUsername(String username){
        User user = userDao.getByUsername(username);
        user.setRoles(userDao.getRolesByUserId(user.getId()));
        return user;
    }
}
