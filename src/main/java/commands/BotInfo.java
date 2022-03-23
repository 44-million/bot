package commands;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static rcs.info.prefix;
import static rcs.utilities.discardable;
import static rcs.utilities.equalsAny;

public class BotInfo implements MessageCreateListener {

    public static String[] aliases = {prefix + "botinfo", prefix + "binfo", "aoisinfo"};

    public static String description() {

        return "Info about the bot! Tells you the `creation time` of the bot, what language it's written in, etc!\n\n**SYNTAX**\n```\n" + prefix + "binfo\n```\n\nEasy as that";

    }

    public static String getAliases() {

        StringBuilder j = new StringBuilder();

        for (String s : aliases) {
            j.append("**").append(s.replace("?", "")).append("**").append("\n");
        }

        return j.toString();

    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (discardable(event, aliases)) {
            return;
        }

        if (equalsAny(aliases, event.getMessageContent(), true)) {


            EmbedBuilder embed = new EmbedBuilder();

            embed.setAuthor(event.getMessageAuthor());
            embed.setTitle("Aois is a bot");
            embed.addInlineField("Commands", "This list is too long to put here, try `" + prefix + "help` or `" + prefix + "help 2`");
            embed.addInlineField("Creator:", "ٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴٴ#8966");
            embed.addInlineField("Syntax", "This bot uses the prefix: `" + prefix + "`.\nWhen viewing the `help` page, **<args>** denotes a required argument, and **[args]** represents an optional argument!");
            embed.addField("Other info:", "The bot is made in `JAVA` using JavaCord. The original repository for this bot can be found [here](https://github.com/primevibetime/Aois-bot) (note, this will probably be private)");

            embed.setFooter("Requested by: " + event.getMessageAuthor().getDiscriminatedName());
            embed.setTimestampToNow();

            event.getMessage().reply(embed);
            return;

        }

    }

}
