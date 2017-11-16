
/**
 * Enumeration eDonnee - Enregistrement des entêtes des coups
 *
 * @author (PAA Thierry Mater
 * @version 0.1.0beta du 02/11/17
 */
public enum eDonnee
{
    Donnee0("val"), 
    Donnee1("val"),
    Donnee2("val"),
    Donnee3("val"),
    Donnee4("val"),
    Donnee5("val"),
    Donnee6("val"),
    Donnee7("val"),
    Donnee8("val"),
    Donnee9("val"),
    Donnee10("val"),
    Donnee11("val"),
    Donnee12("val"),
    Donnee13("val"),
    Donnee14("val");

    private String m_valeur = "";

    eDonnee (String valeur)
    {
        this.m_valeur = valeur;
    }

    public String getValeur()
    {
        return m_valeur;
    }

    public void setValeur(String valeur)
    {
        this.m_valeur = valeur;
    }

    public void raz()
    {
            this.m_valeur = "";
    }
}
