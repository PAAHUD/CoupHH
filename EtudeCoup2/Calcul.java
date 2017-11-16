import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import java.text.DecimalFormat;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.DataInputStream ;
import java.net.*;

/**
 * class Calcul
 *
 * @author Thierry Mater
 * @version 0.2
 * @since 13/09/2017
 * 
 * *********************************************************************************
 *  Version 0.0.4beta
 *      19/10/17 : mise en place provisoire une ligne spécifique pour les statistiques
 *      31/10/17 : modification de la méthode ecritureResultat
 *  Version 0.1.0beta
 *      10/11/17 => découpage de la class
 * *********************************************************************************
 */
public class Calcul
{
    // variables d'instance
    static DecimalFormat df = new DecimalFormat("####.####");

    private List<Float> tabShoeTime;
    private int[] repTabFV = new int[10];
    private Hashtable<Integer, Float> tabFV;
    private ArrayList<BigDeal> tabBigDeal;
    private ReadXMLFile resultat;
    private Hashtable<Integer, Float> tabFM;
    private Hashtable<Integer, Float> tabCond1FM;
    private Hashtable<Integer, Float> tabCond2FM;
    private Hashtable<Integer, Float> tabFNJ;
    private Hashtable<Character, Float> tabFDA;
    private TreeMap<Integer, String> valeurFichier;

    /*
    private String chemin = "";
    private String cartesFlop, cartesTurn, cartesRiver;
    private String extension = "111", nomFichierArchive;
    private boolean badBeat, bigDeal, shoeTime;
    private String repertoireInit;
    private String cartesHero, cartesVillain;
    private Integer jetonPreFlop=0, jetonFlop=0, jetonTurn=0, jetonRiver=0;
    private char dernierAvenue;
    private int joueursActifs = 0;
    private int TM, NJP, vitesseMTT, indexVitesseMTT;
    private String message;
    private float winPreFlop = 0.0000f, losePreFlop = 0.0000f, tiePreFlop = 0.0000f;
    private float winFlop = 0.0000f, loseFlop = 0.0000f, tieFlop = 0.0000f;
    private float winTurn = 0.0000f, loseTurn = 0.0000f, tieTurn = 0.0000f;
    private boolean A1BB, A1BD, A1ST;
    private boolean badBeatPreFlop, badBeatFlop, badBeatTurn;
    private float facteurValeurMainBD = 0.0000f, facteurValiditeBD = 0.0000f;
    private float facteurBD_PreFlop = 0.0000f, facteurBD_Flop = 0.0000f, facteurBD_Turn = 0.0000f;
    private float FV_BD_PreFlop = 0.0000f, FV_BD_Flop = 0.0000f, FV_BD_Turn = 0.0000f;
    private Integer ACC_BD_PreFlop=0, ACC_BD_Flop=0, ACC_BD_Turn=0, ACC_BD_River=0;
    private Integer ACC_ST_PreFlop=0, ACC_ST_Flop=0, ACC_ST_Turn=0, ACC_ST_River=0;
    private float facteurShoeTime;
    private float NJ1_ST = 0.0000f, NJ2_ST = 0.0000f, NJ3_ST = 0.0000f, NJ4_ST = 0.0000f;
    private float NJ1_BB = 0.0000f, NJ2_BB = 0.0000f, NJ3_BB = 0.0000f, NJ4_BB = 0.0000f;
    private float FV_ST_PreFlop = 0.0000f, FV_ST_Flop = 0.0000f, FV_ST_Turn = 0.0000f;;
    private float FMRR = 0.0000f;
    private float TJP_BB = 0.0000f, TJP_BD = 0.0000f, TJP_ST = 0.0000f;
    private Integer ACC_BB_PreFlop=0, ACC_BB_Flop=0, ACC_BB_Turn=0, ACC_BB_River=0;
    private float NJ1_BD = 0.0000f, NJ2_BD = 0.0000f, NJ3_BD = 0.0000f, NJ4_BD = 0.0000f;
    private Integer TIJ, TFJ;
    private int JPP_BB, JPP_BD, JPP_ST,PRORATA_JPP_BB, PRORATA_JPP_BD, PRORATA_JPP_ST;
    private float FPJP_BB= 0.0000f, FPJP_BD = 0.0000f, FPJP_ST= 0.0000f;
    private float FPJP_TM_BB= 0.0000f, FPJP_TM_BD = 0.0000f, FPJP_TM_ST= 0.00f;
    private float FPJP = 0.0000f, FPJP_TM = 0.0000f;
    private int indexTabFV;
    private float FM, FMR;
    private float FMR_BB = 0.0000f, FMR_ST = 0.0000f, FMR_BD = 0.0000f;
    private boolean  accidentA = false, accidentNA = false;
    private boolean ITM = false, rechercheAccident;
    private float FTJ = 0.0000f;
    private float baseRemboursement ,remboursement;
    private float FNJ = 0.0000f;
    private float rebuy = 0.0000f;
    private int addon;
    private float BR, FV, FDA;
    private float accident = 0.0000f,MRA_NA = 0.0000f, MRBB_A = 0.0000f, MRBD_A = 0.0000f,
    MRST_A = 0.0000f, MRBBBD_A = 0.0000f, MRBBST_A = 0.0000f, FMSA = 0.0000f;
    private float momentCalcule = 0.0000f;
    private float MGR = 0.0000f, MRBB = 0.0000f, MRBD = 0.0000f, MRST = 0.0000f;
    private Integer keyValeurFichier = 0;
    */
   
    private Integer typeGame = 0, nbCartesJoueur=0;
    BadBeat BBpreFlop;
    BadBeat BBFlop;
    BadBeat BBTurn;

    public Calcul(String dossier)
    {
        eVariables.chemin.setString(dossier);
        resultat = new ReadXMLFile();
        tabBigDeal = new ArrayList<BigDeal>();
        tabShoeTime = new ArrayList<Float>();
        //tabFV = new Hashtable<Integer, Float>();
        //tabFM = new Hashtable<Integer, Float>();
        //tabCond1FM = new Hashtable<Integer, Float>();
        //tabCond2FM = new Hashtable<Integer, Float>();
        //tabFNJ = new Hashtable<Integer, Float>();
        //tabFDA = new Hashtable<Character, Float>();
        valeurFichier = new TreeMap<>();

        initTreeMapRoig();
        BBpreFlop = new BadBeat();
        BBFlop = new BadBeat();
        BBTurn = new BadBeat();
    }

