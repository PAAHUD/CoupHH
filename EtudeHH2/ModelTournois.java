
/**
 * Décrivez votre classe ModelTournois ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ModelTournois
{
    // Déclaration des variables du modéle
    private String nomTournois;
    private boolean summary;
    private String dateModification;
    private int nmrLigne;

    /**
     * ModelTournois Constructeur
     *
     */
    public ModelTournois() {
        this("","",false, 0);
    }

    /**
     * Constructeur avec renseignement
     * @param tournois
     * @param modification
     * @param valeur
     */
    public ModelTournois(String nomTournois, String dateModification, boolean summary, int nmrLigne) {
        super();
        this.nomTournois = nomTournois;
        this.summary = summary;
        this.dateModification = dateModification;
        this.nmrLigne = nmrLigne;
    }

    /**
     * @return tournoi
     */
    public String getNomTournois() {
        return nomTournois;
    }

    /**
     * @param tournoi
     */
    public void setNomTournois(String nomTournois) {
        this.nomTournois = nomTournois;
    }

    /**
     * @return valeur
     */
    public boolean getSummary() {
        return summary;
    }

    /**
     * @param valeur
     */
    public void setSummary(boolean summary) {
        this.summary = summary;
    }

    /**
     * @return modification
     */
    public String getDateModification() {
        return dateModification;
    }

    /**
     * @param modification
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }


    /**
     * Méthode getNmrLigne
     *
     * @return La valeur numéro de ligne en cours (Integer)
     */
    public int getNmrLigne() {
        return nmrLigne;
    }

 
    /**
     * Méthode setNmrLigne
     *
     * @param nmrLigne : valeur numéro de ligne en cours (Integer)
     */
    public void setNmrLigne(int nmrLigne) {
        this.nmrLigne = nmrLigne;
    }

    /**
     * Méthode ecriture
     *
     * @return un String contenant la mise en forme des données pour l'affichage ou l'enregistrement dans un fichier
     */
    public String ecriture()
    {
        return this.nomTournois  + ";" + this.dateModification + ";" + this.summary + ";" + this.nmrLigne;
    }
}
