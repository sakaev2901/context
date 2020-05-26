package com.example.demo.context;

import com.example.demo.app1.TelegramBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface MessageReceiver {
    void receiveFromDiscord(GuildMessageReceivedEvent event) throws TelegramApiException;
    void receiveFromTelegram(TelegramBot telegramBot, Update update);
    void sendGameResponse(String word);
}
