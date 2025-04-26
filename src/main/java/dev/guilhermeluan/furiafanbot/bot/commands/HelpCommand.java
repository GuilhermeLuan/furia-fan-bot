package dev.guilhermeluan.furiafanbot.bot.commands;

import dev.guilhermeluan.furiafanbot.handler.ResponseHandler;
import dev.guilhermeluan.furiafanbot.util.Constants;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;

import java.util.function.Consumer;

@Component
public class HelpCommand extends AbstractBotCommand {

    private final ResponseHandler responseHandler;

    public HelpCommand(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public String getName() {
        return "ajuda";
    }

    @Override
    public String getInfo() {
        return "Comando de ajuda.";
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
            responseHandler.sendMessage(ctx.chatId(), Constants.HELP_TEXT);
        };
    }
}
