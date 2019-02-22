package hbase;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class load2HbaseTest {

    @Test
    public void test1(){
        String aa = "1\t2\t3\t";
        String[] split = aa.split("\t");
        for (String s : split) {
            System.out.println(s);
        }
        split = Arrays.copyOfRange(split, 1, split.length);
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println(String.join("@@", split));
    }
}