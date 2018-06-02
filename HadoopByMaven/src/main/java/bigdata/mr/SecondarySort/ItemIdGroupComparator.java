package bigdata.mr.SecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ItemIdGroupComparator extends WritableComparator {
    //传入作为key的class类型
    protected ItemIdGroupComparator() {
        super(OrderBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean aa = (OrderBean) a;
        OrderBean bb = (OrderBean) b;
        return aa.getItemId().compareTo(bb.getItemId());
    }
}
