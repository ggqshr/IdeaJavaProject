package Hbase;

import com.google.inject.servlet.ServletScopes;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Table;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HbsetTest {
    static Configuration config;
    Connection connection;
    Table tab;

    @Before
    public void init() throws IOException {
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "Mini1,Mini2,Mini3");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        connection = ConnectionFactory.createConnection(config);
        tab = connection.getTable(TableName.valueOf("user"));
    }

    /**
     * 创建表
     * @throws IOException
     */
    @Test
    public void createtable() throws IOException {
        HBaseAdmin admin = new HBaseAdmin(config);
        TableName tableName = TableName.valueOf("test1");
        HTableDescriptor desc = new HTableDescriptor(tableName);
        HColumnDescriptor f1 = new HColumnDescriptor("info");
        desc.addFamily(f1);
        admin.createTable(desc);
    }
}
