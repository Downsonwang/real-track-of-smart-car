package net.downson.znhy.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.downson.znhy.bean.GPS;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class SearchDao {

    public static List<GPS>  querySearch() throws SQLException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        QueryRunner queryRunner=new QueryRunner(dataSource);
        String sql="select * from iot";
        List<GPS> sa = queryRunner.query(sql, new BeanListHandler<GPS>(GPS.class));
        dataSource.close();
        return  sa;
    }
}
