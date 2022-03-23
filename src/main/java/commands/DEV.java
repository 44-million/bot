package commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static rcs.info.prefix;
import static rcs.utilities.discardable;
import static rcs.utilities.equalsAny;

public class DEV implements MessageCreateListener {

    public static String[] aliases = {prefix + "enterdev", prefix + "devlog"};

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (discardable(event, aliases)) {
            return;
        }

        if (equalsAny(aliases, event.getMessageContent(), true)) {


            if (event.getMessageAuthor().getIdAsString().equalsIgnoreCase("517345566028726276")) {


                event.getMessage().reply("Dev mode entered.");


            }

        }

    }
}
