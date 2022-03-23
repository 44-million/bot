package commands;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static rcs.info.prefix;
import static rcs.utilities.*;

public class Latency implements MessageCreateListener {

    public static String[] aliases = {prefix + "ping", prefix + "latency"};

    /**
     * @return The description for this command
     */
    public static String description() {
        return "A simple ping command! Returns the `ping` in **ms**!" +
                "\n **syntax**\n" +
                "\n```html\n" + prefix + "ping" +
                "\n```";
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
            long start = System.currentTimeMillis();
            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Pong!");
            embed.setColor(getRndColor());
            embed.setFooter(event.getMessageAuthor().getDisplayName(), event.getMessageAuthor().getAvatar().getUrl().toString());
            embed.setDescription(System.currentTimeMillis() - start + "ms");

            event.getMessage().reply(embed);
        }
    }
}
