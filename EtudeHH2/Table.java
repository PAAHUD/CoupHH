
/**
 * Description : 
 *
 * @author Thierry Mater
 * @version 1.0.0 (23/08/2017)
 * 
 * @version 0.0.3beta (18/10/17)
 *      ajout d'une variable d'instance m_typeGame
 */

public class Table
{
    // variables d'instance
    private String m_nom;//
    private String m_numero;
    private String m_room;//
    private String m_buyin;//
    private String m_dateHeure;//
    private Integer m_typeGame;//
    private String m_numeroMain;//
    private String m_level;//
    private String m_table;//

    public Table(){

    }

    /**
     *   Winamax Poker - Tournament "Deepstack Hold'em" buyIn: 4,50€ + 0,50€ level: 21 - HandId: #138224739213639681-207-1352137175 - Holdem no limit (2000/8000/16000) - 2012/11/05 17:39:35 UTC
     **/
    public void setTable(String val)
    {
        String[] tabString;
        String strProvisoire;
        /**
         * Récupération du nom de la ROOM
         * Winamax Poker
         **/
        tabString = val.split(" - ");
        m_room = tabString[0];

        /**
         * Récupération du nom du tournoi
         * Deepstack Hold'em
         **/
        tabString = val.split("Tournament \"");
        strProvisoire = tabString[1];
        tabString = strProvisoire.split("\" buyIn");
        m_nom = tabString[0];

        /**
         * Récupération du buyIn
         * 4,50€ + 0,50€
         **/
        tabString = val.split(" buyIn: ");
        strProvisoire = tabString[1];
        tabString = strProvisoire.split(" level: ");
        m_buyin = tabString[0];

        /**
         * Récupération du level
         * 21
         **/
        strProvisoire = tabString[1];
        tabString = strProvisoire.split(" - HandId:");
        m_level = tabString[0];

        /**
         * Récupération du numéro de la main
         * 138224739213639681-207-1352137175
         **/
        tabString = val.split("HandId: #");
        strProvisoire = tabString[1];
        tabString = strProvisoire.split(" - ");
        m_numeroMain = tabString[0];

        /**
         * Récupération de la Date Heure
         * 1352137175
         **/
        tabString = m_numeroMain.split("-");
        m_dateHeure = tabString[2];

        /**
         * Récupération du type de jeu
         *  holdem
         *  holdem8
         *  omaha = Omaha pot limit
         *  omaha8
         *  stud7
         *  stud78
         *  stud78nq
         *  razz
         * 
         **/
        if(val.contains("Holdem"))
        {
            if(val.contains("Hi/Lo"))
                m_typeGame = 1;
            else
                m_typeGame = 0;
        }

        if(val.contains("Omaha"))
        {
            if(val.contains("Hi/Lo"))
                m_typeGame = 3;
            else
                m_typeGame = 2;
        }

        if(val.contains("7-Card Stud"))
        {
            if(val.contains("Hi/Lo"))
                m_typeGame = 5;
            else
                m_typeGame = 4;
        }

        if(val.contains("razz"))
            m_typeGame = 7;
    }

    /**
     * Récupération du numéro du tournoi
     * Et du numéro de la table
     * Table: 'Deepstack Omaha(32183065)#000' 6-max (real money) Seat #2 is the button
     */
    public void setTableNumero(String val)
    {
        String[] tabString;
        String strProvisoire;

        /**
         * Récupération du numéro du tournois
         **/
        tabString = val.split("\\(");
        strProvisoire = tabString[1];
        tabString = strProvisoire.split("\\)");
        m_numero = tabString[0];

        /**
         * Récupération du numéro de la table
         **/
        m_table = tabString[1].substring(0, 4);
    }

    /**
     *   GETTER
     **/

    String getNom()
    {
        return m_nom;
    }

    String getNumero()
    {
        return m_numero;
    }

    String getRoom()
    {
        return m_room;
    }

    String getBuyin()
    {
        return m_buyin;
    }

    String getDateHeure()
    {
        return m_dateHeure;
    }

    Integer getTypeGame()
    {
        return m_typeGame;
    }

    String getNumeroMain()
    {
        return m_numeroMain;
    }

    String getLevel()
    {
        return m_level;
    }

    String getTable()
    {
        return m_table;
    }
}
