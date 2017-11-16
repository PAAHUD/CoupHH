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
    private boolean boolDossier;

    /**
     * Dossier Constructeur
     *
     */
    public Dossier()
    {
        fichiers = new ArrayList<String>();
        boolDossier = false;
    }

    public void enumDossier(String repertoire) throws IOException
    {
        Path path = Paths.get(repertoire);
        File f = new File(path.toString());

        String xml = "";
        String archive = "";
        String resultat = "";

        if(!f.exists())
        {
            System.out.println("Attention le dossier n'existe pas");
        }

        // Création d'une liste du contenu du dossier
        dossier = new java.io.File(f.toString()).listFiles();

        for (int i=0; i<dossier.length; i++)
        {
            if(dossier[i].isDirectory())
            {
                if (dossier[i].getName().contains("xml"))
                {
                    xml = dossier[i].toString();
                    break;
                }
            }
        }

        dossier = new java.io.File(xml).listFiles();

        for (int i=0; i<dossier.length; i++)
        {
            if(dossier[i].isDirectory())
            {
                if (dossier[i].getName().contains("archive"))         
                    archive = dossier[i].toString();

                if (dossier[i].getName().contains("resultat"))         
                    resultat = dossier[i].toString();
            }
        }

        for(CheminDossier chemin: CheminDossier.values())
        {
            switch(chemin.getId())
            {
                case 0:
                chemin.setChemin(repertoire);
                break;
                case 1:
                chemin.setChemin(archive);
                break;
                case 2:
                chemin.setChemin(resultat);
                break;
                default:
                break;
            }
            boolDossier = true;
        }
    }

    /**
     * Méthode lectureDossier
     *
     * @param repertoire Un paramètre
     * @return La valeur de retour
     */
    public List lectureDossier(String repertoire) throws IOException
    {
        if(!boolDossier)
            enumDossier(repertoire);

        // Mise en forme de la chaine String en chaine adresse
        Path path = Paths.get(repertoire);
        // 
        File f = new File(path.toString());

        if(!f.exists())
        {
            System.out.println("Attention le dossier n'existe pas");
        }

        // Création d'une liste du contenu du dossier
        dossier = new java.io.File(f.toString()).listFiles();

        fichiers.clear();

        for (int i=0; i<dossier.length; i++) 
        {
            // Enregistrement des informations de chaque élément
            if(dossier[i].isFile())
            {
                if (dossier[i].getName().contains(".xml"))         
                    fichiers.add(dossier[i].getName());
            }
        }

        Collections.sort(fichiers);

        return fichiers;
    }
}
