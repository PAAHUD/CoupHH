
/**
 * Enumeration eJoueur
 *
 * @author PAA Thierry Mater
 * @version 0.0.1beat du 31/10/17
 */
public enum eJoueur
{
    Hero(0, "Hero1"),

    Vilain1(1, "vilain1"),

    Vilain2(2, "vilain2"),

    Vilain3(3, "vilain3"),

    Vilain4(4, "vilain4"),

    Vilain5(5, "vilain5"),

    Vilain6(6, "vilain6"),

    Vilain7(7, "vilain7"),

    Vilain8(8, "vilain8"),

    Vilain9(9, "vilain9");

    private Integer m_id = 0;
    private String m_nom = "";
    private Integer m_jetonDebut = 0;
    private Integer m_jetonAnteBlinds = 0;
    private Integer m_jetonPreFlop = 0;
    private Integer m_jetonFlop = 0;
    private Integer m_jetonTurn = 0;
    private Integer m_jetonRiver = 0;
    private String m_carteDealt = "";
    private char m_allIn = 'z';
    private String m_show = "";
    private Boolean m_actif = false;

    eJoueur(Integer id, String nom)
    {
        this.m_nom = nom;
        this.m_id = id;
    }

    public Integer getId()
    {
        return m_id;
    }

    public String getNom() 
    {
        return m_nom;
    }

    public Integer getJetonDebut() 
    {
        return m_jetonDebut;
    }

    public Integer getJetonAnteBlinds() 
    {
        return m_jetonAnteBlinds;
    }

    public Integer getJetonPreFlop() 
    {
        return m_jetonPreFlop;
    }

    public Integer getJetonFlop() 
    {
        return m_jetonFlop;
    }

    public Integer getJetonTurn()
    {
        return m_jetonTurn;
    }

    public Integer getJetonRiver() 
    {
        return m_jetonRiver;
    }

    public String getCarteDealt() 
    {
        return m_carteDealt;
    }

    public char getAllIn() 
    {
        return m_allIn;
    }

    public String getShow()
    {
        return m_show;
    }

    public void setNom(String nom)
    {
        m_nom = nom;
    }

    public void setJetonDebut(Integer jetonDebut)
    {
        m_jetonDebut = jetonDebut;
    }

    public void setJetonAnteBlinds(Integer jetonAnteBlinds)
    {
        m_jetonAnteBlinds = jetonAnteBlinds;
    }

    public void setJetonPreFlop(Integer jetonPreFlop) 
    {
        m_jetonPreFlop = jetonPreFlop;
    }

    public void setJetonFlop(Integer jetonFlop) 
    {
        m_jetonFlop = jetonFlop;
    }

    public void setJetonTurn(Integer jetonTurn) 
    {
        m_jetonTurn = jetonTurn;
    }

    public void setJetonRiver(Integer jetonRiver)
    {
        m_jetonRiver = jetonRiver;
    }

    public void setCarteDealt(String carteDealt) 
    {
        m_carteDealt = carteDealt;
    }

    public void setAllIn(char allIn) 
    {
        m_allIn = allIn;
    }

    public void setShow(String show) 
    {
        m_show = show;
    }

    public Boolean getActif() 
    {
        return m_actif;
    }

    public void setActif(Boolean actif) 
    {
        m_actif = actif;
    }

    public void raz() 
    {
        m_nom = ""; 
        m_jetonDebut = 0;
        m_jetonAnteBlinds = 0;
        m_jetonPreFlop = 0; 
        m_jetonFlop = 0;
        m_jetonTurn = 0;
        m_jetonRiver = 0; 
        m_carteDealt = "";
        m_allIn = 'z';
        m_show = "";
        m_actif = false;
    }    
    
    public String ecriture()
    {
        return m_nom + " - " + 
        m_jetonDebut + " - " + m_jetonAnteBlinds + " - " + m_jetonPreFlop + " - " + 
        m_jetonFlop + " - " + m_jetonTurn + " - " + m_jetonRiver + " - " + 
        m_carteDealt + " - " + m_allIn + " - " + m_show + " - " + m_actif + "\n";
    }
}
