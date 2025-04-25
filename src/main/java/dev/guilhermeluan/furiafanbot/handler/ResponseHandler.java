package dev.guilhermeluan.furiafanbot.handler;

import dev.guilhermeluan.furiafanbot.bot.FuriaBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import static dev.guilhermeluan.furiafanbot.util.Constants.LOGO_URL;
import static dev.guilhermeluan.furiafanbot.util.Constants.START_TEXT;

public class ResponseHandler {
    private final FuriaBot botInstance;

    public ResponseHandler(FuriaBot botInstance) {
        this.botInstance = botInstance;
    }

    public void sendMessage(Long chatId) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(START_TEXT);
        try {
            botInstance.execute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendImage(Long chatId) {
        InputFile img = new InputFile(LOGO_URL);
        SendPhoto photo = new SendPhoto();

        photo.setChatId(chatId.toString());
        photo.setPhoto(img);

        try {
            botInstance.execute(photo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
