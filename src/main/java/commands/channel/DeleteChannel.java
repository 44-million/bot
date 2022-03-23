package commands.channel;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.ExecutionException;

import static rcs.info.prefix;
import static rcs.utilities.discardable;
import static rcs.utilities.equalsAny;

public class DeleteChannel implements MessageCreateListener {


    public static final String[] aliases = {prefix + "deletechannel", prefix + "dchannel", prefix + "delete"};

    public static String description() {
        return "A command to *delete* text channels!\n\n**SYNTAX**\n\n```html\n" + prefix + "delete [#channel]\n```";
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

        if (equalsAny(aliases, event.getMessageContent(), true) && event.getMessage().getMentionedChannels().isEmpty()) {
            event.getMessage().reply("Please mention a channel to delete, like so!\n\n```html\n" + prefix + "delete [#channel]\n```");
            return;
        }

        if (equalsAny(aliases, event.getMessageContent(), true) && !event.getServer().get().canYouManage()) {

            EmbedBuilder embed = new EmbedBuilder();

            embed.setAuthor(event.getMessageAuthor());
            embed.setTitle("I can't delete your channel");
            embed.setDescription("I don't have the proper permissions for that, try giving me permissions to `manage channel`\nThat should work!");
            embed.setTimestampToNow();
            event.getMessage().reply(embed);
            return;

        }

        if (equalsAny(aliases, event.getMessageContent(), true) && !event.getMessageAuthor().canManageServer() && !(event.getMessageAuthor().getId() == 517345566028726276L)) {

            EmbedBuilder embed = new EmbedBuilder();

            embed.setAuthor(event.getMessageAuthor());
            embed.setTitle("I can't delete your channel");
            embed.setDescription("You don't have the proper permissions for that, try getting permissions to `manage channel`\nThat should work!");
            embed.setTimestampToNow();
            event.getMessage().reply(embed);
            return;

        }

        if (equalsAny(aliases, event.getMessageContent(), true) && (event.getMessageAuthor().canManageServer() || (event.getMessageAuthor().getId() == 517345566028726276L) && !event.getMessage().getMentionedChannels().isEmpty() /*&& event.getMessageAuthor().getId() != 517345566028726276L*/)) {

            for (Channel channel : event.getMessage().getMentionedChannels()) {
                EmbedBuilder embed = new EmbedBuilder();

                embed.setAuthor(event.getMessageAuthor());
                embed.setTitle("Deleted: #" + channel.asServerTextChannel().get().getName());
                try {
                    embed.setDescription("This channel had: **" + channel.asServerTextChannel().get().getMessagesAsStream().count() + "** messages sent in it... before you deleted it\n\n" +
                            "It also had " + channel.asServerTextChannel().get().getPins().get().size() + "pins\n\nAs well as " + channel.asServerTextChannel().get().getWebhooks().get().size() + " webhooks...");
                } catch (InterruptedException | ExecutionException e) {
                    embed.setDescription("There was an internal error counting the pins...\n\npoint is, the channel's **gone**");
                }

                embed.setTimestampToNow();
                embed.setFooter("Deleted by: " + event.getMessageAuthor().getDiscriminatedName());

                channel.asServerTextChannel().get().delete().join();

                event.getMessage().reply(embed);
                return;


            }

/*            if (equalsAny(aliases, event.getMessageContent(), true) && !event.getMessageAuthor().canManageServer() && !event.getMessage().getMentionedChannels().isEmpty() && event.getMessageAuthor().getIdAsString().equalsIgnoreCase("517345566028726276")) {

                for (Channel channel : event.getMessage().getMentionedChannels()) {
                    EmbedBuilder embed = new EmbedBuilder();

                    embed.setAuthor(event.getMessageAuthor());
                    embed.setTitle("Deleted: #" + channel.asServerTextChannel().get().getName());
                    try {
                        embed.setDescription("This channel had: **" + channel.asServerTextChannel().get().getMessagesAsStream().count() + "** messages sent in it... before you deleted it\n\n" +
                                "It also had " + channel.asServerTextChannel().get().getPins().get().size() + " pins\n\nAs well as " + channel.asServerTextChannel().get().getWebhooks().get().size() + " webhooks...");
                    } catch (InterruptedException | ExecutionException e) {
                        embed.setDescription("There was an internal error counting the pins, or something like that...\n\npoint is, the channel's **gone**");
                    }

                    embed.setTimestampToNow();
                    embed.setFooter("Deleted by: " + event.getMessageAuthor().getDiscriminatedName());

                    try {
                        channel.asServerTextChannel().get().delete().get();
                    } catch (InterruptedException | ExecutionException e) {
                        event.getMessage().reply("There was an error deleting the channel...");
                        return;
                    }

                    event.getMessage().reply(embed);
                    return;


                }

            } */

        }

    }
}

