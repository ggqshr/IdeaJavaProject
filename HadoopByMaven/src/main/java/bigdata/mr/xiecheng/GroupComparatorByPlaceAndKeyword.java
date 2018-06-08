package bigdata.mr.xiecheng;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparatorByPlaceAndKeyword extends WritableComparator {
    /**
     * 用来确定要比较的对象的类型
     */
    protected GroupComparatorByPlaceAndKeyword() {
        super(KeyWordBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        KeyWordBean aa = (KeyWordBean) a;
        KeyWordBean bb = (KeyWordBean) b;
        if (aa.getComment_place().equals(bb.getComment_place())) {
            if (aa.getKeyword().equals(bb.getKeyword())) {
                return 0;
            }
        }
        return 1;
    }
}
