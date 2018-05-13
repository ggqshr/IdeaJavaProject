package test;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;
import com.hankcs.hanlp.suggest.Suggester;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        PerceptronLexicalAnalyzer analyzer = new PerceptronLexicalAnalyzer();
        analyzer.learn("人太多/a");
        Sentence iWords = analyzer.analyze("人太多");
        System.out.println(iWords.wordList.size());
        analyzer.learn(iWords.toString());
        System.out.println(analyzer.analyze("人太多"));
    }
    public void test() throws IOException {
//        String str = "携程137元，可以返现37元，买票还要携程买，温泉环境整体不错，一楼温泉，二楼是免费休息大厅和汗蒸，及自助餐厅，自助餐58元一位，我们到的有点晚，一点多，菜品太少了，就没吃，自己带了一点点零食和水，度假村离海边不远，计划下次去，住一晚，还可以逛逛海边";
//        List<String> strings = HanLP.extractPhrase(str, 100);
//        for (String string : strings) {
//            System.out.println(string);
//        }
//        System.out.println("@@@@@@@@@@@");
//        List<String> strings1 = HanLP.extractSummary(str, 100);
//        for (String s : strings1) {
//            System.out.println(s);
//        }
//        System.out.println("@@@@");
//        List<String> strings2 = HanLP.extractKeyword(str, 100);
//        for (String s : strings2) {
//            System.out.println(s);
//        }
//        PerceptronLexicalAnalyzer analyzer = new PerceptronLexicalAnalyzer();
//        System.out.println(analyzer.analyze("现在"));
        CoNLLSentence sentence = HanLP.parseDependency("徐先生还具体帮助他确定了把画雄鹰、松鼠和麻雀作为主攻目标。");
        System.out.println(sentence);
    }
}
