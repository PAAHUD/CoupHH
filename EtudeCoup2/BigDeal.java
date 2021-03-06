/**
 * Décrivez votre classe BigDeal ici.
 *
 * @author Thierry Mater
 * @version 0.2
 * @since 13/09/2017
 */
public class BigDeal
{
    // variables d'instance
	private String m_ass;
	private String m_adv;
	private Float m_validiteBD;
	private Float m_valeurMain;

	/**
	 * Default constructor.
	 */
	public BigDeal()
	{
	    this(null, null, 0.0000f, 0.0000f);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 */
	public BigDeal(String ass, String adv, float validiteBD, float valeurMain)
	{
		m_ass = ass;
		m_adv = adv;
		m_validiteBD = validiteBD;
		m_valeurMain = valeurMain;
	}

	public String getAss()
	{
		return m_ass;
	}

	public String getAdv()
	{
		return m_adv;
	}

	public float getvaliditeBD()
	{
		return m_validiteBD;
	}

	public float getValeurMain()
	{
		return m_valeurMain;
	}
}