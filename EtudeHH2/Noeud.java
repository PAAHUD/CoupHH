
/**
 * Description : A faire
 *
 * @author Thierry Mater
 * @version 1.0.0 (30/05/2017)
 */

public class Noeud
{
    // variables d'instance
    private String m_noeud;
    private String m_nom;
    private String m_action;
    private String m_valeur;

    /**
     * Default constructor.
     */
    public Noeud() {

    }

    /**
     * 
     * @param noeud
     * @param nom
     * @param action
     * @param valeur
     */
    public Noeud(String noeud, String nom, String action, String valeur) {
        m_noeud = noeud;
        m_nom = nom;
        m_action = action;
        m_valeur = valeur;
    }

    /**
     *   GETTER
     **/
     
    public String getNoeud() {
        return m_noeud;
    }

    public String getNom() {
        return m_nom;
    }

    public String getAction() {
        return m_action;
    }

    public String getValeur() {
        return m_valeur;
    }
}
