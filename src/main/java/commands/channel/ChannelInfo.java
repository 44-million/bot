package commands.channel;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import rcs.utilities;

import static rcs.info.prefix;
import static rcs.utilities.discardable;
import static rcs.utilities.equalsAny;

public class ChannelInfo implements MessageCreateListener {

    public static String[] aliases = {prefix + "channel", prefix + "channelinfo", prefix + "cinfo", prefix + "getinfo", prefix + "cha"};

    public static String description() {
        return "A command to get information about a channel.\n\nIt yields information like, your permissions in the channel, creation date, **NSFW** status, and much more!\n**SYNTAX**\n\n```html\n" + prefix + "channel [#channel]\n```";
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

        if (equalsAny(aliases, event.getMessageContent(), true) && !event.getMessage().getMentionedChannels().isEmpty()) {

            for (Channel channel : event.getMessage().getMentionedChannels()) {

                EmbedBuilder embed = new EmbedBuilder();

                embed.setTitle("Info about #" + channel.asServerTextChannel().get().getName());
                try {
                    embed.addInlineField("Pinned message count: ", "**" + channel.asServerTextChannel().get().getPins().get().size() + "**");
                    embed.addInlineField("NSFW Status:", utilities.nsfw(channel));
                    embed.addInlineField("Webhook count:", utilities.webhooks(channel));
                    embed.addField("Your perms in this channel:", utilities.perms(channel, event.getMessageAuthor()));

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    embed.addInlineField("NSFW Status:", utilities.nsfw(channel));
                    embed.addInlineField("Webhook count:", utilities.webhooks(channel));
                    embed.addField("Your perms in this channel:", utilities.perms(channel, event.getMessageAuthor()));
                }

                embed.setTimestampToNow();

                event.getMessage().reply(embed);

            }
        } else if (equalsAny(aliases, event.getMessageContent(), true) && event.getMessage().getMentionedChannels().isEmpty()) {

            Channel channel = event.getChannel();

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Info about #" + channel.asServerTextChannel().get().getName());
            try {
                embed.setDescription("So you wanna know about `#" + channel.asServerTextChannel().get().getName() + "`, eh?");

                embed.addInlineField("Pinned message count: ", "**" + channel.asServerTextChannel().get().getPins().get().size() + "**");
                embed.addInlineField("NSFW Status:", utilities.nsfw(channel));
                embed.addInlineField("Webhook count:", utilities.webhooks(channel));
                embed.addField("Your perms in this channel", utilities.perms(channel, event.getMessageAuthor()));

            } catch (Exception e) {
                System.out.println(e.getMessage());
                embed.addInlineField("NSFW Status:", utilities.nsfw(channel));
                embed.addInlineField("Webhook count:", utilities.webhooks(channel));
                embed.addField("Your perms in this channel:", utilities.perms(channel, event.getMessageAuthor()));
            }

            embed.setTimestampToNow();

            event.getMessage().reply(embed);


        } else if (equalsAny(aliases, event.getMessageContent(), true) && !event.getMessage().getMentionedUsers().isEmpty()) {

            Channel channel = event.getChannel();

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Listen, that's a user. Not a channel.");
            try {
                embed.setDescription("So you wanna know about `#" + channel.asServerTextChannel().get().getName() + "`, eh?");

                embed.addInlineField("Pinned message count: ", "**" + channel.asServerTextChannel().get().getPins().get().size() + "**");
                embed.addInlineField("NSFW Status:", utilities.nsfw(channel));
                embed.addInlineField("Webhook count:", utilities.webhooks(channel));
                embed.addField("Your perms in this channel", utilities.perms(channel, event.getMessageAuthor()));

            } catch (Exception e) {
                System.out.println(e.getMessage());
                embed.addInlineField("NSFW Status:", utilities.nsfw(channel));
                embed.addInlineField("Webhook count:", utilities.webhooks(channel));
                embed.addField("Your perms in this channel:", utilities.perms(channel, event.getMessageAuthor()));
            }

            embed.setTimestampToNow();

            event.getMessage().reply(embed);


        }

    }
}
