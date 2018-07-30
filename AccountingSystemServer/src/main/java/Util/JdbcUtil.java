package Util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository(value = "JdbcUtil")
public class JdbcUtil {
    @Autowired
    private ComboPooledDataSource dataSources;
    @Autowired
    private QueryRunner qr;
    private final Logger logger = Logger.getLogger("sqlLogger");

    /**
     * 查询一个对象
     * @param sql sql语句
     * @param para 参数列表
     * @param rsh 类型构造器
     * @return 一个结果实例
     */
    public Object queryOne(String sql, Object[] para, ResultSetHandler rsh) {
        logger.info("开始查询");
        Object obj = null;
        try {
            obj = qr.query(dataSources.getConnection(), sql, rsh, para);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("结束查询");
        return obj;
    }

    //执行查询操作
    public List queryMany(String sql, Object[] para, ResultSetHandler rsh) {
        logger.info("开始查询");
        List Result = null;
        try {
            Result = (List) qr.query(dataSources.getConnection(), sql, rsh, para);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("结束查询");
        return Result;
    }

    //执行插入和更新以及删除操作
    public int executeSql(String sql, Object[] para) {
        logger.info("开始更新");
        int result = -1;
        try {
            result = qr.update(dataSources.getConnection(), sql, para);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("结束更新");
        return result;
    }
}
