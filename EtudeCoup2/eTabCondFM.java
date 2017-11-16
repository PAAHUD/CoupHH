
/**
 * Enumeration eTabCondFM
 * 
 * @author PAA Thierry Mater
 * @version 0.1.0beta du 14/11/17
 */
import java.net.*;
import java.io.InputStream;
import java.io.DataInputStream ;

public enum eTabCondFM
{
    C1FM0(0,60.50f),

    C1FM1(1,150.00f),

    FM99(99,9.99f),

    C2FM0(0,0.00f),

    C2FM1(1,0.50f),

    C2FM2(2,0.80f),

    C2FM3(3,0.90f),

    C2FM4(4,1.00f);

    private Float m_valeur = 0.00f;
    private int m_id = 0;

    eTabCondFM(int id, Float valeur)
    {
        this.m_valeur = valeur;
        this.m_id = id;
    }

    public static void setFacteurMoment()
    {
        URL url;
        InputStream is;
        DataInputStream dis;

        String fichier = eVariables.repertoireInit.getString() + "/valeurTabFM.ini";

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

                for(eTabCondFM v : values())
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

    public static Float facteurAvenue(int id, boolean C1)
    {
        if(C1)
        {
            switch (id)
            {
                case 0:
                return C1FM0.m_valeur;
                case 1:
                return C1FM1.m_valeur;
                default :
                return 0.99f;
            }
        }
        else
        {
            switch (id)
            {
                case 0:
                return C2FM0.m_valeur;
                case 1:
                return C2FM1.m_valeur;
                case 2:
                return C2FM2.m_valeur;
                case 3:
                return C2FM3.m_valeur;
                case 4:
                return C2FM4.m_valeur;
                default :
                return 0.99f;
            }
        }
    }
}

