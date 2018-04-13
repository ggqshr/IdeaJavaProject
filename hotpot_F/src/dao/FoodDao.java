package dao;

import Enetity.Food;

import java.util.ArrayList;


public interface FoodDao {
    //����һ��ʳ��
    public String insertFood(Food food);

    //����ʳ��
    public void updateFood(Food f);

    //���ձ�Ų�ѯʳ��
    public Food queryFoodAsNumber(String foodNumber);

    //�������ֲ�ѯʳ��
    public Food queryFoodAsName(String foodName);

    //����ʳ�����Ͳ鿴ʳ��
    public ArrayList<Food> queryFoodAsType(String type);

    //����ʳ����ɾ��ʳ��
    public void deleteFoodAsNumber(String foodNumber);

    //��ʾ����ʳ��
    public ArrayList<Food> listFood();
}
