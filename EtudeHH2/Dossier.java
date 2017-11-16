/**
 * Décrivez votre classe Dossier ici.
 *
 * @author Thierry MATER
 * @version 1.0.0 (26 mars 2017)
 */
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Collections;

public class Dossier
{
    private File[] dossier;
    private List<String> fichiers;

    /**
     * Dossier Constructeur
     *
     */
    public Dossier()
    {
        fichiers = new ArrayList<String>();
    }

    /**
     * Méthode lectureDossier
     *
     * @param repertoire Un paramètre
     * @return La valeur de retour
     */
    public List lectureDossier(String repertoire) throws IOException 
    {

        // Mise en forme de la chaine String en chaine adresse
        Path path = Paths.get(repertoire);
        // 
        File f = new File(path.toString());

        if(!f.exists())
        {
            new Log("Class Dossier : public List lectureDossier(String " + repertoire +") throws IOException");
            new Log("Attention le dossier n'existe pas");
        }

        // Création d'une liste du contenu du dossier
        dossier = new java.io.File(f.toString()).listFiles();

        fichiers.clear();

        for (int i=0; i<dossier.length; i++) 
        {
            // Enregistrement des informations de chaque élément
            if(dossier[i].isFile())
            {
                if (!dossier[i].getName().contains("fichier.fic") && !dossier[i].getName().contains(".xsl") && !dossier[i].getName().contains(".xml"))         
                    fichiers.add(dossier[i].getName() + ";" + String.valueOf(dossier[i].lastModified()));
            }
        }

        Collections.sort(fichiers);

        return fichiers;
    }
}
