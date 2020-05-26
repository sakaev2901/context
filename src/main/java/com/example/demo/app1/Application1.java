package com.example.demo.app1;


import com.example.demo.context.MessageReceiver;
import com.example.demo.context.WordRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class Application1 implements CommandLineRunner { // интерфейс для запуска не веб-приложений

    @Autowired
    private MessageReceiver messageReceiver;


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application1.class);
        app.run(args);
    }

    static {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("socksProxyHost", "127.0.0.1"); // этот блок нужен для телеграмма. тут мы задаем прокси, ибо блокируется
        System.getProperties().put("socksProxyPort", "9150");
        ApiContextInitializer.init();
    }

    @Override
    public void run(String... args) throws Exception {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBot(messageReceiver)); // сама процедура запуска бота

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }


}
