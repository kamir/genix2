/**
 *  The HBaseAdapter is a class, which will be instanziated in MATLAB or other
 *  systems, to implement a direct link to an HBase cluster.
 * 
 *  Just define the connection to the Zookeeper server and start putting or 
 *  getting data from HBase. As all data is just binary, we have use some 
 *  higher abstraction layer on top of the adapter to map data structures 
 *  the right way.
 * 
 *  So we can easily handle networks as lists or arrays, dependent on the
 *  algorithm which will be applied to the data.
 * 
 */
package data.io.adapter;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author kamir
 */
public class HBaseAdapter {
    
    String zookeeperIP = genix.cfg.CFG.hbase_zookeeper_quorum;
    
    Configuration config = null;
    
    public HBaseAdapter( String theZookeeperIP ) {
        zookeeperIP = theZookeeperIP;
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", zookeeperIP );  
    }
    
    /**
     * 
     * @param tableName
     * @return
     * @throws IOException
     * @throws Exception 
     */
    public HTable getTable( String tableName ) throws IOException, Exception {
        if ( config != null ) {
            HTable table = new HTable(config, tableName );
            return table;
        }
        else { 
            throw new Exception( "> No config object in " + this.getClass() );
        }
    }
    

}
