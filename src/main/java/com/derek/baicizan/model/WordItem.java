package com.derek.baicizan.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author derek zhan
 * @date 2023/6/1 16:26:45
 */
public class WordItem {

    // 英文单词
    private String word;
    // 音标
    private String accent;
    // 中文意思
    @SerializedName("mean_cn")
    private String meanCn;
    // 英文解释(可能为空字符串, 但绝大多数单词有英文解释.)
    @SerializedName("mean_en")
    private String meanEn;
    // 英文例句(可能为空字符串, 但大部分单词有例句.)
    @SerializedName("sentence")
    private String sentence;
    // 英文例句翻译
    @SerializedName("sentence_trans")
    private String sentenceTrans;
    // 相关的短语(可能为空字符串)
    private String sentencePhrase;
    // 单词词源(可能为空字符串)
    @SerializedName("word_etyma")
    private String wordEtyma;
    // 一些其他数据(可能为空)
    ClozeData clozeData;

    @Override
    public String toString() {
        return String.format("%s,音标:[%s],中文:%s,例句:%s,翻译:%s", word, accent, meanCn, sentence, sentenceTrans);
    }


    // Getter Methods

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAccent() {
        return accent;
    }

    public void setAccent(String accent) {
        this.accent = accent;
    }

    public String getMeanCn() {
        return meanCn;
    }

    public void setMeanCn(String meanCn) {
        this.meanCn = meanCn;
    }

    public String getMeanEn() {
        return meanEn;
    }

    public void setMeanEn(String meanEn) {
        this.meanEn = meanEn;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentenceTrans() {
        return sentenceTrans;
    }

    public void setSentenceTrans(String sentenceTrans) {
        this.sentenceTrans = sentenceTrans;
    }

    public String getSentencePhrase() {
        return sentencePhrase;
    }

    public void setSentencePhrase(String sentencePhrase) {
        this.sentencePhrase = sentencePhrase;
    }

    public String getWordEtyma() {
        return wordEtyma;
    }

    public void setWordEtyma(String wordEtyma) {
        this.wordEtyma = wordEtyma;
    }

    public ClozeData getClozeData() {
        return clozeData;
    }

    public void setClozeData(ClozeData clozeData) {
        this.clozeData = clozeData;
    }
}

class ClozeData {
    private String syllable;
    private String cloze;
    List<String> options = new ArrayList<String>();
    List<String> tips = new ArrayList<String>();


    // Getter Methods

    public String getSyllable() {
        return syllable;
    }

    public String getCloze() {
        return cloze;
    }

    // Setter Methods

    public void setSyllable(String syllable) {
        this.syllable = syllable;
    }

    public void setCloze(String cloze) {
        this.cloze = cloze;
    }
}
