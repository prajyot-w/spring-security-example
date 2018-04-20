package com.security.security;

import com.security.security.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author prajyot on 20/4/18.
 * @project security.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCTemplateTest {

    @Autowired
    @Qualifier("pgJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    @Test
    public void selectTest(){

        List<User> users;

        try {
            users = jdbcTemplate.query("SELECT * FROM users;", new ResultSetExtractor<List<User>>() {
                @Nullable
                @Override
                public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    ResultSetMetaData meta = resultSet.getMetaData();
                    List<User> usersList = new ArrayList<>();

                    while(resultSet.next()){
                        User userRecord = new User();
                        userRecord.setId(resultSet.getInt(1));
                        userRecord.setUsername(resultSet.getString(2));
                        userRecord.setPassword(resultSet.getString(3));
                        usersList.add(userRecord);
                    }
                    return usersList;
                }
            });

            System.out.println("\nUSER LIST ---------------------------");
            users.forEach(System.out::println);
        } catch (Exception e){
            System.out.println("Exception at query execution");
            e.printStackTrace();
        }
    }
}
