package ch.cpnv.angrywirds.providers;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.provider.Language;
import ch.cpnv.angrywirds.model.Data.SemanticWord;
import ch.cpnv.angrywirds.model.Data.Vocabulary;

public class VocProvider {
    // static variable single_instance of type VocProvider
    private static VocProvider single_instance = null;

    private ArrayList<Vocabulary> vocs;
    private ArrayList<Language> languages;

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

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    private VocProvider()
    {
        vocs = new ArrayList<Vocabulary>();
        SemanticWord sw;
        Vocabulary voc = new Vocabulary("L'argent");

        try {
            sw = new SemanticWord(); sw.addTranslation("fr","la banque"); sw.addTranslation("en","the bank"); sw.addTranslation("es","el Banco"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","l''argent liquide"); sw.addTranslation("en","cash"); sw.addTranslation("es","efectivo"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le paiement"); sw.addTranslation("en","payment"); sw.addTranslation("es","pago"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un carnet de chèques"); sw.addTranslation("en","checkbook"); sw.addTranslation("es","talonario de cheques"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","déposer de l''argent"); sw.addTranslation("en","to deposit money"); sw.addTranslation("es","depositar dinero"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","prêter de l''argent"); sw.addTranslation("en","to borrow money"); sw.addTranslation("es","pedir dinero prestado"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","les services financiers"); sw.addTranslation("en","financial services"); sw.addTranslation("es","servicios financieros"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la commission"); sw.addTranslation("en","commission"); sw.addTranslation("es","comisión"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un investissement"); sw.addTranslation("en","an investment"); sw.addTranslation("es","una inversión"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un virement"); sw.addTranslation("en","transfer"); sw.addTranslation("es","transferir"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la gestion de l''argent"); sw.addTranslation("en","money management"); sw.addTranslation("es","administración del dinero"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une transaction"); sw.addTranslation("en","a transaction"); sw.addTranslation("es","una transacción"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un guichet automatique"); sw.addTranslation("en","ATM machine"); sw.addTranslation("es","cajero automático"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","attendre dans la queue"); sw.addTranslation("en","to wait in line"); sw.addTranslation("es","a esperar en línea"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une carte bancaire"); sw.addTranslation("en","banking/ATM card"); sw.addTranslation("es","tarjeta bancaria / ATM"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","faire des économies"); sw.addTranslation("en","to save money"); sw.addTranslation("es","para ahorrar dinero"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le montant"); sw.addTranslation("en","sum/total amount"); sw.addTranslation("es","suma / monto total"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un compte-chèques"); sw.addTranslation("en","checking account"); sw.addTranslation("es","cuenta de cheques"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","l''argent"); sw.addTranslation("en","money"); sw.addTranslation("es","dinero"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","les fonds"); sw.addTranslation("en","funds"); sw.addTranslation("es","fondos"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un chèque"); sw.addTranslation("en","check"); sw.addTranslation("es","cheque"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un dépôt"); sw.addTranslation("en","deposit"); sw.addTranslation("es","depositar"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le crédit"); sw.addTranslation("en","credit"); sw.addTranslation("es","crédito"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","les marchés financiers"); sw.addTranslation("en","financial markets"); sw.addTranslation("es","mercados financieros"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la clientèle"); sw.addTranslation("en","clientele"); sw.addTranslation("es","clientela"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","les frais"); sw.addTranslation("en","fees"); sw.addTranslation("es","Tarifa"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une institution financière"); sw.addTranslation("en","financial institution"); sw.addTranslation("es","institución financiera"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le taux d''intérêt"); sw.addTranslation("en","interest rate"); sw.addTranslation("es","tasa de interés"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la monnaie"); sw.addTranslation("en","currency"); sw.addTranslation("es","moneda"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le bilan"); sw.addTranslation("en","balance"); sw.addTranslation("es","equilibrar"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","prendre un numéro"); sw.addTranslation("en","to take a number"); sw.addTranslation("es","tomar un número"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une carte de crédit"); sw.addTranslation("en","credit card"); sw.addTranslation("es","tarjeta de crédito"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un emprunt"); sw.addTranslation("en","a loan"); sw.addTranslation("es","un préstamo"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","retirer de l''argent"); sw.addTranslation("en","to take out money"); sw.addTranslation("es","sacar dinero"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un compte d''épargne"); sw.addTranslation("en","savings account"); sw.addTranslation("es","cuenta de ahorros"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un distributeur automatique"); sw.addTranslation("en","ATM machine"); sw.addTranslation("es","cajero automático"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un reçu"); sw.addTranslation("en","receipt"); sw.addTranslation("es","recibo"); voc.addWord(sw);
        } catch (Exception e) {
            Gdx.app.log("ANGRY", "Error while adding word: "+e.getMessage());
        }

        vocs.add(voc);

        voc = new Vocabulary("Les meubles");

        try {
            sw = new SemanticWord(); sw.addTranslation("fr","une table"); sw.addTranslation("en","a table"); sw.addTranslation("es","una mesa"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une chaise"); sw.addTranslation("en","a chair"); sw.addTranslation("es","una silla"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une bibliothèque"); sw.addTranslation("en","a bookcase"); sw.addTranslation("es","una estantería"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une table basse"); sw.addTranslation("en","a coffee table"); sw.addTranslation("es","una mesa para cafe"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une cheminée"); sw.addTranslation("en","a fireplace"); sw.addTranslation("es","una chimenea"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une table de chevet"); sw.addTranslation("en","a bedside table"); sw.addTranslation("es","una mesita de noche"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une étagère"); sw.addTranslation("en","a shelf"); sw.addTranslation("es","un estante"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une armoire"); sw.addTranslation("en","a wardrobe"); sw.addTranslation("es","un armario"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une commode"); sw.addTranslation("en","a dresser"); sw.addTranslation("es","un vestidor"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","une moquette"); sw.addTranslation("en","a carpet"); sw.addTranslation("es","Una alfombra"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un siège"); sw.addTranslation("en","a seat"); sw.addTranslation("es","un asiento"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un tabouret"); sw.addTranslation("en","a stool"); sw.addTranslation("es","un taburete"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un placard"); sw.addTranslation("en","a cupboard"); sw.addTranslation("es","un armario"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un tiroir"); sw.addTranslation("en","a drawer"); sw.addTranslation("es","un cajon"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un fauteuil"); sw.addTranslation("en","a armchair"); sw.addTranslation("es","un sillón"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un lit"); sw.addTranslation("en","a bed"); sw.addTranslation("es","una cama"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un bureau"); sw.addTranslation("en","a desk"); sw.addTranslation("es","un escritorio"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un miroir"); sw.addTranslation("en","a mrror"); sw.addTranslation("es","un mrror"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un meuble"); sw.addTranslation("en","a piece of furniture"); sw.addTranslation("es","un mueble"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","un oreiller"); sw.addTranslation("en","a pillow"); sw.addTranslation("es","una almohada"); voc.addWord(sw);
        } catch (Exception e) {
            Gdx.app.log("ANGRY", "Error while adding word: "+e.getMessage());
        }
        vocs.add(voc);

        voc = new Vocabulary("Les couleurs");

        try {
            sw = new SemanticWord(); sw.addTranslation("fr","blanc"); sw.addTranslation("en","white"); sw.addTranslation("es","blanco"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","bleu clair"); sw.addTranslation("en","light blue"); sw.addTranslation("es","azul claro"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","multicolore"); sw.addTranslation("en","muli-colored"); sw.addTranslation("es","multicolor"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","gris"); sw.addTranslation("en","grey"); sw.addTranslation("es","gris"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","vert"); sw.addTranslation("en","green"); sw.addTranslation("es","verde"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","noir"); sw.addTranslation("en","black"); sw.addTranslation("es","negro"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","argenté"); sw.addTranslation("en","silver"); sw.addTranslation("es","plata"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","jaune"); sw.addTranslation("en","yellow"); sw.addTranslation("es","amarillo"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","orange"); sw.addTranslation("en","orange"); sw.addTranslation("es","naranja"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","bleu"); sw.addTranslation("en","blue"); sw.addTranslation("es","azul"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","bleu foncé"); sw.addTranslation("en","dark blue"); sw.addTranslation("es","azul oscuro"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","rose"); sw.addTranslation("en","pink"); sw.addTranslation("es","rosado"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","rouge"); sw.addTranslation("en","red"); sw.addTranslation("es","rojo"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","marron"); sw.addTranslation("en","brown"); sw.addTranslation("es","marrón"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","violet"); sw.addTranslation("en","purple"); sw.addTranslation("es","púrpura"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","beige"); sw.addTranslation("en","beige"); sw.addTranslation("es","beige"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","turquoise"); sw.addTranslation("en","turquoise"); sw.addTranslation("es","turquesa"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","doré"); sw.addTranslation("en","golden"); sw.addTranslation("es","dorado"); voc.addWord(sw);
        } catch (Exception e) {
            Gdx.app.log("ANGRY", "Error while adding word: "+e.getMessage());
        }
        vocs.add(voc);

        voc = new Vocabulary("La famille");

        try {
            sw = new SemanticWord(); sw.addTranslation("fr","le père"); sw.addTranslation("en","the father"); sw.addTranslation("es","el padre"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","les parents"); sw.addTranslation("en","the parents"); sw.addTranslation("es","los padres"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la sœur"); sw.addTranslation("en","the sister"); sw.addTranslation("es","la hermana"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la fille"); sw.addTranslation("en","the daughter"); sw.addTranslation("es","la hija"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le bébé"); sw.addTranslation("en","the baby"); sw.addTranslation("es","el bebé"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la femme"); sw.addTranslation("en","the wife"); sw.addTranslation("es","La esposa"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la fiancée"); sw.addTranslation("en","the fiancée"); sw.addTranslation("es","la prometida"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la grand-mère"); sw.addTranslation("en","the grandmother"); sw.addTranslation("es","la abuela"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","les petits-enfants"); sw.addTranslation("en","the grandchildren"); sw.addTranslation("es","los nietos"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la tante"); sw.addTranslation("en","the aunt"); sw.addTranslation("es","la tía"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la nièce"); sw.addTranslation("en","the niece"); sw.addTranslation("es","la sobrina"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la belle-mère"); sw.addTranslation("en","the step mother"); sw.addTranslation("es","La Madrastra"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la cousine"); sw.addTranslation("en","the cousin (female)"); sw.addTranslation("es","la prima (mujer)"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la belle-sœur"); sw.addTranslation("en","sister in-law"); sw.addTranslation("es","cuñada"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","la mère"); sw.addTranslation("en","the mother"); sw.addTranslation("es","La madre"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le frère"); sw.addTranslation("en","the brother"); sw.addTranslation("es","el hermano"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le fils"); sw.addTranslation("en","the son"); sw.addTranslation("es","el hijo"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","l’enfant"); sw.addTranslation("en","the child"); sw.addTranslation("es","el niño"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le mari"); sw.addTranslation("en","the husband"); sw.addTranslation("es","el esposo"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le fiancé"); sw.addTranslation("en","the fiancé"); sw.addTranslation("es","el prometido"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le grand-père"); sw.addTranslation("en","the grandfather"); sw.addTranslation("es","el abuelo"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","les grand-parents"); sw.addTranslation("en","the grandparents"); sw.addTranslation("es","los abuelos"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","l’oncle"); sw.addTranslation("en","the uncle"); sw.addTranslation("es","el tío"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le neveu"); sw.addTranslation("en","the nephew"); sw.addTranslation("es","el sobrino"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le beau-père"); sw.addTranslation("en","the step father"); sw.addTranslation("es","el padrastro"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le cousin"); sw.addTranslation("en","the cousin (male)"); sw.addTranslation("es","el primo (hombre)"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le beau-frère"); sw.addTranslation("en","brother in-law"); sw.addTranslation("es","cuñado"); voc.addWord(sw);
            sw = new SemanticWord(); sw.addTranslation("fr","le beau-père"); sw.addTranslation("en","father in-law"); sw.addTranslation("es","suegro"); voc.addWord(sw);
        } catch (Exception e) {
            Gdx.app.log("ANGRY", "Error while adding word: "+e.getMessage());
            e.printStackTrace();
        }
        vocs.add(voc);

    }
}
