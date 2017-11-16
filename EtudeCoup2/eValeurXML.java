
/**
 * Enumeration eDonnee - Enregistrement des valeur pour le fichier
 * résultat
 *
 * @author (PAA Thierry Mater
 * @version 0.1.0beta du 02/11/17
 */
public enum eValeurXML
{
    Donnee1("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>","","\n"), 

    Donnee2("<?xml-stylesheet type=\"text/xsl\" href= \"CoupXML.xsl\" ?>","\n",""),

    Donnee3("","","\n"),

    Donnee4("<Coup nom = \"","val","\"\n"),

    Donnee5("\t","<cartes>","\n"),

    Donnee6("\t\t<carte nom=\"flop\">","val","</carte>\n"),

    Donnee7("\t\t<carte nom=\"turn\">","val","</carte>\n"),

    Donnee8("\t\t<carte nom=\"river\">","val","</carte>\n"),

    Donnee9("\t","</cartes>","\n"),

    Donnee10("\t","<cotes>","\n"), 

    Donnee11("\t\t<preflop nom=\"won\">","val","</preflop>\n"),

    Donnee12("\t\t<preflop nom=\"lose\">","val","</preflop>\n"),

    Donnee13("\t\t<preflop nom=\"tie\">","val","</preflop>\n"),

    Donnee14("\t\t<flop nom=\"won\">","val","</flop>\n"),

    Donnee15("\t\t<flop nom=\"lose\">","val","</flop>\n"),

    Donnee16("\t\t<flop nom=\"tie\">","val","</flop>\n"),

    Donnee17("\t\t<turn nom=\"won\">","val","</turn>\n"),

    Donnee18("\t\t<turn nom=\"lose\">","val","</turn>\n"),

    Donnee19("\t\t<turn nom=\"tie\">","val","</turn>\n"),

    Donnee20("\t","</cotes>","\n"), 

    Donnee21("\t","<badbeat>","\n"),

    Donnee22("\t\t<preflop>","val","</preflop>\n"),

    Donnee23("\t\t<flop>","val","</flop>\n"),

    Donnee24("\t\t<turn>","val","</turn>\n"),

    Donnee25("\t","</badbeat>","\n"),

    Donnee26("\t<bigdeal nom = \"","val","\">\n"),

    Donnee27("\t\t<valMainBD>","val","</valMainBD>\n"),

    Donnee28("\t\t<validBD>","val","</validBD>\n"),

    Donnee29("\t\t<BDpreFlop>","val","</BDpreFlop>\n"),

    Donnee30("\t\t<BDFlop>","val","</BDFlop>\n"), 

    Donnee31("\t\t<validBDFlop>","val","</validBDFlop>\n"),

    Donnee32("\t\t<BDTurn>","val","</BDTurn>\n"),

    Donnee33("\t\t<validBDTurn>","val","</validBDTurn>\n"),

    Donnee34("\t\t<FMRR>","val","</FMRR>\n"),

    Donnee35("\t","</bigdeal>","\n"),

    Donnee36("\t","<jetons>","\n"),

    Donnee37("\t\t<badbeat nom=\"total\">","val","</badbeat>\n"),

    Donnee38("\t\t<badbeat nom=\"preflop\"","val","</badbeat>\n"),

    Donnee39("\t\t<badbeat nom=\"flop\"","val","</badbeat>\n"),

    Donnee40("\t\t<badbeat nom=\"turn\"","val","</badbeat>\n"), 

    Donnee41("\t\t<badbeat nom=\"river\">","val","</badbeat>\n"),

    Donnee42("\t\t<bigdeal nom=\"total\">","val","</bigdeal>\n"),

    Donnee43("\t\t<bigdeal nom=\"preflop\">","val","</bigdeal>\n"),

    Donnee44("\t\t<bigdeal nom=\"flop\">","val","</bigdeal>\n"),

    Donnee45("\t\t<bigdeal nom=\"turn\">","val","</bigdeal>\n"),

    Donnee46("\t\t<bigdeal nom=\"river\">","val","</bigdeal>\n"),

    Donnee47("\t\t<shoetime nom=\"total\">","val","</shoetime>\n"),

    Donnee48("\t\t<shoetime nom=\"preflop\">","val","</shoetime>\n"),

    Donnee49("\t\t<shoetime nom=\"flop\">","val","</shoetime>\n"),

    Donnee50("\t\t<shoetime nom=\"turn\">","val","</shoetime>\n"), 

