package com.example.demo.app1;

import com.example.demo.context.MessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private MessageReceiver messageReceiver;

    public TelegramBot(MessageReceiver messageReceiver) {
        this.messageReceiver = messageReceiver;
    }


    @Override
    public void onUpdateReceived(Update update) { // метод который реагирует на каждое сообщение
        System.out.println(update.getMessage().getText());
        messageReceiver.receiveFromTelegram(this, update); // здесь мы передаем объект класса TelegramBot и update;
        // почему не сразу текст отправляем в наш обработчик ? Так как this нам нужен для обратной отправки сообщения и update
        // хранит не только сообщение, но и другую важную информацию которая нудна messageReceiver
    }

    @Override
    public String getBotUsername() {
        return "WORDS_GAME_BOT";
    }

    @Override
    public String getBotToken() {
        return "916203242:AAFCl4c9o--78Yy_dgoTUGjXtzD5Yjx2XQU";
    }
}
