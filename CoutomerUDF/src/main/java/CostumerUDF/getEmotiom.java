package CostumerUDF;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class getEmotiom extends UDF {
    private static IClassifier i = new NaiveBayesClassifier(); //分类器模型
    static {
        try {
            i.train("trainData"); //模型训练,传入训练数据地址
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String evaluate(String line) throws IOException {
        String emotion = i.classify(changeCodeToUtf8(line));
        return emotion;
    }

    public static String changeCodeToUtf8(String origin) throws UnsupportedEncodingException {
        return new String(origin.getBytes("utf-8"), "utf-8");
    }
}
