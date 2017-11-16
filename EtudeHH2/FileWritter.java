/**
 * Décrivez votre classe FileWritter ici.
 * 
 * @author Thierry MaTer
 * @version 1.0 (30 mai 2017)
 */
import java.io.*;
import java.lang.*;
import java.util.*;
public class FileWritter
{
    /**
     * FileWritter Constructeur
     *
     */
    public FileWritter()
    {

    }

    /**
     * Méthode exportDate
     *
     * @param date Un paramètre
     */
    public void exportDate(String repertoire, String date)
    {
        File f = new File(repertoire + "\\fichier.fic");

        try 
        {
            FileWriter fw = new FileWriter(f);

            fw.write(date);
            fw.flush();

            fw.close();
        } 
        catch (IOException e) 
        {
            new Log("Class FileWritter : public void exportDate(String " + repertoire + ", String " + date + ")");
            new Log("erreur lors de l'écriture du fichier fichier.fic");
            new Log(e.getMessage());
        }
    }

    /**
     * Méthode exportFichier
     *
     * @param date Un paramètre
     * @param dossier Un paramètre
     */
    public void exportFichier(String repertoire, String date, List dossier)
    {
        File f = new File(repertoire + "\\fichier.fic");

        try 
        {
            FileWriter fw = new FileWriter(f);

            fw.write(date + "\n");
            fw.flush();

            //On récupère un Iterator
            Iterator<ModelTournois> it = dossier.iterator();
            while(it.hasNext())
            {
                String str = it.next().ecriture();
                fw.write(str + "\n");
                fw.flush();
            }

            fw.close();
        } 
        catch (IOException e)
        {
            new Log("Class FileWritter : public void exportFichier(String " + repertoire +", String " + date + ", List " + dossier + ")");
            new Log("erreur lors de l'écriture du fichier fichier.fic");
            new Log(e.getMessage());
        }
    }
}
