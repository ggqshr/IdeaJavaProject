package impl;

import Enetity.OrderQuene;
import Factory.GetInf;
import dao.OrderQueneDao;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderQueneDaoImpl extends BaseDao implements OrderQueneDao {

    GetInf gif = new GetInf();

    @Override
    public ArrayList<OrderQuene> listQuene() {
        // TODO Auto-generated method stub
        String sql = "select * from orderquene";
        ResultSet resultSet = null;
        ArrayList<OrderQuene> orderQuenes = new ArrayList<>();
        OrderQuene orderQuene = null;
        Object[] params = {

        };
        try {
            resultSet = util.executeQuery(sql, params);
            orderQuenes = gif.getOrderQuene(resultSet, orderQuene, orderQuenes);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return orderQuenes;
    }

    @Override
    public ArrayList<OrderQuene> listUnfinished() {
        // TODO Auto-generated method stub
        String sql = "select * from orderquene where orderStatu=0";
        ResultSet resultSet = null;
        ArrayList<OrderQuene> orderQuenes = new ArrayList<>();
        OrderQuene orderQuene = null;
        Object[] params = {

        };
        try {
            resultSet = util.executeQuery(sql, params);
            orderQuenes = gif.getOrderQuene(resultSet, orderQuene, orderQuenes);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return orderQuenes;
    }

    @Override
    public ArrayList<OrderQuene> listFinished() {
        String sql = "select * from orderquene where orderStatu=1";
        ResultSet resultSet = null;
        ArrayList<OrderQuene> orderQuenes = new ArrayList<>();
        OrderQuene orderQuene = null;
        Object[] params = {

        };
        try {
            resultSet = util.executeQuery(sql, params);
            orderQuenes = gif.getOrderQuene(resultSet, orderQuene, orderQuenes);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return orderQuenes;
    }

    @Override
    public OrderQuene queryQueneAsBusinessNumber(String businessNumber) {
        String sql = "select * from orderquene where businessNumber = ?";
        ResultSet resultSet = null;
        ArrayList<OrderQuene> orderQuenes = new ArrayList<>();
        OrderQuene orderQuene = null;
        Object[] params = {
                businessNumber
        };
        try {
            resultSet = util.executeQuery(sql, params);
            orderQuenes = gif.getOrderQuene(resultSet, orderQuene, orderQuenes);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        orderQuene = orderQuenes.get(0);
        return orderQuene;
    }

    @Override
    public ArrayList<OrderQuene> queryQueneAsCustNumber(String custNumber) {
        // TODO Auto-generated method stub
        String sql = "select * from orderquene where customerNumber = ?";
        ResultSet resultSet = null;
        ArrayList<OrderQuene> orderQuenes = new ArrayList<>();
        OrderQuene orderQuene = null;
        Object[] params = {
                custNumber
        };
        try {
            resultSet = util.executeQuery(sql, params);
            orderQuenes = gif.getOrderQuene(resultSet, orderQuene, orderQuenes);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return orderQuenes;
    }

    @Override
    public void addQuene(OrderQuene quene) {
        // TODO Auto-generated method stub
        Date date = new Date();
        String sql = " insert into orderquene(businessNumber,customerNumber,"
                + " orderStatu,orderDate) values(?,?,?,?)";
        Object[] params = {
                quene.getBusinessNumber(), quene.getCustomerNumber(),
                0, date
        };
        try {
            util.executeInsert(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuene(int queneNumber) {
        // TODO Auto-generated method stub
        Date date = new Date();
        String sql = "update orderquene set orderStatu =?,"
                + " endDate = ? where queneNumber=?";
        Object[] params = {
                1, date, queneNumber
        };
        try {
            util.executeUpdate(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
