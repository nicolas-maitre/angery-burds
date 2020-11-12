package ch.cpnv.angrywirds.model.Data;

import java.util.HashMap;

import ch.cpnv.angrywirds.provider.Language;
import ch.cpnv.angrywirds.provider.TranslationDoesNotExistException;
import ch.cpnv.angrywirds.provider.TranslationExistsException;

public class SemanticWord {
    private HashMap<String, String> values;
    public SemanticWord(){
        values = new HashMap<String, String>();
    }
    public void addTranslation(String language, String value) throws TranslationExistsException {
        if(values.containsKey(language)){
            throw new TranslationExistsException();
        }
        values.put(language, value);
    }
    public String getValue(String language) throws TranslationDoesNotExistException {
        String value = values.get(language);
        if(value == null){
            throw new TranslationDoesNotExistException();
        }
        return value;
    }

    public String getValue(Language language) throws TranslationDoesNotExistException {
        return getValue(language.getISO_639_1());
    }
}