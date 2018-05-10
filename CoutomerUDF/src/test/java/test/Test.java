package test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    private static IClassifier i = new NaiveBayesClassifier(); //分类器模型
    static{
        try {
            i.train("trainData");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String changeCodeToUtf8(String origin) throws UnsupportedEncodingException {
        return new String(origin.getBytes("utf-8"), "utf-8");
    }
    @org.junit.Test
    public void evaluate() throws IOException {
        String emotion = i.classify(changeCodeToUtf8("hao"));
        System.out.println(emotion);
    }
}
