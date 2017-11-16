import java.io.IOException;

/**
 * Description : Classe principal
 *
 * @author Thierry Mater
 * @version 1.0.0 (30/05/2017)
 * 
 * **************************************************
 * 18/10/17
 *      version 0.0.3beta
 * **************************************************
 */
public class Main
{
    // variables d'instance
    private static String repertoire;

    //public static void main(String args[]){
    //    
       
    public static void main(String arg[]) throws IOException
    {
         //Récupération du chemin d'accés au répertoire des HH

        if(arg[0] != null)
        {
            repertoire = arg[0];
        }
        else
        {
            repertoire = "D:\\001_PAA\\010_Programme\\hud-paa\\trunk\\Java\\HH";
        }

        // Vérification et lecture si existant du fichier des archives HH
        /**
        * Version serveur
        */
        //if(arg[1].contains("true"))
        //Traitement des fichiers HH
        //@SuppressWarnings("unused")
        RepetAction activationTraitement = new RepetAction(repertoire);
    }
}
