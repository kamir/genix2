/*
 * The Genix file importer moves gene data to the GenIX.DB.
 * 
 * We import a single file :    importGeneFile( String path, String filename )
 * or a full directory     :    importGeneFiles( String path )
 */
package data.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamir
 */
public class GenixFileImporter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String mode = args[0];
        String path = args[1];
        String file = null;
        if( mode.equals("file") ) {
            file = args[2];
            File fi = new File( path, file );
            importFile( fi );
        }
        else {
            File f = new File( path );
            File[] list = f.listFiles();
            for( File fi : list ) importFile( fi );
        }
        
    }

    private static void importFile(File f) {
        try {
            String data[] = GenixFileProvider.getGeneFileData( f );
        
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenixFileImporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenixFileImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
