
/**
 * Classe etapeB.
 * 
 * @author PAA Thierry Mater
 * @version 0.1.0beta du 14/11/17
 */

import java.text.DecimalFormat;

public class B
{
    // variables d'instance
    static DecimalFormat df = new DecimalFormat("####.####");
    
    /**
     * Constructeur d'objets de classe etapeB4
     */
    public B()
    {

    }

    /**
     *  B1 / Facteur de vitesse du MTT au moment de coup : FV
     *  (heure du coup de l'accident - l'heure du 1er coup de la HH-h) / (level du coup de l'accident
     *   - level du coup de la HH-h dans lequel, pour la 1ère fois, le level est supérieur Ã  0) 
     *
     * @param rien
     * @return rien
     */
    public static void etape1() 
    {
        /*
         *  B1 / Facteur de vitesse du MTT au moment de coup : FV
         *  (heure du coup de l'accident - l'heure du 1er coup de la HH-h) / (level du coup de l'accident
         *   - level du coup de la HH-h dans lequel, pour la 1ère fois, le level est supérieur Ã  0)
         */
        int tempsDeJeu = Integer.parseInt(eDonnee.Donnee1.getValeur()) - Integer.parseInt(eDonnee.Donnee3.getValeur());
        int differenceLevel = Integer.parseInt(eDonnee.Donnee2.getValeur()) - Integer.parseInt(eDonnee.Donnee4.getValeur());
        if (differenceLevel == 0)
            differenceLevel = 1;

        eVariables.vitesseMTT.setInt(tempsDeJeu / differenceLevel);

        //fctValeurFichier("Temps de Jeu : " + tempsDeJeu + " = " + Integer.parseInt(eDonnee.Donnee1.getValeur()) + " - " + Integer.parseInt(eDonnee.Donnee3.getValeur()), "FV");
        //fctValeurFichier("Différence de Niveau : " + differenceLevel + " = " + Integer.parseInt(eDonnee.Donnee2.getValeur()) + " - " + Integer.parseInt(eDonnee.Donnee4.getValeur()), "");
        //fctValeurFichier("Vitesse du MTT : " + eVariables.vitesseMTT.getInt() + " = " + tempsDeJeu + " / " + differenceLevel, "");

        eVariables.FV.setFloat(eTabFV.facteurVitesse());
    }

    /**
     *  B2 / Facteur de moment du MTT : FM
     *      2/ calcul de la durÃ©e jusqu'au moment de l'accident
     *      (heure du coup de lâ€™accident â€“ heure du coup dÃ©but du tournoi)
     *  
     *      3/ on multiplie cette durÃ©e rÃ©elle de jeu par le multiplicateur correspondant
     *      du tableau valeurFM.ini
     *
     *      5/ et on calcule la variable <<Facteur de moment>> avec les tableaux
     *
     * @param rien
     * @return rien
     */
    public static void etape2() 
    {
        int tempsDeJeu = -Integer.parseInt(eDonnee.Donnee3.getValeur()) + Integer.parseInt(eDonnee.Donnee1.getValeur());

        /*
         * 3/ on multiplie cette durÃ©e rÃ©elle de jeu par le multiplicateur correspondant
         * du tableau valeurFM.ini
         */
        eVariables.momentCalcule.setFloat(tempsDeJeu * eTabFM.facteurAvenue(eTabFV.getId(eVariables.FV.getFloat())));

        /*
         * 5/ et on calcule la variable <<Facteur de moment>> avec les tableaux
         */
        if(eVariables.momentCalcule.getFloat() < eTabCondFM.facteurAvenue(0, true)) 
        {
            if(eVariables.rebuy.getFloat() > 0.00f)
                eVariables.FM.setFloat(eTabCondFM.facteurAvenue(0, false));
            else if(eVariables.rebuy.getFloat() == 0 && eVariables.addon.getInt() > 0) 
                eVariables.FM.setFloat(eTabCondFM.facteurAvenue(1, false));
            else 
                eVariables.FM.setFloat(eTabCondFM.facteurAvenue(2, false));
        } 
        else 
        {
            if(eVariables.momentCalcule.getFloat() < eTabCondFM.facteurAvenue(1, true))
                eVariables.FM.setFloat(eTabCondFM.facteurAvenue(3, false));
            else 
                eVariables.FM.setFloat(eTabCondFM.facteurAvenue(4, false));
        }

        //fctValeurFichier("Durée réelle de jeu : " + tempsDeJeu, "EtapeB2");
        //fctValeurFichier("moment calculé : " + df.format(momentCalcule), "");
        //fctValeurFichier("facteur du moment : " + df.format(FM), "");
    }

