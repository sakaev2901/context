package com.example.demo.context;

public interface WordRepository {

    boolean isExists(String word);
    void save(String word);
    String findLastWord();
    boolean isEmpty();
    void dropTable();
}
