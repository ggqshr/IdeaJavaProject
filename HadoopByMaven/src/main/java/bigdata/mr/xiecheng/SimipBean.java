package bigdata.mr.xiecheng;

import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SimipBean implements WritableComparable<SimipBean> {
    private String comment_place;
    private String emotiom;
    private String keyword;
    static PerceptronLexicalAnalyzer analyzer;
    static {
        try {
            analyzer = new PerceptronLexicalAnalyzer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SimipBean() {
    }

    public void set(String comment_place, String emotiom, String keyword) {
        this.comment_place = comment_place;
        this.emotiom = emotiom;
        this.keyword = keyword;
    }

    public String getComment_place() {
        return comment_place;
    }

    public void setComment_place(String comment_place) {
        this.comment_place = comment_place;
    }

    public String getEmotiom() {
        return emotiom;
    }

    public void setEmotiom(String emotiom) {
        this.emotiom = emotiom;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int compareTo(SimipBean o) {
        String thiscomment_place = this.comment_place;
        String thatcomment_place = o.getComment_place();
        int cmp = thiscomment_place.compareTo(thatcomment_place);
        if(cmp == 0)
        {
            cmp = this.keyword.compareTo(o.getKeyword());
        }
        return cmp;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.comment_place);
        dataOutput.writeUTF(this.emotiom);
        dataOutput.writeUTF(this.keyword);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.comment_place = dataInput.readUTF();
        this.emotiom = dataInput.readUTF();
        this.keyword = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return comment_place + '\t' +emotiom +"\t"+keyword;
    }
}
