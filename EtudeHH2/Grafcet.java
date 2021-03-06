import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Description : A faire
 *
 * @author Thierry Mater
 * @version 1.0.0 (23/08/2017)
 */

public class Grafcet
{
    // variables d'instance

    private Table tableTournois;
    private List<Noeud> noeuds = new ArrayList<Noeud>();
    private CarteB carteBoard;
    private List<Summary> summarys = new ArrayList<Summary>();

    private String chemin = "";

    private int etape = 0;
    private String noeud = "";
    private String action = "";
    private String nom = "";
    private String valeur = "";
    private boolean bool = false;

    private String siege = "";
    private String option = "";
    private String action1 = "";
    private String actioninfo1 ="";
    private String action2 = "";
    private String actioninfo2 = "";
    private String gain = "";

    private String heureDebutTournoi = "";
    private String levelDebutTournoi = "";
    private String hero = "";

    private boolean sidePot = false;
    private String pot = "";

    public Grafcet(String dossier)
    {
        tableTournois = new Table();
        carteBoard = new CarteB();
        chemin = dossier;
    }

    public void etudeGrafcet(String ligne) throws IOException
    {
        switch (etape)
        {
            case 0:
            {

                if(ligne.contains("Winamax"))
                {
                    tableTournois.setTable(ligne);

                    if(heureDebutTournoi == "")
                    {
                        heureDebutTournoi = tableTournois.getDateHeure();
                        levelDebutTournoi = tableTournois.getLevel();
                    }
                }

                if(ligne.contains("Table"))
                {
                    tableTournois.setTableNumero(ligne);

                    etape = 1;
                }
                break;
            }

            /**
             * Traitement de la position des joueurs sur la table
             */
            case 1:
            {
                if(ligne.contains("*** ANTE"))
                {
                    etape = 2;
                    break;
                }

                String[] split1 = ligne.split(": ");
                String[] split2 = split1[1].split(" \\(");

                noeud = "siege";
                action = split1[0].substring(5);
                nom = split2[0];
                valeur = split2[1].substring(0, split2[1].length()-1);

                noeuds.add(new Noeud(noeud, nom, action, valeur));

                break;
            }

            /**
             * Je suis dans l'ANTE/BLIND
             * 
             * *** ANTE/BLINDS ***
            jujubelucky posts ante 2000
            azn2vn posts ante 2000
            Perrineleis posts ante 2000
            CARREJJ posts ante 2000
            xxxkandjar posts ante 1156 and is all-in
            OMtropuissan posts ante 2000
            PHIPHI DU73 posts ante 2000
            jafar31 posts ante 2000
            jujubelucky posts small blind 8000
            azn2vn posts big blind 16000
             */

            case 2:
            {
                noeud = "anteblinds";
                bool = false;

                if (ligne.contains("posts ante"))
                {
                    String[] split1 = ligne.split(" posts ante ");

                    nom = split1[0]; // nom du joueur
                    action = "posts ante";
                    valeur = split1[1]; // nombre de jeton

                    bool = true;                
                }

                if (ligne.contains("small blind"))
                {
                    String[] split1 = ligne.split(" posts small blind ");

                    nom = split1[0]; // nom du joueur
                    action = "small blind";
                    valeur = split1[1]; // nombre de jeton

                    bool = true;
                }

                if (ligne.contains("big blind"))
                {
                    String[] split1 = ligne.split(" posts big blind ");

                    nom = split1[0]; // nom du joueur
                    action = "big blind";
                    valeur = split1[1]; // nombre de jeton

                    bool = true;                
                }

                if (ligne.contains("Dealt to"))
                {
                    String[] split1 = ligne.split(" \\[");

                    nom = split1[0].substring(9); // nom du joueur
                    hero = nom;
                    action = "dealt";
                    valeur = split1[1].substring(0, split1[1].length()-1); // Carte privative du joueur

                    bool = true;
                }

                if(ligne.contains("*** PRE"))
                {
                    etape = 3;
                    break;
                }

                if(bool)
                {
                    noeuds.add(new Noeud(noeud, nom, action, valeur));
                    break;
                }
            }

            /**
             * je suis dans le PRE/FLOP
             */
            case 3:
            {
                analyse(ligne, "preflop");

                if(ligne.contains("*** FLOP"))
                {
                    // récupération des cartes du Flop
                    carteBoard.setCartesFlop(ligne);

                    etape = 4;
                    break;
                }

                if(ligne.contains("*** TURN"))
                {
                    // récupération des cartes du TURN                  
                    carteBoard.setCartesTurn(ligne);

                    etape = 5;
                    break;
                }

                if(ligne.contains("*** RIVER"))
                    {
                    // récupération des cartes du RIVER                 
                    carteBoard.setCartesRiver(ligne);

                    etape = 6;
                    break;
                }

                if(ligne.contains("*** SHOW"))
                {
                    etape = 7;
                    break;
                }

                if(ligne.contains("*** SUMMARY"))
                {
                    etape = 8;
                    break;
                }

                if(bool)
                {
                    noeuds.add(new Noeud(noeud, nom, action, valeur));
                    break;
                }	
            }

            /**
             * je suis dans le FLOP
             */

            case 4:
            {
                analyse(ligne, "flop");

                if(ligne.contains("*** TURN"))
                {
                    // récupération des cartes du TURN                  
                    carteBoard.setCartesTurn(ligne);

                    etape = 5;
                    break;
                }

                if(ligne.contains("*** RIVER"))
                {
                    // récupération des cartes du RIVER                 
                    carteBoard.setCartesRiver(ligne);

                    etape = 6;
                    break;
                }

                if(ligne.contains("*** SHOW"))
                {
                    etape = 7;
                    break;
                }

                if(ligne.contains("*** SUMMARY"))
                {
                    etape = 8;
                    break;
                }

                if(bool)
                {
                    noeuds.add(new Noeud(noeud, nom, action, valeur));
                    break;
                }
            }
            /**
             * je suis dans le TURN
             */
            case 5:
            {

                analyse(ligne, "turn");

                if(ligne.contains("*** RIVER"))
                {
                    // récupération des cartes du RIVER                 
                    carteBoard.setCartesRiver(ligne);

                    etape = 6;
                    break;
                }

                if(ligne.contains("*** SHOW"))
                {
                    etape = 7;
                    break;
                }

                if(ligne.contains("*** SUMMARY"))
                {
                    etape = 8;
                    break;
                }

                if(bool)
                {
                    noeuds.add(new Noeud(noeud, nom, action, valeur));
                    break;
                }
            }

            /**
             * je suis dans le RIVER
             */

            case 6:
            {
                analyse(ligne, "river");

                if(ligne.contains("*** SHOW"))
                {
                    etape = 7;
                    break;
                }

                if(ligne.contains("*** SUMMARY"))
                {
                    etape = 8;
                    break;
                }
                
                if(bool)
                {
                    noeuds.add(new Noeud(noeud, nom, action, valeur));
                    break;
                }
            }

            /**
             * je suis dans le SHOW
             */

            case 7:
            {
                /**
                 * TODO : AJOUTER L'ETUDE SIDE POT SUR HERO POUR TRANSFORMER WON EN LOST
                 * 
                 * 
                 * 
                 */

                if(ligne.contains(hero))
                {
                    if(ligne.contains("side pot"))
                    {
                        sidePot  = true;
                        System.out.println("Side pot :" + sidePot);
                    }
                }

                if(ligne.contains("*** SUMMARY"))
                {
                    etape = 8;
                    break;
                }
            }

            /**
             * je suis dans le SUMMARY
             */
            case 8: 
            {
                if (ligne.contains("pot"))
                {
                    String[] split1 = ligne.split(" ");
                    pot = split1[2];
                    break;
                }

                if (ligne.contains("Board"))
                {
                    carteBoard.setCartesBoard(ligne);
                    break;
                }

                /**
                 * Recherche et mise en forme des résulats du coup joué d'après le Summary
                 * 
                 * EXEMPLE de mise en forme
                 *      <joueur nom="xxxkandjar">
                 *          <siege>6</siege>
                 *          <option>small blind</option> -> autre option bid blind ou button
                 *          <action1 nom = "showed">Ks As Th 7d</action1> -> autre action1 folded
                 *          <action2 nom = "won" gain = "700">with Flush Ace high Ks As Th 7d</action2> -> autre action2 lost
                 *      </joueur>   
                 */
                if (ligne.contains("Seat "))
                {
                    String[] split1 = ligne.split(": ");
                    siege = split1[0].substring(5); // numéro du siege
                    String recherche = split1[1];

                    if (recherche.contains("small blind"))
                    {
                        if (recherche.contains("folded"))
                        {
                            String[] folded = recherche.split(" folded ");
                            nom = folded[0].substring(0, folded[0].length()-14);
                            option = "small blind";
                            action1 = "folded";
                            actioninfo1 = folded[1];
                            action2 = "";
                            gain = "";
                            actioninfo2 = "";
                            affichage();
                            break;
                        }

                        if (recherche.contains("showed"))
                        {
                            if (recherche.contains(" won "))
                            {
                                String[] showed = recherche.split(" showed ");
                                nom = showed[0].substring(0, showed[0].length()-14);
                                option = "small blind";
                                action1 = "showed";
                                String[] infoShowed = showed[1].split(" and won ");
                                actioninfo1 = infoShowed[0].substring(1, infoShowed[0].length()-1);

                                if(nom.contains(hero))
                                {
                                    if(sidePot)
                                    {
                                        action2 = "lost";
                                    }
                                    else
                                    {
                                        action2 = "won";
                                    }
                                }
                                else
                                {
                                    action2 = "won";
                                }

                                String[] gainwon = infoShowed[1].split(" with");
                                gain = gainwon[0];
                                String[] actionString = ligne.split(" with");
                                actioninfo2 = "with" + actionString[1];
                                affichage();
                                break;
                            }
                            else
                            {
                                String[] showed = recherche.split(" showed ");
                                nom = showed[0].substring(0, showed[0].length()-14);
                                option = "small blind";
                                action1 = "showed";
                                String[] infoShowed = showed[1].split(" and lost ");
                                actioninfo1 = infoShowed[0].substring(1, infoShowed[0].length()-1);
                                action2 = "lost";
                                gain = "";
                                String[] actionString = ligne.split(" with");
                                actioninfo2 = "with" + actionString[1];
                                affichage();
                                break;
                            }
                        }

                        if (recherche.contains(" won "))
                        {
                            String[] smallWon = recherche.split(" won ");
                            nom = smallWon[0].substring(0, smallWon[0].length()-14);
                            option = "small blind";
                            action1 = "";
                            actioninfo1 = "";

                            if(nom.contains(hero))
                            {
                                if(sidePot)
                                {
                                    action2 = "lost";
                                }
                                else
                                {
                                    action2 = "won";
                                }
                            }
                            else
                            {
                                action2 = "won";
                            }

                            gain = smallWon[1];
                            actioninfo2 = "";
                            affichage();
                            break;
                        }
                    }

                    if (recherche.contains("big blind"))
                    {
                        if (recherche.contains("folded"))
                        {
                            String[] folded = recherche.split(" folded ");
                            nom = folded[0].substring(0, folded[0].length()-12);
                            option = "big blind";
                            action1 = "folded";
                            actioninfo1 = folded[1];
                            action2 = "";
                            gain = "";
                            actioninfo2 = "";
                            affichage();
                            break;
                        }

                        if (recherche.contains("showed"))
                        {
                            if (recherche.contains(" won "))
                            {
                                String[] showed = recherche.split(" showed ");
                                nom = showed[0].substring(0, showed[0].length()-12);
                                option = "big blind";
                                action1 = "showed";
                                String[] infoShowed = showed[1].split(" and won ");
                                actioninfo1 = infoShowed[0].substring(1, infoShowed[0].length()-1);

                                if(nom.contains(hero))
                                {
                                    if(sidePot)
                                    {
                                        action2 = "lost";
                                    }
                                    else
                                    {
                                        action2 = "won";
                                    }
                                }
                                else
                                {
                                    action2 = "won";
                                }

                                String[] gainwon = infoShowed[1].split(" with");
                                gain = gainwon[0];
                                String[] actionString = ligne.split(" with");
                                actioninfo2 = "with" + actionString[1];
                                affichage();
                                break;
                            }
                            else
                            {
                                String[] showed = recherche.split(" showed ");
                                nom = showed[0].substring(0, showed[0].length()-12);
                                option = "big blind";
                                action1 = "showed";
                                String[] infoShowed = showed[1].split(" and lost ");
                                actioninfo1 = infoShowed[0].substring(1, infoShowed[0].length()-1);
                                action2 = "lost";
                                gain = "";
                                String[] actionString = ligne.split(" with");
                                actioninfo2 = "with" + actionString[1];
                                affichage();
                                break;
                            }
                        }

                        if (recherche.contains(" won "))
                        {
                            String[] bigWon = recherche.split(" won ");
                            nom = bigWon[0].substring(0, bigWon[0].length()-12);
                            option = "big blind";
                            action1 = "";
                            actioninfo1 = "";

                            if(nom.contains(hero))
                            {
                                if(sidePot)
                                {
                                    action2 = "lost";
                                }
                                else
                                {
                                    action2 = "won";
                                }
                            }
                            else
                            {
                                action2 = "won";
                            }
                            gain = bigWon[1];
                            actioninfo2 = "";
                            affichage();
                            break;
                        }
                    }

                    if (recherche.contains("button"))
                    {
                        if (recherche.contains("folded"))
                        {
                            String[] folded = recherche.split(" folded ");
                            nom = folded[0].substring(0, folded[0].length()-9);
                            option = "button";
                            action1 = "folded";
                            actioninfo1 = folded[1];
                            action2 = "";
                            gain = "";
                            actioninfo2 = "";
                            affichage();
                            break;
                        }

                        if (recherche.contains("showed"))
                        {
                            if (recherche.contains(" won "))
                            {
                                String[] showed = recherche.split(" showed ");
                                nom = showed[0].substring(0, showed[0].length()-9);
                                option = "button";
                                action1 = "showed";
                                String[] infoShowed = showed[1].split(" and won ");
                                actioninfo1 = infoShowed[0].substring(1, infoShowed[0].length()-1);

                                if(nom.contains(hero))
                                {
                                    if(sidePot)
                                    {
                                        action2 = "lost";
                                    }
                                    else
                                    {
                                        action2 = "won";
                                    }
                                }
                                else
                                {
                                    action2 = "won";
                                }

                                String[] gainwon = infoShowed[1].split(" with");
                                gain = gainwon[0];
                                String[] actionString = ligne.split(" with");
                                actioninfo2 = "with" + actionString[1];
                                affichage();
                                break;
                            }
                            else
                            {
                                String[] showed = recherche.split(" showed ");
                                nom = showed[0].substring(0, showed[0].length()-9);
                                option = "button";
                                action1 = "showed";
                                String[] infoShowed = showed[1].split(" and lost ");
                                actioninfo1 = infoShowed[0].substring(1, infoShowed[0].length()-1);
                                action2 = "lost";
                                gain = "";
                                String[] actionString = ligne.split(" with");
                                actioninfo2 = "with" + actionString[1];
                                affichage();
                                break;
                            }
                        }

                        if (recherche.contains(" won "))
                        {
                            String[] bigWon = recherche.split(" won ");
                            nom = bigWon[0].substring(0, bigWon[0].length()-9);
                            option = "button";
                            action1 = "";
                            actioninfo1 = "";
                            if(nom.contains(hero))
                            {
                                if(sidePot)
                                {
                                    action2 = "lost";
                                }
                                else
                                {
                                    action2 = "won";
                                }
                            }
                            else
                            {
                                action2 = "won";
                            }
                            gain = bigWon[1];
                            actioninfo2 = "";
                            affichage();
                            break;
                        }
                    }

                    if (recherche.contains("folded"))
                    {
                        String[] folded = recherche.split(" folded ");
                        nom = folded[0];
                        option = "";
                        action1 = "folded";
                        actioninfo1 = folded[1];
                        action2 = "";
                        gain = "";
                        actioninfo2 = "";
                        affichage();
                        break;
                    }

                    if (recherche.contains("showed"))
                    {
                        if (recherche.contains(" won "))
                        {
                            String[] showed = recherche.split(" showed ");
                            nom = showed[0];
                            option = "";
                            action1 = "showed";
                            String[] infoShowed = showed[1].split(" and won ");
                            actioninfo1 = infoShowed[0].substring(1, infoShowed[0].length()-1);

                            if(nom.contains(hero))
                            {
                                if(sidePot)
                                {
                                    action2 = "lost";
                                }
                                else
                                {
                                    action2 = "won";
                                }
                            } 
                            else
                            {
                                action2 = "won";
                            }

                            String[] gainwon = infoShowed[1].split(" with");
                            gain = gainwon[0];
                            String[] actionString = ligne.split(" with");
                            actioninfo2 = "with" + actionString[1];
                            affichage();
                            break;
                        }else
                        {
                            String[] showed = recherche.split(" showed ");
                            nom = showed[0];
                            option = "";
                            action1 = "showed";
                            String[] infoShowed = showed[1].split(" and lost ");
                            actioninfo1 = infoShowed[0].substring(1, infoShowed[0].length()-1);
                            action2 = "lost";
                            gain = "";
                            String[] actionString = ligne.split(" with");
                            actioninfo2 = "with" + actionString[1];
                            affichage();
                            break;
                        }
                    }

                    if (recherche.contains(" won "))
                    {
                        String[] splitwon = recherche.split(" won ");
                        nom = splitwon[0];
                        option = "";
                        action1 = "";
                        actioninfo1 = "";

                        if(nom.contains(hero))
                        {
                            if(sidePot)
                            {
                                action2 = "lost";
                            }
                            else
                            {
                                action2 = "won";
                            }
                        }
                        else
                        {
                            action2 = "won";
                        }
                        gain = splitwon[1];
                        actioninfo2 = "";
                        affichage();
                        break;
                    }
                }

                if(ligne.length() == 0)
                {    
                    Noeud itrNoeud;//itrNoeud
                    Summary itrSummary;//itrSummary;

                    String cheminFichier = chemin + "\\" + tableTournois.getNumero() + "_" + tableTournois.getDateHeure() + "_" + hero + ".xml";

                    final File fichier =new File(cheminFichier); 
                    try
                    {
                        // Creation du fichier
                        fichier .createNewFile();
                        // creation d'un writer
                        final FileWriter ecriture = new FileWriter(fichier);
                        try
                        {
                            ecriture.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n");
                            ecriture.write("<?xml-stylesheet type=\"text/xsl\" href= \"HHXML.xsl\" ?>\n");
                            ecriture.write("\n");                   
                            ecriture.write("<main numero = \"" + tableTournois.getNumeroMain() + "\">\n");
                            ecriture.write("\t<coup heure = \"" + tableTournois.getDateHeure() + "\">" + tableTournois.getLevel() + "</coup>\n");
                            ecriture.write("\t<debut heure = \"" + heureDebutTournoi + "\">" + levelDebutTournoi + "</debut>\n");
                            ecriture.write("\t<tournoi type= \"" + tableTournois.getTypeGame() + "\">" + tableTournois.getNom() + "</tournoi>\n");
                            ecriture.write("\t<numero>" + tableTournois.getNumero() + "</numero>\n");
                            ecriture.write("\t<serveur>" + tableTournois.getRoom() + "</serveur>\n");
                            ecriture.write("\t<buyin>" + tableTournois.getBuyin() + "</buyin>\n");
                            ecriture.write("\t<table>" + tableTournois.getTable() + "</table>\n");
							ecriture.write("\t<hero>" + hero + "</hero>\n");

                            ecriture.write("\t<siege>\n");

                            for (Iterator<Noeud> itr = noeuds.iterator(); itr.hasNext();) {
                                itrNoeud = itr.next();
                                if (itrNoeud.getNoeud()=="siege") {
                                    ecriture.write("\t\t<joueur nom = \"" + itrNoeud.getNom() + "\" action = \"" + itrNoeud.getAction() + "\">" + itrNoeud.getValeur() + "</joueur>\n");
                                }
                            }
                            ecriture.write("\t</siege>\n");

                            ecriture.write("\t<anteblinds>\n");
                            for (Iterator<Noeud> itr = noeuds.iterator(); itr.hasNext();) {
                                itrNoeud = itr.next();
                                if (itrNoeud.getNoeud()=="anteblinds") {
                                    ecriture.write("\t\t<joueur nom = \"" + itrNoeud.getNom() + "\" action = \"" + itrNoeud.getAction() + "\">" + itrNoeud.getValeur() + "</joueur>\n");
                                }
                            }
                            ecriture.write("\t</anteblinds>\n");

                            ecriture.write("\t<preflop>\n");
                            for (Iterator<Noeud> itr = noeuds.iterator(); itr.hasNext();) {
                                itrNoeud = itr.next();
                                if (itrNoeud.getNoeud()=="preflop") {
                                    ecriture.write("\t\t<joueur nom = \"" + itrNoeud.getNom() + "\" action = \"" + itrNoeud.getAction() + "\">" + itrNoeud.getValeur() + "</joueur>\n");
                                }
                            }
                            ecriture.write("\t</preflop>\n");

                            ecriture.write("\t<flop>\n");
                            ecriture.write("\t\t<carte>" + carteBoard.getFlop1() + " " + carteBoard.getFlop2()
                                + " " + carteBoard.getFlop3() + "</carte>\n");
                            for (Iterator<Noeud> itr = noeuds.iterator(); itr.hasNext();) {
                                itrNoeud = itr.next();
                                if (itrNoeud.getNoeud()=="flop") {
                                    ecriture.write("\t\t<joueur nom = \"" + itrNoeud.getNom() + "\" action = \"" + itrNoeud.getAction() + "\">" + itrNoeud.getValeur() + "</joueur>\n");
                                }
                            }
                            ecriture.write("\t</flop>\n");

                            ecriture.write("\t<turn>\n");
                            ecriture.write("\t\t<carte>" + carteBoard.getTurn() + "</carte>\n");
                            for (Iterator<Noeud> itr = noeuds.iterator(); itr.hasNext();) {
                                itrNoeud = itr.next();
                                if (itrNoeud.getNoeud()=="turn") {
                                    ecriture.write("\t\t<joueur nom = \"" + itrNoeud.getNom() + "\" action = \"" + itrNoeud.getAction() + "\">" + itrNoeud.getValeur() + "</joueur>\n");
                                }
                            }
                            ecriture.write("\t</turn>\n");

                            ecriture.write("\t<river>\n");
                            ecriture.write("\t\t<carte>" + carteBoard.getRiver() + "</carte>\n");
                            for (Iterator<Noeud> itr = noeuds.iterator(); itr.hasNext();) {
                                itrNoeud = itr.next();
                                if (itrNoeud.getNoeud()=="river") {
                                    ecriture.write("\t\t<joueur nom = \"" + itrNoeud.getNom() + "\" action = \"" + itrNoeud.getAction() + "\">" + itrNoeud.getValeur() + "</joueur>\n");
                                }
                            }
                            ecriture.write("\t</river>\n");

                            ecriture.write("\t<showdown>\n");
                            for (Iterator<Noeud> itr = noeuds.iterator(); itr.hasNext();) {
                                itrNoeud = itr.next();
                                if (itrNoeud.getNoeud()=="showdown") {
                                    ecriture.write("\t\t<joueur nom = \"" + itrNoeud.getNom() + "\" action = \"" + itrNoeud.getAction() + "\">" + itrNoeud.getValeur() + "</joueur>\n");
                                }
                            }
                            ecriture.write("\t</showdown>\n");

                            ecriture.write("\t<summary>\n");
                            ecriture.write("\t<pot>" + pot + "</pot>\n");
                            ecriture.write("\t<carte>" + carteBoard.getCartesBoard() + "</carte>\n");

                            for (Iterator<Summary> itr = summarys.iterator(); itr.hasNext();) {
                                itrSummary = itr.next();
                                ecriture.write("\t<joueur nom = \"" + itrSummary.getNom() + "\">\n");
                                ecriture.write("\t\t<siege>" + itrSummary.getSiege() + "</siege>\n");
                                ecriture.write("\t\t<option>" + itrSummary.getOption() + "</option>\n");
                                ecriture.write("\t\t<action1 nom = \"" + itrSummary.getAction1() + "\">" + itrSummary.getActionInfo1() + "</action1>\n");
                                ecriture.write("\t\t<action2 nom = \"" + itrSummary.getAction2() + "\" gain = \"" + itrSummary.getGain() + "\">" + itrSummary.getActionInfo2() + "</action2>\n");
                                ecriture.write("\t</joueur>\n");
                            }
                            ecriture.write("\t</summary>\n");
                            ecriture.write("</main>\n");                        

                        } finally {
                            // quoiqu'il arrive, on ferme le fichier
                            ecriture.close();
                        }
                        ecriture.close();
                    } catch (Exception e) {
                        System.out.println("Impossible de creer le fichier");
                    }

                    etape = 0;
                    sidePot = false;
                    pot = "0";
                    noeuds.clear();
                    summarys.clear();

                    break;
                }
            }

            default:
            break;
        }
    }

