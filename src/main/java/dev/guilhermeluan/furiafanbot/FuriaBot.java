package dev.guilhermeluan.furiafanbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
public class FuriaBot extends AbilityBot {

    @Value("${telegram.creator.id}")
    private Long creatorId;
    private ResponseHandler responseHandler;

    @Autowired
    public FuriaBot(
            @Value("${telegram.bot.token}") String token,
            @Value("${telegram.bot.username}") String username
    ) {
        super(token, username, new DefaultBotOptions());
        this.responseHandler = new ResponseHandler(silent);
    }

    public Ability startBot(){
        return Ability.builder()
                .name("start")
                .info("Start the bot")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    responseHandler.sendMessage(ctx.chatId());

                } )
                .build();
    }

    @Override
    public long creatorId() {
        return creatorId;
    }
}
