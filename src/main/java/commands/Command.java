package commands;

import commands.channel.CreateChannel;
import commands.channel.DeleteChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import commands.channel.ChannelInfo;

import static rcs.info.*;
import static rcs.utilities.*;

public class Command implements MessageCreateListener {

    public static String[] aliases = { prefix + "command", prefix + "cmd", prefix + "helpcmd", prefix + "helpcommand", prefix + "infoabout" };

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        
        
        if (discardable(event, aliases)) {
            return;
        }

        if (equalsAny(aliases, event.getMessageContent(), true)) {

            String[] command = event.getMessageContent().split(" ");

            String two = command[1].replace("?", "");
            // just so i dont forget, this is to remove the prefix and `command` segment, so we just have the requested command.
            String main = prefix + two;
            System.out.println(main);


            if (equalsAny(ChannelInfo.aliases, main, true)) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Info about " + main);
                embed.setFooter("Hope this helped!");
                embed.setTimestampToNow();
                embed.setAuthor(event.getMessageAuthor());

                embed.addInlineField("Basic Usage", ChannelInfo.description());
                embed.addInlineField("Aliases (these can be used in lieu of `" + main + "`)\n** **\n** **\n** **\n", ChannelInfo.getAliases());
                embed.addField("Other information", "This command returns lots of information, and thus can be laggy.\n\nFor channels with an extremely high message count, the request may time out.");


                event.getMessage().reply(embed);

            } else if (equalsAny(CreateChannel.aliases, main, true)) {

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Info about: " + main);
                embed.setFooter("Hope this helped!");
                embed.setTimestampToNow();
                embed.setAuthor(event.getMessageAuthor());

                embed.addInlineField("Basic Usage", CreateChannel.description());
                embed.addInlineField("Aliases (these can be used in lieu of `" + main + "`)\n** **\n** **\n** **\n", CreateChannel.getAliases());
                embed.addField("Other information", "This command just creates a text channel");


                event.getMessage().reply(embed);

            } else if (equalsAny(DeleteChannel.aliases, main, true)) {

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Info about: " + main);
                embed.setFooter("Hope this helped!");
                embed.setTimestampToNow();
                embed.setAuthor(event.getMessageAuthor());

                embed.addInlineField("Basic Usage", DeleteChannel.description());
                embed.addInlineField("Aliases (these can be used in lieu of `" + main + "`)\n** **\n** **\n** **\n", DeleteChannel.getAliases());
                embed.addField("Other information", "This command just deletes a text channel");


                event.getMessage().reply(embed);

            }  else if (equalsAny(ServerInfo.aliases, main, true)) {

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Info about: " + main);
                embed.setFooter("Hope this helped!");
                embed.setTimestampToNow();
                embed.setAuthor(event.getMessageAuthor());

                embed.addInlineField("Basic Usage", ServerInfo.description());
                embed.addInlineField("Aliases (these can be used in lieu of `" + main + "`)\n** **\n** **\n** **\n", DeleteChannel.getAliases());
                embed.addField("Other information", "This command gets a large amount of information about the server");


                event.getMessage().reply(embed);

            } else if (equalsAny(BotInfo.aliases, main, true)) {

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Info about: " + main);
                embed.setFooter("Hope this helped!");
                embed.setTimestampToNow();
                embed.setAuthor(event.getMessageAuthor());

                embed.addInlineField("Basic Usage", BotInfo.description());
                embed.addInlineField("Aliases (these can be used in lieu of `" + main + "`)\n** **\n** **\n** **\n", DeleteChannel.getAliases());
                embed.addField("Other information", "This command is very basic");


                event.getMessage().reply(embed);

            }


        }

        
    }
    
}