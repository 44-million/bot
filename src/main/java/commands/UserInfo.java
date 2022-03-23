package commands;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import rcs.Aois;
import rcs.utilities;

import static rcs.info.prefix;
import static rcs.utilities.discardable;
import static rcs.utilities.equalsAny;

public class UserInfo implements MessageCreateListener {

    public static String[] aliases = {prefix + "whois", prefix + "ui", prefix + "userinfo"};

    /**
     * @return The description for this command
     */
    public static String description() {
        return "A command to get information about the mentioned user!\n\nThis command yields the user's `creation time` and the `creation date` and the `roles` and the `status`, `voice` connection status, and much more!\n\n**SYNTAX**\n\n```html\n" + prefix + "ui[@user]\n```\n\nThis command has simple syntax, but lots of information!";
    }

    public static String getAliases() {

        StringBuilder j = new StringBuilder();

        for (String s : aliases) {
            j.append(s.replace("?", "")).append("\n");
        }

        return j.toString();

    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (discardable(event, aliases)) {
            return;
        }

        if (equalsAny(aliases, event.getMessageContent(), true) && !event.getMessage().getMentionedUsers().isEmpty()) {

            for (User user : event.getMessage().getMentionedUsers()) {

                EmbedBuilder embed = new EmbedBuilder();
                Server server = event.getServer().get();

                embed.setAuthor(user);
                embed.setFooter("Requested by: " + event.getMessage().getAuthor().getName(), event.getMessage().getAuthor().getAvatar().getUrl().toString());
                embed.setTitle("Info about: " + user.getName());

                embed.addInlineField("`" + user.getName() + "`'s id", user.getIdAsString());
                embed.addInlineField("`" + user.getName() + "`'s Nickname:", utilities.nickname(user, server));
                embed.addField("Join date:", "**" + utilities.getExactCreationDate(user) + "** at **" + utilities.getExactCreationTime(user) + "**\n\nThat was " + utilities.getCreationDate(user));
                embed.addField("Voice connection status", utilities.voice(server, user));
                embed.addInlineField("`" + user.getName() + "`'s colors!", utilities.colors(user, server));
                embed.addField("All of their roles:", utilities.getRolesWithSpace(user, server));

                event.getMessage().reply(embed);

            }
        }

        if (equalsAny(aliases, event.getMessageContent(), true) && event.getMessage().getMentionedUsers().isEmpty()) {

            try {
                String request = event.getMessageContent()
                        .replace(prefix + "ui", "")
                        .replace(prefix + "userinfo", "")
                        .replace(prefix + "whois", "");
                String[] command = request.split(" ");

                long id = Long.parseLong(command[1]);

                User user = Aois.aois.getUserById(id).get();

                EmbedBuilder embed = new EmbedBuilder();
                Server server = event.getServer().get();

                embed.setAuthor(user);
                embed.setFooter("Requested by: " + event.getMessage().getAuthor().getName(), event.getMessage().getAuthor().getAvatar().getUrl().toString());
                embed.setTitle("Info about: " + user.getName());

                embed.addInlineField("`" + user.getName() + "`'s id", user.getIdAsString());
                embed.addInlineField("`" + user.getName() + "`'s Nickname:", utilities.nickname(user, server));
                embed.addField("Join date:", "**" + utilities.getExactCreationDate(user) + "** at **" + utilities.getExactCreationTime(user) + "**\n\nThat was " + utilities.getCreationDate(user));
                embed.addField("Voice connection status", utilities.voice(server, user));
                embed.addInlineField("`" + user.getName() + "`'s colors!", utilities.colors(user, server));
                embed.addField("All of their roles:", utilities.getRolesWithSpace(user, server));

                event.getMessage().reply(embed);

            } catch (Exception e) {

                User user = event.getMessageAuthor().asUser().get();

                EmbedBuilder embed = new EmbedBuilder();
                Server server = event.getServer().get();

                embed.setAuthor(event.getMessageAuthor());
                embed.setFooter("Requested by: " + event.getMessage().getAuthor().getName(), event.getMessage().getAuthor().getAvatar().getUrl().toString());
                embed.setTitle("Here's your info!");

                embed.addInlineField("Your id", user.getIdAsString());
                embed.addInlineField("Nickname:", utilities.nickname(user, server));
                embed.addField("Join date:", "**" + utilities.getExactCreationDate(user) + "** at **" + utilities.getExactCreationTime(user) + "**\n\nThat was " + utilities.getCreationDate(user));
                embed.addField("Voice connection status", utilities.voice(server, user));
                embed.addInlineField("Your colors!", utilities.colors(user, server));
                embed.addField("All of your roles:", utilities.getRolesWithSpace(user, server));
                event.getMessage().reply(embed);

            }


        }

    }
}
