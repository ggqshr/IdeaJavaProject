package com.relation.main;

import com.relation.util.ParserUtil;
import com.relation.util.RelationUtil;

import java.util.Set;

/**
 * Created by 刘绪光 on 2018/6/6.
 */
public class Main {

    public static void main(String[] args) {
        String testArr = ParserUtil.transFile2Sort("111.txt");
        String[] tt = testArr.split("([。！!.？?])");
        Set<String> result = RelationUtil.entityRelation(tt);

        for (String relation :
                result) {
            System.out.println(relation);
        }
    }

}