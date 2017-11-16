
/**
 * Enumeration eDonnee - Enregistrement des valeur pour le fichier
 * résultat
 *
 * @author (PAA Thierry Mater
 * @version 0.1.0beta du 02/11/17
 */
public enum ejlRoig
{
    Donnee1("\t","x"),

    Donnee2("\t","x"),

    Donnee3("\t","x"),

    Donnee4("\t","x"),

    Donnee5("\t","x"),

    Donnee6("\t","x"),

    Donnee7("\t","x"),

    Donnee8("\t","x"),

    Donnee9("\t","x"),

    Donnee10("\t","x"),

    Donnee11("\t","x"),

    Donnee12("\t","x"),

    Donnee13("\t","x"),

    Donnee14("\t","x"),

    Donnee15("\t","x"),

    Donnee16("\t","x"),

    Donnee17("\t","x"),

    Donnee18("\t","x"),

    Donnee19("\t","x"),

    Donnee20("\t","x"),

    Donnee21("\t","x"),

    Donnee22("\t","x"),

    Donnee23("\t","x"),

    Donnee24("\t","x"),

    Donnee25("\t","x"),

    Donnee26("\t","x"),

    Donnee27("\t","x"),

    Donnee28("\t","x"),

    Donnee29("\t","x"),

    Donnee30("\t","x"),

    Donnee31("\t","x"),

    Donnee32("\t","x"),

    Donnee33("\t","x"),

    Donnee34("\t","x"),

    Donnee35("\t","x"),

    Donnee36("\t","x"),

    Donnee37("\t","x"),

    Donnee38("\t","x"),

    Donnee39("\t","x"),

    Donnee40("\t","x"),

    Donnee41("\t","x"),

    Donnee42("\t","x"),

    Donnee43("\t","x"),

    Donnee44("\t","x"),

    Donnee45("\t","x"),

    Donnee46("\t","x"),

    Donnee47("\t","x"),

    Donnee48("\t","x"),

    Donnee49("\t","x"),

    Donnee50("\t","x"),

    Donnee51("\t","x"),

    Donnee52("\t","x"),

    Donnee53("\t","x"),

    Donnee54("\t","x"),

    Donnee55("\t","x"),

    Donnee56("\t","x"),

    Donnee57("\t","x"),

    Donnee58("\t","x"),

    Donnee59("\t","x"),

    Donnee60("\t","x"),

    Donnee61("\t","x"),

    Donnee62("\t","x"),

    Donnee63("\t","x"),
    
    Donnee64("\t","x"),

    Donnee65("\t","x"),

    Donnee66("\t","x"),

    Donnee67("\t","x"),

    Donnee68("\t","x"),

    Donnee69("\t","x"),

    Donnee70("\t","x"),

    Donnee71("\t","x"),

    Donnee72("\t","x"),

    Donnee73("\t","x"),

    Donnee74("\t","x"),

    Donnee75("\t","x"),

    Donnee76("\t","x"),

    Donnee77("\t","x"),

    Donnee78("\t","x"),

    Donnee79("\t","x"),

    Donnee80("\t","x");

    private String m_valeur = "";
    private String m_txt1 = "";

    ejlRoig (String txt1, String valeur)
    {
        this.m_valeur = valeur;
        this.m_txt1 = txt1;
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
            this.m_valeur = "x";
    }

    public String ecriture()
    {
        return this.m_txt1 + this.m_valeur;
    }
}
