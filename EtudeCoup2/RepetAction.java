import java.util.Timer;
import java.util.TimerTask;

/**
 * Description : A faire
 *
 * @author Thierry Mater
 * @version 0.0.2beta (11/10/2017)
 *  ajout des erreurs dans un fichier Log
 */

public class RepetAction
{
    // variables d'instance
    Timer t;
    private String dossierXML;
    private Gestion gestionDossier = new Gestion();

    public RepetAction(String repertoire)
    {

        dossierXML = repertoire;

        try
        {
            gestionDossier.travail(dossierXML);
        }
        catch (Exception e)
        {
            new Log("Class RepetAction : RepetAction(String " + repertoire +")");
            new Log("Impossible de lire le dossier");
            //new Log(e.getMessage());
        }

        t = new Timer();
        t.schedule(new GestionHH(), 1*1000, 10*1000);
    }

    public void stop()
    {
        t.cancel();
    }

    class GestionHH extends TimerTask
    {
        public void run()
        {
            try
            {
                gestionDossier.traitementXML();
            }
            catch (Exception e)
            {
                new Log("Class GestionHH extends TimeTask : void run()");
                new Log("Erreur dans le traitement des XML");
                //new Log(e.getMessage());
            }
        }
    }
}
