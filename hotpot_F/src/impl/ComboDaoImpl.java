package impl;

import Enetity.Combo;
import Factory.GetInf;
import dao.ComboDao;
import util.BaseDao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ComboDaoImpl extends BaseDao implements ComboDao {

    GetInf gif = new GetInf();

    @Override
    public String  insertCombo(Combo combo) {
        // TODO Auto-generated method stub
        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("MMdHms");
        String businessNumber = "c" + format.format(date);
        String sql = " insert into combo values(?,?,?,?,?,?,?,?,?,?,?)";
        String comboPhotoPath = File.separator+"combo"+ File.separator+businessNumber;
        Object[] para = {
                businessNumber, combo.getComboName(),
                comboPhotoPath, combo.getHotpotSoup(),
                combo.getNoodlesNum(), combo.getMeatNum(),
                combo.getVegetableNum(), combo.getSoupNum(),
                combo.getComboPrice(), 0, 0
        };
        try {
            util.executeInsert(sql, para);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return businessNumber;
    }

    @Override
    public void updateCombo(Combo combo) {
        // TODO Auto-generated method stub
        String sql = " "
                + " update combo set comboName=?,hotpotSoup=?,"
                + " noodlesNum=?,"
                + " meatNum=?,vegetableNum=?,soupNum=?,"
                + " comboPrice=?"
                + " where comboNumber=?";
        Object[] para = {
                combo.getComboName(), combo.getHotpotSoup(),
                combo.getNoodlesNum(), combo.getMeatNum(),
                combo.getVegetableNum(), combo.getSoupNum(),
                combo.getComboPrice(), combo.getComboNumber()
        };
        try {
            util.executeUpdate(sql, para);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCombo(String comboNumber) {
        // TODO Auto-generated method stub
        String sql = " "
                + " delete from combo where comboNumber=?";
        Object[] params = {
                comboNumber
        };

        try {
            util.executeInsert(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Combo> listCombo() {
        // TODO Auto-generated method stub
        ArrayList<Combo> combos = new ArrayList<>();
        Combo combo = null;
        ResultSet resultSet = null;
        String sql = " select * from combo ";
        Object[] para = {};
        try {
            resultSet = util.executeQuery(sql, para);
            combos = gif.getCombo(resultSet, combo, combos);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return combos;
    }

    @Override
    public Combo queryComboAsNumber(String comboNumber) {
        // TODO Auto-generated method stub
        ArrayList<Combo> combos = new ArrayList<>();
        Combo combo = null;
        ResultSet resultSet = null;
        String sql = " select * from combo where comboNumber=?";
        Object[] para = {
                comboNumber
        };
        try {
            resultSet = util.executeQuery(sql, para);
            combos = gif.getCombo(resultSet, combo, combos);
            if (combos.size() != 0) {
                combo = combos.get(0);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return combo;
    }

    @Override
    public Combo queryComboAsInf(String inf) {
        // TODO Auto-generated method stub
        ResultSet resultSet = null;
        ArrayList<Combo> combos = new ArrayList<>();
        Combo combo = null;
        String sql = " "
                + " select * from combo where comboName=? or comboNumber=?";
        Object[] params = {inf, inf};
        try {
            resultSet = util.executeQuery(sql, params);
            combos = gif.getCombo(resultSet, combo, combos);
            if (combos.size() != 0)
                combo = combos.get(0);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return combo;
    }

}
