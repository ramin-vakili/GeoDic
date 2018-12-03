package vakili.ramin.civildictionary.entities;

/**
 * Created by RaminV on 12/3/2018.
 */
public class Word {
    private String word, meaning, voice="", image="";

    public Word(String word, String meaning) {
        this.word = word;
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
}
