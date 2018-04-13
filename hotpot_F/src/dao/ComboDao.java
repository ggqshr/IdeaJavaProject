package dao;

import Enetity.Combo;

import java.util.ArrayList;


public interface ComboDao {
    //�����ײ�
    public String  insertCombo(Combo combo);

    //�����ײ�
    public void updateCombo(Combo combo);

    //ɾ���ײ�
    public void deleteCombo(String comborNumber);

    //��ʾȫ���ײ�
    public ArrayList<Combo> listCombo();

    //�����ײͱ�Ų�ѯ�ײ�
    public Combo queryComboAsNumber(String comboNumber);

    //ģ����ѯ�ײ�
    public Combo queryComboAsInf(String inf);
}
