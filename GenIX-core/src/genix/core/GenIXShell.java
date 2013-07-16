/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genix.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamir
 */
public class GenIXShell {
    
    public static final String version = "0.0.1";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print(">>>  GenIX.Shell   ");
        System.out.println("[Version:  " + version + "]" );
        System.out.println("[ZK.server] " + genix.cfg.CFG.hbase_zookeeper_quorum + ";" );
        System.out.println("[ZK.port]   " + genix.cfg.CFG.hbase_zookeeper_property_clientPort + ";" );
        
        
        
        // show a hotspot on a Node
        // TDOO: use sample code from HBase Admin Coockbook
        findHotSpot();
        
        
        test();
        
    }

    private static void findHotSpot() {
        System.out.println("* findHotSpot() ... Not yet implemented");
    }

    private static void test() {
        try {
            System.out.println("* run HBaseTester.main(null) ... ");
            HBaseTester.main(null);
        } 
        catch (IOException ex) {
            Logger.getLogger(GenIXShell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
