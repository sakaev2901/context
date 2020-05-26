package com.example.demo.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Game {



    private final WordService wordService;

    public Game(WordService wordService) {
        this.wordService = wordService;
        initGame();
    }

    public void initGame() {
        wordService.dropTable();
    }

    public String handleWord(String word) {
        word = word.toLowerCase(); // убираем большие букавки
        if (wordService.isTableEmpty()) { // если таблица пуста перво же слово сохраняется и отправляется уже ответ сервера на следующее слово
            wordService.saveWord(word);
            return "Введите слово на \" "+ word.charAt(word.length() - 1) +" \"";
        }
        if (wordService.isNotUnique(word)) {
            return "Слово уже появлялось в игре";
        }
        if (wordService.isNotSingleWord(word)) {
            return "Введите слово, а не словосочетание";
        }
        if (wordService.isFirstLetterNotEqualToLastLetter(word)) {
            return "Слово не подходит. Введите слово на \" "+ wordService.getLastLetter() +" \"";
        }
        if (wordService.isNorCorrectLastLetter(word)) {
            return "Слово не должно оканчиваться на \"ь\", \"ъ\" и \"ы\"";
        }
        wordService.saveWord(word); // все понятно тут думаю
        return "Введите слово на \" "+ word.charAt(word.length() - 1) +" \"";
    }

    public String getCurrentStatus() { // непосредственно статус для новых игроков
        if (wordService.isTableEmpty()) {
            return "Введите первое слово";
        } else {
            return "Введите слово на \" "+ wordService.getLastLetter() +" \"";
        }
    }
}
