package Utils;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;

import java.text.*;
import java.util.*;

public class TestCase {
    @Test
    public void test1() throws ParseException {
        String str = "2017-01-05";
        String[] split = str.split("-");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
