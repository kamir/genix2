/**
 *
 * High level adapter for storing and retrieving Time Series from HBase.
 *
 */
package data.io.adapter;

import data.io.adapter.HBaseAdapter;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author kamir
 */
public class HBaseGeneAdapter {

    private HBaseGeneAdapter() {
        // You need a configuration object to tell the client where to connect.
        // When you create a HBaseConfiguration, it reads in whatever you've set
        // into your hbase-site.xml and in hbase-default.xml, as long as these can
        // be found on the CLASSPATH
        config = HBaseConfiguration.create();

        config.set("hbase.zookeeper.quorum", defaultZookeeperIP);  // Here we are running zookeeper locally
        config.set("hbase.zookeeper.property.clientPort", defaultZookeeperPORT);  // Here we are running zookeeper locally

    }
    
    static Configuration config = null;

    static String defaultZookeeperIP = genix.cfg.CFG.hbase_zookeeper_quorum;
    static String defaultZookeeperPORT = genix.cfg.CFG.hbase_zookeeper_property_clientPort;
    static String tabName = genix.cfg.CFG.test_tab_name;
    
    static HBaseGeneAdapter hba = null;
    static HTable table = null;

    /**
     * Connect to a Zookeeper, who knows the location of an HBase Master.
     *
     * @param zk
     */
    public static HBaseGeneAdapter init() {
        if (hba == null) {
            hba = new HBaseGeneAdapter();

            try {
              initTable( config , tabName );
              System.out.println("!!! NEW table !!!");  
            }
            catch(Exception ex) {
              System.out.println("***> !!! no new table !!!");  
              System.out.println("***> " + ex.getMessage());  

            }
            
            try {
                // This instantiates an HTable object that connects you to
                // the "myLittleHBaseTable" table.
                hba.table = new HTable(config, tabName);

                String k = "Gen";
                String v = "IX";

                System.out.println("> work with table : " + table);


                System.out.println("> put ... ");
                putGene(k.getBytes(), v.getBytes());

                System.out.println("> get ... ");
                String r = new String(getGene(k.getBytes()));

                System.out.println(">>>" + k + " " + r);

            } catch (Exception ex) {
                Logger.getLogger(HBaseGeneAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        return hba;
    }

    /**
     *
     * the object can be a Messreihe.
     *
     * @param data
     * @param pageID
     */
    public static void putGene(byte[] k, byte[] v) throws IOException, Exception {

        // To add to a row, use Put.  A Put constructor takes the name of the row
        // you want to insert into as a byte array.  In HBase, the Bytes class has
        // utility for converting all kinds of java types to byte arrays.  In the
        // below, we are converting the String "pageID" into a byte array to
        // use as a row key for our update. Once you have a Put instance, you can
        // adorn it by setting the names of columns you want to update on the row,
        // the timestamp to use in your update, etc.If no timestamp, the server
        // applies current time to the edits.

//        System.out.println(">> k     " + new String( k ) );
//        System.out.println(">> v     " + new String( v ) );
//        System.out.println(">> hba   " + hba == null);
//        System.out.println(">> table " + hba.table == null);

        Put p = new Put(k);

        // To set the value you'd like to update in the row "pageID", specify
        // the column family, column qualifier, and value of the table cell you'd
        // like to update.  The column family must already exist in your table
        // schema.  The qualifier can be anything.  All must be specified as byte
        // arrays as hbase is all about byte arrays.  Lets pretend the table
        // 'wikinodes' was created with a family 'accessts'.
        p.add(Bytes.toBytes("gfr"), Bytes.toBytes("raw"), v);

        // Once you've adorned your Put instance with all the updates you want to
        // make, to commit it do the following (The HTable#put method takes the
        // Put instance you've been building and pushes the changes you made into
        // hbase)
        hba.table.put(p);

    }
    
    public static void initTable(Configuration config, String name) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
        HBaseAdmin hbase = new HBaseAdmin( config );
        HTableDescriptor desc = new HTableDescriptor( name ); 
        HColumnDescriptor raw = new HColumnDescriptor("gfr".getBytes());
        HColumnDescriptor parsed = new HColumnDescriptor("pars".getBytes());
        HColumnDescriptor patterns = new HColumnDescriptor("patt".getBytes());
        desc.addFamily(raw);
        desc.addFamily(parsed);
        desc.addFamily(patterns);
        hbase.createTable(desc);
    }

    public static byte[] getGene(byte[] key) throws IOException, Exception {

        // Now, to retrieve the data we just wrote. The values that come back are
        // Result instances. Generally, a Result is an object that will package up
        // the hbase return into the form you find most palatable.
        Get g = new Get(key);
        Result r = hba.table.get(g);
        byte[] value = r.getValue(Bytes.toBytes("gfr"), Bytes.toBytes("raw"));

        if (value == null) {
            value = "NULL".getBytes();
        }
        return value;
    }

    /**
     * Is a gene already stored?
     *
     * @param key = genename (filename)
     * @return
     * @throws IOException
     */
    public boolean hasGene(byte[] k) throws IOException {
        // Now, to retrieve the data we just wrote. The values that come back are
        // Result instances. Generally, a Result is an object that will package up
        // the hbase return into the form you find most palatable.
        Get g = new Get(k);
        g.setMaxVersions(1);
        g.setFilter(new KeyOnlyFilter());

        boolean ret = false;

        Result r = hba.table.get(g);

        byte[] value = r.getValue(Bytes.toBytes("gfr"), Bytes.toBytes("raw"));

        if (value == null) {
            value = "NULL".getBytes();
        }
        return ret;
    }
}
