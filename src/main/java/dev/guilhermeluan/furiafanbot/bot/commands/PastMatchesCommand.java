package dev.guilhermeluan.furiafanbot.bot.commands;

import dev.guilhermeluan.furiafanbot.client.dto.MatchDTO;
import dev.guilhermeluan.furiafanbot.handler.ResponseHandler;
import dev.guilhermeluan.furiafanbot.service.MatchInfoService;
import dev.guilhermeluan.furiafanbot.util.TelegramMessageFormatter;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;

import java.util.List;
import java.util.function.Consumer;

@Component
public class PastMatchesCommand extends AbstractBotCommand {

    private final ResponseHandler responseHandler;
    private final MatchInfoService matchInfoService;

    public PastMatchesCommand(ResponseHandler responseHandler, MatchInfoService matchInfoService) {
        this.responseHandler = responseHandler;
        this.matchInfoService = matchInfoService;
    }

    @Override
    public String getName() {
        return "ultimoresultado";
    }

    @Override
    public String getInfo() {
        return "Exibe os 3 ultimos jogos da FURIA CS.";
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
            List<MatchDTO> pastMatches = matchInfoService.getPastMatches();
            String formattedNextMatch = TelegramMessageFormatter.formatLastMatches(pastMatches);
            responseHandler.sendMessage(ctx.chatId(), formattedNextMatch);
        };
    }
}