    Donnee51("\t\t<shoetime nom=\"river\">","val","</shoetime>\n"),

    Donnee52("\t\t<proteges nom=\"Bad Beat\">","val","</proteges>\n"),

    Donnee53("\t\t<proteges nom=\"Big Deal\">","val","</proteges>\n"),

    Donnee54("\t\t<proteges nom=\"Shoe Time\">","val","</proteges>\n"),

    Donnee55("\t","</jetons>","\n"),

    Donnee56("\t","<tapis>","\n"),

    Donnee57("\t\t<moyen>","val","</moyen>\n"),

    Donnee58("\t\t<initial>","val","</initial>\n"),

    Donnee59("\t\t<badbeat>","val","</badbeat>\n"),

    Donnee60("\t\t<bigdeal>","val","</bigdeal>\n"), 

    Donnee61("\t\t<shoetime>","val","</shoetime>\n"),

    Donnee62("\t\t","<moyenne>","\n"),

    Donnee63("\t\t\t<badbeat>","val","</badbeat>\n"),

    Donnee64("\t\t\t<bigdeal>","val","</bigdeal>\n"),

    Donnee65("\t\t\t<shoetime>","val","<shoetime>\n"),

    Donnee66("\t\t","</moyenne>","\n"),

    Donnee67("\t","</tapis>","\n"),

    Donnee68("\t","<facteur>","\n"),

    Donnee69("\t\t<vitesse valeur = \"","val","\">\n"),

    Donnee70("\t\t\t<temps>","val","</temps>\n"), 

    Donnee71("\t\t\t<niveau>","val","</niveau>\n"),

    Donnee72("\t\t\t<mtt>","val","</mtt>\n"),

    Donnee73("\t\t","</vitesse>","\n"),

    Donnee74("\t\t</moment valeur = \"","val","\">\n"),

    Donnee75("\t\t\t<duree>","val","</duree>\n"),

    Donnee76("\t\t\t<calcule>","val","</calcule>\n"),

    Donnee77("\t\t","</moment>","\n"),

    Donnee78("\t\t","<nbjoueur>","\n"),

    Donnee79("\t\t\t<actifs","val",""),

    Donnee80("\t\t\t<valeur>","val","</valeur>\n"), 

    Donnee81("\t\t","</nbjoueur>","\n"),

    Donnee82("\t\t","<avenue>","\n"),

    Donnee83("\t\t\t<nom>","val","</nom>\n"),

    Donnee84("\t\t\t<valeur>","val","</valeur>\n"),

    Donnee85("\t\t","</avenue>","\n"),

    Donnee86("\t\t","<fmrr>","\n"),

    Donnee87("\t\t\t<globale valeur = \"","val","\">\n"),

    Donnee88("\t\t\t\t<badbeat>","val","</badbeat>\n"),

    Donnee89("\t\t\t\t<bigdeal>","val","</bigdeal>\n"),

    Donnee90("\t\t\t\t<shoetime>","val","</shoetime>\n"),

    Donnee91("\t\t\t","</glogable","\n"),

    Donnee92("\t\t\t","<remboursements>","\n"),

    Donnee93("\t\t\t\t<badbeat>","val","</badbeat>\n"),

    Donnee94("\t\t\t\t<bigdeal>","val","</bigdeal>\n"),

    Donnee95("\t\t\t\t<shoetime>","val","</shoetime>\n"),

    Donnee96("\t\t\t","</remboursements>","\n"),

    Donnee97("\t\t","</fmrr>","\n"),

    Donnee98("\t\t","<fmsa>","\n"),

    Donnee99("\t\t\t<aa>","val","</aa>\n"),

    Donnee100("\t\t\t<ana>","val","</ana>\n"),

    Donnee101("\t\t\t<tana>","val","</tana>\n"),

    Donnee102("\t\t\t<bba>","val","</bba>\n"),

    Donnee103("\t\t\t<bda>","val","</bda>\n"),

    Donnee104("\t\t\t<sta>","val","</sta>\n"),

    Donnee105("\t\t\t<dabbbd>","val","</dabbbd>\n"),

    Donnee106("\t\t\t<dabbst>","val","</dabbst>\n"),

    Donnee107("\t\t","</fmsa>","\n"),

    Donnee108("\t\t<ftj valeur = \"","val","\">\n"),

    Donnee109("\t\t\t<calcule>","val","</calcule>"),

    Donnee110("\t\t\t<brut>","val","</brut>\n"), 

    Donnee111("\t\t\t<itm>","val","</itm>\n"),

