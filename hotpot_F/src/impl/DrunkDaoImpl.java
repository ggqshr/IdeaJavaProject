package impl;

import Enetity.Drunk;
import dao.DrunkDao;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class DrunkDaoImpl extends BaseDao implements DrunkDao {

    @Override
    public void insertDrunk(Drunk drunk) {
        // TODO Auto-generated method stub
        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("MMdHms");
        String businessNumber = "d" + format.format(date);
        String sql = " insert into drunk values(?,?,?,?)";
        Object[] params = {
                businessNumber, drunk.getDrunkName(),
                drunk.getDrunkPhoto(), drunk.getDrunkPirce()
        };
        try {
            util.executeInsert(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updateDrunk(Drunk drunk) {
        // TODO Auto-generated method stub
        String sql = "update drunk set drunkName=?,drunkPrice=? where drunkNumber=?";
        Object[] params = {
                drunk.getDrunkName(), drunk.getDrunkPirce(), drunk.getDrunkNumber()
        };
        try {
            util.executeUpdate(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Drunk queryDrunkAsNumber(String drunkNumber) {
        ResultSet resultSet = null;
        Drunk drunk = null;
        String sql = " select * from drunk where drunkNumber=?";
        Object[] params = {
                drunkNumber
        };
        try {
            resultSet = util.executeQuery(sql, params);
            if (resultSet.next()) {
                drunk = new Drunk();
                drunk.setDrunkNumber(resultSet.getString(1));
                drunk.setDrunkName(resultSet.getString(2));
                drunk.setDrunkPhoto(resultSet.getString(3));
                drunk.setDrunkPirce(resultSet.getFloat(4));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch blockO
            e.printStackTrace();
        }
        return drunk;
    }

    @Override
    public Drunk queryDrunkAsName(String drunkName) {
        ResultSet resultSet = null;
        Drunk drunk = null;
        String sql = " select * from drunk where drunkName=?";
        Object[] params = {
                drunkName
        };
        try {
            resultSet = util.executeQuery(sql, params);
            if (resultSet.next()) {
                drunk = new Drunk();
                drunk.setDrunkNumber(resultSet.getString(1));
                drunk.setDrunkName(resultSet.getString(2));
                drunk.setDrunkPhoto(resultSet.getString(3));
                drunk.setDrunkPirce(resultSet.getFloat(4));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return drunk;
    }

    @Override
    public ArrayList<Drunk> queryAllDrunk() {
        ResultSet resultSet = null;
        ArrayList<Drunk> drunks = new ArrayList<>();
        Drunk drunk = null;
        String sql = " "
                + " select * from drunk";
        Object[] params = {};
        try {
            resultSet = util.executeQuery(sql, params);
            while (resultSet.next()) {
                drunk = new Drunk();
                drunk.setDrunkNumber(resultSet.getString(1));
                drunk.setDrunkName(resultSet.getString(2));
                drunk.setDrunkPhoto(resultSet.getString(3));
                drunk.setDrunkPirce(resultSet.getFloat(4));
                drunks.add(drunk);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return drunks;
    }

}
