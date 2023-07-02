package net.downson.znhy.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.downson.znhy.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {


    public User login(String name,String password) throws SQLException {

        ComboPooledDataSource dataSource=new ComboPooledDataSource();
//        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from user where username=? and userpwd=?";
        User user = null;
        user = queryRunner.query(sql, new BeanHandler<User>(User.class),name,password);
        dataSource.close();
        return user;


    }

}
