package bigdata.mr.LogTrans;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogBean implements WritableComparable<LogBean> {
    private String time;
    private String ipAddr;
    private String session;
    private String request;
    private String referal;

    public LogBean() {
    }

    @Override
    public String toString() {
        return time + '\t' + ipAddr + '\t' + session + '\t' + request + '\t' + referal;
    }

    public void set(String time, String ipAddr, String session, String request, String referal) {
        this.time = time;
        this.ipAddr = ipAddr;
        this.session = session;
        this.request = request;
        this.referal = referal;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getReferal() {
        return referal;
    }

    public void setReferal(String referal) {
        this.referal = referal;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(time);
        dataOutput.writeUTF(ipAddr);
        dataOutput.writeUTF(session);
        dataOutput.writeUTF(request);
        dataOutput.writeUTF(referal);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        time = dataInput.readUTF();
        ipAddr = dataInput.readUTF();
        session = dataInput.readUTF();
        request = dataInput.readUTF();
        referal = dataInput.readUTF();
    }

    @Override
    public int compareTo(LogBean o) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        try {
            Date thisD = dateFormat.parse(this.time);
            Date thatD = dateFormat.parse(o.getTime());
            return thisD.before(thatD)?-1:(thisD.equals(thatD)?0:1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
