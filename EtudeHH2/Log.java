import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class permettant l'enregistrement des erreurs dans un fichier
 *
 * @author PAA Thierry Mater
 * @version 0.0.1beta du 11/10/17
 */
public class Log
{
    // variables d'instance

    /**
     * Ouvre le fichier et ajouter une ligne de texte
     * @param text
     */
    public Log(String text)
    {
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        String filename = "EtudeCoupLog.log";// + new java.util.Date( ) + ".log";
        try {
            fileWriter = new FileWriter(filename, true);
            bufWriter = new BufferedWriter(fileWriter);
            //Insérer un saut de ligne
            bufWriter.newLine();
            bufWriter.write(text);
            bufWriter.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            try 
            {
                bufWriter.close();
                fileWriter.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}