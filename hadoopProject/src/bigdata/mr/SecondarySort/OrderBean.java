package bigdata.mr.SecondarySort;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    private Text itemId;
    private DoubleWritable amount;

    public OrderBean() {

    }

    public void set(Text itemId, DoubleWritable amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public Text getItemId() {
        return itemId;
    }

    public void setItemId(Text itemId) {
        this.itemId = itemId;
    }

    public DoubleWritable getAmount() {
        return amount;
    }

    public void setAmount(DoubleWritable amount) {
        this.amount = amount;
    }


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(itemId.toString());
        dataOutput.writeDouble(amount.get());
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.itemId = new Text(dataInput.readUTF());
        this.amount = new DoubleWritable(dataInput.readDouble());
    }

    @Override
    public int compareTo(OrderBean o) {
        int cmp = this.itemId.compareTo(o.getItemId());
        if (cmp == 0) { //若订单号相同，则比较价格
            cmp = -this.amount.compareTo(o.getAmount()); //加负号是为了反序
        }
        return cmp;
    }

    @Override
    public String toString() {
        return itemId + "\t" + amount ;
    }
}
