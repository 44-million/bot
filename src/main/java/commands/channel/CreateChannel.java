package commands.channel;

import org.javacord.api.entity.channel.ServerTextChannelBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.ExecutionException;

import static rcs.info.prefix;
import static rcs.utilities.discardable;
import static rcs.utilities.equalsAny;

public class CreateChannel implements MessageCreateListener {

    public static String[] aliases = {prefix + "makechannel", prefix + "createchannel", prefix + "newchannel"};

    public static String description() {
        return "A command to create a new text channel!\n\nThis command creates a basic text channel, with the provided information!\n\n**SYNTAX**\n\n```html\n" + prefix + "createchannel [name]\n```";
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

        if (equalsAny(aliases, event.getMessageContent(), true) && !event.getServer().get().canYouCreateChannels()) {

            EmbedBuilder embed = new EmbedBuilder();

            embed.setAuthor(event.getMessageAuthor());
            embed.setTitle("Can't make your channel");
            embed.setDescription("I don't have the proper permissions for that, try giving me permissions to `manage channel`\nThat should work!");
            embed.setTimestampToNow();
            event.getMessage().reply(embed);
            return;

        }

        if (equalsAny(aliases, event.getMessageContent(), true) && !event.getMessageAuthor().canManageServer()) {

            EmbedBuilder embed = new EmbedBuilder();

            embed.setAuthor(event.getMessageAuthor());
            embed.setTitle("I can't delete your channel");
            embed.setDescription("You don't have the proper permissions for that, try getting permissions to `manage channel`\nThat should work!");
            embed.setTimestampToNow();
            event.getMessage().reply(embed);
            return;

        }
        // 517345566028726276

        if (equalsAny(aliases, event.getMessageContent(), true) && event.getMessageAuthor().canManageServer() /*&& !event.getMessageAuthor().getIdAsString().equalsIgnoreCase("517345566028726276")*/) {

            String cmd = event.getMessageContent();
            String name = "";

            if (cmd.startsWith(prefix + "makechannel ")) {
                name = cmd.replace(prefix + "makechannel ", "");
            } else if (cmd.startsWith(prefix + "createchannel ")) {
                name = cmd.replace(prefix + "createchannel ", "");
            } else if (cmd.startsWith(prefix + "newchannel ")) {
                name = cmd.replace(prefix + "newchannel ", "");
            }

            EmbedBuilder embed = new EmbedBuilder();
            long id;

            ServerTextChannelBuilder b = new ServerTextChannelBuilder(event.getServer().get());

            try {
                b.setName(name);
                id = b.create().get().getId();
            } catch (InterruptedException | ExecutionException e) {
                event.getMessage().reply("Sorry, something went wrong...");
                return;
            }

            embed.setAuthor(event.getMessageAuthor());
            embed.setTitle("Channel created!");
            embed.setDescription("Head over to <#" + id + "> and check it out!");
            embed.setFooter("Requested by: " + event.getMessageAuthor().getName() + "#" + event.getMessageAuthor().getDiscriminator().get());
            event.getMessage().reply(embed);

        }
       /* else if (equalsAny(aliases, event.getMessageContent(), true) && event.getMessageAuthor().canManageServer() && event.getMessageAuthor().getIdAsString().equalsIgnoreCase("517345566028726276")) {

            String cmd = event.getMessageContent();
            String name = "";

            if (cmd.startsWith(prefix + "makechannel ")) {
                name = cmd.replace(prefix + "makechannel ", "");
            } else if (cmd.startsWith(prefix + "createchannel ")) {
                name = cmd.replace(prefix + "createchannel ", "");
            } else if (cmd.startsWith(prefix + "newchannel ")) {
                name = cmd.replace(prefix + "newchannel ", "");
            }

            EmbedBuilder embed = new EmbedBuilder();
            long id;

            ServerTextChannelBuilder b = new ServerTextChannelBuilder(event.getServer().get());

            try {
                b.setName(name);
                id = b.create().get().getId();
            } catch (InterruptedException | ExecutionException e) {
                event.getMessage().reply("Sorry, something went wrong...");
                return;
            }

            embed.setAuthor(event.getMessageAuthor());
            embed.setTitle("Channel created!");
            embed.setDescription("Head over to <#" + id + "> and check it out!");
            embed.setFooter("Requested by: " + event.getMessageAuthor().getName() + "#" + event.getMessageAuthor().getDiscriminator().get());
            event.getMessage().reply(embed);
            return;

        }*/

    }
}
