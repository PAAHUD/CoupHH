
/**
 * Enumeration eTabFDA
 *
 * @author PAA Thierry Mater
 * @version 0.1.0beta du 14/11/17
 */
import java.net.*;
import java.io.InputStream;
import java.io.DataInputStream ;

public enum eTabFDA
{
    A('A',0.00f),

    B('B',0.00f),

    C('C',0.00f),

    D('D',0.00f),

    E('E',0.00f);

    private Float m_valeur = 0.00f;
    private char m_car = 'a';

    eTabFDA(char car, Float valeur)
    {
        this.m_valeur = valeur;
        this.m_car = car;
    }

    public static void setFacteurAvenue()
    {
        URL url;
        InputStream is;
        DataInputStream dis;

        String fichier = eVariables.repertoireInit.getString() + "/valeurTabFDA.ini";

        //lecture du fichier texte
        try
        {
            url = new URL(fichier);
            is = url.openStream();
            dis = new DataInputStream(is);

            String ligne;

            while ((ligne=dis.readLine())!=null)
            {
                String[] splitLigne = ligne.split(";");

                for(eTabFDA v : values())
                {
                    if( v.m_car == splitLigne[0].charAt(0))
                    {
                        v.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                    }
                }
            }
            dis.close(); 
        }       
        catch (Exception e)
        {
            new Log("Class Calcul : private void initTabB()");
            new Log("Impossible d'initialiser le tableau tabFDA");
            //new Log(e.getMessage());
        }
    }

    public static Float facteurAvenue(char car)
    {
        switch(car)
        {
            case 'A':
            return A.m_valeur;
            case 'B':
            return B.m_valeur;
            case 'C':
            return C.m_valeur;
            case 'D':
            return D.m_valeur;
            case 'E':
            return E.m_valeur;
            default :
            return 1.00f;
        }
    }
}
