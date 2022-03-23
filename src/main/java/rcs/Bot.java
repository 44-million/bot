package rcs;

import commands.*;
import commands.channel.ChannelInfo;
import commands.channel.CreateChannel;
import commands.channel.DeleteChannel;
import main.App;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.user.UserStatus;
import org.javacord.api.listener.message.MessageCreateListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static rcs.info.prefix;
import static rcs.info.token;
import static rcs.utilities.reset;
import static rcs.utilities.yellow;

@SuppressWarnings("all")
public class Bot {

    public static DiscordApi aois;
    public static ArrayList<MessageCreateListener> commands = new ArrayList<>();
    private final String s;
    private final long epoch;


    public Bot() {
        this.s = "Aois";
        this.epoch = Instant.now().getEpochSecond();
    }

    public DiscordApi get() {
        return aois;
    }

    public String getUptime() {
        return "<t:" + epoch + ":R>";
    }

    public Bot init() {

        Bot.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Bot.aois.updateStatus(UserStatus.DO_NOT_DISTURB);
        Bot.aois.updateActivity(ActivityType.CUSTOM, prefix + "help");
        System.out.println(Bot.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Bot.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Bot init(String status) {

        Bot.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Bot.aois.updateStatus(UserStatus.DO_NOT_DISTURB);
        Bot.aois.updateActivity(ActivityType.CUSTOM, status);
        System.out.println(Bot.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Bot.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Bot init(ActivityType at, String status) {

        Bot.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Bot.aois.updateStatus(UserStatus.DO_NOT_DISTURB);
        Bot.aois.updateActivity(at, status);
        System.out.println(Bot.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Bot.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Bot init(ActivityType at, String status, UserStatus u) {

        Bot.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Bot.aois.updateStatus(u);
        Bot.aois.updateActivity(at, status);
        System.out.println(Bot.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Bot.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Bot init(String status, UserStatus u) {

        try {
            Bot.aois = new DiscordApiBuilder()
                    .setToken(token)
                    .login().join();

            Bot.aois.updateStatus(u);
            Bot.aois.updateActivity(ActivityType.CUSTOM, status);
            System.out.println(Bot.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Bot.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

            App.uptime = getUptime();
            App.bot = this;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return this;
    }

    public Bot init(UserStatus u) {

        Bot.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Bot.aois.updateStatus(u);
        Bot.aois.updateActivity(ActivityType.CUSTOM, prefix + "help");
        System.out.println(Bot.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Bot.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Bot upsertCommands() {

        Bot.commands.add(new CreateChannel());
        Bot.commands.add(new ChannelInfo());
        Bot.commands.add(new Help());
        Bot.commands.add(new Latency());
        Bot.commands.add(new UserInfo());
        Bot.commands.add(new DeleteChannel());
        Bot.commands.add(new Command());
        Bot.commands.add(new BotInfo());
        Bot.commands.add(new ServerInfo());

        for (MessageCreateListener listener : Bot.commands) {

            Bot.aois.addListener(listener);

        }
        return this;

    }


    public Bot upsertCommands(List<MessageCreateListener> ignore) {

        if (!ignore.contains(new CreateChannel())) {
            Bot.commands.add(new CreateChannel());
        }
        Bot.commands.add(new ChannelInfo());
        if (!ignore.contains(new ChannelInfo())) {
            Bot.commands.add(new ChannelInfo());
        }
        Bot.commands.add(new Help());
        Bot.commands.add(new Latency());
        Bot.commands.add(new UserInfo());
        Bot.commands.add(new DeleteChannel());
        Bot.commands.add(new Command());

        for (MessageCreateListener listener : Bot.commands) {

            Bot.aois.addListener(listener);

        }

        return this;
    }

}
