package dev.guilhermeluan.furiafanbot.bot;

import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Privacy;

import java.util.function.Consumer;

public interface BotCommand {
    String getName();
    String getInfo();
    Locality getLocality();
    Privacy getPrivacy();
    Ability getAbility();
    Consumer<MessageContext> getAction();

}