    /*
     * Calcul des accidents
     */
    public void CalculAccident(String cheminResultat, String nomFichier) throws IOException 
    {
        /*
         * Recherche si information LOST pour notre Hero
         */

        if(heroLost()) {

            /**************************
             * 
             *      ***********
             *      **       ** 
             *      **       **
             *      ***********
             *      **       **
             *      **       **
             *      **       **
             *    
             **************************/

            initTabDB_ST();

            /**
             * A0 : Récupération du tapis initial TIJ
             *      Calcul du tapis final TFJ
             *      Calcul du nombre de jeton perdus dans le coup NJP
             *      Récupération des jetons joués par Hero
             *      Récupération des cartes privatives du Villain
             *      Récupération des cartes privatives du Hero
             *      Répurération du nombre de joueurs actifs
             */

            /**
             * Type de jeu
             */

            typeGame = Integer.parseInt(eDonnee.Donnee13.getValeur());

            for(GameType game: GameType.values())
            {
                if(game.getId() == typeGame)
                    nbCartesJoueur = game.getcardsOnHandPlayers();
            }

            /**
             * Initialisation des cartes du tapis
             */
            if(typeGame<4)
            {
                eVariables.cartesFlop.setString(eDonnee.Donnee10.getValeur().replace(" ", "%20"));
                eVariables.cartesTurn.setString(eDonnee.Donnee11.getValeur());
                eVariables.cartesRiver.setString(eDonnee.Donnee12.getValeur());
                String[] splitFlop = eDonnee.Donnee10.getValeur().split(" ");

                ejlRoig.Donnee21.setValeur(splitFlop[0]);
                ejlRoig.Donnee22.setValeur(splitFlop[1]);
                ejlRoig.Donnee23.setValeur(splitFlop[2]);
                ejlRoig.Donnee24.setValeur(eVariables.cartesTurn.getString());
                ejlRoig.Donnee25.setValeur(eVariables.cartesRiver.getString());

                fctValeurFichier("Cartes du Flop : " + eDonnee.Donnee10.getValeur(),"TAPIS");
                fctValeurFichier("Cartes de la Turn : " + eVariables.cartesTurn.getString(), "");
                fctValeurFichier("Cartes de la River : " +eVariables.cartesRiver.getString(), "");
            }

            etapeA0();

            if(etapeA1())
            {
                etapeA2();
                etapeA3();
                etapeA4();
                etapeA5();
                etapeA6();
                etapeA7();

                /**************************
                 * 
                 *      **********
                 *      **       ** 
                 *      **      **
                 *      *********
                 *      **      **
                 *      **       **
                 *      **********
                 * 
                 **************************/

                /**
                 * PAA a établi les tableaux suivants concernant les autres facteurs d'appréciation
                 * de l'accident
                 */

                /*
                 * Initialisation des tableaux B
                 */
                TableauB.init();

               B.etape1();

                /**
                 * B2 / Facteur de momena du MTT: FM
                 */
                B.etape2();

                /**
                 * B3 / Facteur du nombre de joueurs actifs (ayant recu les cartes
                 *      privatives) a la table au moment du coup : FNJ
                 */
                B.etape3();

                /**
                 * B4 / Facteur de derniere avenue (preflop, flop, turn, river) : 
                 *      derniere avenue ou le hero a mise des jetons (raise ou bet
                 *      ou call dans la HH) : FDA
                 */
                B.etape4();
                B.etape5();
                B.etape6();
                B.etape7();

                /**************************
                 * 
                 *      **********
                 *      **
                 *      **
                 *      **
                 *      **
                 *      **
                 *      **********
                 * 
                 **************************/

                /**
                 * C / La base de remboursement BR est definie selon le type du tournoi MTT
                 *      Freezout : BR = x% du buy-in du MTT
                 *      rebuy ou re-entry : BR = x% des buy-in + recaves ou re-entry+add-on assures
                 */
                /**
                 * Initialisation de la valeur du rebuy
                 */
                etapeC();

                /**************************
                 * 
                 *      *********
                 *      **      **
                 *      **       **
                 *      **       **
                 *      **       **
                 *      **      **
                 *      *********
                 * 
                 **************************/

                /**
                 * D / dÃ©finition de la formule mathÃ©matique de remboursement
                 *      applicable aux accidents Anti-Tilt
                 */
                /**
                 * Initialisation de la valeur du rebuy
                 */
                etapeD1();

                extensionFicher();
                etapeD2(cheminResultat, nomFichier);

                eVariables.nomFichierArchive.setString(nomFichier.substring(0, nomFichier.length()-3) + eVariables.extension.getString());

                for(eJoueur game: eJoueur.values())
                    fctValeurFichier(game.ecriture(),"");

                ecritureResultat(cheminResultat, eVariables.nomFichierArchive.getString());

                new Log("ARCHIVAGE RESULTAT" + eVariables.nomFichierArchive.getString());
            }
            // mise en archive simple du coup
            else
            {
                eVariables.nomFichierArchive.setString(nomFichier.substring(0, nomFichier.length()-3) + "222");

                for(eJoueur game: eJoueur.values()) 
                    fctValeurFichier(game.ecriture(),"");

                ecritureResultat(cheminResultat, eVariables.nomFichierArchive.getString());

                new Log("ARCHIVAGE RESULTAT" + eVariables.nomFichierArchive.getString());
            }
        }
        // mise en archive simple du coup
        else
        {
            eVariables.nomFichierArchive.setString(nomFichier.substring(0, nomFichier.length()-3) + "000");

            ecritureResultat(cheminResultat, eVariables.nomFichierArchive.getString());
            new Log("ARCHIVAGE RESULTAT" + eVariables.nomFichierArchive.getString());
        }   
    }

    private void extensionFicher()
    {

        if (eVariables.badBeat.getBool() && !eVariables.bigDeal.getBool() && !eVariables.shoeTime.getBool())
            eVariables.extension.setString("100");

        if (!eVariables.badBeat.getBool() && eVariables.bigDeal.getBool() && !eVariables.shoeTime.getBool())
            eVariables.extension.setString("010");

        if (!eVariables.badBeat.getBool() && !eVariables.bigDeal.getBool() && eVariables.shoeTime.getBool())
            eVariables.extension.setString("001");

        if (eVariables.badBeat.getBool() && eVariables.bigDeal.getBool() && !eVariables.shoeTime.getBool())
            eVariables.extension.setString("110");

        if (eVariables.badBeat.getBool() && !eVariables.bigDeal.getBool() && eVariables.shoeTime.getBool())
            eVariables.extension.setString("101");    
    }

    /**************************************************************************
     * 
     *                          GESTION DES SOUS FONCTIONS
     * 
     *  Version : 0.1.0 du 13/09/17
     *  @author Thierry
     * 
     *********************************************************************** */

    private void initRepertoire()
    {
        eVariables.repertoireInit.setString("http://thierry.mater.free.fr/paaini");
    }

    /**
     * 
     * @param coupAssure
     */
    private void etapeA0() 
    {
        int totalJeton = 0, nbJoueur = 0;
        eVariables.joueursActifs.setInt(0);

        for(eJoueur game: eJoueur.values())
        {
            if(game.getShow().contains("won"))
            {
                if(game.getId()>=1)
                    eVariables.cartesVillain.setString(game.getCarteDealt().replace(" ", "%20"));
                else
                {
                    // Mise Ã  jour tapis initial
                    eVariables.TIJ.setInt(game.getJetonDebut());

                    // Mise Ã  jour tapis final
                    eVariables.TFJ.setInt(eVariables.TIJ.getInt() - game.getJetonAnteBlinds()
                            - game.getJetonPreFlop() - game.getJetonFlop() - game.getJetonTurn()
                            - game.getJetonRiver());
                            
                    ejlRoig.Donnee28.setValeur(game.getJetonAnteBlinds()+"");
                    ejlRoig.Donnee29.setValeur(game.getJetonPreFlop()+"");
                    
                    // Calcul du nombre de jetons perdu
                    eVariables.NJP.setInt(eVariables.TIJ.getInt() - eVariables.TFJ.getInt());

                    eVariables.rechercheAccident.setBool(false);
                    
                    if(eVariables.NJP.getInt()>=1)
                        eVariables.rechercheAccident.setBool(true);

                    eVariables.jetonPreFlop.setInt(game.getJetonAnteBlinds() + game.getJetonPreFlop());
                    eVariables.jetonFlop.setInt(game.getJetonFlop());
                    eVariables.jetonTurn.setInt(game.getJetonTurn());
                    eVariables.jetonRiver.setInt(game.getJetonRiver());
                    /*
                     * B4 / Facteur de derniÃ¨re avenue : derniÃ¨re avenue oÃ¹ l'assurÃ© a misÃ© des jetons (FDA)
                     */
                    eVariables.dernierAvenue.setChar(game.getAllIn());

                    eVariables.cartesHero.setString(game.getCarteDealt().replace(" ", "%20"));
                }
            }

            if(game.getShow().contains("lost"))
            {
                if(game.getId()<=0) 
                {
                    // Mise Ã  jour tapis initial
                    eVariables.TIJ.setInt(game.getJetonDebut());
                    // Mise Ã  jour tapis final
                    eVariables.TFJ.setInt(eVariables.TIJ.getInt() - game.getJetonAnteBlinds()
                            - game.getJetonPreFlop() - game.getJetonFlop()
                            - game.getJetonTurn() - game.getJetonRiver());

                    ejlRoig.Donnee28.setValeur(game.getJetonAnteBlinds()+"");
                    ejlRoig.Donnee29.setValeur(game.getJetonPreFlop()+"");

                    // Calcul du nombre de jetons perdu
                    eVariables.NJP.setInt(eVariables.TIJ.getInt() - eVariables.TFJ.getInt());

                    eVariables.rechercheAccident.setBool(true);

                    if(eVariables.NJP.getInt()<1)
                        eVariables.rechercheAccident.setBool(false);

                    eVariables.jetonPreFlop.setInt(game.getJetonAnteBlinds() + game.getJetonPreFlop());
                    eVariables.jetonFlop.setInt(game.getJetonFlop());
                    eVariables.jetonTurn.setInt(game.getJetonTurn());
                    eVariables.jetonRiver.setInt(game.getJetonRiver());
                    /*
                     * B4 / Facteur de derniÃ¨re avenue : derniÃ¨re avenue oÃ¹ l'assurÃ© a misÃ© des jetons (FDA)
                     */
                    eVariables.dernierAvenue.setChar(game.getAllIn());

                    eVariables.cartesHero.setString(game.getCarteDealt().replace(" ", "%20"));
                }
            }

            // B3 / FACTEUR DE NOMBRE DE JOUEURS ACTIFS
            /*
             *  nb jouers actifs |  2   |   3  |  4   |   5  |   6  |  7  |  8  |  9  | 10  |
             *  FNJ              | 120% | 115% | 110% | 105% | 100% | 95% | 90% | 85% | 80% |
             */
            if(game.getActif())
            {
                eVariables.joueursActifs.setInt(eVariables.joueursActifs.getInt() + 1);
            }

            totalJeton = totalJeton + game.getJetonDebut();
            nbJoueur = nbJoueur + 1;
        }

        ejlRoig.Donnee56.setValeur(nbJoueur + "");
        ejlRoig.Donnee27.setValeur(eVariables.TIJ.getInt() + "");
        ejlRoig.Donnee30.setValeur(eVariables.jetonFlop.getInt() + "");
        ejlRoig.Donnee31.setValeur(eVariables.jetonTurn.getInt() + "");
        ejlRoig.Donnee32.setValeur(eVariables.jetonRiver.getInt() + "");

        eVariables.TM.setInt(totalJeton / nbJoueur);
    }

