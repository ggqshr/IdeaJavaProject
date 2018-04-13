package impl;

import Enetity.Food;
import dao.FoodDao;
import util.BaseDao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FoodDaoImpl extends BaseDao implements FoodDao {

    @Override
    public String insertFood(Food food) {
        // TODO Auto-generated method stub
        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("MMdHms");
        String businessNumber = "f" + format.format(date);
        String sql = " insert into food values(?,?,?,?,?,?)";
        Object[] params = {
                businessNumber, food.getFoodName(),
                File.separator+"food"+File.separator+businessNumber, food.getFoodType(),
                food.getFoodPrice(), food.getFoodBanlance()
        };
        try {
            util.executeInsert(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return businessNumber;
    }

    @Override
    public void updateFood(Food f) {
        // TODO Auto-generated method stub
        String sql = "update food set foodName=?,foodPrice=?,foodBanlance=? where foodNumber=?";
        Object[] params = {
                f.getFoodName(), f.getFoodPrice(), f.getFoodBanlance(), f.getFoodNumber()
        };
        try {
            util.executeUpdate(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Food queryFoodAsNumber(String foodNumber) {
        // TODO Auto-generated method stub
        ResultSet resultSet = null;
        Food food = null;
        String sql = " select * from food where foodNumber=?";
        Object[] params = {
                foodNumber
        };
        try {
            resultSet = util.executeQuery(sql, params);
            if (resultSet.next()) {
                food = new Food();
                food.setFoodNumber(resultSet.getString(1));
                food.setFoodName(resultSet.getString(2));
                food.setFoodPhoto(resultSet.getString(3));
                food.setFoodType(resultSet.getString(4));
                food.setFoodPrice(resultSet.getFloat(5));
                food.setFoodBanlance(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return food;
    }

    @Override
    public Food queryFoodAsName(String foodName) {
        // TODO Auto-generated method stub
        ResultSet resultSet = null;
        Food food = null;
        String sql = " select * from food where foodName=?";
        Object[] params = {
                foodName
        };
        try {
            resultSet = util.executeQuery(sql, params);
            if (resultSet.next()) {
                food = new Food();
                food.setFoodNumber(resultSet.getString(1));
                food.setFoodName(resultSet.getString(2));
                food.setFoodPhoto(resultSet.getString(3));
                food.setFoodType(resultSet.getString(4));
                food.setFoodPrice(resultSet.getFloat(5));
                food.setFoodBanlance(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return food;
    }

    @Override
    public ArrayList<Food> queryFoodAsType(String type) {
        // TODO Auto-generated method stub
        ResultSet resultSet = null;
        ArrayList<Food> foods = new ArrayList<>();
        Food food = null;
        String sql = " "
                + " select * from " + type;
        Object[] params = {};
        try {
            resultSet = util.executeQuery(sql, params);
            while (resultSet.next()) {
                food = new Food();
                food.setFoodNumber(resultSet.getString(1));
                food.setFoodName(resultSet.getString(2));
                food.setFoodPhoto(resultSet.getString(3));
                food.setFoodType(resultSet.getString(4));
                food.setFoodPrice(resultSet.getFloat(5));
                food.setFoodBanlance(resultSet.getInt(6));
                foods.add(food);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public void deleteFoodAsNumber(String foodNumber) {
        // TODO Auto-generated method stub
        String sql = " delete from food where foodNumber = ?";
        Object[] params = {
                foodNumber
        };
        try {
            util.executeInsert(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Food> listFood() {
        // TODO Auto-generated method stub
        ResultSet resultSet = null;
        ArrayList<Food> foods = new ArrayList<>();
        Food food = null;
        String sql = " "
                + " select * from food";
        Object[] params = {};
        try {
            resultSet = util.executeQuery(sql, params);
            while (resultSet.next()) {
                food = new Food();
                food.setFoodNumber(resultSet.getString(1));
                food.setFoodName(resultSet.getString(2));
                food.setFoodPhoto(resultSet.getString(3));
                food.setFoodType(resultSet.getString(4));
                food.setFoodPrice(resultSet.getFloat(5));
                food.setFoodBanlance(resultSet.getInt(6));
                foods.add(food);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return foods;
    }

}
