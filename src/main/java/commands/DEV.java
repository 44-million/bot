package commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import javax.mail.event.MessageChangedEvent;
import javax.mail.event.MessageChangedListener;

import static rcs.utilities.*;
import static rcs.info.*;

public class DEV implements MessageCreateListener {

    public static String[] aliases = { prefix + "enterdev", prefix + "devlog" };

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
