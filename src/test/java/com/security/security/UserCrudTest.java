package com.security.security;

import com.security.security.model.dao.RoleDao;
import com.security.security.model.dao.UserDao;
import com.security.security.model.entity.Role;
import com.security.security.model.entity.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCrudTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Ignore
    @Test
    public void addTest(){
        User user = new User();
        user.setUsername("prajyot.walali@gmail.com");
        user.setPassword("prajyot123");

        userDao.add(user);
    }

    @Ignore
    @Test
    public void updateTest(){
        User user = new User();
        user.setId(1);
        user.setUsername("prajyotwalali.21@gmail.com");
        user.setPassword("AADDBBCC");

        userDao.update(user);
    }

    @Ignore
    @Test
    public void deleteTest(){
        User user = new User();
        user.setId(1);

        userDao.delete(user);
    }

    @Ignore
    @Test
    public void getUserById(){
        System.out.println("\n$$$$$$$$$$$$$$ By Id");
        System.out.println(userDao.getById(1).toString());
        System.out.println("$$$$$$$$$$$$$$\n");
    }

    @Ignore
    @Test
    public void getUserByUserName(){
        System.out.println("\n$$$$$$$$$$$$$$ By Username");
        System.out.println(userDao.getByUsername("prajyot.walali@gmail.com").toString());
        System.out.println("$$$$$$$$$$$$$$\n");
    }

    @Ignore
    @Test
    public void getAllRoles(){
        User user = userDao.getByUsername("prajyot.walali@gmail.com");

        System.out.println("\n$$$$$$$$$$$$$$ By Username");
        System.out.println(userDao.getRolesByUserId(user.getId()).toString());
        System.out.println("$$$$$$$$$$$$$$\n");
    }

    @Ignore
    @Test
    public void getAllUsers(){
        List<User> users = userDao.getAll();

        users.forEach(user -> user.setRoles(userDao.getRolesByUserId(user.getId())));

        System.out.println("\n$$$$$$$$$$$$$$ By Username");
        System.out.println(users);
        System.out.println("$$$$$$$$$$$$$$\n");
    }

    @Ignore
    @Test
    public void removeRoles(){
        User user = userDao.getByUsername("prajyot.walali@gmail.com");
        userDao.removeRoleMap(user);

        user.setRoles(userDao.getRolesByUserId(user.getId()));

        System.out.println("\n$$$$$$$$$$$$$$ By Username");
        System.out.println(user);
        System.out.println("$$$$$$$$$$$$$$\n");
    }

    @Ignore
    @Test
    public void addRoleMap(){
        User user = userDao.getByUsername("prajyot.walali@gmail.com");
        Role role = roleDao.getByName("USER");

        userDao.addRoleMap(user.getId(), role.getId());

        user.setRoles(userDao.getRolesByUserId(user.getId()));

        System.out.println("\n$$$$$$$$$$$$$$ By Username");
        System.out.println(user);
        System.out.println("$$$$$$$$$$$$$$\n");
    }

}
