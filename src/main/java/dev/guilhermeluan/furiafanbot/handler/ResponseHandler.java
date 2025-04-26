package dev.guilhermeluan.furiafanbot.handler;

import dev.guilhermeluan.furiafanbot.bot.FuriaBot;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class ResponseHandler {
    private final FuriaBot botInstance;

    public ResponseHandler(@Lazy FuriaBot botInstance) {
        this.botInstance = botInstance;
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(text);

        try {
            botInstance.execute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
