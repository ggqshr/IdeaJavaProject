package bigdata.mr.flowsum;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自定义对象使用mapreduce时，需要实现writable接口
 */
public class FlowBean implements WritableComparable<FlowBean> {
    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

    /**
     * 在写入到文件中，需要调用toString 方法，这样可以自己控制写入到文本中的内容
     *
     * @return
     */
    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    /**
     * 反序列化时需要调用空参数构造
     */
    public FlowBean() {
    }

    public FlowBean(Long upFlow, Long downFlow) {

        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = downFlow + upFlow;
    }

    public void setAll(Long upFlow, Long downFlow) {

        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = downFlow + upFlow;
    }

    /**
     * 序列化方法
     *
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    /**
     * 反序列化方法
     *
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getUpFlow() {
        return upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public int compareTo(FlowBean o) {
        long thisV = this.sumFlow;
        long thatV = o.getSumFlow();
        return thisV > thatV ? -1 : 1;
    }
}
