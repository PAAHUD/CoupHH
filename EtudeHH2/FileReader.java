/**
 * Décrivez votre classe FileReader ici.
 *
 * @author Thierry Mater
 * @version 1.0.0 (30/05/2017)
 */
import java.io.*;
import java.lang.*;
import java.net.URL;
import java.net.URI;

public class FileReader
{
    /**
     * FileReader Constructeur
     *
     */
    public FileReader()
    {
        
    }

    /**
     * Méthode showFile
     *
     * @param fileName Un paramètre
     */
    public void showFile(String fileName)
    throws IOException
    {
        InputStream fstream = openFile(fileName);

        BufferedReader in = new BufferedReader(new InputStreamReader(fstream));

        System.out.println("File: " + fileName);
        String ligne = in.readLine();
        while(ligne != null) {
            System.out.println(ligne);
            ligne = in.readLine();
        }
        System.out.println("<fin du fichier>");
    }

    /**
     * Méthode checkedShowFile
     *
     * @param fileName Un paramètre
     */
    public void checkedShowFile(String fileName)
    {
        try {
            showFile(fileName);
        }
        catch(IOException exc) {
            System.out.println("Il y a un problème.");
            System.out.println("L'erreur est : ");
            System.out.println(exc);
        }
    }

    /**
     * Méthode openFile
     *
     * @param fileName Un paramètre
     * @return La valeur de retour
     */
    public InputStream openFile(String fileName)
    throws IOException
    {
        if(fileName == null)
            throw new IOException("ouverture du fichier impossible - filename est null.");

        String tempFileName = fileName;

        if(tempFileName.contains("\\"))
            fileName = "file:///" + tempFileName.replace("\\", "//");
        else
            fileName = "file:///" + tempFileName;

        //URL url = getClass().getClassLoader().getResource(fileName);
        URL url = new URL(fileName);
        //URL url = fileName.toURI().toURL();
        
        if(url == null)
            throw new IOException("fichier nom trouvé : " + fileName);
        return url.openStream();
    }
}
