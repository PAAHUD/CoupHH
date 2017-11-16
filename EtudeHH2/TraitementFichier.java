/**
 * Décrivez votre classe TraitementFichier ici.
 *
 * @author (Thierry Mater
 * @version 1.0.0 (27 mai 2017)
 */
import java.io.*;

public class TraitementFichier
{
    private FileReader fichierArchive;
    private Grafcet grafcetHH;

    /**
     * TraitementFichier Constructeur
     *
     */
    public TraitementFichier()
    {
        fichierArchive = new FileReader();
    }

    /**
     * Méthode selectionFichier
     * 
     *
     * @param fichier Un paramètre
     * @return La valeur de retour
     */
    public int selectionFichier(String dossier, String fichier, int derniereLigne) throws IOException
    {
        int compteur = 0;
        grafcetHH = new Grafcet(dossier);

        String dossierFichier = dossier + "//" + fichier;
        InputStream fstream = fichierArchive.openFile(new File(dossierFichier).getAbsolutePath());

        BufferedReader in = new BufferedReader(new InputStreamReader(fstream));

        String ligne = in.readLine();

        new Log("Class TraitementFichier : public int selectionFichier(String " + dossier + ", String " + fichier + ", int " + derniereLigne + ") throws IOException");
        new Log("Traitement du fichier");

        while(ligne != null)
        {
            compteur = compteur +1;
            if(compteur > derniereLigne)
                grafcetHH.etudeGrafcet(ligne);

            ligne = in.readLine();
        }
        in.close();

        new Log("Fin du traitement du fichier nombre de ligne lue :" + compteur);
        return compteur;
    }    
}
