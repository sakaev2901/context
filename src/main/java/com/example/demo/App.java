package com.example.demo;

import com.example.demo.app1.Application1;
import com.example.demo.app2.Application2;
import com.example.demo.context.GameConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder() // лаунчер нашего приложения
                .parent(GameConfiguration.class).web(WebApplicationType.NONE) // здесь указали родительский конфиг, для того чтобы бины игры работали в контекстах обоих ботов
                .child(Application1.class).web(WebApplicationType.NONE) // первый бот
                .sibling(Application2.class).web(WebApplicationType.NONE) // второй
                .run(args);
    }


}
