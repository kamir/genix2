/**
 * 
 * The Genix file provider gives us the gene files, stored in
 * local file system.
 * 
 * We import a single file :    importGeneFile( String path, String filename )
 * or a full directory     :    importGeneFiles( String path )
 */
package data.io;

import java.io.*;

/**
 *
 * @author kamir
 */
public class GenixFileProvider {

    /**
     * 
     * @param f
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    static String[] getGeneFileData(File f) throws FileNotFoundException, IOException {
        String[] dat = new String[2];
        dat[0] = f.getName();
        StringBuffer sb = new StringBuffer();
        FileReader fr = new FileReader( f );
        BufferedReader br = new BufferedReader( fr ); 
        while( br.ready() ) {
            sb.append(br.readLine());
            sb.append("\n");
        }
        dat[1] = sb.toString();
        return dat;
    }
    
    /**
     * 
     * @param path
     * @param filename
     * @return 
     */
    public String getGeneFileData( String path, String filename ) {
        return getSampleFileContent();
    }
            
    /**
     * low level tests ...
     * @return 
     */
    private static String getSampleFileContent() { 
        return "A nice gene ...";
    }
    private static String getRandomKeyName() {
        int rand = (int)(Math.random() * 10000.0);
        return System.currentTimeMillis() + "_random_gene_name_" + rand;
    }
    public static String[] getSample() {
        String[] dat = new String[2];
        dat[0] = getRandomKeyName();
        dat[1] = getSampleFileContent();
        return dat;
    }
    
    
}
