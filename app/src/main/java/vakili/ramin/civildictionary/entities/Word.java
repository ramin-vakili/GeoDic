package vakili.ramin.civildictionary.entities;

/**
 * Created by RaminV on 12/3/2018.
 */
public class Word {
    private String word, meaning, voice="", image="", phonetic="";
    private int isFavorite;

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public Word(String word, String meaning, String voice, String phonetic, int isFavorite) {
        this.word = word;
        this.meaning = meaning;
        this.voice = voice;
        this.phonetic = phonetic;
        this.isFavorite = isFavorite;
    }

    public Word() {
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
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

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public boolean isFavorite() {
        return !(isFavorite == 0);
    }

    public void setFavorite(int isFav) {
        isFavorite = isFav;
    }
}
