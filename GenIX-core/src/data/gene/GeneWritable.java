/**
 *
 * Our simplest representation of Gene is the
 * content of a gene-file, which has to be parsed.
 * 
 **/
package data.gene;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Properties;
import org.apache.hadoop.io.Writable;

/**
 *
 * @author kamir
 */
public class GeneWritable implements Writable {
    
    public double[] data;
    public GeneWritable( int nrOfvalues ) {
        data = new double[nrOfvalues];
        for( int i = 0; i < nrOfvalues; i++ ) {
            data[i] = 0.0;
        }
    }
    
    public Properties props = new Properties();
    
    public static GeneWritable initRandomSeries( int z ) {
        GeneWritable mapper = new GeneWritable( z );
        for( int i = 0; i < z; i++ ) {
            mapper.data[i] = Math.random();
        }
        return mapper;
    }

    @Override
    public void write(DataOutput d) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