    /**
     * boolean A1 : Demande des cotes pour le PreFlop, Flop et le Turn
     *              A1BB : Recherche d'un BB
     *              A1BD : Recherche d'un BD
     *              A1ST : Recherche d'un ST
     * 
     * 
     * Création du message pour récupération de la valeur de Gain Nul Perte
     *      4 = uniquement les cartes des joueurs
     *      7 = cartes joueurs et les cartes du Flop
     *      8 = 7 et la carte Turn
     *      9 = 8 et la carte River
     *
     * @return Vrais si le Hero a perdu le coup et qu'un accident de jeu est détecté
     */
    private boolean etapeA1()
    {
        /** 
         * Il manque le type de jeu et le nombre de joueurs
         */

        /**
         * AVENUE PREFLOP
         */
        eVariables.message.setString(creationMessage(4));
        envoiRequette(eVariables.message.getString());

        eVariables.winPreFlop.setFloat(Float.parseFloat(resultat.getWin()) / 100.00f);
        eVariables.losePreFlop.setFloat(Float.parseFloat(resultat.getLose()) / 100.00f);
        eVariables.tiePreFlop.setFloat(Float.parseFloat(resultat.getTie()) / 100.00f);

        fctValeurFichier("Gain potentiel Preflop : " +  resultat.getWin() + " %", "");
        fctValeurFichier("Perte potentielle Preflop : " + resultat.getLose() + " %", "");
        fctValeurFichier("Egalité potentielle Preflop : " + resultat.getTie() + " %", "");

        ejlRoig.Donnee33.setValeur(resultat.getWin());
        ejlRoig.Donnee34.setValeur(resultat.getLose());
        ejlRoig.Donnee35.setValeur(resultat.getTie());
        /**
         * AVENUE FLOP
         */
        eVariables.message.setString(creationMessage(7));   
        envoiRequette(eVariables.message.getString());

        eVariables.winFlop.setFloat(Float.parseFloat(resultat.getWin()) / 100.00f);
        eVariables.loseFlop.setFloat(Float.parseFloat(resultat.getLose()) / 100.00f); 
        eVariables.tieFlop.setFloat(Float.parseFloat(resultat.getTie()) / 100.00f);

        fctValeurFichier("Gain potentiel Flop : " + resultat.getWin() + " %", "");
        fctValeurFichier("Perte potentielle Flop : " + resultat.getLose() + " %", "");
        fctValeurFichier("Egalité potentielle Flop : " + resultat.getTie() + " %", "");

        ejlRoig.Donnee36.setValeur(resultat.getWin());
        ejlRoig.Donnee37.setValeur(resultat.getLose());
        ejlRoig.Donnee38.setValeur(resultat.getTie());

        /**
         * AVENUE TURN
         */
        eVariables.message.setString(creationMessage(8));   
        envoiRequette(eVariables.message.getString());

        eVariables.winTurn.setFloat(Float.parseFloat(resultat.getWin()) / 100.00f);
        eVariables.loseTurn.setFloat(Float.parseFloat(resultat.getLose()) / 100.00f); 
        eVariables.tieTurn.setFloat(Float.parseFloat(resultat.getTie()) / 100.00f);

        fctValeurFichier("Gain potentiel Turn : " + resultat.getWin() + " %", "");
        fctValeurFichier("Perte potentielle Turn : " + resultat.getLose() + " %", "");
        fctValeurFichier("Egalité potentielle Turn : " + resultat.getTie() + " %", "");

        ejlRoig.Donnee39.setValeur(resultat.getWin());
        ejlRoig.Donnee40.setValeur(resultat.getLose());
        ejlRoig.Donnee41.setValeur(resultat.getTie());

        eVariables.A1BB.setBool(etapeA1BB());

        eVariables.A1ST.setBool(false);
        eVariables.A1BD.setBool(false);

        if (typeGame < 2)
        {
            eVariables.A1BD.setBool(etapeA1BD());

            eVariables.shoeTime.setBool(false);
            eVariables.A1ST.setBool(false);

            if (!eVariables.A1BD.getBool())
            {
                eVariables.A1ST.setBool(etapeA1ST());
            }
        }

        return eVariables.A1BB.getBool() || eVariables.A1BD.getBool() || eVariables.A1ST.getBool();
    }

    /**
     * 
     * @return
     */
    private boolean etapeA1BB()
    {
        byte calcul = 0;

        // résultat PréFlop
        BBpreFlop.calcul(eVariables.jetonPreFlop.getInt(), eVariables.winPreFlop.getInt() + eVariables.tiePreFlop.getInt());

        // résultat Flop
        BBFlop.calcul(eVariables.jetonFlop.getInt(), eVariables.winFlop.getInt() + eVariables.tieFlop.getInt());

        // résultat Turn
        BBTurn.calcul(eVariables.jetonTurn.getInt(), eVariables.winTurn.getInt() + eVariables.tieTurn.getInt());

        calcul = (byte) (BBpreFlop.valeur() & BBFlop.valeur());
        calcul = (byte) (calcul & BBTurn.valeur());

        eVariables.badBeat.setBool(BBpreFlop.resultat() && BBFlop.resultat() && BBTurn.resultat());

        // inscription dans le fichier archive resultat
        fctValeurFichier("***** Bad Beat PréFlop *****", "");
        fctValeurFichier("\tBouclier max : " + BouclierBB.recherche(BBpreFlop.valeur()), "");
        fctValeurFichier("***** Bad Beat Flop *****", "");
        fctValeurFichier("\tBouclier max : " + BouclierBB.recherche(BBFlop.valeur()), "");
        fctValeurFichier("***** Bad Beat Turn *****", "");
        fctValeurFichier("\tBouclier max : " + BouclierBB.recherche(BBTurn.valeur()), "");

        // ligne spécifique JL Roig phase de test
        ejlRoig.Donnee70.setValeur("" + BouclierBB.recherche(calcul));

        return eVariables.badBeat.getBool();
    }

