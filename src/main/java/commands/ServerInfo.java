package commands;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static rcs.utilities.*;
import static rcs.info.*;

public class ServerInfo implements MessageCreateListener {

    public static String[] aliases = { prefix + "info", prefix + "serverinfo", prefix + "sinfo" };

    public static String description() {
        return "Returns information about the requested server!\n\nReturns info like the **member count** and much more!\n\n**SYNTAX**\n\n```\n" + prefix + "sinfo\n```";
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

            if (event.getServer().isPresent()) {
                Server server = event.getServer().get();

                EmbedBuilder embed = new EmbedBuilder();

                embed.setAuthor(server.getName(), null, serverIcon(server));
                embed.setTimestampToNow();
                embed.setFooter("Info about: " + event.getServer().get().getName());

                embed.addInlineField("Name:", "__" + server.getName() + "__");
                embed.addInlineField("Member count:", "**" + server.getMemberCount() + "**");
                embed.addInlineField("Boost level:", (server.getBoostLevel().name().equalsIgnoreCase("none") ? "This server has *no* boosts!" : server.getBoostLevel().name().toLowerCase()));
                embed.addInlineField("Icon:", "[Click Here for the icon](" + serverIcon(server) + ")");
                embed.addInlineField("Creation date:", getCreationDateServer(server));
                embed.addInlineField("Creation Time:", getCreationTimeServer(server));

                embed.addField("Channels", "Channel count: **" + (server.getTextChannels().size() + server.getVoiceChannels().size()) + "**\nThere are **" + server.getTextChannels().size() + "** text channels, and **" + server.getVoiceChannels().size() + "** voice channels! (`That you can see`)");

                embed.addField("Roles:", getServerRoles(server));

                event.getMessage().reply(embed);

            } else {
                event.getMessage().reply("This is not a server. Can't get info about a **non-existent** server can I?");
                return;
            }


        }

    }


}