    /**
     * 
     *
     * @param rien
     * @return rien
     */
    public static void etape3() 
    {
        eVariables.FNJ.setFloat(eTabFNJ.facteurNbreJoueur(eVariables.joueursActifs.getInt()));

        //fctValeurFichier("Nb de joueurs actifs : " + joueursActifs, "EtapeB3");
        //fctValeurFichier("Facteur de nb de joueurs actifs : " + df.format(FNJ), "");
    }

    /**
     * B4 / Facteur de derniere avenue (preflop, flop, turn, river) : 
     *      derniere avenue ou le hero a mise des jetons (raise ou bet
     *      ou call dans la HH) : FDA
     *
     * @param rien
     * @return rien
     */
    public static void etape4() 
    {

        eVariables.FDA.setFloat(eTabFDA.facteurAvenue(eVariables.dernierAvenue.getChar()));

        //fctValeurFichier("Facteur de dernière avenue : " + df.format(FDA), "EtapeB4");
        //fctValeurFichier("Dernière avenue : " + dernierAvenue, "");
    }

    /**
     * B5 / Définition de plusieurs facteurs de modulation :
     *      Modulation Globale des remboursements : MGR
     *      Modulation des Remboursements Bad*Beat : MRBB
     *      Modulation des Remboursements Big-Deal : MRBD
     *      Modulation des Remboursements Shoe-Time : MRST
     * 
     *      Donc selon le type d'accident et selon la modulation 
     *      globale des remboursements, nous obtenons un facteur
     *      de modulation des remboursements :
     *      FMR = MGR * (MRBB || MRBD || MRST)
     *
     * @param rien
     * @return rien
     */
    public static void etape5() 
    {
        if (eVariables.A1BB.getBool())
        {
            eVariables.FMR_BB.setFloat(eTabFMR.getMRBB() * eTabFMR.getMGR());
            eVariables.FMR.setFloat(eVariables.FMR_BB.getFloat());
        }

        if (eVariables.A1BD.getBool())
        {
            eVariables.FMR_BD.setFloat(eTabFMR.getMRBD() * eTabFMR.getMGR());
            eVariables.FMR.setFloat(eVariables.FMR_BD.getFloat());
        }

        if (eVariables.A1ST.getBool())
        {
            eVariables.FMR_ST.setFloat(eTabFMR.getMRST() * eTabFMR.getMGR());
            eVariables.FMR.setFloat(eVariables.FMR_ST.getFloat());
        }
        
        /**
        fctValeurFichier("modulation globale des remboursements : " + df.format(MGR), "EtapeB5");
        fctValeurFichier("modulation des remboursements Bad-Beat : " + df.format(MRBB), "");
        fctValeurFichier("modulation des remboursements Big-Deal : " + df.format(MRBD), "");
        fctValeurFichier("modulation des remboursements Shoe-Time : " + df.format(MRST), "");
        fctValeurFichier("facteur de modulation des remboursements Bad-Beat : " + df.format(FMR_BB), "");
        fctValeurFichier("facteur de modulation des remboursements Big-Deal : " + df.format(FMR_BD), "");
        fctValeurFichier("facteur de modulation des remboursements Shoe-Time : " + df.format(FMR_ST), "");
        */
    }

