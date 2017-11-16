/**
 * Description : A faire
 *
 * @author Thierry Mater
 * @version 1.0.0 (30/05/2017)
 */
import java.util.*;
import java.io.*;

public class Gestion
{
    // variables d'instance
    private TraitementDate dateDeTravail;
    private Dossier dossierDeTravail;
    private FileWritter fichierDeTravail;
    private TraitementFichier fichierHH;
    private List<String> listeDeFichier = new ArrayList<String>();
    private List<ModelTournois> sousListeDeFichier = new ArrayList<ModelTournois>();
    private String date = "";
    private boolean bDate = false;
    private int tailleSousListeDeFichier = 0;
    private String argRepertoire;

    /**
     * Constructeur d'objets de classe Gestion
     */
    public Gestion()
    {
        dateDeTravail = new TraitementDate();
        dossierDeTravail = new Dossier();
        fichierDeTravail = new FileWritter();
        fichierHH = new TraitementFichier();
    }

    /**
     * Méthode travail sans paramètre
     *
     * Permet la mise à jour de la liste des fichiers présent dans le dossier des HH
     */
    public void travail() throws IOException
    {
        argRepertoire = "D:\\001_PAA\\010_Programme\\hud-paa\\trunk\\Java\\HH";
        travail(argRepertoire);
    }

    /**
     * Méthode travail avec pamatètre
     *
     * Permet la mise à jour de la liste des fichiers présent dans le dossier des HH
     * @param repertoire Un paramètre
     */
    public void travail(String repertoire) throws IOException
    {
        argRepertoire = repertoire;
        String selectionDate = dateDeTravail.selectionDate(repertoire);

        listeDeFichier = dossierDeTravail.lectureDossier(repertoire);

        if(selectionDate.contains("99999999"))
        {
            date = listeDeFichier.get(0).substring(0, 8);
            bDate = false;
        }
        else
        {
            date = selectionDate;
            bDate= true;
        }
    }

    /**
     * Méthode extractionList
     *
     */
    public void extractionList() throws IOException
    { 
        if(bDate)
        {
            sousListeDeFichier.clear();
            dateDeTravail.selectionTournois(argRepertoire, sousListeDeFichier);
        }
        else
        {
            Iterator<String> iterator = listeDeFichier.iterator();
            while (iterator.hasNext())
            {
                String[] nomFichier = iterator.next().split(";");

                if(nomFichier[0].contains(date) && !nomFichier[0].contains("_summary"))
                {
                    sousListeDeFichier.add(new ModelTournois(nomFichier[0], nomFichier[1], false, 0));
                }
            }
        }

        tailleSousListeDeFichier = sousListeDeFichier.size();
    }

    /**
     * Méthode traitementHH
     *
     */
    public void traitementHH() throws IOException
    {
        int compteur = 0;
        int ligne;
        String tournoisSummary = "";

        Iterator<ModelTournois> it = sousListeDeFichier.iterator();

        while(it.hasNext()){
            ModelTournois tournois = it.next();

            if(!tournois.getSummary())
            {
                // traitement du fichier HH et retourne la derniere ligne lue
                // utile si le fichier est modifié ulterieurement
                ligne = fichierHH.selectionFichier(argRepertoire, 
                    tournois.getNomTournois(), tournois.getNmrLigne());

                // enregistre la derniere ligne lue
                tournois.setNmrLigne(ligne);

                // recherche si le fichier summary existe
                tournoisSummary = tournois.getNomTournois().replace(".txt", "_summary.txt");

                Iterator<String> iterator = listeDeFichier.iterator();
                while (iterator.hasNext())
                {
                    String nomFichier = iterator.next();
                    if(nomFichier.contains(tournoisSummary))
                    {
                        tournois.setSummary(true);
                        compteur = compteur + 1;
                    }
                }
            }
            //else
            //compteur = compteur + 1;
        }

        if(compteur == tailleSousListeDeFichier)
        {
            bDate = false;

            // suppression des fichiers de la sous liste
            Iterator<ModelTournois> itsl = sousListeDeFichier.iterator();
            while(itsl.hasNext()){
                ModelTournois tournois = itsl.next();

                String fichierHH = argRepertoire + "\\" + tournois.getNomTournois();
                String fichierHHs = argRepertoire + "\\" + tournois.getNomTournois().replace(".txt", "_summary.txt"); 

                // test de suppression
                File suppressionHH = new File(fichierHH);

                if (suppressionHH.delete())
                {
                    new Log("Class Gestion : public void traitementHH() throws IOException");
                    new Log("Suppression du fichierHH " + fichierHH + " réussi.");
                }
                else
                {
                    new Log("Class Gestion : public void traitementHH() throws IOException");
                    new Log("Suppression du fichierHH " + fichierHH + " a échoué.");
                }

                File suppressionHHs = new File(fichierHHs);
                if (suppressionHHs.delete())
                {
                    new Log("Class Gestion : public void traitementHH() throws IOException");
                    new Log("Suppression du fichierHH " + fichierHHs + " réussi.");
                }
                else
                {
                    new Log("Class Gestion : public void traitementHH() throws IOException");
                    new Log("Suppression du fichierHH " + fichierHHs + " a échoué.");
                }
            }
            // remise à zéro de la date et de la sous liste
            date = "99999999";
            sousListeDeFichier.clear();

        }
        // enregistement de la liste de la date et des fichiers à traiter si encore présent
        enregistrementList();

        // mise à jour des fichiers à traiter si besoin
        travail(argRepertoire);
        extractionList();
    }

    /**
     * Méthode enregistrementList
     *
     */
    public void enregistrementList()
    {
        fichierDeTravail.exportFichier(argRepertoire, date, sousListeDeFichier);
    }
}
