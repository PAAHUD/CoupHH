
/**
 * Enumeration BouclierBB - retourne la valeur d'un bouclier en fonction de la valeur boolean
 *
 * @author PAA Thierry Mater
 * @version 0.1.0beta
 */
public enum BouclierBB
{
    B0((byte)0,(byte)0), 
    B60((byte)1,(byte)60), 
    B65((byte)3,(byte)65), 
    B70((byte)7,(byte)70), 
    B75((byte)15,(byte)75), 
    B80((byte)31,(byte)80), 
    B85((byte)63,(byte)85), 
    B90((byte)127,(byte)90);

    private byte m_bouclier = 0;
    private byte m_id = 0;

    BouclierBB(byte id,byte bouclier)
    {
        this.m_id = id;
        this.m_bouclier = bouclier;
    }

    public static byte recherche(byte id)
    {
        for(BouclierBB v : values())
        {
            if( v.m_id == id)
            {
                return v.m_bouclier;
            }
        }
        return 0;
    }
}