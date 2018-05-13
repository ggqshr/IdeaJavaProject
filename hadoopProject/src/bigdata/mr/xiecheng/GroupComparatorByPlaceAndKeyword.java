package bigdata.mr.xiecheng;

import com.sun.mail.imap.IMAPBodyPart;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparatorByPlaceAndKeyword extends WritableComparator {
    /**
     * 用来确定要比较的对象的类型
     */
    protected GroupComparatorByPlaceAndKeyword() {
        super(SimipBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        SimipBean aa = (SimipBean) a;
        SimipBean bb = (SimipBean) b;
        if (aa.getComment_place().equals(bb.getComment_place())) {
            if (aa.getKeyword().equals(bb.getKeyword())) {
                return 0;
            }
        }
        return 1;
    }
}
