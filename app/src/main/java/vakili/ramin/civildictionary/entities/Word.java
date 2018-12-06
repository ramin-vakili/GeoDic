package vakili.ramin.civildictionary.entities;


/**
 * Created by RaminV on 12/3/2018.
 */
public class Word {
    private String word, meaning, voice="", image="", phonetic="";
    private String[] voicesFilePath = new String[0];
    private int isFavorite;

    public Word(String word, String meaning, String voice, String phonetic, int isFavorite) {
        this.word = word;
        this.meaning = meaning;
        this.voice = voice;
        this.phonetic = phonetic;
        this.isFavorite = isFavorite;

        if (!voice.equals("")){
            voicesFilePath = voice.split(",");
        }
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getVoice() {
        return voice;
    }

    public String getImage() {
        return image;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public boolean isFavorite() {
        return !(isFavorite == 0);
    }

    public String[] getVoicesFilePath() {
        return voicesFilePath;
    }
}
