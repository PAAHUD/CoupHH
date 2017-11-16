
/**
 * Enumeration eTabFV - écrire ici la description de l'énumération
 *
 * @author PAA Thierry Mater
 * @version 0.1.0beta du 14/11/17
 */
import java.net.*;
import java.io.InputStream;
import java.io.DataInputStream ;

public enum eTabFV
{
    FV0(0,0,0.00f),

    FV1(1,0,0.00f),

    FV2(2,0,0.00f),

    FV3(3,0,0.00f),

    FV4(4,0,0.00f),

    FV5(5,0,0.00f);

    private Float m_valeur = 0.00f;
    private int m_id = 0;
    private int m_vitesse = 0;

    eTabFV(int id, int vitesse, Float valeur)
    {
        this.m_valeur = valeur;
        this.m_id = id;
        this.m_vitesse = vitesse;
    }

    public static void setFacteurVitesse()
    {
        URL url;
        InputStream is;
        DataInputStream dis;

        String fichier = eVariables.repertoireInit.getString() + "/valeurFV.ini";

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

                for(eTabFV v : values())
                {
                    v.m_vitesse = Integer.valueOf(splitLigne[0]);
                    v.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
            }
            dis.close(); 
        }       
        catch (Exception e)
        {
            new Log("enum eTabFV : public static void setFacteurVitesse()");
            new Log("Impossible d'initialiser le tableau tabFV");
            //new Log(e.getMessage());
        }
    }

    public static Float facteurVitesse()
    {
        Float resultat = 0.00f;

        for (eTabFV v : values()) 
        {
            if(eVariables.vitesseMTT.getInt() <= v.m_vitesse)
                resultat = v.m_valeur;

            if(eVariables.vitesseMTT.getInt() > v.m_vitesse) 
                resultat = v.m_valeur;
        }

        return resultat;
    }

    public static int getId(Float valeur)
    {
        for (eTabFV v : values())
        {
            if(v.m_valeur == valeur)
                return v.m_id;
        }
        return 0;
    }
}