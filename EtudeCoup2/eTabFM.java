
/**
 * Enumeration eTabFM
 *
 * @author PAA Thierry Mater
 * @version 0.1.0beta du 14/11/17
 */
import java.net.*;
import java.io.InputStream;
import java.io.DataInputStream ;

public enum eTabFM
{
    FM0(0,0.50f),

    FM1(1,0.75f),

    FM2(2,1.00f),

    FM3(3,1.25f),

    FM4(4,1.50f),

    FM5(5,1.75f);

    private Float m_valeur = 0.00f;
    private int m_id = 0;

    eTabFM(int id, Float valeur)
    {
        this.m_valeur = valeur;
        this.m_id = id;
    }

    public static void setFacteurMoment()
    {
        URL url;
        InputStream is;
        DataInputStream dis;

        String fichier = eVariables.repertoireInit.getString() + "/valeurFM.ini";

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

                for(eTabFM v : values())
                {
                    if(v.m_id == Integer.valueOf(splitLigne[0]))
                        v.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
            }
            dis.close(); 
        }       
        catch (Exception e)
        {
            new Log("Class Calcul : private void initTabB()");
            new Log("Impossible d'initialiser le tableau tabFM");
            //new Log(e.getMessage());
        }
    }

    public static Float facteurAvenue(int id)
    {
        switch(id)
        {
            case 0:
            return FM0.m_valeur;
            case 1:
            return FM1.m_valeur;
            case 2:
            return FM2.m_valeur;
            case 3:
            return FM3.m_valeur;
            case 4:
            return FM4.m_valeur;
            case 5:
            return FM5.m_valeur;
            default :
            return 0.99f;
        }
    }
}
