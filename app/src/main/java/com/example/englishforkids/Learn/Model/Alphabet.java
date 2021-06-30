package com.example.englishforkids.Learn.Model;

import java.io.Serializable;

public class Alphabet implements Serializable {
    public String ID, alpha, soundWord, translateWord;
    public byte[] word, image;

    public Alphabet() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getSoundWord() {
        return soundWord;
    }

    public void setSoundWord(String soundWord) {
        this.soundWord = soundWord;
    }

    public String getTranslateWord() {
        return translateWord;
    }

    public void setTranslateWord(String translateWord) {
        this.translateWord = translateWord;
    }

    public byte[] getWord() {
        return word;
    }

    public void setWord(byte[] word) {
        this.word = word;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String toString() {
        return alpha;
    }
}
