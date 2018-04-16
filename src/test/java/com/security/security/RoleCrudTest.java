package com.security.security;

import com.security.security.model.dao.RoleDao;
import com.security.security.model.entity.Role;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class RoleCrudTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void addRole(){
        Role role = new Role();
        role.setName("ADMIN");

        roleDao.add(role);

        Assert.assertEquals(role.getId(), roleDao.getById(role.getId()).getId());
        Assert.assertEquals(role.getName(), roleDao.getById(role.getId()).getName());
    }

    @Test
    public void addRoleUser(){
        Role role = new Role();
        role.setName("user");

        roleDao.add(role);

        Assert.assertEquals(role.getId(), roleDao.getById(role.getId()).getId());
        Assert.assertEquals(role.getName(), roleDao.getById(role.getId()).getName());
    }

    @Test
    public void updateRoleUser(){
        Role role = roleDao.getByName("user");
        role.setName("USERS");

        roleDao.update(role);

        Assert.assertEquals(role.getId(), roleDao.getById(role.getId()).getId());
        Assert.assertEquals(role.getName(), roleDao.getById(role.getId()).getName());
    }

    @Test
    public void getAllPrint(){
        System.out.println(roleDao.getAll());
    }

    @Test
    public void deleteRole(){
        Role role = new Role();
        role.setName("dummy");
        roleDao.add(role);

        System.out.println("\n######### Before\n"+roleDao.getAll()+"\n#########\n");

        roleDao.delete(role);

        System.out.println("\n######### After\n"+roleDao.getAll()+"\n#########\n");

    }
}
