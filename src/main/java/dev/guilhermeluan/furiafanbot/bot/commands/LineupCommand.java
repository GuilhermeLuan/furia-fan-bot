package dev.guilhermeluan.furiafanbot.bot.commands;

import dev.guilhermeluan.furiafanbot.client.dto.TeamDTO;
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
public class LineupCommand extends AbstractBotCommand {

    private final ResponseHandler responseHandler;
    private final MatchInfoService matchInfoService;

    public LineupCommand(ResponseHandler responseHandler, MatchInfoService matchInfoService) {
        this.responseHandler = responseHandler;
        this.matchInfoService = matchInfoService;
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
            List<TeamDTO> furiaTeamInfo = matchInfoService.getFuriaTeamInfo();
            String formattedLineup = TelegramMessageFormatter.formatLineup(furiaTeamInfo.getFirst());
            responseHandler.sendMessage(ctx.chatId(), formattedLineup);
        };
    }
}
