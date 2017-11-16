
/**
 * Décrivez votre classe BadBeat ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class BadBeat
{
    // variables d'instance
    private byte x;

    /**
     * Constructeur d'objets de classe BadBeat
     */
    public BadBeat()
    {
        // initialisation des variables d'instance
        x = 0;
    }

    /**
     *
     */
    public void raz()
    { 
        x= 0;
    }

    public void calcul(Integer jeton, float cote)
    {
        if(jeton > 0)
        {
            if(cote>=0.60)
                x = (byte)(x + 1);
            if(cote>=0.65)
                x = (byte)(x + 2);
            if(cote>=0.70)
                x = (byte)(x + 4);
            if(cote>=0.75)
                x = (byte)(x + 8);
            if(cote>=0.80)
                x = (byte)(x + 16);
            if(cote>=0.85)
                x = (byte)(x + 32);
            if(cote>=0.90)
                x = (byte)(x + 64);
        }
        else
            x = (byte)255;
    }

    public byte valeur()
    {
        if(x==0)
            return (byte)255;
        else
            return x;
    }

    public boolean resultat()
    {
        if(x<=0)
            return false;
        else
            return true;
    }
}
