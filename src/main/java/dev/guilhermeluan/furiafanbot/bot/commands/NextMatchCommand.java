package dev.guilhermeluan.furiafanbot.bot.commands;

import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;
import dev.guilhermeluan.furiafanbot.handler.ResponseHandler;
import dev.guilhermeluan.furiafanbot.service.MatchInfoService;
import dev.guilhermeluan.furiafanbot.util.Constants;
import dev.guilhermeluan.furiafanbot.util.TelegramMessageFormatter;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;

import java.util.List;
import java.util.function.Consumer;

@Component
public class NextMatchCommand extends AbstractBotCommand{

    private final ResponseHandler responseHandler;
    private final MatchInfoService matchInfoService;

    public NextMatchCommand(ResponseHandler responseHandler, MatchInfoService matchInfoService) {
        this.responseHandler = responseHandler;
        this.matchInfoService = matchInfoService;
    }

    @Override
    public String getName() {
        return "proximojogo";
    }

    @Override
    public String getInfo() {
        return "Exibe os próximos jogos da FURIA CS.";
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
            List<MatchDTO> nextMatches = matchInfoService.getNextMatches();
            String formattedNextMatch = TelegramMessageFormatter.formatNextMatches(nextMatches);
            responseHandler.sendMessage(ctx.chatId(), formattedNextMatch);
        };
    }
}
