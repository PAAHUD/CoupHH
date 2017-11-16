import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Décrivez votre classe ReadXMLFile ici.
 * 
 * @author Thierry Mater 
 * @version 0.2.1
 * @since 27/08/2015
 * 
 * 0.1 : version original
 * 24/09/16
 * 0.2 : modification des valeurs WIN LOSE TIE par la suppression du caractère %
 * 13/09/17
 * 0.2.1 : modification mineur avant abondon
 */
public class ReadXMLFile
{
    // variables d'instance

	/**
	 * Lecture du contenu du fichier XML
	 * 
	 * @param null
	 */
	private String win;
	private String lose;
	private String tie;

	public void xmlFile(String retour) throws Exception
	{
	    try
	    {
	        InputSource s = new InputSource(new StringReader(retour));
	        
	        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(s);
		    final Element racine = document.getDocumentElement(); // racine
		    
		    final NodeList racineNoeuds = racine.getChildNodes(); // Liste des noeuds 1er niveau    
		    final int nbRacineNoeuds = racineNoeuds.getLength();
			
		    for (int i = 0; i<nbRacineNoeuds; i++)
		    {
		        if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
		        {
		            final Node result = racineNoeuds.item(i);
		            if(result.getNodeName() == "result")
		            {
		    		    final NodeList resultNoeuds = result.getChildNodes(); // Liste des noeuds "result"    
		    		    final int nbResultNoeuds = resultNoeuds.getLength();
		    		    for (int j = 0; j<nbResultNoeuds; j++)
		    		    {
		    		    	if(resultNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE)
		    		    	{
		    		    		final Node handNoeuds = resultNoeuds.item(j);
		    		            if(handNoeuds.getNodeName() == "hand")
		    		            {		    		            
		    		                final Element joueur = (Element) resultNoeuds.item(j);
		    		                String numeroJoueur = joueur.getAttribute("number");
		    		                if(numeroJoueur.equals("1")) // selection du joueur 1 uniquement
		    		                {
			    		    		    final NodeList handNode = handNoeuds.getChildNodes(); // Liste des noeuds "hand"    
			    		    		    final int nbHandNode = handNode.getLength();

			    		    		    for (int k = 0; k<nbHandNode; k++)
			    		    		    {
			    		    		    	if(handNode.item(k).getNodeType() == Node.ELEMENT_NODE)
			    		    		    	{
			    		    		    		final Node childHand = handNode.item(k);
			    		    		    		 // enregistrement des r�sultats
			    		    		            switch (childHand.getNodeName())
			    		    		            {
			    		    					case "win":
			    		    						win = childHand.getTextContent().replace("%", "");
			    		    						break;
			    		    					case "lose":
			    		    						lose = childHand.getTextContent().replace("%", "");
			    		    						break;
			    		    					case "tie":
			    		    						tie = childHand.getTextContent().replace("%", "");
			    		    						break;
			    		    					default:
			    		    						break;
			    		    					}
			    		    		    	}
			    		    		    }
		    		                }
		    		            }
		    		    	}
		    		    }
		            }
		        }				
		    }
	    }
	
	    catch (final ParserConfigurationException e)
	    {
	        e.printStackTrace();
	    }
	    catch (final SAXException e)
	    {
	        e.printStackTrace();
	    }
	    catch (final IOException e)
	    {
	        e.printStackTrace();
	    }		
	}
	
	  //*************   ACCESSEURS *************
	  
	  //Retourne la valeur de win
	  public String getWin()
	  {  
	    return win;
	  }

	  //Retourne la valeur de lose
	  public String getLose()
	  {
	    return lose;
	  }

	  // Retourne la valeur de tie
	  public String getTie()
	  {
	    return tie;
	  } 
}
