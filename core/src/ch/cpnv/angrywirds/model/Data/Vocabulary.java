package ch.cpnv.angrywirds.model.Data;
import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.model.Data.SemanticWord;

public class Vocabulary {
    /*public String title;
    public ArrayList<Word> words = new ArrayList<Word>();
    public Vocabulary(String title){
        this.title = title;
    }
    public void addWord(){

    }*/
    private String vocName;
    private ArrayList<SemanticWord> words;
    public Vocabulary(String vocName){
        this.vocName = vocName;
        words = new ArrayList<SemanticWord>();
    }
    public void addWord(SemanticWord word){
        words.add(word);
    }
    public SemanticWord pickAWord(){
        return words.get(AngryWirds.alea.nextInt(words.size()));
    }
}
