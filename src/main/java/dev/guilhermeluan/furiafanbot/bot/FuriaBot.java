package dev.guilhermeluan.furiafanbot.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.util.AbilityExtension;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import java.util.List;

@Component
public class FuriaBot extends AbilityBot {

    @Value("${telegram.creator.id}")
    private Long creatorId;
    private final List<AbilityExtension> extensions;

    @Autowired
    public FuriaBot(
            @Value("${telegram.bot.token}")
            String token,
            @Value("${telegram.bot.username}")
            String username,
            List<AbilityExtension> extensions
    ) {
        super(token, username, new DefaultBotOptions());
        this.extensions = extensions;
    }

    @PostConstruct
    public void registerCommands() {
        extensions.forEach(this::addExtension);
    }

    @Override
    public long creatorId() {
        return creatorId;
    }
}
