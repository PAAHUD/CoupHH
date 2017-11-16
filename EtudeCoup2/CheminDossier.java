
/**
 * Enumeration CheminDossier - écrire ici la description de l'énumération
 *
 * @author (votre nom)
 * @version (numéro de version ou date)
 */
public enum CheminDossier
{
    hh(0, "HH"),

    archive(1, "archive"),

    resultat(2, "resultat");

    private Integer id = 0;
    private String chemin = "";

    CheminDossier(Integer id, String chemin)
    {
        this.id = id;
        this.chemin = chemin;
    }

    public String getChemin()
    {
        return chemin;
    }

    public void setChemin(String chemin)
    {
        this.chemin = chemin;
    }

    public Integer getId()
    {
        return id;
    }
}