    /**
     * Analyse les actions des joueurs
     * @param ligne
     * @param infoNoeud
     */
    private void analyse(String ligne, String infoNoeud)
    {
        noeud = infoNoeud;
        bool = false;
        nom = "";
        action = "";
        valeur = "";

        /**
         * recherche d'un calls
         * nom calls 9999
         */

        if (ligne.contains("calls"))
        {
            String[] split1 = ligne.split(" calls ");

            nom = split1[0];
            action = "calls";
            valeur = split1[1];

            bool = true;

            /**
             * recherche d'un folds
             * nom folds
             */
        }
        else if (ligne.contains("folds"))
        {
            String[] split1 = ligne.split(" folds");

            nom = split1[0];
            action = "folds";
            valeur = "";

            bool = true;

            /**
             * recherche d'un bets
             * nom bets 99999
             */
        }
        else if (ligne.contains("bets"))
        {
            String[] split1 = ligne.split(" bets ");

            nom = split1[0];
            action = "bets";
            valeur = split1[1];

            bool = true;

            /**
             * recherche d'un checks
             * nom checks
             */
        }
        else if (ligne.contains("checks"))
        {
            String[] split1 = ligne.split(" checks");

            nom = split1[0];
            action = "checks";
            valeur = "";

            bool = true;

            /**
             * recherche d'un raises
             * nom raises 1111 to 2222
             */
        }
        else if (ligne.contains("raises"))
        {
            String[] split1 = ligne.split(" raises ");

            nom = split1[0];
            action = "raises";
            valeur = split1[1];

            bool = true;

            /**
             * recherche d'un collected
             * nom collected 9999 from pot
             */
        }
        else if (ligne.contains("collected")) 
        {
            String[] split1 = ligne.split(" collected ");

            nom = split1[0];
            action = "collected";
            valeur = split1[1].substring(0, split1[1].length()-9);

            bool = true;
        }
        else if (ligne.contains("shows"))
        {
            String[] split1 = ligne.split(" shows ");

            nom = split1[0];
            action = "shows";
            valeur = split1[1];

            bool = true;
        }
    }

    private void affichage()
    {
        summarys.add(new Summary(siege, nom, option, action1, actioninfo1, action2, actioninfo2, gain));
    }
}
