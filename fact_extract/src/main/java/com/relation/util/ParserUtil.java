package com.relation.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘绪光 on 2018/6/6.
 */
public class ParserUtil {

    public static void main(String[] args) {
        for (Map<String, List<CoNLLWord>> li :
                dict("刘小绪和小明是同学")) {
            System.out.println(li);
        }
    }

    public static String transFile2Sort(String filename) {
        File file = new File("D:\\Project\\intellij project\\fact_extract\\src\\main\\resources\\111.txt");
        BufferedReader reader = null;
        String[] strings = new String[0];
        StringBuilder sb = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            sb = new StringBuilder();
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
                sb.append(tempString);
                line++;
            }
//            strings = sb.toString().split("([。！!.？?])");
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    /**
     * @param text 待分析的句子
     * @return 分析结果，按分词结果的顺序组织的
     */
    public static List<CoNLLWord> parser(String text) {

        CoNLLSentence sentence = HanLP.parseDependency(text);

        CoNLLWord wordArr[] = sentence.getWordArray();

        List<CoNLLWord> result = new ArrayList<CoNLLWord>();

        for (int i = 0; i < wordArr.length; i++) {
            result.add(wordArr[i]);
        }

        return result;
    }

    /**
     * 最外层list是为了记录是第几个
     * map中的key记录的是关系
     * map中的list记录的是这个关系的词语
     *
     * @param text 带分析的句子
     * @return 词语依存字典
     */
    public static List<Map<String, List<CoNLLWord>>> dict(String text) {

        CoNLLSentence sentence = HanLP.parseDependency(text);

        //System.out.println(sentence);

        CoNLLWord[] wordArray = sentence.getWordArray();

        List<Map<String, List<CoNLLWord>>> result = new ArrayList<Map<String, List<CoNLLWord>>>();

        for (int i = 0; i < wordArray.length; i++) {

            CoNLLWord word = wordArray[i];
            HashMap<String, List<CoNLLWord>> map = new HashMap<String, List<CoNLLWord>>();

            for (int j = 0; j < wordArray.length; j++) {
                CoNLLWord child = wordArray[j];
                if (word.LEMMA.equals(child.HEAD.LEMMA)) {

                    if (map.containsKey(child.DEPREL)) {
                        map.get(child.DEPREL).add(child);
                    } else {
                        List<CoNLLWord> list = new ArrayList<CoNLLWord>();
                        list.add(child);

                        map.put(child.DEPREL, list);
                    }

                }
            }

            result.add(map);
        }

        return result;
    }
}