    /**
     * B6 / Définition et quantification des différences entre accidents
     *      aggravés et accidents non aggravés :
     *      TIJ étant le tapis initial du joueur au début du coup et TFJ
     *      étant le tapis final du joueur Ã  la fin du coup, le facteur
     *      de gravité de l'accident = NJP / TIJ exprimé en % 
     *
     * @param rien
     * @return rien
     */
    public static void etape6() 
    {
        float calcul = (float) eVariables.NJP.getInt() / (float)eVariables.TIJ.getInt();

        if(calcul >= eVariables.accident.getFloat()) 
        {
            eVariables.accidentA.setBool(true);
            eVariables.accidentNA.setBool(false);
        } 
        else 
        {
            eVariables.accidentA.setBool(false);
            eVariables.accidentNA.setBool(true);
        }

        if (eVariables.accidentNA.getBool()) 
            eVariables.FMSA.setFloat(eTabFMSA.getMRA_NA());
        else
        {
            if (eVariables.A1BB.getBool()) 
                eVariables.FMSA.setFloat(eTabFMSA.getMRBB_A());

            if (eVariables.A1BD.getBool())
                eVariables.FMSA.setFloat(eTabFMSA.getMRBD_A());

            if (eVariables.A1ST.getBool()) 
                eVariables.FMSA.setFloat(eTabFMSA.getMRST_A());

            if (eVariables.A1BB.getBool() && eVariables.A1BD.getBool()) 
                eVariables.FMSA.setFloat(eTabFMSA.getMRBBBD_A());
 
            if (eVariables.A1BB.getBool() && eVariables.A1ST.getBool())
                eVariables.FMSA.setFloat(eTabFMSA.getMRBBST_A());
        }
        
        /**
        fctValeurFichier("accident aggravé: " + accidentA, "EtapeB6");
        fctValeurFichier("accident non aggravé : " + accidentNA, "");
        fctValeurFichier("\tNJP : " + NJP, "");
        fctValeurFichier("\tTIJ : " + TIJ, "");
        fctValeurFichier("\tCalcul : " + calcul, "");
        fctValeurFichier("\taccident : " + accident, "");
        fctValeurFichier("modulation remboursements tout accident non aggravé : " + df.format(MRA_NA), "");
        fctValeurFichier("modulation remboursements Bad-Beat aggravés : " + df.format(MRBB_A), "");
        fctValeurFichier("modulation remboursements Big-Deal aggravés : " + df.format(MRBD_A), "");
        fctValeurFichier("modulation remboursements Shoe-Time aggravés : " + df.format(MRST_A), "");
        fctValeurFichier("modulation remboursements double accident Bad-Beat + Big-Deal : " + df.format(MRBBBD_A), "");
        fctValeurFichier("modulation remboursements double accident Bad-Beat + Shoe-Time : " + df.format(MRBBST_A), "");
        */
    }

    /**
     * Facteur de Temps de jeu : FTJ
     *  Plus le moment de l'accident est éloigné du début du tournoi,
     *  plus le dédommagement est important : 
     *  FTJ = nombre de secondes écoulées depuis le 1er coup de la HH
     *  jusqu'au coup de l'accident divisé par cent
     *  (FTJ = temps / 100%, ou  FTJ = temps / 10 000) mais
     *      si Hero est ITM, alors FTJ = 100%
     *      si FTJ < 50% (ou FTJ < 0.5), alors FTJ = 50%
     *      si FTJ > 200% (ou FTJ > 2.0), alors FTJ = 200%
     *
     * @param rien
     * @return rien
     */
    public static void etape7() 
    {
        
        int tempsDeJeu = -Integer.parseInt(eDonnee.Donnee3.getValeur()) + Integer.parseInt(eDonnee.Donnee1.getValeur());

        eVariables.FTJ.setFloat((float) (tempsDeJeu) / 10000.00f);

        //fctValeurFichier("Temps de jeu calculé : " + tempsDeJeu, "EtapeB7");
        //fctValeurFichier("FTJ brut : " + df.format(FTJ), "");

        eVariables.FTJ.setFloat(0.85f + eVariables.FTJ.getFloat()/10.00f);

        //fctValeurFichier("Valeur ITM : " + ITM, "");
        //fctValeurFichier("FTJ corrigé : " + df.format(FTJ), "");

        ejlRoig.Donnee42.setValeur(df.format(eVariables.FTJ.getFloat()));
    }
}
