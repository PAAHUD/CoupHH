
/**
 * Classe TableauB.
 *
 * @author PAA Thierry Mater
 * @version 0.1.0beta du 14/11/17
 */
public class TableauB
{
    // variables d'instance

    /**
     * Constructeur d'objets de classe TableauB
     */
    public TableauB()
    {
        eVariables.repertoireInit.setString("http://thierry.mater.free.fr/paaini");
    }

    /**
     * Initialisation des différents tableau de variable concernant l'Etape B du calcul
     *
     * @param rien
     * @return rien
     */
    public static void init()
    {
        eTabFDA.setFacteurAvenue();
        eTabFM.setFacteurMoment();
        eTabFV.setFacteurVitesse();
        eTabCondFM.setFacteurMoment();
        eTabFNJ.setFacteurMoment();
        eTabFMR.setFacteurModulation();
        eTabFMSA.setFacteurModulation();
    }
}
