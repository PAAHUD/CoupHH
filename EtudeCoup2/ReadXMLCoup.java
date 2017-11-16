import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Décrivez votre classe ReadXMLCoup ici.
 *
 * @author Thierry Mater
 * @version 0.2
 * @since 13/09/2017
 */
public class ReadXMLCoup
{
    // variables d'instance
    String hero = "";
    public ReadXMLCoup()
    {

    }

    public void xmlFile(String fichier) throws Exception
    {

        System.out.println(fichier);

        for(eJoueur game: eJoueur.values())
            game.raz();

        // récupération d'une instance de la classe
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // création du parseur
            final DocumentBuilder builder = factory.newDocumentBuilder();

            // création du document
            final Document document = builder.parse(new File(fichier));

            // récupération de l'élément racine
            final Element racine = document.getDocumentElement();

            // récupération de tous les noeuds enfants de la racine
            final NodeList racineNoeuds = racine.getChildNodes();
            final int nbRacineNoeuds = racineNoeuds.getLength();

            eDonnee.Donnee0.setValeur(racine.getAttributes().getNamedItem("numero").getNodeValue()); // numéro du coup en étude
            eDonnee.Donnee1.setValeur(racineNoeuds.item(1).getAttributes().getNamedItem("heure").getNodeValue()); // Heure début du tournoi
            eDonnee.Donnee2.setValeur(racineNoeuds.item(1).getTextContent()); // level du début du tournoi
            eDonnee.Donnee3.setValeur(racineNoeuds.item(3).getAttributes().getNamedItem("heure").getNodeValue());// Heure du coup en cours
            eDonnee.Donnee4.setValeur(racineNoeuds.item(3).getTextContent()); // level du coup en cours
            eDonnee.Donnee5.setValeur(racineNoeuds.item(5).getTextContent()); // Nom du tournoi
            eDonnee.Donnee13.setValeur(racineNoeuds.item(5).getAttributes().getNamedItem("type").getNodeValue());; // type de tournois
            eDonnee.Donnee6.setValeur(racineNoeuds.item(7).getTextContent()); // Numéro du tournoi
            eDonnee.Donnee7.setValeur(racineNoeuds.item(9).getTextContent()); // Serveur du tournoi
            eDonnee.Donnee8.setValeur(racineNoeuds.item(11).getTextContent()); // Buy-In
            eDonnee.Donnee9.setValeur(racineNoeuds.item(13).getTextContent()); // Numéro de la table
            hero = racineNoeuds.item(15).getTextContent();
            eDonnee.Donnee14.setValeur(hero); // Nom du hero

            for (int i = 11; i<nbRacineNoeuds; i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {     

                    /**
                     * Etape SIEGE
                     */
                    if (racineNoeuds.item(i).getNodeName().contains("siege")) {
                        final NodeList sieges = racineNoeuds.item(i).getChildNodes();
                        final int nbSieges = sieges.getLength();

                        String nom , jetonDebut;
                        int iJoueur = 1;

                        for(int j = 0; j<nbSieges; j++) {

                            if(sieges.item(j).getNodeType() == Node.ELEMENT_NODE){

                                jetonDebut = sieges.item(j).getTextContent();
                                nom = sieges.item(j).getAttributes().getNamedItem("nom").getNodeValue();

                                /**
                                 * Ajout d'information dans l'Enumération des joueurs (eJoueur)
                                 */ 
                                if(nom.contains(hero))
                                {
                                    eJoueur.Hero.setNom(nom);
                                    eJoueur.Hero.setJetonDebut(Integer.parseInt(jetonDebut));
                                }
                                else
                                {
                                    for(eJoueur game: eJoueur.values())
                                    {
                                        if(game.getId() == iJoueur)
                                        {
                                            game.setNom(nom);
                                            game.setJetonDebut(Integer.parseInt(jetonDebut));
                                            iJoueur++;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    /**
                     * Etape ANTE BLINDS
                     */
                    if (racineNoeuds.item(i).getNodeName().contains("anteblinds"))
                    {
                        final NodeList sieges = racineNoeuds.item(i).getChildNodes();
                        final int nbSieges = sieges.getLength();

                        String nom , action, valeur;

                        for(int j = 0; j<nbSieges; j++)
                        {
                            if(sieges.item(j).getNodeType() == Node.ELEMENT_NODE)
                            {
                                nom = sieges.item(j).getAttributes().getNamedItem("nom").getNodeValue();
                                action = sieges.item(j).getAttributes().getNamedItem("action").getNodeValue();
                                valeur = sieges.item(j).getTextContent();

                                for(eJoueur game: eJoueur.values())
                                {
                                    if(game.getNom().contains(nom))
                                    {
                                        if(!action.contains("dealt")) 
                                        {
                                            int jeton = game.getJetonAnteBlinds();
                                            game.setAllIn('A');
                                            if(valeur.contains("all-in"))
                                            {
                                                String[] valeurJeton = valeur.split(" and ");
                                                game.setJetonAnteBlinds(jeton + Integer.parseInt(valeurJeton[0]));
                                            }
                                            else 
                                            {
                                                game.setJetonAnteBlinds(jeton + Integer.parseInt(valeur));
                                            }
                                        }
                                        else 
                                        {
                                            game.setCarteDealt(valeur);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    /**
                     * Etape PRE FLOP
                     */
                    if (racineNoeuds.item(i).getNodeName().contains("preflop")) {
                        final NodeList sieges = racineNoeuds.item(i).getChildNodes();
                        final int nbSieges = sieges.getLength();

                        String nom , action, valeur;
                        Boolean raises = false;
                        Integer memoireRaises = 0;

                        for(int j = 0; j<nbSieges; j++) {

                            if(sieges.item(j).getNodeType() == Node.ELEMENT_NODE) {		
                                nom = sieges.item(j).getAttributes().getNamedItem("nom").getNodeValue();
                                action = sieges.item(j).getAttributes().getNamedItem("action").getNodeValue();
                                valeur = sieges.item(j).getTextContent();

                                // Mise à jour des informations
                                for(eJoueur game: eJoueur.values())
                                {
                                    if(game.getNom().contains(nom))
                                    {
                                        game.setActif(true);
                                        if(!action.contains("folds") && !action.contains("checks")) 
                                        {
                                            int jeton = game.getJetonPreFlop();
                                            game.setAllIn('B');
                                            if(action.contains("calls")) 
                                            {
                                                if(valeur.contains("all-in"))
                                                {
                                                    String[] calls = valeur.split(" and is all-in");
                                                    game.setJetonPreFlop(jeton + Integer.parseInt(calls[0]));
                                                } 
                                                else 
                                                {
                                                    game.setJetonPreFlop(jeton + Integer.parseInt(valeur));
                                                }
                                            }
                                            if(action.contains("bets")) 
                                            {
                                                if(valeur.contains("all-in"))
                                                {
                                                    String[] bets = valeur.split(" and is all-in");
                                                    game.setJetonPreFlop(jeton + Integer.parseInt(bets[0]));
                                                } 
                                                else 
                                                {
                                                    game.setJetonPreFlop(jeton + Integer.parseInt(valeur));
                                                }
                                            }
                                            if(valeur.contains("all-in")) 
                                            {
                                                game.setAllIn('B');
                                            }

                                            if(action.contains("raises")) 
                                            {
                                                // récupération de la valeur des jetons
                                                String jeton1, jeton2;
                                                String[] raises1 = valeur.split(" to ");
                                                jeton1 = raises1[0];
                                                if(raises1[1].contains("and")) 
                                                {
                                                    String[] raises2 = raises1[1].split(" and ");
                                                    jeton2 = raises2[0];
                                                }
                                                else 
                                                    jeton2 = raises1[1];

                                                // déja un Raises ?
                                                if(raises)
                                                    game.setJetonPreFlop(Integer.parseInt(jeton1) + memoireRaises);
                                                else
                                                {
                                                    game.setJetonPreFlop(Integer.parseInt(jeton2));
                                                    raises = true;
                                                }
                                                memoireRaises = Integer.parseInt(jeton1);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    /**
                     * Etape FLOP
                     */
                    if (racineNoeuds.item(i).getNodeName().contains("flop") && !racineNoeuds.item(i).getNodeName().contains("preflop")) 
                    {
                        final NodeList sieges = racineNoeuds.item(i).getChildNodes();
                        final int nbSieges = sieges.getLength();

                        String nom , action, valeur, noeud;
                        Boolean raises = false;
                        Integer memoireRaises = 0;

                        for(int j = 0; j<nbSieges; j++) 
                        {
                            if(sieges.item(j).getNodeType() == Node.ELEMENT_NODE) 
                            {
                                noeud = sieges.item(j).getNodeName();

                                if(noeud.contains("carte")) 
                                {
                                    eDonnee.Donnee10.setValeur(sieges.item(j).getTextContent()); // carte du Flop
                                } 
                                else 
                                {
                                    nom = sieges.item(j).getAttributes().getNamedItem("nom").getNodeValue();
                                    action = sieges.item(j).getAttributes().getNamedItem("action").getNodeValue();
                                    valeur = sieges.item(j).getTextContent();

                                    if(!action.contains("folds") && !action.contains("checks")) 
                                    {
                                        // Mise à jour des informations
                                        for(eJoueur game: eJoueur.values())
                                        {
                                            if(game.getNom().contains(nom))
                                            {
                                                int jeton = game.getJetonFlop();
                                                game.setAllIn('C');
                                                if(action.contains("calls")) 
                                                {
                                                    if(valeur.contains("all-in"))
                                                    {
                                                        String[] calls = valeur.split(" and is all-in");
                                                        game.setJetonFlop(jeton + Integer.parseInt(calls[0]));
                                                    } 
                                                    else
                                                        game.setJetonFlop(jeton + Integer.parseInt(valeur));	
                                                }
                                                if(action.contains("bets"))
                                                {
                                                    if(valeur.contains("all-in"))
                                                    {
                                                        String[] bets = valeur.split(" and is all-in");
                                                        game.setJetonFlop(jeton + Integer.parseInt(bets[0]));
                                                    } 
                                                    else
                                                        game.setJetonFlop(jeton + Integer.parseInt(valeur));
                                                }

                                                if(valeur.contains("all-in"))
                                                    game.setAllIn('C');

                                                if(action.contains("raises")) 
                                                {
                                                    // récupération de la valeur des jetons
                                                    String jeton1, jeton2;
                                                    String[] raises1 = valeur.split(" to ");
                                                    jeton1 = raises1[0];
                                                    if(raises1[1].contains("and")) 
                                                    {
                                                        String[] raises2 = raises1[1].split(" and ");
                                                        jeton2 = raises2[0];
                                                    }
                                                    else 
                                                        jeton2 = raises1[1];

                                                    // déja un Raises ?
                                                    if(raises)
                                                        game.setJetonFlop(Integer.parseInt(jeton1) + memoireRaises);
                                                    else
                                                    {
                                                        game.setJetonFlop(Integer.parseInt(jeton2));
                                                        raises = true;
                                                    }
                                                    memoireRaises = Integer.parseInt(jeton1);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }            		
                    }
                }

                /**
                 * Etape TURN
                 */
                if (racineNoeuds.item(i).getNodeName().contains("turn"))
                {
                    final NodeList sieges = racineNoeuds.item(i).getChildNodes();
                    final int nbSieges = sieges.getLength();

                    String nom , action, valeur, noeud;
                    Boolean raises = false;
                    Integer memoireRaises = 0;

                    for(int j = 0; j<nbSieges; j++)
                    {
                        if(sieges.item(j).getNodeType() == Node.ELEMENT_NODE) 
                        {
                            noeud = sieges.item(j).getNodeName();

                            if(noeud.contains("carte"))
                                eDonnee.Donnee11.setValeur(sieges.item(j).getTextContent()); // carte du Turn
                            else 
                            {
                                nom = sieges.item(j).getAttributes().getNamedItem("nom").getNodeValue();
                                action = sieges.item(j).getAttributes().getNamedItem("action").getNodeValue();
                                valeur = sieges.item(j).getTextContent();

                                if(!action.contains("folds") && !action.contains("checks")) 
                                {
                                    // Mise à jour des informations
                                    for(eJoueur game: eJoueur.values())
                                    {
                                        if(game.getNom().contains(nom))
                                        {
                                            int jeton = game.getJetonTurn();
                                            game.setAllIn('D');

                                            if(action.contains("calls")) 
                                            {
                                                if(valeur.contains("all-in"))
                                                {
                                                    String[] calls = valeur.split(" and is all-in");
                                                    game.setJetonTurn(jeton + Integer.parseInt(calls[0]));
                                                } 
                                                else
                                                    game.setJetonTurn(jeton + Integer.parseInt(valeur));
                                            }

                                            if(action.contains("bets")) 
                                            {
                                                if(valeur.contains("all-in"))
                                                {
                                                    String[] bets = valeur.split(" and is all-in");
                                                    game.setJetonTurn(jeton + Integer.parseInt(bets[0]));
                                                } 
                                                else
                                                    game.setJetonTurn(jeton + Integer.parseInt(valeur));
                                            }

                                            if(valeur.contains("all-in"))
                                                game.setAllIn('D');

                                            if(action.contains("raises")) 
                                            {
                                                // récupération de la valeur des jetons
                                                String jeton1, jeton2;
                                                String[] raises1 = valeur.split(" to ");
                                                jeton1 = raises1[0];
                                                if(raises1[1].contains("and")) 
                                                {
                                                    String[] raises2 = raises1[1].split(" and ");
                                                    jeton2 = raises2[0];
                                                }
                                                else 
                                                    jeton2 = raises1[1];

                                                // déja un Raises ?
                                                if(raises)
                                                    game.setJetonTurn(Integer.parseInt(jeton1) + memoireRaises);
                                                else
                                                {
                                                    game.setJetonTurn(Integer.parseInt(jeton2));
                                                    raises = true;
                                                }
                                                memoireRaises = Integer.parseInt(jeton1);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }            		
                }

                /**
                 * Etape RIVER
                 */
                if (racineNoeuds.item(i).getNodeName().contains("river")) 
                {
                    final NodeList sieges = racineNoeuds.item(i).getChildNodes();
                    final int nbSieges = sieges.getLength();

                    String nom , action, valeur, noeud;
                    Boolean raises = false;
                    Integer memoireRaises = 0;

                    for(int j = 0; j<nbSieges; j++) 
                    {
                        if(sieges.item(j).getNodeType() == Node.ELEMENT_NODE) 
                        {
                            noeud = sieges.item(j).getNodeName();

                            if(noeud.contains("carte")) 
                                eDonnee.Donnee12.setValeur(sieges.item(j).getTextContent()); // carte du River
                            else 
                            {
                                nom = sieges.item(j).getAttributes().getNamedItem("nom").getNodeValue();
                                action = sieges.item(j).getAttributes().getNamedItem("action").getNodeValue();
                                valeur = sieges.item(j).getTextContent();

                                if(!action.contains("folds") && !action.contains("checks")) 
                                {
                                    // Mise à jour des informations
                                    for(eJoueur game: eJoueur.values())
                                    {
                                        if(game.getNom().contains(nom))
                                        {
                                            int jeton =game.getJetonRiver();
                                            game.setAllIn('E');
                                            if(action.contains("calls")) 
                                            {
                                                if(valeur.contains("all-in"))
                                                {
                                                    String[] calls = valeur.split(" and is all-in");
                                                    game.setJetonRiver(jeton + Integer.parseInt(calls[0]));
                                                } 
                                                else
                                                    game.setJetonRiver(jeton + Integer.parseInt(valeur));	
                                            }

                                            if(action.contains("bets")) 
                                            {
                                                if(valeur.contains("all-in"))
                                                {
                                                    String[] bets = valeur.split(" and is all-in");
                                                    game.setJetonRiver(jeton + Integer.parseInt(bets[0]));
                                                } 
                                                else
                                                    game.setJetonRiver(jeton + Integer.parseInt(valeur));
                                            }

                                            if(valeur.contains("all-in"))
                                                game.setAllIn('E');

                                            if(action.contains("raises")) 
                                            {
                                                // récupération de la valeur des jetons
                                                String jeton1, jeton2;
                                                String[] raises1 = valeur.split(" to ");
                                                jeton1 = raises1[0];
                                                if(raises1[1].contains("and")) 
                                                {
                                                    String[] raises2 = raises1[1].split(" and ");
                                                    jeton2 = raises2[0];
                                                }
                                                else 
                                                    jeton2 = raises1[1];

                                                // déja un Raises ?
                                                if(raises)
                                                    game.setJetonRiver(Integer.parseInt(jeton1) + memoireRaises);
                                                else
                                                {
                                                    game.setJetonRiver(Integer.parseInt(jeton2));
                                                    raises = true;
                                                }
                                                memoireRaises = Integer.parseInt(jeton1);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }            		
                }

                /**
                 * Etape SHOWDOWN
                 */
                if (racineNoeuds.item(i).getNodeName().contains("showdown"))
                {
                    final NodeList sieges = racineNoeuds.item(i).getChildNodes();
                    final int nbSieges = sieges.getLength();

                    String nom , action, valeur;

                    for(int j = 0; j<nbSieges; j++) 
                    {

                        if(sieges.item(j).getNodeType() == Node.ELEMENT_NODE)
                        {		
                            nom = sieges.item(j).getAttributes().getNamedItem("nom").getNodeValue();
                            action = sieges.item(j).getAttributes().getNamedItem("action").getNodeValue();
                            valeur = sieges.item(j).getTextContent();			
                            if(action.contains("shows")) 
                            {
                                // Mise à jour des informations
                                for(eJoueur game: eJoueur.values())
                                {
                                    if(game.getNom().contains(nom))
                                        game.setCarteDealt(valeur);
                                }
                            }
                        }
                    }
                }

                /**
                 * Etape SUMMARY
                 */
                if (racineNoeuds.item(i).getNodeName().contains("summary"))
                {
                    final NodeList joueurs = racineNoeuds.item(i).getChildNodes();
                    final int nbSieges = joueurs.getLength();

                    String nom , action, valeur;

                    for(int j = 0; j<nbSieges; j++)
                    {
                        if(joueurs.item(j).getNodeName().contains("joueur"))
                        {
                            nom = joueurs.item(j).getAttributes().getNamedItem("nom").getNodeValue();

                            final NodeList informations = joueurs.item(j).getChildNodes();
                            final int nbInformations = informations.getLength();

                            for(int k = 0; k<nbInformations; k++)
                            {
                                if(informations.item(k).getNodeName().contains("action2"))
                                {
                                    action = informations.item(k).getAttributes().getNamedItem("nom").getNodeValue();
                                    // Mise à jour des informations
                                    for(eJoueur game: eJoueur.values())
                                    {
                                        if(game.getNom().contains(nom))
                                            game.setShow(action);
                                    }
                                }
                                if(informations.item(k).getNodeName().contains("action1"))
                                {
                                    action = informations.item(k).getAttributes().getNamedItem("nom").getNodeValue();
                                    valeur = informations.item(k).getTextContent();
                                    // Mise à jour des informations
                                    if(action.contains("showed"))
                                    {
                                        for(eJoueur game: eJoueur.values())
                                        {
                                            if(game.getNom().contains(nom))
                                                game.setCarteDealt(valeur);
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
}
