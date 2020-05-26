package com.example.demo.context;

public interface WordService {

    boolean isNotSingleWord(String word);
    boolean isNotUnique(String word);
    boolean isFirstLetterNotEqualToLastLetter(String word);
    boolean isNorCorrectLastLetter(String word);
    void saveWord(String word);
    char getLastLetter();
    boolean isTableEmpty();

    void dropTable();
}
