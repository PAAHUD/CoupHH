import java.util.Timer;
import java.util.TimerTask;

/**
 * Description : A faire
 *
 * @author Thierry Mater
 * @version 1.0.0 (30/05/2017)
 */

public class RepetAction
{
    // variables d'instance
    Timer t;
    private String fichierArchive = "fichier.fic";
    private String dossierHH;
    private Gestion gestionDossier = new Gestion();

    public RepetAction(String repertoire)
    {

        dossierHH = repertoire;

        try 
        {
            gestionDossier.travail(dossierHH);
        }
        catch (Exception e) 
        {
            new Log("Class RepetAction : RepetAction(String " + repertoire +")");
            new Log("Impossible de lire le dossier");
            new Log(e.getMessage());
        }

        try 
        {
            gestionDossier.extractionList();
        }
        catch (Exception e) 
        {
            new Log("Class GestionHH extends TimeTask : void run()");
            new Log("Impossible d'extraire une liste");
            new Log(e.getMessage());
        }

        t = new Timer();
        t.schedule(new GestionHH(), 20*1000, 25*1000);
    }

    public void stop()
    {
        t.cancel();
    }

    class GestionHH extends TimerTask
    {

        public void run()
        {
            try {
                gestionDossier.traitementHH();
            }
            catch (Exception e) {
                System.out.println("Erreur dans le traitement HH");
            }
        }
    }
}
