/**
 *
 * @author (Thierry Mater
 * @version 1.0.0 (27 mai 2017)
 */
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class TraitementDate
{
    private FileReader fichierArchive;

    /**
     * Constructeur d'objets de classe TraitementDate
     */
    public TraitementDate()
    {
        fichierArchive = new FileReader();
    }

    /**
     * Méthode selectionDate
     *
     * @return La valeur de retour
     */
    public String selectionDate(String repertoire) throws IOException
    {
        InputStream fstream = fichierArchive.openFile(repertoire + "\\fichier.fic");

        BufferedReader in = new BufferedReader(new InputStreamReader(fstream));

        String ligne = in.readLine();

        return ligne;
    }

    /**
     * Méthode selectionTournois
     *
     * @param listeTournois Un paramètre
     */
    public void selectionTournois(String repertoire, List listeTournois) throws IOException
    {
        String[] tournois;
        boolean summary = false;
        InputStream fstream = fichierArchive.openFile(repertoire + "\\fichier.fic");

        BufferedReader in = new BufferedReader(new InputStreamReader(fstream));

        String ligne = in.readLine();
        ligne = in.readLine();

        while(ligne != null)
        {
            tournois = ligne.split(";");
            if(tournois[2].contains("true"))
                summary = true;
            else
                summary = false;

            listeTournois.add(new ModelTournois(tournois[0], tournois[1], summary, Integer.parseInt(tournois[3])));
            ligne = in.readLine();
        }
    }
    
    /**
     * Méthode dateSuivante
     *
     * @param dateAncienne Un paramètre
     * @return La valeur de retour
     */
    public String dateSuivante(String dateAncienne)
    {
        int iDateA = Integer.parseInt(dateAncienne) + 1;

        return Integer.toString(iDateA);
    }
}
