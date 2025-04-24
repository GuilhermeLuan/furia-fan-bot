package dev.guilhermeluan.furiafanbot;

import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import static dev.guilhermeluan.furiafanbot.Constants.*;

public class ResponseHandler {
    private final SilentSender sender;
    // private final Map<Long, UserState> chatStates;

    public ResponseHandler(SilentSender sender) {
        this.sender = sender;
    }

    public void sendMessage(Long chatId) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(START_TEXT);
        try {
            sender.execute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendImage(Long chatId) {
        InputFile img = new InputFile(LOGO_URL);
        SendPhoto photo = new SendPhoto();

        photo.setChatId(chatId.toString());
        photo.setPhoto(img);
        photo.setCaption(LOGO_FURIA_CAPTION);

        try {
            sender.execute((BotApiMethod<Message>) photo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
