package Factory;

import Enetity.Business;
import Enetity.Combo;
import Enetity.OrderQuene;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class GetInf {
    public ArrayList<Business> getBusiness(ResultSet r, Business business, ArrayList<Business> businesses) throws SQLException {
        while (r.next()) {
            business = new Business();
            business.setBusinessNumber(r.getString("businessNumber"));
            business.setCustomerNumber(r.getString("customerNumber"));
            business.setBusinessType(r.getString("businessType"));
            business.setFoodNumber(r.getString("foodNumber"));
            business.setComboNumber(r.getString("comboNumber"));
            business.setFoodSum(r.getInt("foodSum"));
            business.setBusinessMoney(r.getFloat("businessMoney"));
            business.setBusinessDate(r.getString("businessDate"));
            business.setBusinessScore(r.getInt("businessScore"));
            business.setComboName(r.getString("comboName"));
            businesses.add(business);
        }
        return businesses;
    }

    public ArrayList<Combo> getCombo(ResultSet r, Combo b, ArrayList<Combo> bArrayList) throws SQLException {
        while (r.next()) {
            Combo b1 = new Combo();
            b1.setComboNumber(r.getString("comboNumber"));
            b1.setComboName(r.getString("comboName"));
            b1.setComboPhoto(r.getString("comboPhoto"));
            b1.setHotpotSoup(r.getString("hotpotSoup"));
            b1.setNoodlesNum(r.getInt("noodlesNum"));
            b1.setMeatNum(r.getInt("meatNum"));
            b1.setVegetableNum(r.getInt("vegetableNum"));
            b1.setSoupNum(r.getInt("soupNum"));
            b1.setComboPrice(r.getFloat("comboPrice"));
            b1.setComboScore(r.getFloat("comboScore"));
            bArrayList.add(b1);
        }
        return bArrayList;
    }

    public ArrayList<OrderQuene> getOrderQuene(ResultSet r, OrderQuene q, ArrayList<OrderQuene> orderQuenes) throws SQLException {
        while (r.next()) {
            q = new OrderQuene();
            q.setQueneNumber(r.getInt(1));
            q.setBusinessNumber(r.getString(2));
            q.setCustomerNumber(r.getString(3));
            q.setOrderStatu(r.getInt(4));
            q.setOrderDate(r.getString(5));
            q.setEndDate(r.getString(6));
            orderQuenes.add(q);
        }
        return orderQuenes;
    }
}
