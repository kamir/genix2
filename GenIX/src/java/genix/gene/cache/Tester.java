/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genix.gene.cache;

import genix.core.HBaseTester;
import data.io.adapter.HBaseGeneAdapter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamir
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        HBaseTester.main(args);
        HBaseGeneAdapter a = HBaseGeneAdapter.init();
        
        String k = "MyGen";
        String v = "IX";
        try {
            a.putGene(k.getBytes(), v.getBytes() );
        
            String r = new String( a.getGene( k.getBytes() ) );
        
            System.out.println( k + " " + r );
        } 
        catch (Exception ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
