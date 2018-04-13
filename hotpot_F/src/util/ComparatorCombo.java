package util;

import Enetity.Combo;

import java.util.Comparator;


@SuppressWarnings("rawtypes")
public class ComparatorCombo implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Combo f1 = (Combo) o1;
        Combo f2 = (Combo) o2;
        Float c1 = f1.getComboScore();
        Float c2 = f2.getComboScore();
        int flag = c2.compareTo(c1);
        return flag;
    }

}
