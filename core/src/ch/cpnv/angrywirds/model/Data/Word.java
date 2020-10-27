package ch.cpnv.angrywirds.model.Data;

/**
 * Created by Xavier on 21.06.18.
 */

public class Word {
    private String value1;
    private String value2;
    private boolean allocated; // Tells if the word has been given to one of the pigs
    private boolean found; // Tells if the player has solved that translation

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public Word(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
        allocated = false;
        found = false;
    }

}