    Donnee112("\t\t","</ftj>","\n"),

    Donnee113("\t\t<fmsa valeur = \"","val","\">\n"),

    Donnee114("\t\t\t<buyIn>","val","</buyIn>\n"),

    Donnee115("\t\t\t<br>","val","</br>\n"),

    Donnee116("\t\t","</fmsa>","\n"),

    Donnee117("\t","</facteur>","\n"),

    Donnee118("\t","<calcul>","\n"),

    Donnee119("\t\t<br>","val","</br>\n"),

    Donnee120("\t\t<ftj>","val","</ftj>\n"),

    Donnee121("\t\t<fv>","val","</fv>\n"),

    Donnee122("\t\t<fm>","val","</fm>\n"),

    Donnee123("\t\t<fnj>","val","</fnj>\n"),

    Donnee124("\t\t<fda>","val","</fda>\n"),

    Donnee125("\t\t<fpjp>","val","</fpjp>\n"),

    Donnee126("\t\t<FPJP_TM>","val","</FPJP_TM>\n"),

    Donnee127("\t\t<fmr>","val","</fmr>\n"),

    Donnee128("\t\t<fmsa>","val","</fmsa>\n"),

    Donnee129("\t\t<remboursement>","val","</remboursement>\n"),

    Donnee130("\t\t<soit>","val"," % du BR</soit>\n"),

    Donnee131("\t","</calcul>","\n"),

    Donnee132("\t","<remboursement>","\n"),

    Donnee133("\t\t<calculeBB>","val","</calculeBB>\n"),

    Donnee134("\t\t<calculeBD>","val","</calculeBD>\n"),

    Donnee135("\t\t<calculeST>","val","</calculeST>\n"),

    Donnee136("\t\t<dernier>","val","</dernier>\n"),

    Donnee137("\t\t<retenu>","val","</retenu>\n"),

    Donnee138("\t","</remboursement>","\n"),

    Donnee139("\t","<archive>","\n"),

    Donnee140("\t\t<ligne1>","val","</ligne1>\n"),

    Donnee141("\t\t<ligne2>","val","</ligne2>\n"),

    Donnee142("\t\t<ligne3>","val","</ligne3>\n"),

    Donnee143("\t\t<ligne4>","val","</ligne4>\n"),

    Donnee144("\t\t<ligne5>","val","</ligne5>\n"),

    Donnee145("\t\t<ligne6>","val","</ligne6>\n"),

    Donnee146("\t\t<ligne7>","val","</ligne7>\n"),

    Donnee147("\t\t<ligne8>","val","</ligne8>\n"),

    Donnee148("\t\t<ligne9>","val","</ligne9>\n"),

    Donnee149("\t\t<ligne10>","val","</ligne10>\n"),

    Donnee150("\t\t<ligne11>","val","</ligne11>\n"),

    Donnee151("\t\t<ligne12>","val","</ligne12>\n"),

    Donnee152("\t\t<ligne13>","val","</ligne13>\n"),

    Donnee153("\t\t<joueur1>","val","</joueur1>\n"),

    Donnee154("\t\t<joueur2>","val","</joueur2>\n"),

    Donnee155("\t\t<joueur3>","val","</joueur3>\n"),

    Donnee156("\t\t<joueur4>","val","</joueur4>\n"),

    Donnee157("\t\t<joueur5>","val","</joueur5>\n"),

    Donnee158("\t\t<joueur6>","val","</joueur6>\n"),

    Donnee159("\t\t<joueur7>","val","</joueur7>\n"),

    Donnee160("\t\t<joueur8>","val","</joueur8>\n"),

    Donnee161("\t\t<joueur9>","val","</joueur9>\n"),

    Donnee162("\t\t<joueur10>","val","</joueur10>\n"),

    Donnee163("\t","</archive>","\n"),

    Donnee164("","</Coup>","\n"),

    Donnee165("","","");

    private String m_valeur = "";
    private String m_txt1 = "";
    private String m_txt2 = "";

    eValeurXML (String txt1, String valeur, String txt2)
    {
        this.m_valeur = valeur;
        this.m_txt1 = txt1;
        this.m_txt2 = txt2;
    }

    public String getValeur()
    {
        return m_valeur;
    }

    public void setValeur(String valeur)
    {
        this.m_valeur = valeur;
    }

    public void raz()
    {
            this.m_valeur = "";
    }

    public String ecriture()
    {
        return this.m_txt1 + this.m_valeur + this.m_txt2;
    }
}
