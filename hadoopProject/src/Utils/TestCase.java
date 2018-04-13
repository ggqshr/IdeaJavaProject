package Utils;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;

import java.text.*;
import java.util.*;

public class TestCase {
    @Test
    public void test1() throws ParseException {
        String[] aa = {"a","b","c","d","e","f"};
        List<String> ls = new ArrayList<String>();
        for (String s : aa) {
            ls.add(s);
        }
        Iterator<String> iterator = ls.listIterator();
        List<String> list = IteratorUtils.toList(iterator);
        String[] strings = list.toArray(new String[list.size()]);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
