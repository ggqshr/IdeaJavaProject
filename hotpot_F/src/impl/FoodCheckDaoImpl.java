package impl;

import Enetity.FoodCheck;
import dao.FoodCheckDao;
import util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class FoodCheckDaoImpl extends BaseDao implements FoodCheckDao {

    @Override
    public void insertFoodCheck(FoodCheck foodCheck) {
        // TODO Auto-generated method stub
        java.util.Date date = new java.util.Date();
        @SuppressWarnings("unused")
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String sql = " insert into foodcheck(businessNumber,customerNumber,foodNumber,foodNum,checkPrice,checkDate) "
                + " values(?,?,?,?,?,?)";
        Object[] params = {
                foodCheck.getBusinessNumber(), foodCheck.getCustomerNumber(),
                foodCheck.getFoodNumber(), foodCheck.getFoodNum(),
                foodCheck.getCheckPrice(), date
        };
        try {
            util.executeInsert(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<FoodCheck> queryFoodCheckAsBusinessNumber(String businessNumber) {
        // TODO Auto-generated method stub
        ResultSet resultSet = null;
        FoodCheck foodCheck = null;
        ArrayList<FoodCheck> foodChecks = new ArrayList<>();
        String sql = " select * from foodcheck where businessNumber = ?";
        Object[] params = {
                businessNumber
        };
        try {
            resultSet = util.executeQuery(sql, params);
            while (resultSet.next()) {
                foodCheck = new FoodCheck();
                foodCheck.setCheckNumber(resultSet.getInt("checkNumber"));
                foodCheck.setBusinessNumber(resultSet.getString("businessNumber"));
                foodCheck.setCustomerNumber(resultSet.getString("customerNumber"));
                foodCheck.setFoodNumber(resultSet.getString("foodNumber"));
                foodCheck.setFoodNum(resultSet.getInt("foodNum"));
                foodCheck.setCheckPrice(resultSet.getFloat("checkPrice"));
                foodCheck.setCheckDate(resultSet.getString("checkDate"));
                foodChecks.add(foodCheck);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return foodChecks;
    }

    @Override
    public ArrayList<FoodCheck> queryFoodCheckAsCustomerNumber(String customerNumber) {
        ResultSet resultSet = null;
        FoodCheck foodCheck = null;
        ArrayList<FoodCheck> foodChecks = new ArrayList<>();
        String sql = " select * from foodcheckPlus where customerNumber = ?";
        Object[] params = {
                customerNumber
        };
        try {
            resultSet = util.executeQuery(sql, params);
            while (resultSet.next()) {
                foodCheck = new FoodCheck();
                foodCheck.setCheckNumber(resultSet.getInt("checkNumber"));
                foodCheck.setBusinessNumber(resultSet.getString("businessNumber"));
                foodCheck.setCustomerNumber(resultSet.getString("customerNumber"));
                foodCheck.setFoodNumber(resultSet.getString("foodNumber"));
                foodCheck.setFoodNum(resultSet.getInt("foodNum"));
                foodCheck.setCheckPrice(resultSet.getFloat("checkPrice"));
                foodCheck.setCheckDate(resultSet.getString("checkDate"));
                foodCheck.setFoodName(resultSet.getString("foodName"));
                foodCheck.setFoodPhoto(resultSet.getString("foodPhoto"));
                foodChecks.add(foodCheck);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return foodChecks;
    }
    public ArrayList<FoodCheck> getPlusFoodCheck(String businessNumber)
    {
        ResultSet resultSet = null;
        FoodCheck foodCheck = null;
        ArrayList<FoodCheck> foodChecks = new ArrayList<>();
        String sql = " select * from foodcheckPlus where businessNumber = ?";
        Object[] params = {
                businessNumber
        };
        try {
            resultSet = util.executeQuery(sql, params);
            while (resultSet.next()) {
                foodCheck = new FoodCheck();
                foodCheck.setCheckNumber(resultSet.getInt("checkNumber"));
                foodCheck.setCustomerNumber(resultSet.getString("customerNumber"));
                foodCheck.setFoodNumber(resultSet.getString("foodNumber"));
                foodCheck.setFoodNum(resultSet.getInt("foodNum"));
                foodCheck.setCheckPrice(resultSet.getFloat("checkPrice"));
                foodCheck.setCheckDate(resultSet.getString("checkDate"));
                foodCheck.setFoodName(resultSet.getString("foodName"));
                foodChecks.add(foodCheck);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return foodChecks;
    }

}
