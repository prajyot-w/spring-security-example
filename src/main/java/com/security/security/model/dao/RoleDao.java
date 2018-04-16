package com.security.security.model.dao;

import com.security.security.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */
@Mapper
public interface RoleDao {

    public void add(Role role);

    public void update(Role role);

    public void delete(Role role);

    public Role getById(@Param("id") int id);

    public Role getByName(@Param("name") String name);

    public List<Role> getAll();

}
