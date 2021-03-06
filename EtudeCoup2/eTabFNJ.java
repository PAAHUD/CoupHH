
/**
 * Enumeration eTabFNJ - écrire ici la description de l'énumération
 * 
 * @author PAA Thierry Mater
 * @version 0.1.0beta du 15/11/17
 */
import java.net.*;
import java.io.InputStream;
import java.io.DataInputStream ;

public enum eTabFNJ
{
    FNJ0(0,1.30f),

    FNJ1(1,1.25f),

    FNJ2(2,1.20f),

    FNJ3(3,1.15f),

    FNJ4(4,1.10f),

    FNJ5(5,1.05f),

    FNJ6(6,1.00f),

    FNJ7(7,0.95f),

    FNJ8(8,0.90f),

    FNJ9(9,0.85f),

    FNJ10(10,0.80f);

    private Float m_valeur = 0.00f;
    private int m_id = 0;

    eTabFNJ(int id, Float valeur)
    {
        this.m_valeur = valeur;
        this.m_id = id;
    }

    public static void setFacteurMoment()
    {
        URL url;
        InputStream is;
        DataInputStream dis;

        String fichier = eVariables.repertoireInit.getString() + "/valeurTabFNJ.ini";

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

                for(eTabFNJ v : values())
                {
                    if(v.m_id == Integer.valueOf(splitLigne[0]))
                        v.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
            }
            dis.close(); 
        }       
        catch (Exception e)
        {
            new Log("public enum eTabFNJ : public static void setFacteurMoment()");
            new Log("Impossible d'initialiser le tableau tabFNJ");
            //new Log(e.getMessage());
        }
    }

    public static Float facteurNbreJoueur(int id)
    {
        switch (id)
        {
            case 0:
            return FNJ0.m_valeur;
            case 1:
            return FNJ1.m_valeur;
            case 2:
            return FNJ2.m_valeur;
            case 3:
            return FNJ3.m_valeur;
            case 4:
            return FNJ4.m_valeur;
            case 5:
            return FNJ5.m_valeur;
            case 6:
            return FNJ6.m_valeur;
            case 7:
            return FNJ7.m_valeur;
            case 8:
            return FNJ8.m_valeur;
            case 9:
            return FNJ9.m_valeur;
            case 10:
            return FNJ10.m_valeur;
            default :
            return 1.35f;
        } 
    }
}