    /**
     * 
     * @return
     */
    private boolean etapeA1BD() {

        eVariables.bigDeal.setBool(false);

        String cHero = decodageCarteHero(eVariables.cartesHero.getString());
        String cVillain = decodageCarteVillain(eVariables.cartesVillain.getString());

        /** *******************************
         * Facteur Big Deal au Pre Flop
         ********************************* */
        Iterator<BigDeal> iter = tabBigDeal.iterator();

        while(iter.hasNext())
        {
            BigDeal bigDealCarte = (BigDeal) iter.next();

            if(bigDealCarte.getAss().contains(cHero)) 
            {
                if(bigDealCarte.getAdv().contains(cVillain)) 
                {  
                    eVariables.facteurValeurMainBD.setFloat(bigDealCarte.getValeurMain());
                    eVariables.facteurValiditeBD.setFloat(bigDealCarte.getvaliditeBD());
                    eVariables.facteurBD_PreFlop.setFloat((eVariables.facteurValeurMainBD.getFloat() 
                        + eVariables.losePreFlop.getFloat()) * eVariables.facteurValiditeBD.getFloat());

                    if(eVariables.facteurBD_PreFlop.getFloat()>0.0000f)
                    {
                        eVariables.FV_BD_PreFlop.setFloat(1.0000f);
                        eVariables.bigDeal.setBool(true);
                    }
                }
            }
        }

        /** ***********************************
         *      Facteur Big Deal Flop
         ************************************ */
        // Calcul de la cote de non perte à flop
        float coteNonPerteFlop = eVariables.winFlop.getFloat() + eVariables.tieFlop.getFloat();

        if(coteNonPerteFlop<0.6000f && eVariables.bigDeal.getBool())
        {
            eVariables.FV_BD_Flop.setFloat(1.0000f);
            eVariables.facteurBD_Flop.setFloat((eVariables.loseFlop.getFloat() * 3/4)
                + (eVariables.facteurValeurMainBD.getFloat() * 3/4));
        }

        /** ***********************************
         *      Facteur Big Deal Turn
         ************************************ */
        // Calcul de la cote de non perte à turn
        float coteNonPerteTurn = eVariables.winTurn.getFloat() + eVariables.tieTurn.getFloat();

        if(coteNonPerteTurn<0.6000f && eVariables.bigDeal.getBool())
        {
            eVariables.FV_BD_Turn.setFloat(1.0000f);
            eVariables.facteurBD_Turn.setFloat((eVariables.loseTurn.getFloat() * 2/3) 
                + (eVariables.facteurValeurMainBD.getFloat() * 2/3));
        }

        fctValeurFichier("facteur de valeur de la Main BD : " 
                + df.format(eVariables.facteurValeurMainBD.getFloat()), "A1BD");
        fctValeurFichier("facteur de validité du BD : " 
                + df.format(eVariables.facteurValiditeBD.getFloat()), "");
        fctValeurFichier("facteur BD au préFlop : " 
                + df.format(eVariables.facteurBD_PreFlop.getFloat()), "");
        fctValeurFichier("facteur BD au Flop : " 
                + df.format(eVariables.facteurBD_Flop.getFloat()), "");
        fctValeurFichier("facteur validité BD au Flop : " 
                + df.format(eVariables.FV_BD_Flop.getFloat()), "");
        fctValeurFichier("facteur BD à la Turn : " 
                + df.format(eVariables.facteurBD_Turn.getFloat()), "");
        fctValeurFichier("facteur validité BD à la Turn : " 
                + df.format(eVariables.FV_BD_Turn.getFloat()), "");
        fctValeurFichier("Résultat BD : " + eVariables.bigDeal.getBool(), "");

        return eVariables.bigDeal.getBool();
    }

    /**
     * 
     * @return
     */
    private boolean etapeA1ST()
    {

        eVariables.shoeTime.setBool(false);
        int cTemp = 0;
        int ecart = 0;

        float chanceGain = 0.0000f;
        float chancePerte = 0.0000f;
        float chanceNul = 0.0000f;
        float chanceST = 0.3300f;
        String[] splitCarte = eVariables.cartesHero.getString().split("%20");

        int carteH1 = decodageCarte(splitCarte[0]);
        int carteH2 = decodageCarte(splitCarte[1]);

        // Force à mettre la carte la plus haute dans la variable carteH1
        if(carteH1 < carteH2)
        {
            cTemp = carteH1;
            carteH1 = carteH2;
            carteH2 = cTemp;
        }

        splitCarte = eVariables.cartesVillain.getString().split("%20");

        int carteV1 = decodageCarte(splitCarte[0]);
        int carteV2 = decodageCarte(splitCarte[1]);

        // Force à mettre la carte la plus haute dans la variable carteV1
        if(carteV1 < carteV2)
        {
            cTemp = carteV1;
            carteV1 = carteV2;
            carteV2 = cTemp;
        }

        /**
         * Carte Héro identique ?
         */

        if(carteH1 == carteH2)
        {
            if(carteV1 == carteV2) 
            {
                if(carteH1 < carteV1)
                {
                    ecart = carteV1 - carteH1;

                    if(0<ecart && ecart<7) 
                        eVariables.shoeTime.setBool(true);
                }
            }
        }

        if(carteH1 == carteV1){
            if(carteH2 < carteV2)
            {
                ecart = carteV2 - carteH2;

                if(0<ecart && ecart<7)
                    eVariables.shoeTime.setBool(true);
            }
        }

        eVariables.facteurShoeTime.setFloat(tabShoeTime.get(ecart));

        fctValeurFichier("\tEcart retenu carte haute et basse : " + ecart, "A1ST");
        fctValeurFichier("\tfacteur Shoe-Time : " + df.format(eVariables.facteurShoeTime.getFloat()), "");
        fctValeurFichier("\tRésultat Shoe-TimeT : " + eVariables.shoeTime.getBool(), "");

        /** *******************************
         * Calcul Shoe Time au Pre Flop
         ********************************* */
        // Calcul de la cote de non perte à flop
        // Le héro a moins de 33% de chance de remporte le coup
        chanceGain = eVariables.winPreFlop.getFloat() / (eVariables.winPreFlop.getFloat() 
                + eVariables.losePreFlop.getFloat());
        // Le villain a plus de 33% de chance de gagner le coup
        chancePerte = eVariables.losePreFlop.getFloat();
        // Le héro a moins de 33% de chance de partager le coup
        chanceNul = eVariables.tiePreFlop.getFloat();

        if(chanceGain<chanceST && chancePerte>chanceST && chanceNul<chanceST)
        {
            FV_ST_PreFlop = 1.0000f;
        }

        fctValeurFichier("\tfacteur validité Shoe-Time au PréFlop : " + df.format(FV_ST_PreFlop), "");
        fctValeurFichier("\t\tfacteur chance gain au PréFlop : " + df.format(chanceGain), "");
        fctValeurFichier("\t\tfacteur chance perte au PréFlop : " + df.format(chancePerte), "");
        fctValeurFichier("\t\tfacteur chance égalité au PréFlop : " + df.format(chanceNul), "");

        /** *******************************
         * Calcul Shoe Time au Flop
         ********************************* */
        // Calcul de la cote de non perte à flop
        // Le héro a moins de 33% de chance de remporte le coup
        chanceGain = winFlop / (winFlop + loseFlop);
        // Le villain a plus de 33% de chance de gagner le coup
        chancePerte = loseFlop;
        // Le héro a moins de 33% de chance de partager le coup
        chanceNul = tieFlop;

        if(chanceGain<chanceST && chancePerte>chanceST && chanceNul<chanceST){
            FV_ST_Flop = 1.0000f;
        }

        fctValeurFichier("\tfacteur validité Shoe-Time au Flop : " + df.format(FV_ST_Flop), "");
        fctValeurFichier("\t\tfacteur chance gain au Flop : " + df.format(chanceGain), "");
        fctValeurFichier("\t\tfacteur chance perte au Flop : " + df.format(chancePerte), "");
        fctValeurFichier("\t\tfacteur chance égalité au Flop : " + df.format(chanceNul), "");

        /** *******************************
         * Calcul Shoe Time à  la Turn
         ********************************* */
        // Calcul de la cote de non perte à flop
        // Le héro a moins de 33% de chance de remporte le coup
        chanceGain = winTurn / (winTurn + loseTurn);
        // Le villain a plus de 33% de chance de gagner le coup
        chancePerte = loseTurn;
        // Le héro a moins de 33% de chance de partager le coup
        chanceNul = tieTurn;

        if(chanceGain<chanceST && chancePerte>chanceST && chanceNul<chanceST){
            FV_ST_Turn = 1.0000f;
        }

        fctValeurFichier("\tfacteur validité Shoe-Time à la Turn : " + df.format(FV_ST_Turn), "");
        fctValeurFichier("\t\tfacteur chance gain à la Turn : " + df.format(chanceGain), "");
        fctValeurFichier("\t\tfacteur chance perte à la Turn : " + df.format(chancePerte), "");
        fctValeurFichier("\t\tfacteur chance égalité à la Turn : " + df.format(chanceNul), "");

        return shoeTime;
    }

    private int decodageCarte(String carte) {
        byte valeur = (byte) carte.charAt(0);

        switch (valeur) {
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            return valeur-'1';

            case 'T': // carte 10
            return 9;

            case 'J': // carte Valet
            return 10;

            case 'Q': // carte Dame
            return 11;

            case 'K': // carte Roi
            return 12;

            case 'A': // carte As
            return 13;

            default:
            break;
        }
        return 0;
    }

