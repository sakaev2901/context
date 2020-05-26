package com.example.demo.app2;

import com.example.demo.context.MessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application2 implements CommandLineRunner {

    @Autowired
    private MessageReceiver messageReceiver;


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application2.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        DiscordBot.startBot(messageReceiver);
    }
}
