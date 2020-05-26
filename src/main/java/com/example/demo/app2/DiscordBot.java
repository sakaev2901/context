package com.example.demo.app2;

import com.example.demo.context.MessageReceiver;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    public static JDA jda;

    public static void startBot(MessageReceiver messageReceiver) throws LoginException, InterruptedException {
        jda = new JDABuilder(AccountType.BOT).setToken("NzEzNDc4Mjk1NzI3MTEyMjEz.Xsgs1Q.V5-bvLYulPHWRGc4YpWpGdeUVQo").build().awaitReady();
        jda.addEventListener(new Commands(messageReceiver));
    }
}
