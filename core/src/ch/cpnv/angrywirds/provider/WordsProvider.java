package ch.cpnv.angrywirds.provider;

import java.util.HashMap;

import ch.cpnv.angrywirds.model.Data.Vocabulary;

public class WordsProvider {
    private static WordsProvider instance = null;
    private HashMap<String, ch.cpnv.angrywirds.model.Data.Vocabulary> vocabularies;
    private WordsProvider(){
        throw new Error("nooooo");
    }
    public WordsProvider instance(){
        if(instance == null){
            instance = new WordsProvider();
        }
        return instance;
    }
    public Vocabulary getVocabulary(String vocName){
        return vocabularies.get(vocName);
    }
}
