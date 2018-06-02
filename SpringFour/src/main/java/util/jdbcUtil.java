package util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class jdbcUtil {
    private static String driveClassName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/xchadoopdataexport?useUnicode=true&characterEncoding=utf8";
    static Connection connn = null;
    private static String user = "root";
    private static String password = "123";

    public static Connection getConnect() {
        Connection conn = null;
        //load driver
        try {
            Class.forName(driveClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("load driver failed!");
            e.printStackTrace();
        }

        //connect db
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("connect failed!");
            e.printStackTrace();
        }
        return conn;
    }

    public static List query(String sql, Object para[], ResultSetHandler rsh) {
        QueryRunner qr = new QueryRunner();
        List result = null;
        if (connn == null) {
            connn = getConnect();
        }
        try{
            result = (List) qr.query(connn,sql,para,rsh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