    /**
     * @author T. MATER
     * @serialData 0.0.1
     * @since 6 avril 2017
     * @param carte - valeur des cartes du Hero brut
     * @return  un string codifiant la valeur des cartes du Hero
     */
    private String decodageCarteHero(String carte) {        
        String[] splitCarte = carte.split("%20");

        int carte1 = decodageCarte(splitCarte[0]);
        int carte2 = decodageCarte(splitCarte[1]);
        byte couleurCarte1 = (byte) splitCarte[0].charAt(1);
        byte couleurCarte2 = (byte) splitCarte[1].charAt(1);

        // Force à mettre la carte la plus haute dans la variable xxxCarte1
        if(carte1 < carte2) {
            int cTemp = carte1;
            carte1 = carte2;
            carte2 = cTemp;
        } 

        // Carte Roi Roi
        if(carte1 == 12 && carte2 == 12) 
        {
            return "RR";
        }

        // Carte Dame Dame
        if(carte1 == 11 && carte2 == 11) 
        {
            return "DD";
        }

        // Carte Valet Valet
        if(carte1 == 10 && carte2 == 10)
        {
            return "VV";
        }

        // Carte As Roi
        if(carte1 == 13 && carte2 == 12) 
        {
            if(couleurCarte1 == couleurCarte2)
                return "ARs"; // même couleur
            else
                return "ARu"; // couleur différente
        }

        // Carte As Dame
        if(carte1 == 13 && carte2 == 11) 
        {
            if(couleurCarte1 == couleurCarte2)
                return "ADs"; // même couleur
            else
                return "ADu"; // couleur différente
        }

        // Carte As Valet
        if(carte1 == 13 && carte2 == 10)
        {
            if(couleurCarte1 == couleurCarte2)
                return "AVs"; // même couleur
            else
                return "AVu"; // couleur différente
        }

        return "XX";
    }

    /**
     * @author T. MATER
     * @serialData 0.0.1
     * @since 6 avril 2017
     * @param carte - valeur des cartes du Villain brut
     * @return  un string codifiant la valeur des cartes du Hero
     */
    private String decodageCarteVillain(String carte) 
    {     
        String[] splitCarte = carte.split("%20");

        int carte1 = decodageCarte(splitCarte[0]);
        int carte2 = decodageCarte(splitCarte[1]);

        // Force à mettre la carte la plus haute dans la variable xxxCarte1
        if(carte1 < carte2) 
        {
            int cTemp = carte1;
            carte1 = carte2;
            carte2 = cTemp;
        } 

        // Carte As As
        if(carte1 == 13 && carte2 == 13)
        {
            return "AA";
        }

        // Carte Roi Roi
        if(carte1 == 12 && carte2 == 12) 
        {
            return "RR";
        }

        // Carte Dame Dame
        if(carte1 == 11 && carte2 == 11)
        {
            return "DD";
        }

        // Carte As Roi
        if(carte1 == 13 && carte2 == 12) 
        {
            return "AR";
        }

        // Carte As Dame
        if(carte1 == 13 && carte2 == 11) 
        {
            return "AD";
        }

        // Carte Valet Valet
        if(carte1 == 10 && carte2 == 10)
        {
            return "VV";
        }

        return "XX";
    }

    /**
     * Définition du facteur de modulation de remboursement pour la River : FMRR
     */
    private void etapeA2()
    {
        eVariables.FMRR.setFloat(0.50f);
        fctValeurFichier("Facteur de modulation de remboursement FMRR : " 
                        + df.format(eVariables.FMRR.getFloat()), "");
    }

    /**
     * Calcul du nombre de jetons protégés à chaque avenue et par type d'accident
     */
    private void etapeA3()
    {

        /**
         * Calcul des jetons protégés pour Bad-Beat
         */

        if(eVariables.badBeatPreFlop.getBool())
            eVariables.NJ1_BB.setFloat(eVariables.jetonPreFlop.getFloat() * (eVariables.winPreFlop.getFloat() 
                        + eVariables.tiePreFlop.getFloat()));

        eVariables.ACC_BB_PreFlop.setInt((int) eVariables.NJ1_BB.getFloat());

        if(eVariables.badBeatFlop.getBool())
            eVariables.NJ2_BB.setFloat(eVariables.jetonFlop.getFloat() * (eVariables.winFlop.getFloat() 
                        + eVariables.tieFlop.getFloat()));

       eVariables.ACC_BB_Flop.setInt((int) eVariables.NJ2_BB.getFloat());

        if(eVariables.badBeatTurn.getBool()) 
        {
            eVariables.NJ3_BB.setFloat(eVariables.jetonTurn.getFloat() * (eVariables.winTurn.getFloat() 
                        + eVariables.tieTurn.getFloat()));
            eVariables.NJ4_BB.setFloat(eVariables.jetonRiver.getFloat() * (eVariables.winTurn.getFloat() 
                        + eVariables.tieTurn.getFloat()));  
        }

        eVariables.ACC_BB_Turn.setInt((int) eVariables.NJ3_BB.getFloat());
        eVariables.ACC_BB_River.setInt((int) eVariables.NJ4_BB.getFloat());

        /**
         * Calcul des jetons protégés pour Big-Deal
         */

        eVariables.NJ1_BD.setFloat(eVariables.jetonPreFlop.getFloat() 
                        * eVariables.facteurBD_PreFlop.getFloat() * eVariables.FV_BD_PreFlop.getFloat());

        eVariables.NJ2_BD.setFloat(eVariables.jetonFlop.getFloat() 
                        * eVariables.facteurBD_Flop.getFloat() * eVariables.FV_BD_Flop.getFloat());

        eVariables.NJ3_BD.setFloat(eVariables.jetonTurn.getFloat() 
                        * eVariables.facteurBD_Turn.getFloat() * eVariables.FV_BD_Turn.getFloat());

        eVariables.NJ4_BD.setFloat(eVariables.jetonRiver.getFloat() 
                        * eVariables.facteurBD_Turn.getFloat() * eVariables.FV_BD_Turn.getFloat() 
                          * eVariables.FMRR.getFloat());

        eVariables.ACC_BD_PreFlop.setInt((int) eVariables.NJ1_BD.getFloat());
        eVariables.ACC_BD_Flop.setInt((int) eVariables.NJ2_BD.getFloat());
        eVariables.ACC_BD_Turn.setInt((int) eVariables.NJ3_BD.getFloat());
        eVariables.ACC_BD_River.setInt((int) eVariables.NJ4_BD.getFloat());

        /**
         * Calcul des jetons protégés pour Shoe-Time
         */

        eVariables.NJ1_ST.setFloat(eVariables.jetonPreFlop.getFloat() 
                        * eVariables.facteurShoeTime.getFloat() * eVariables.FV_ST_PreFlop.getFloat());

        eVariables.NJ2_ST.setFloat(eVariables.jetonFlop.getFloat() 
                        * eVariables.facteurShoeTime.getFloat() * eVariables.FV_ST_Flop.getFloat());

        eVariables.NJ3_ST.setFloat(eVariables.jetonTurn.getFloat() 
                        * eVariables.facteurShoeTime.getFloat() * eVariables.FV_ST_Turn.getFloat());

        eVariables.NJ4_ST.setFloat(eVariables.jetonRiver.getFloat() 
                        * eVariables.facteurShoeTime.getFloat() * eVariables.FV_ST_Turn.getFloat() 
                          * eVariables.FMRR.getFloat());

        eVariables.ACC_ST_PreFlop.setFloat((int) eVariables.NJ1_ST.getFloat());
        eVariables.ACC_ST_Flop.setFloat((int) eVariables.NJ2_ST.getFloat());
        eVariables.ACC_ST_Turn.setFloat((int) eVariables.NJ3_ST.getFloat());
        eVariables.ACC_ST_River.setFloat((int) eVariables.NJ4_ST.getFloat());

    }

