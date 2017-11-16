
/**
 * Enumeration eTabFMSA
 * 
 * @author PAA Thierry Mater
 * @version 0.1.0beta du 15/11/17
 */
import java.net.*;
import java.io.InputStream;
import java.io.DataInputStream ;

public enum eTabFMSA
{
    Accident(0.80f),

    MRA_NA(1.00f),

    MRBB_A(2.00f),

    MRBD_A(2.00f),

    MRST_A(2.00f),

    MRBBBD_A(2.00f),

    MRBBST_A(2.00f),

    Remboursement(1.00f);

    private Float m_valeur = 0.00f;

    eTabFMSA( Float valeur)
    {
        this.m_valeur = valeur;
    }

    public static void setFacteurModulation()
    {
        URL url;
        InputStream is;
        DataInputStream dis;

        String fichier = eVariables.repertoireInit.getString() + "/valeurFMSA.ini";

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

                if(splitLigne[0].contains("accident"))
                {
                    Accident.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
                if(splitLigne[0].contains("MRA_NA")) 
                {
                    MRA_NA.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
                if(splitLigne[0].contains("MRBB_A"))
                {
                    MRBB_A.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
                if(splitLigne[0].contains("MRBD_A")) 
                {
                    MRBD_A.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
                if(splitLigne[0].contains("MRST_A"))
                {
                    MRST_A.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
                if(splitLigne[0].contains("MRBBBD_A")) 
                {
                    MRBBBD_A.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
                if(splitLigne[0].contains("MRBBST_A"))
                {
                    MRBBST_A.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
                }
                if(splitLigne[0].contains("Remboursement"))
                {
                    Remboursement.m_valeur = Float.valueOf(splitLigne[1].trim()).floatValue();
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

    public static Float getAccident()
    {
        return Accident.m_valeur;
    }

    public static Float getMRA_NA()
    {
        return MRA_NA.m_valeur;
    }

    public static Float getMRBB_A()
    {
        return MRBB_A.m_valeur;
    }

    public static Float getMRBD_A()
    {
        return MRBD_A.m_valeur;
    }
    
    public static Float getMRST_A()
    {
        return MRST_A.m_valeur;
    }
    
        public static Float getMRBBBD_A()
    {
        return MRBBBD_A.m_valeur;
    }

    public static Float getRemboursement()
    {
        return Remboursement.m_valeur;
    }
    
    public static Float getMRBBST_A()
    {
        return MRBBST_A.m_valeur;
    }
}

