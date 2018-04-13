package impl;

import Enetity.Business;
import Enetity.Combo;
import Factory.GetInf;
import dao.BusinessDao;
import util.BaseDao;
import util.ComparatorCombo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class BusinessDaoImpl extends BaseDao implements BusinessDao {

    private GetInf gif = new GetInf();

    @Override
    public Object[] insertBusiness(Business business) {
        java.util.Date date = null;
        SimpleDateFormat format = null;
        date = new java.util.Date();
        format = new SimpleDateFormat("yyyyMMddHHmmSSS");
        String businessNumber = format.format(date);
        System.out.println(businessNumber);
        business.setBusinessNumber(businessNumber);
        String sql = " "
                + " insert into business values(?,?,?,?,?,?,?,?,?) ";
        Object[] para = {
                businessNumber, business.getCustomerNumber(),
                business.getBusinessType(), business.getFoodNumber(),
                business.getComboNumber(), business.getFoodSum(),
                business.getBusinessMoney(), date,
                0
        };
        Object[] reObjects = {
                business.getBusinessNumber(), businessNumber
        };
        try {
            util.executeInsert(sql, para);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reObjects;
    }


    @Override
    public ArrayList<Business> QueryBusinessAsCustNumber(String custNumber) {
            // TODO Auto-generated method stub
            ArrayList<Business> businesses = new ArrayList<>();
            Business business = null;
            ResultSet resultSet = null;
            Object[] params = {
                    custNumber
            };
        String sql = " select * from businessplus where customerNumber = ? ";
        try {
            resultSet = util.executeQuery(sql, params);
            businesses = gif.getBusiness(resultSet, business, businesses);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return businesses;
    }

    @Override
    public ArrayList<Business> QueryBusinessAsDay(String date) {
        // TODO Auto-generated method stub
        String sql = " select * from business where left(businessDate,10)=?";
        Object[] para = {
                date
        };
        ResultSet resultSet = null;
        Business business = null;
        ArrayList<Business> businesses = new ArrayList<>();
        try {
            resultSet = util.executeQuery(sql, para);
            businesses = gif.getBusiness(resultSet, business, businesses);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return businesses;
    }

    @Override
    public void queryBusinessAsMonth(String date) {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Combo> queryComboScore() {
        // TODO Auto-generated method stub
        ArrayList<Combo> combos = new ArrayList<>();
        Combo combo = null;
        ResultSet resultSet = null;
        String sql = " select combo.* from business,combo "
                + " where business.comboNumber=combo.comboNumber and "
                + " businessType='combo' "
                + " group by comboNumber";
        ComparatorCombo combo2 = new ComparatorCombo();
        Object[] para = {};
        try {
            resultSet = util.executeQuery(sql, para);
            combos = gif.getCombo(resultSet, combo, combos);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Collections.sort(combos, combo2);
        return combos;
    }

    @Override
    public boolean updateScore(String businessNumber, int score) {
        // TODO Auto-generated method stub
        Object[] params = {
                score, businessNumber
        };
        String sql = " "
                + " update business set businessScore = ? where businessNumber = ?";
        try {
            util.executeUpdate(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<Business> listBusiness() {
        ArrayList<Business> businesses = new ArrayList<>();
        Business business = null;
        ResultSet resultSet = null;
        String sql = " select * from businessplus ";
        Object[] para = {};
        try {
            resultSet = util.executeQuery(sql, para);
            businesses = gif.getBusiness(resultSet, business, businesses);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return businesses;
    }

}