    /**
     * Calcul du total des jetons protégés perdus dans le coup : TJP
     */
    private void etapeA4() 
    {

        if(eVariables.A1BB.getBool())
            eVariables.TJP_BB.setFloat(eVariables.NJ1_BB.getFloat() + eVariables.NJ2_BB.getFloat()
                        + eVariables.NJ3_BB.getFloat() + eVariables.NJ4_BB.getFloat());

        if(eVariables.A1BD.getBool())
            eVariables.TJP_BD.setFloat(eVariables.NJ1_BD.getFloat() + eVariables.NJ2_BD.getFloat()
                        + eVariables.NJ3_BD.getFloat() + eVariables.NJ4_BD.getFloat());

        if(eVariables.A1ST.getBool())
            eVariables.TJP_ST.setFloat(eVariables.NJ1_ST.getFloat() + eVariables.NJ2_ST.getFloat()
                        + eVariables.NJ3_ST.getFloat() + eVariables.NJ4_ST.getFloat());

        fctValeurFichier("jetons Bad Beat : " + df.format(eVariables.TJP_BB.getFloat()), "EtapeA4");
        fctValeurFichier("\tNJ1_BB : " + df.format(eVariables.NJ1_BB.getFloat()), "");
        fctValeurFichier("\tNJ2_BB : " + df.format(eVariables.NJ2_BB.getFloat()), "");
        fctValeurFichier("\tNJ3_BB : " + df.format(eVariables.NJ3_BB.getFloat()), "");
        fctValeurFichier("\tNJ4_BB : " + df.format(eVariables.NJ4_BB.getFloat()), "");

        fctValeurFichier("jetons Big Deal : " + df.format(eVariables.TJP_BD.getFloat()), "");
        fctValeurFichier("\tNJ1_BD : " + df.format(eVariables.NJ1_BD.getFloat()), "");
        fctValeurFichier("\tNJ2_BD : " + df.format(eVariables.NJ2_BD.getFloat()), "");
        fctValeurFichier("\tNJ3_BD : " + df.format(eVariables.NJ3_BD.getFloat()), "");
        fctValeurFichier("\tNJ4_BD : " + df.format(eVariables.NJ4_BD.getFloat()), "");

        fctValeurFichier("jetons Shoe Time : " + df.format(eVariables.TJP_ST.getFloat()), "");
        fctValeurFichier("\tNJ1_ST : " + df.format(eVariables.NJ1_ST.getFloat()), "");
        fctValeurFichier("\tNJ2_ST : " + df.format(eVariables.NJ2_ST.getFloat()), "");
        fctValeurFichier("\tNJ3_ST : " + df.format(eVariables.NJ3_ST.getFloat()), "");
        fctValeurFichier("\tNJ4_ST : " + df.format(eVariables.NJ4_ST.getFloat()), "");
    }

    /**
     * Calcul en cas de double accident dans le coup
     * Somme des jetons protégés perdus pour chaque type d'accident
     * Calcul du prorata de jetons protégés perdus pour les deux catégories de double accident
     */
    private void etapeA5() 
    {
        /*
         * En cas de double accident dans le coup, PAA fait la somme des jetons
         * protégés perdus pour chaque type d'accident :
         */
        eVariables.JPP_BB.setInt(eVariables.ACC_BB_PreFlop.getInt() + eVariables.ACC_BB_Flop.getInt()
                        + eVariables.ACC_BB_Turn.getInt() + eVariables.ACC_BB_River.getInt());

        eVariables.JPP_BD.setInt(eVariables.ACC_BD_PreFlop.getInt() + eVariables.ACC_BD_Flop.getInt()
                        + eVariables.ACC_BD_Turn.getInt() + eVariables.ACC_BD_River.getInt());

        eVariables.JPP_ST.setInt(eVariables.ACC_ST_PreFlop.getInt() + eVariables.ACC_ST_Flop.getInt()
                        + eVariables.ACC_ST_Turn.getInt() + eVariables.ACC_ST_River.getInt());

        fctValeurFichier("jetons protegés perdu Bad Beat : " + eVariables.JPP_BB.getInt(), "");
        fctValeurFichier("jetons protegés perdu Big Deal : " + eVariables.JPP_BD.getInt(), "");
        fctValeurFichier("jetons protegés perdu Shoe Time : " + eVariables.JPP_ST.getInt(), "");

        /*
         * PAA calcule les prorata de jetons protégés perdus pour les deux
         * catégories de double accident :
         */
         eVariables.PRORATA_JPP_BB.setInt( eVariables.JPP_BB.getInt() 
            / ( eVariables.JPP_BB.getInt() +  eVariables.JPP_BD.getInt() +  eVariables.JPP_ST.getInt()));
         eVariables.PRORATA_JPP_BD.setInt( eVariables.JPP_BD.getInt() 
            / ( eVariables.JPP_BB.getInt() +  eVariables.JPP_BD.getInt() +  eVariables.JPP_ST.getInt()));
         eVariables.PRORATA_JPP_ST.setInt( eVariables.JPP_ST.getInt() 
            / ( eVariables.JPP_BB.getInt() +  eVariables.JPP_BD.getInt() +  eVariables.JPP_ST.getInt()));
    }

    /**
     * PAA calcul le tapis moyen de l'ensemble des joueurs non-éliminés du MTT
     * TM = addition de tous les jetons des joueurs (même ceux n'ayant pas reçu les
     *      cartes privatives) / nombre de joueurs non-éliminés
     *      (Le tapis moyen est calculé sur la table de l'assure dans un 1er temps.
     *      Par la suite, ce sera peut-être sur l'ensemble des joueurs du tournoi, si on
     *      arrive à recuperer l'info)
     */
    private void etapeA6() 
    {

        fctValeurFichier("tapis moyen : " +  eVariables.TM.getInt(), "");

    }

    /**
     * PAA calcul
     *      le facteur de perte de jetons proteges sur son propre tapis FPJP
     *      Le facteur de perte de jeotns proteges sur le tapis moyen FPJP_TM
     */
    private void etapeA7()
    {

        fctValeurFichier("tapis initial de l'assuré avant le début du coup : " +  eVariables.TIJ.getInt(), "");

        eVariables.FPJP_BB.setFloat(eVariables.TJP_BB.getFloat() / (float)eVariables.TIJ.getInt());
        eVariables.FPJP_BD.setFloat(eVariables.TJP_BD.getFloat() / (float)eVariables.TIJ.getInt());
        eVariables.FPJP_ST.setFloat(eVariables.TJP_ST.getFloat() / (float)eVariables.TIJ.getInt());

        eVariables.FPJP_TM_BB.setFloat(eVariables.TJP_BB.getFloat() / eVariables.TM.getInt());
        eVariables.FPJP_TM_BD.setFloat(eVariables.TJP_BD.getFloat() / eVariables.TM.getInt());
        eVariables.FPJP_TM_ST.setFloat(eVariables.TJP_ST.getFloat() / eVariables.TM.getInt());

        if(eVariables.A1BB.getBool()) 
        {
            eVariables.FPJP.setFloat(eVariables.FPJP_BB.getFloat());
            eVariables.FPJP_TM.setFloat(eVariables.FPJP_TM_BB.getFloat());
        }

        if(eVariables.A1BD.getBool()) 
        {
            eVariables.FPJP.setFloat(eVariables.FPJP_BD.getFloat());
            eVariables.FPJP_TM.setFloat(eVariables.FPJP_TM_BD.getFloat());
        }

        if(eVariables.A1ST.getBool()) 
        {
            eVariables.FPJP.setFloat(eVariables.FPJP_ST.getFloat());
            eVariables.FPJP_TM.setFloat(eVariables.FPJP_TM_ST.getFloat());
        }

        fctValeurFichier("propre tapis BadBeat : " + df.format(eVariables.FPJP_BB.getFloat()), "");
        fctValeurFichier("propre tapis BigDeal : " + df.format(eVariables.FPJP_BD.getFloat()), "");
        fctValeurFichier("propre tapis ShoeTime : " + df.format(eVariables.FPJP_ST.getFloat()), "");

        fctValeurFichier("tapis moyen BadBeat : " + df.format(eVariables.FPJP_TM_BB.getFloat()), "");
        fctValeurFichier("tapis moyen BigDeal : " + df.format(eVariables.FPJP_TM_BD.getFloat()), "");
        fctValeurFichier("tapis moyen ShoeTime : " + df.format(eVariables.FPJP_TM_ST.getFloat()), "");
    }

    /**
     * PUBLIC STATIC VOID ENVOIREQUETTE(STRING MESSAGE)
     */
    private String requettePAAIni(String message)
    {
        Http requetteGet = new Http();
        String retour = "";
        try
        {
            retour = requetteGet.get(message);
        }
        catch (IOException e)
        {
            new Log("Class Calcul : private String requettePAAIni(String " + message + ")");
            new Log("Impossible de lire le dossier PAAIni");
            //new Log(e.getMessage());
        }

        return retour;
    }

