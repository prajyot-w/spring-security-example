package com.security.security.model.dao;

import com.security.security.model.entity.Role;
import com.security.security.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */
@Mapper
public interface UserDao {

    public void add(User user);

    public void update(User user);

    public void delete(User user);

    public User getById(@Param("id") int id);

    public User getByUsername(@Param("username") String username);

    public void addRoleMap(@Param("user_id") int userid, @Param("role_id") int roleid);

    public void removeRoleMap(User user);

    public List<User> getAll();

    public List<Role> getRolesByUserId(@Param("id") int id);
}
