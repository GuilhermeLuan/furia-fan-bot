package dev.guilhermeluan.furiafanbot.bot.commands;

import dev.guilhermeluan.furiafanbot.bot.BotCommand;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.util.AbilityExtension;

public abstract class AbstractBotCommand implements BotCommand, AbilityExtension {

    @Override
    public Ability getAbility() {
        return Ability.builder()
                .name(getName())
                .info(getInfo())
                .locality(getLocality())
                .privacy(getPrivacy())
                .action(getAction())
                .build();
    }
}
