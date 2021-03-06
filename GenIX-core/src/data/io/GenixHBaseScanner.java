package data.io;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Some table statistics is calculated in this module.
 *
 * @author kamir
 */
public class GenixHBaseScanner {



    public static void main(String[] args) throws IOException {
        
    // You need a configuration object to tell the client where to connect.
    // When you create a HBaseConfiguration, it reads in whatever you've set
    // into your hbase-site.xml and in hbase-default.xml, as long as these can
    // be found on the CLASSPATH
    Configuration config = HBaseConfiguration.create();
    
    config.set("hbase.zookeeper.quorum", genix.cfg.CFG.hbase_zookeeper_quorum);  // Here we are running zookeeper locally
    config.set("hbase.zookeeper.property.clientPort", genix.cfg.CFG.hbase_zookeeper_property_clientPort);  // Here we are running zookeeper locally
    
    
    String tabName = genix.cfg.CFG.test_tab_name;

    initTable( config , tabName );
    
    // This instantiates an HTable object that connects you to
    // the "myLittleHBaseTable" table.
    HTable table = new HTable(config, tabName );
 

    // Sometimes, you won't know the row you're looking for. In this case, you
    // use a Scanner. This will give you cursor-like interface to the contents
    // of the table.  To set up a Scanner, do like you did above making a Put
    // and a Get, create a Scan.  Adorn it with column names, etc.
    /**
     * 
     * gfr = "gene file raw" ... this an unparsed gene-file, and all it's 
     *       compartements.
     */
    Scan s = new Scan();
    s.addColumn(Bytes.toBytes("gfr"), Bytes.toBytes("raw"));
    ResultScanner scanner = table.getScanner(s);
    try {
      // Scanners return Result instances.
      // Now, for the actual iteration. One way is to use a while loop like so:
      for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
        // print out the row we found and the columns we were looking for
        System.out.println("Found row: " + rr);
      }

      // The other approach is to use a foreach loop. Scanners are iterable!
      // for (Result rr : scanner) {
      //   System.out.println("Found row: " + rr);
      // }
    } finally {
      // Make sure you close your scanners when you are done!
      // Thats why we have it inside a try/finally clause
      scanner.close();
    }
  }

    private static void initTable(Configuration config, String name) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
        HBaseAdmin hbase = new HBaseAdmin( config );
        HTableDescriptor desc = new HTableDescriptor( name ); 
        HColumnDescriptor meta = new HColumnDescriptor("gfr".getBytes());
        HColumnDescriptor prefix = new HColumnDescriptor("raw".getBytes());
        desc.addFamily(meta);
        desc.addFamily(prefix);
        hbase.createTable(desc);
    }
}

    