    /**
     * 
     */
    private DataInputStream initTableau(String fichier)
    {
        URL url;
        InputStream is;
        DataInputStream dis;

        //lecture du fichier texte
        try{
            url = new URL(fichier);
            is = url.openStream();
            dis = new DataInputStream(is);

            return dis;
        }       
        catch (Exception e)
        {
            new Log("Class Calcul : private DataInputStream initTableau(String " + fichier + ")");
            new Log("Impossible de lire le fichier : " + fichier);
            //new Log(e.getMessage());
        }

        return null;
    }

    /**
     * 
     */
    private void initTabDB_ST()
    {
        /**
         * Initialisation du tableau BigDeal
         */
        DataInputStream dis;

        //lecture du fichier texte
        try
        {
            dis = initTableau(eVariables.repertoireInit.getString() + "/valeurBigDeal.ini");
            String ligne;
            while ((ligne=dis.readLine())!=null)
            {
                String[] splitLigne = ligne.split(";");

                tabBigDeal.add(new BigDeal
                    (splitLigne[0],splitLigne[1],
                        Float.valueOf(splitLigne[2].trim()).floatValue(),
                        Float.valueOf(splitLigne[3].trim()).floatValue()));
            }
            dis.close(); 
        }       
        catch (Exception e)
        {
            new Log("Class Calcul : private void initTabDB_ST()");
            new Log("Impossible d'initialiser le tableau tabBigDeal");
            //new Log(e.getMessage());
        }

        /*
         * Initialisation du tableau ST
         */
        dis = initTableau(eVariables.repertoireInit.getString() + "/valeurShoeTime.ini");
        //lecture du fichier texte
        try
        {
            String ligne;

            while ((ligne=dis.readLine())!=null)
            {
                String[] splitLigne = ligne.split(";");

                tabShoeTime.add(Float.valueOf(splitLigne[1].trim()).floatValue());
            }
            dis.close(); 
        }       
        catch (Exception e)
        {
            new Log("Class Calcul : private void initTabDB_ST()");
            new Log("Impossible d'initialiser le tableau tabShoeTime");
            //new Log(e.getMessage());
        }
    }

    /**
     * 
     * @param reBuy
     */
    private void etapeC()
    {
        String reBuy = eDonnee.Donnee8.getValeur();
        reBuy = reBuy.replace("€ + ", ";");
        reBuy = reBuy.replace("€", ";");
        reBuy = reBuy.replaceAll(",", ".");
        String reBuyTab[] = (String[]) reBuy.split(";");

        try 
        {
            rebuy = Float.valueOf(reBuyTab[0]) + Float.valueOf(reBuyTab[1]);

        } 
        catch (Exception e)
        {
            System.out.println("Je suis dans Exception : " + e);
            rebuy = 1.00f;
        }

        BR = baseRemboursement * rebuy;

        fctValeurFichier("BuyIn : " + df.format(rebuy), "EtapeC");
        fctValeurFichier("valeur base remboursement : " + df.format(baseRemboursement), "");
        fctValeurFichier("base remboursement BR : " + df.format(BR), "");

        ejlRoig.Donnee54.setValeur(df.format(BR));
    }

    /**
     * @author Thierry M.
     * @since  28 mars 2017
     * @serialData 1.1
     * 
     * PAA définit ainsi la formule mathématique de remboursement applicable aux accidents Anti-Tilt :
     *      remboursement = BR * FTJ * FV * FM * FNJ * FDA * FPJP * FPJP_TM * FMR * FMSA
     */
    private void etapeD1() {
        remboursement = BR * FTJ * FV * FM * FNJ * FDA * FPJP * FPJP_TM * FMR * FMSA;

        fctValeurFichier("BR : " + df.format(BR), "EtapeD");
        fctValeurFichier("FTJ : " + df.format(FTJ), "");
        fctValeurFichier("FV : " + df.format(FV), "");
        fctValeurFichier("FM : " + df.format(FM), "");
        fctValeurFichier("FNJ : " + df.format(FNJ), "");
        fctValeurFichier("FDA : " + df.format(FDA), "");
        fctValeurFichier("FPJP : " + df.format(FPJP), "");
        fctValeurFichier("FPJP_TM : " + df.format(FPJP_TM), "");
        fctValeurFichier("FMR : " + df.format(FMR), "");
        fctValeurFichier("FMSA : " + df.format(FMSA), "");
        fctValeurFichier("remboursement : " + df.format(remboursement), "");
        fctValeurFichier("soit " + df.format((remboursement/BR)*100.00f) + " % du BR", "");

        ejlRoig.Donnee43.setValeur(df.format(FV));
        ejlRoig.Donnee44.setValeur(df.format(FM));
        ejlRoig.Donnee45.setValeur(df.format(FNJ));
        ejlRoig.Donnee46.setValeur(df.format(FDA));
        ejlRoig.Donnee47.setValeur(df.format(FPJP));
        ejlRoig.Donnee48.setValeur(df.format(FPJP_TM));
        ejlRoig.Donnee49.setValeur(df.format(FMR));
        ejlRoig.Donnee50.setValeur(df.format(FMSA));
    }

    /**
     * @author Thierry M.
     * @since  28 mars 2017
     * @serialData 1.0
     * 
     * Dans un même tournoi, il ne peut y avoir qu'un remboursement par type d'accident : 
     *      si, dans le même tournoi, il y a plusieurs REMBOURSEMENT du même type
     */
    private void etapeD2(String archive, String nomFichier)
    {
        //  0 : extraire le numéro du tournoi de nomFichier
        String[] splitTournoi = nomFichier.split("_");
        String ligne = "0.0000";
        float prorataBB = 0.0000f;
        float prorataBD = 0.0000f;
        float prorataST = 0.0000f;

        //  1 : lire le fichier remboursement du tournoi (si existe)

        String fichierRemboursement = archive + File.separator + splitTournoi[0];

        File fichier = new File(fichierRemboursement);
        if (fichier.exists()) 
        {
            FileReader fr;
            try {
                fr = new FileReader (fichier);
                BufferedReader br = new BufferedReader (fr);
                try 
                {
                    ligne = br.readLine();
                    br.close();
                    fr.close();
                } 
                catch (IOException e)
                {
                    new Log("private void etapeD2(String archive, String nomFichier)");
                    new Log("Erreur lors de la lecture : " + fichierRemboursement);
                    e.printStackTrace();
                }

            } 
            catch (FileNotFoundException e) 
            {
                System.out.println ("Erreur lors de la lecture : " + e.getMessage());
            }
        }

        //  2 : faire les calculs
        prorataBB = remboursement * PRORATA_JPP_BB;
        prorataBD = remboursement * PRORATA_JPP_BD;
        prorataST = remboursement * PRORATA_JPP_ST;

        fctValeurFichier("remboursement calculé BB : " + df.format(prorataBB), "EtapeD2");
        fctValeurFichier("remboursement calculé BD : " + df.format(prorataBD), "");
        fctValeurFichier("remboursement calculé ST : " + df.format(prorataST), "");
        fctValeurFichier("dernier remboursement calculé : " + ligne, "");

        float lesRemboursements[] = {prorataBB,prorataBD,prorataST,remboursement,Float.valueOf(ligne)};
        float leRemboursement = 0;

        for (int i = 0; i < lesRemboursements.length; i++)
        {
            if (lesRemboursements[i] > leRemboursement)
            {
                leRemboursement = lesRemboursements[i];
            }
        }

        fctValeurFichier("remboursement retenu : " + df.format(leRemboursement), "");
        fctValeurFichier("-------------------------------------------------------------------", "");

        //  3 : enregistrer le resultat
        DataOutputStream dos;
        try
        {

            dos = new DataOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(
                        new File(fichierRemboursement))));

            dos.writeBytes(String.valueOf(leRemboursement));

