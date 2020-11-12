package ch.cpnv.angrywirds.provider;

public class Language {
    private String displayName;
    private String isoName;
    public Language(String isoName, String displayName){
        this.isoName = isoName;
        this.displayName = displayName;
    }
    public String getISO_639_1() {
        return isoName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
