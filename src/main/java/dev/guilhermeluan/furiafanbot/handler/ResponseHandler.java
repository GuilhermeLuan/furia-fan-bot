package dev.guilhermeluan.furiafanbot.handler;

import dev.guilhermeluan.furiafanbot.bot.FuriaBot;
import dev.guilhermeluan.furiafanbot.util.KeyboardRowFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
public class ResponseHandler {
    private final FuriaBot botInstance;

    public ResponseHandler(@Lazy FuriaBot botInstance) {
        this.botInstance = botInstance;
    }

    public void sendMessage(Long chatId, String text) {


        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);

        List<String> commands = List.of(
                "/proximojogo",
                "/ultimoresultado",
                "/ajuda"
        );

        List<KeyboardRow> keyboard = KeyboardRowFactory.createRow(commands);

        msg.setReplyMarkup(new ReplyKeyboardMarkup(keyboard));
        msg.setText(text);

        try {
            botInstance.execute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
