package ch.cpnv.angrywirds.model.Data;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;

/**
 * Created by Xavier on 07.06.18.
 */

public class Vocabulary {
    String vocName;
    ArrayList<Word> words;

    public Vocabulary(String vocName){
        this.vocName = vocName;
        this.words = new ArrayList<Word>();
    }

    public void addWord(Word w) {
        words.add(w);
    }

    public Word pickAWord() {
        return words.get(AngryWirds.alea.nextInt(words.size()));
    } // TODO pick a non-allocated word
}
