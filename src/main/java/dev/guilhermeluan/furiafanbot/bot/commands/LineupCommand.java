package dev.guilhermeluan.furiafanbot.bot.commands;

import dev.guilhermeluan.furiafanbot.handler.ResponseHandler;
import dev.guilhermeluan.furiafanbot.util.Constants;
import dev.guilhermeluan.furiafanbot.util.TelegramMessageFormatter;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;

import java.util.function.Consumer;

@Component
public class LineupCommand extends AbstractBotCommand {

    private final ResponseHandler responseHandler;

    public LineupCommand(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public String getName() {
        return "lineup";
    }

    @Override
    public String getInfo() {
        return "Time atual de CS da Furia.";
    }

    @Override
    public Locality getLocality() {
        return Locality.ALL;
    }

    @Override
    public Privacy getPrivacy() {
        return Privacy.PUBLIC;
    }

    @Override
    public Consumer<MessageContext> getAction() {
        return ctx -> {
            TelegramMessageFormatter formatter = new TelegramMessageFormatter();

            responseHandler.sendMessage(ctx.chatId(), "Line UP");
        };
    }
}
