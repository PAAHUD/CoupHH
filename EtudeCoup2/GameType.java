
/**
 * Enumeration GameType
 *
 * @author PAA Thierry Mater
 * @version 0.0.3beta
 */
public enum GameType
{
    holdemGameType(0, "holdem", 4, 2),

    holdem8GameType(1, "holdem8", 4, 2),

    omahaGameType(2, "omaha", 4, 4),

    omaha8GameType(3, "omaha8", 4, 4),

    stud7GameType(4, "7stud", 0, 6),

    stud78GameType(5, "7stud8", 0, 6),

    stud78nqGameType(6, "7stud8nq", 0, 6),

    razzGameType(7, "razz", 0, 6);

    private Integer id = 0;
    private String type = "";
    private Integer cardsOnTable = 0;
    private Integer cardsOnHandPlayers = 0;
    
    GameType(Integer id, String type, Integer cardsOnTable, Integer cardsOnHandPlayers)
    {
        this.id = id;
        this.type = type;
        this.cardsOnTable = cardsOnTable;
        this.cardsOnHandPlayers = cardsOnHandPlayers;
    }
    
    public String getType()
    {
        return type;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public Integer getcardsOnHandPlayers()
    {
        return cardsOnHandPlayers;
    }
}
