/**
 * Description : A faire
 *
 * @author Thierry Mater
 * @version 1.0.0 (30/05/2017)
 * 
 * *********************************************************************************
 *  Version 0.0.3beta
 *      19/10/17 : utilisation champ Enum CheminDossier pour la gestion des dossiers
 *          archivage et resultat pour tenir compte des différents système 
 *          d'exploitation au niveau des séparateurs
 * *********************************************************************************
 */
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gestion
{
    // variables d'instance
    private Dossier dossierDeTravail;
    //private TraitementFichier fichierCoup;
    private List<String> listeDeFichierXML = new ArrayList<String>();
    private ReadXMLCoup fichierCoup;
    private Calcul calculCoup;
    private String argRepertoire;
    private Hashtable<Integer, String> donnee;

    /**
     * Constructeur d'objets de classe Gestion
     */
    public Gestion()
    {
        dossierDeTravail = new Dossier();
        //fichierCoup = new TraitementFichier();
        fichierCoup = new ReadXMLCoup();
        donnee = new Hashtable<Integer, String>();
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
        calculCoup = new Calcul(argRepertoire);
        listeDeFichierXML = dossierDeTravail.lectureDossier(repertoire);

    }

    /**
     * Méthode traitementXML
     *
     */
    public void traitementXML() throws IOException, Exception
    {
        int compteur = 0;

        Iterator<String> it = listeDeFichierXML.iterator();

        while(it.hasNext())
        {
            String fichierXML = it.next();

            String fichierATraiter = CheminDossier.hh.getChemin() + File.separator + fichierXML;
            System.out.println("fichier à traiter : " + fichierATraiter);

            fichierCoup.xmlFile(fichierATraiter);
            System.out.println("fin de fichierCoup.xmlFile : " + fichierATraiter);

            calculCoup.CalculAccident(CheminDossier.resultat.getChemin(), fichierXML);
            System.out.println("fin de calculCoup.CalculAccident : \n" + CheminDossier.resultat.getChemin() + " et " + fichierXML);

            // transfert du fichier
            String fichierSource = fichierATraiter;
            System.out.println("fichier source : " + fichierATraiter);

            String fichierArchive = CheminDossier.archive.getChemin() + File.separator + fichierXML;
            System.out.println("fichier archive : " + fichierArchive);

            listeDeFichierXML.remove(it);

            Path source = Paths.get(fichierSource);
            Path destination = Paths.get(fichierArchive);
            Files.move(source, destination); // Ecrase le fichier destination s'il existe déjà.

            donnee.clear();
            for(eJoueur game: eJoueur.values())
                game.raz();
        }

        // mise à jour des fichiers à traiter si besoin
        travail(argRepertoire);
    }
}
