package com.example.demo.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository wordRepository;

    @Override
    public boolean isNotSingleWord(String word) {
        return word.split(" ").length > 1;
    }

    @Override
    public boolean isNotUnique(String word) {
        return wordRepository.isExists(word);
    }

    @Override
    public boolean isFirstLetterNotEqualToLastLetter(String word) {
        String lastWord = wordRepository.findLastWord();
        return lastWord.charAt(lastWord.length() - 1) != word.charAt(0);
    }

    @Override
    public boolean isNorCorrectLastLetter(String word) {
        char lastChar = word.charAt(word.length() - 1);
        return lastChar == 'ь' || lastChar == 'ъ' || lastChar == 'ы';
    }

    @Override
    public void saveWord(String word) {
        wordRepository.save(word);
    }

    @Override
    public char getLastLetter() {
        String lastWord = wordRepository.findLastWord();
        return lastWord.charAt(lastWord.length() - 1);
    }

    @Override
    public boolean isTableEmpty() {
        return wordRepository.isEmpty();
    }

    @Override
    public void dropTable() {
        wordRepository.dropTable();
    }


}
