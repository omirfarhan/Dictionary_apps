package com.example.dictonary;

public class Datalist {

    int id;

    String word,meaning,partsofspeech,example;

    public Datalist(int id, String word, String meaning, String partsofspeech, String example) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.partsofspeech = partsofspeech;
        this.example = example;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPartsofspeech() {
        return partsofspeech;
    }

    public void setPartsofspeech(String partsofspeech) {
        this.partsofspeech = partsofspeech;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