            dos.close();

        } 
        catch (FileNotFoundException e)
        {
            new Log("private void etapeD2(String archive, String nomFichier)");
            new Log("Erreur lors de l'écriture : " + fichierRemboursement);
            e.printStackTrace();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void fctValeurFichier(String texte, String etape) 
    {
        switch (etape) 
        {
            case "A1BD":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"           Big Deal Pre Flop " + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "A1ST":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"              Shoe-Time" + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;          

            case "TAPIS":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"           CARTES DU TAPIS BOARD" + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeA3BB":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1," Calcul des jetons protégés pour Bad-Beat " + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeA3BD":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1," Calcul des jetons protégés pour Big-deal " + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeA3ST":
            valeurFichier.put(keyValeurFichier,"*******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1," Calcul des jetons protégés pour Shoe Time " + "\n");
            valeurFichier.put(keyValeurFichier+2,"*******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeA4":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"     Calcul total des jetons protégés     " + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeA7":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"     Facteur perte de jetons protégés     " + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "FV":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"            Facteur de vitesse            " + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeB2":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"            Facteur du Moment             " + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeB3":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"    Facteur de nombre de joueurs actif    " + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeB4":
            valeurFichier.put(keyValeurFichier,"**********************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"          Facteur de dernière avenue        " + "\n");
            valeurFichier.put(keyValeurFichier+1,"A Ante - B PréFlop - C Flop - D Turn - E River" + "\n");
            valeurFichier.put(keyValeurFichier+3,"**********************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeB5":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1," Facteur de modulation des remboursements pour la River" + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeB6":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1," Facteur de modulation spécifique accident" + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeB7":
            valeurFichier.put(keyValeurFichier,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+1,"       Facteur de Temps de Jeu" + "\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************" + "\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeC":
            valeurFichier.put(keyValeurFichier,"******************************************\n");
            valeurFichier.put(keyValeurFichier+1," Facteur de modulation spécifique accident\n");
            valeurFichier.put(keyValeurFichier+2,"******************************************\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeD":
            valeurFichier.put(keyValeurFichier,"******************************************\n");
            valeurFichier.put(keyValeurFichier+1,"          Remboursement calculé        \n");
            valeurFichier.put(keyValeurFichier+2,"******************************************\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            case "EtapeD2":
            valeurFichier.put(keyValeurFichier,"******************************************\n");
            valeurFichier.put(keyValeurFichier+1,"      Remboursement pris en compte \n");
            valeurFichier.put(keyValeurFichier+2,"******************************************\n");
            valeurFichier.put(keyValeurFichier+3, texte + "\n");
            keyValeurFichier = keyValeurFichier+4;
            break;

            default:
            valeurFichier.put(keyValeurFichier, texte + "\n");
            keyValeurFichier = keyValeurFichier + 1;
            break;
        }

    }

    private void ecritureResultat(String archive, String nomFichier) 
    {
        DataOutputStream dos;

        try 
        {
            dos = new DataOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(
                        new File(archive + File.separator + nomFichier))));

            Set<Integer> keys = jlroig.keySet();
            Iterator<Integer> it = keys.iterator();

            //Parourir et enregister les valeurs
            while(it.hasNext()) 
            {
                Object key = it.next();
                dos.writeBytes(jlroig.get(key));
            }

            keys = valeurFichier.keySet();
            it = keys.iterator();

            dos.writeBytes("\n" + nomFichier + "\n");

            //Parourir et enregister les valeurs
            while(it.hasNext()) 
            {
                Object key = it.next();
                dos.writeBytes(valeurFichier.get(key));
            }
            dos.close();

            valeurFichier.clear();

        } 
        catch (FileNotFoundException e)
        {
            valeurFichier.clear();
            e.printStackTrace();
            new Log("FileNotFoundException sur private void ecritureResultat(String " + archive + ", String " + nomFichier + ")");
        } 
        catch (IOException e)
        {
            valeurFichier.clear();
            e.printStackTrace();
            new Log("IOException sur private void ecritureResultat(String " + archive + ", String " + nomFichier + ")");
        }       
    }

    /*
     * Recherche du hero et de l'informations lost
     */
    private boolean heroLost() 
    {
        /* Recherche du hero et des informations nÃ©cessaire au calcul */
        boolean lost = false;

        if(eJoueur.Hero.getShow().contains("lost")) 
        {
            lost = true;
        }
        else 
        {                
            // remise à zéro des données si Hero différent de LOST
            for(eDonnee v : eDonnee.values())
                v.raz();

            for(eJoueur game: eJoueur.values())
                fctValeurFichier(game.ecriture(), "");
        }

        return lost;
    }

    /* 
     * *********************************************************************************************
     * *********************************************************************************************
     * *********************************************************************************************
     * *********************************************************************************************
     * *********************************************************************************************
     * *********************************************************************************************
     */

    /*  
     * CrÃ©ation du message pour rÃ©cupÃ©ration de la valeur de Gain Nul Perte
     * 4 = uniquement les cartes des joueurs
     * 7 = cartes joueurs et les cartes du Flop
     * 8 = 7 et la carte Turn
     * 9 = 8 et la carte River
     */
    private String creationMessage(Integer carteEnJeu)
    {
        String message = "http://fr.pokerlistings.com/syndicated/pokerListingsToolsCalculatorLarge.php?";

        // player_1=2h%203h&player_2=Kh%20Ah&board_cards=9s%20Ts%20Qs%20Ad& HTTP/1.1 (avec Turn) - 9 10 Valet pique - As Carreau;

        String sj1 = "player_1=" + cartesHero;
        String sj2 = "&player_2=" + cartesVillain;
        String sflop = "&board_cards=" + cartesFlop;
        String sturn = "%20" + cartesTurn;
        String sriver = "%20" + cartesRiver;

        if(carteEnJeu==4)
        {
            message = message + sj1 + sj2 + "&HTTP/1.1";
        }
        if(carteEnJeu==7)
        {
            message = message + sj1 + sj2 + sflop + "&HTTP/1.1";
        }
        if(carteEnJeu==8)
        {
            message = message + sj1 + sj2 + sflop + sturn + "&HTTP/1.1";
        }
        if(carteEnJeu==9)
        {
            message = message + sj1 + sj2 + sflop + sturn + sriver + "&HTTP/1.1";
        }

        return message;
    }

    /**
     * PUBLIC STATIC VOID ENVOIREQUETTE(STRING MESSAGE)
     */
    private void envoiRequette(String message)
    {
        Http requetteGet = new Http();
        String retour = "";
        try
        {
            retour = requetteGet.get(message);
        }
        catch (IOException e2)
        {
            e2.printStackTrace();
        }

        try
        {
            resultat.xmlFile(retour);
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }

    /**
     * PUBLIC STATIC BOOLEAN BADBEAT(FLOAT VALEUR)
     */
    public boolean badBeat(Float valeur) {

        Float valeurComparaison = (float) (valeur);

        // BAD-BEAT60%
        if(valeurComparaison >= 0.60)
            badBeat60 = true;
        else
            badBeat60 = false;

        fctValeurFichier("Bad Beat 60% : " + badBeat60, "");

        // BAD-BEAT65%
        if(valeurComparaison >= 0.65)
            badBeat65 = true;
        else
            badBeat65 = false;

        fctValeurFichier("Bad Beat 65% : " + badBeat65, "");

        // BAD-BEAT70%
        if(valeurComparaison >= 0.70)
            badBeat70 = true;
        else
            badBeat70 = false;

        fctValeurFichier("Bad Beat 70% : " + badBeat70, "");

        // BAD-BEAT75%
        if(valeurComparaison >= 0.75)
            badBeat75 = true;
        else
            badBeat75 = false;

        fctValeurFichier("Bad Beat 75% : " + badBeat75, "");

        // BAD-BEAT80%
        if(valeurComparaison >= 0.80)
            badBeat80 = true;
        else
            badBeat80 = false;

        fctValeurFichier("Bad Beat 80% : " + badBeat80, "");

        // BAD-BEAT85%
        if(valeurComparaison >= 0.85)
            badBeat85 = true;
        else
            badBeat85 = false;

        fctValeurFichier("Bad Beat 85% : " + badBeat85, "");

        // BAD-BEAT90%
        if(valeurComparaison >= 0.90)
            badBeat90 = true;
        else
            badBeat90 = false;

        fctValeurFichier("Bad Beat 90% : " + badBeat90, "");

        if(badBeat60 || badBeat65 || badBeat70 || badBeat75 || badBeat80 || badBeat85 || badBeat90)
            return true;
        else
            return false;
    }

    /* FIN PUBLIC STATIC BOOLEAN BADBEAT(FLOAT VALEUR) */

    private void initTreeMapRoig()
    {
        for (int i=1; i<76; i++)
        {
            jlroig.put(i, "\tx");
        }
    }
}
