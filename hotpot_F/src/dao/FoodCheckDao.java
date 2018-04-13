package dao;

import Enetity.FoodCheck;

import java.util.ArrayList;


public interface FoodCheckDao {
    //����һ��ʳ�ﶩ��
    public void insertFoodCheck(FoodCheck foodCheck);

    //���ն����Ų�ѯ
    public ArrayList<FoodCheck> queryFoodCheckAsBusinessNumber(String businessNumber);

    //���տͻ���Ų�ѯ
    public ArrayList<FoodCheck> queryFoodCheckAsCustomerNumber(String customerNumber);

    public ArrayList<FoodCheck> getPlusFoodCheck(String businessNumber);
}
