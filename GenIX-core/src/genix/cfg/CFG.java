/**
 * 
 * All APP Cfg Parameters go here!
 * 
 * TODO: - make it flexible with a parsable CFG file
 * 
 */
package genix.cfg;

/**
 *
 * @author kamir
 */
public class CFG {
    

    // hbase.zookeeper.property.clientPort
    public static String hbase_zookeeper_property_clientPort = "2181";
    
    // hbase.zookeeper.quorum
//    public static String hbase_zookeeper_quorum = "localhost";
    public static String hbase_zookeeper_quorum = "localhost.localdomain";
//    public static String hbase_zookeeper_quorum = "172.16.14.225";

    /**
     * Just for some tests
     */
    public static String test_tab_name = "genixtest";
    
}
