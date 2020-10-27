package ch.cpnv.angrywirds.providers;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.model.Data.Vocabulary;
import ch.cpnv.angrywirds.model.Data.Word;

public class VocProvider {

    // static variable single_instance of type VocProvider
    private static VocProvider single_instance = null;

    public ArrayList<Vocabulary> vocs; // all available vocs

    // private constructor restricted to this class itself
    private VocProvider()
    {
        vocs = new ArrayList<Vocabulary>();
        Word w;
        Vocabulary voc = new Vocabulary("L'argent");
        w = new Word("la banque","the bank"); voc.addWord(w);
        w = new Word("l''argent liquide","cash"); voc.addWord(w);
        w = new Word("le paiement","payment"); voc.addWord(w);
        w = new Word("un carnet de chèques","checkbook"); voc.addWord(w);
        w = new Word("déposer de l''argent","to deposit money"); voc.addWord(w);
        w = new Word("prêter de l''argent","to borrow money"); voc.addWord(w);
        w = new Word("les services financiers","financial services"); voc.addWord(w);
        w = new Word("la commission","commission"); voc.addWord(w);
        w = new Word("un investissement","an investment"); voc.addWord(w);
        w = new Word("un virement","transfer"); voc.addWord(w);
        w = new Word("la gestion de l''argent","money management"); voc.addWord(w);
        w = new Word("une transaction","a transaction"); voc.addWord(w);
        w = new Word("un guichet automatique","ATM machine"); voc.addWord(w);
        w = new Word("attendre dans la queue","to wait in line"); voc.addWord(w);
        w = new Word("une carte bancaire","banking/ATM card"); voc.addWord(w);
        w = new Word("faire des économies","to save money"); voc.addWord(w);
        w = new Word("le montant","sum/total amount"); voc.addWord(w);
        w = new Word("un compte-chèques","checking account"); voc.addWord(w);
        w = new Word("l''argent","money"); voc.addWord(w);
        w = new Word("les fonds","funds"); voc.addWord(w);
        w = new Word("un chèque","check"); voc.addWord(w);
        w = new Word("un dépôt","deposit"); voc.addWord(w);
        w = new Word("le crédit","credit"); voc.addWord(w);
        w = new Word("les marchés financiers","financial markets"); voc.addWord(w);
        w = new Word("la clientèle","clientele"); voc.addWord(w);
        w = new Word("les frais","fees"); voc.addWord(w);
        w = new Word("une institution financière","financial institution"); voc.addWord(w);
        w = new Word("le taux d''intérêt","interest rate"); voc.addWord(w);
        w = new Word("la monnaie","currency"); voc.addWord(w);
        w = new Word("le bilan","balance"); voc.addWord(w);
        w = new Word("prendre un numéro","to take a number"); voc.addWord(w);
        w = new Word("une carte de crédit","credit card"); voc.addWord(w);
        w = new Word("un emprunt","a loan"); voc.addWord(w);
        w = new Word("retirer de l''argent","to take out money"); voc.addWord(w);
        w = new Word("un compte d''épargne","savings account"); voc.addWord(w);
        w = new Word("un distributeur automatique","ATM machine"); voc.addWord(w);
        w = new Word("un reçu","receipt"); voc.addWord(w);
        vocs.add(voc);

        voc = new Vocabulary("Les meubles");

        w = new Word("une table","a table"); voc.addWord(w);
        w = new Word("une chaise","a chair"); voc.addWord(w);
        w = new Word("une bibliothèque","a bookcase"); voc.addWord(w);
        w = new Word("une table basse","a coffee table"); voc.addWord(w);
        w = new Word("une cheminée","a fireplace"); voc.addWord(w);
        w = new Word("une table de chevet","a bedside table"); voc.addWord(w);
        w = new Word("une étagère","a shelf"); voc.addWord(w);
        w = new Word("une armoire","a wardrobe"); voc.addWord(w);
        w = new Word("une commode","a dresser"); voc.addWord(w);
        w = new Word("une moquette","a carpet"); voc.addWord(w);
        w = new Word("un siège","a seat"); voc.addWord(w);
        w = new Word("un tabouret","a stool"); voc.addWord(w);
        w = new Word("un placard","a cupboard"); voc.addWord(w);
        w = new Word("un tiroir","a drawer"); voc.addWord(w);
        w = new Word("un fauteuil","a armchair"); voc.addWord(w);
        w = new Word("un lit","a bed"); voc.addWord(w);
        w = new Word("un bureau","a desk"); voc.addWord(w);
        w = new Word("un miroir","a mrror"); voc.addWord(w);
        w = new Word("un meuble","a piece of furniture"); voc.addWord(w);
        w = new Word("un oreiller","a pillow"); voc.addWord(w);
        vocs.add(voc);

        voc = new Vocabulary("Les couleurs");

        w = new Word("blanc","white"); voc.addWord(w);
        w = new Word("bleu clair","light blue"); voc.addWord(w);
        w = new Word("multicolore","muli-colored"); voc.addWord(w);
        w = new Word("gris","grey"); voc.addWord(w);
        w = new Word("vert","green"); voc.addWord(w);
        w = new Word("noir","black"); voc.addWord(w);
        w = new Word("argenté","silver"); voc.addWord(w);
        w = new Word("jaune","yellow"); voc.addWord(w);
        w = new Word("orange","orange"); voc.addWord(w);
        w = new Word("bleu","blue"); voc.addWord(w);
        w = new Word("bleu foncé","dark blue"); voc.addWord(w);
        w = new Word("rose","pink"); voc.addWord(w);
        w = new Word("rouge","red"); voc.addWord(w);
        w = new Word("marron","brown"); voc.addWord(w);
        w = new Word("violet","purple"); voc.addWord(w);
        w = new Word("beige","beige"); voc.addWord(w);
        w = new Word("turquoise","turquoise"); voc.addWord(w);
        w = new Word("doré","golden"); voc.addWord(w);
        vocs.add(voc);

        voc = new Vocabulary("La famille");

        w = new Word("le père","the father"); voc.addWord(w);
        w = new Word("les parents","the parents"); voc.addWord(w);
        w = new Word("la sœur","the sister"); voc.addWord(w);
        w = new Word("la fille","the daughter"); voc.addWord(w);
        w = new Word("le bébé","the baby"); voc.addWord(w);
        w = new Word("la femme","the wife"); voc.addWord(w);
        w = new Word("la fiancée","the fiancée"); voc.addWord(w);
        w = new Word("la grand-mère","the grandmother"); voc.addWord(w);
        w = new Word("les petits-enfants","the grandchildren"); voc.addWord(w);
        w = new Word("la tante","the aunt"); voc.addWord(w);
        w = new Word("la nièce","the niece"); voc.addWord(w);
        w = new Word("la belle-mère","the step mother"); voc.addWord(w);
        w = new Word("la cousine","the cousin (female)"); voc.addWord(w);
        w = new Word("la belle-sœur","sister in-law"); voc.addWord(w);
        w = new Word("la mère","the mother"); voc.addWord(w);
        w = new Word("le frère","the brother"); voc.addWord(w);
        w = new Word("le fils","the son"); voc.addWord(w);
        w = new Word("l’enfant","the child"); voc.addWord(w);
        w = new Word("le mari","the husband"); voc.addWord(w);
        w = new Word("le fiancé","the fiancé"); voc.addWord(w);
        w = new Word("le grand-père","the grandfather"); voc.addWord(w);
        w = new Word("les grand-parents","the grandparents"); voc.addWord(w);
        w = new Word("l’oncle","the uncle"); voc.addWord(w);
        w = new Word("le neveu","the nephew"); voc.addWord(w);
        w = new Word("le beau-père","the step father"); voc.addWord(w);
        w = new Word("le cousin","the cousin (male)"); voc.addWord(w);
        w = new Word("le beau-frère","brother in-law"); voc.addWord(w);
        w = new Word("le beau-père","father in-law"); voc.addWord(w);
        vocs.add(voc);
    }

    // static method to create instance of Singleton class
    public static VocProvider getInstance()
    {
        if (single_instance == null)
            single_instance = new VocProvider();

        return single_instance;
    }

    public Vocabulary pickAVoc()
    {
        return vocs.get(AngryWirds.alea.nextInt(vocs.size()));
    }
}
