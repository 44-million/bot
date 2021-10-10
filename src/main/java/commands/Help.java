package commands;

import static rcs.info.prefix;
import static rcs.utilities.discardable;
import static rcs.utilities.equalsAny;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import commands.channel.ChannelInfo;
import commands.channel.CreateChannel;
import commands.channel.DeleteChannel;

public class Help implements MessageCreateListener {

    public static String[] aliases = { prefix + "help", prefix + "assistance", prefix + "helpme" };

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (discardable(event, aliases)) {
            return;
        }

        if (equalsAny(aliases, event.getMessageContent(), true)) {

            if (event.getMessageContent().split(" ").length == 1) {

                EmbedBuilder embed = new EmbedBuilder();

                embed.setAuthor(event.getMessageAuthor());
                embed.setTitle("Help menu, page one!");
                embed.addInlineField("**User Info Command**", UserInfo.description());
                embed.addInlineField("**Ping Command**", Latency.description());

                embed.setTimestampToNow();
                event.getMessage().reply(embed);
                return;
            } else {
                try {

                    int page = Integer.parseInt(event.getMessageContent().split(" ")[1]);

                        if (page == 1) {

                            EmbedBuilder embed = new EmbedBuilder();

                            embed.setAuthor(event.getMessageAuthor());
                            embed.setTitle("Help menu, page one!");
                            embed.addInlineField("**User Info Command**", UserInfo.description());
                            embed.addInlineField("**Ping Command**", Latency.description());

                            embed.setTimestampToNow();
                            event.getMessage().reply(embed);
                            return;


                        } else if (page == 2) {

                            EmbedBuilder embed = new EmbedBuilder();

                            embed.setAuthor(event.getMessageAuthor());
                            embed.setTitle("Help menu, page two!");

                            embed.addInlineField("**Create Channel Command**", CreateChannel.description());
                            embed.addInlineField("**Delete Channel Command**", DeleteChannel.description());
                            embed.addInlineField("**Channel Information Command**", ChannelInfo.description());

                            embed.setTimestampToNow();
                            event.getMessage().reply(embed);
                            return;

                        } else if (page == 3) {

                            EmbedBuilder embed = new EmbedBuilder();

                            embed.setAuthor(event.getMessageAuthor());
                            embed.setTitle("Help menu, page two!");

                            embed.addInlineField("**Bot Info Command**", BotInfo.description());
                            embed.addInlineField("**Server Info Command**", ServerInfo.description());

                            embed.setTimestampToNow();
                            event.getMessage().reply(embed);
                            return;

                        } else {

                            EmbedBuilder embed = new EmbedBuilder();

                            embed.setAuthor(event.getMessageAuthor());
                            embed.setTitle("Help menu, page one!");
                            embed.addInlineField("**User Info Command**", UserInfo.description());
                            embed.addInlineField("**Ping Command**", Latency.description());

                            embed.setTimestampToNow();
                            event.getMessage().reply(embed);
                            return;

                        }



                } catch (Exception e) {

                    EmbedBuilder embed = new EmbedBuilder();

                    embed.setAuthor(event.getMessageAuthor());
                    embed.setTitle("Help menu, page one!");
                    embed.addInlineField("**User Info Command**", UserInfo.description());
                    embed.addInlineField("**Ping Command**", Latency.description());

                    embed.setTimestampToNow();
                    event.getMessage().reply(embed);
                    return;

                }
            }
        }

    }
}
