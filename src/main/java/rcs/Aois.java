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
public class Aois {

    public static DiscordApi aois;
    public static ArrayList<MessageCreateListener> commands = new ArrayList<>();
    private final String s;
    private final long epoch;


    public Aois() {
        this.s = "Aois";
        this.epoch = Instant.now().getEpochSecond();
    }

    public DiscordApi get() {
        return aois;
    }

    public String getUptime() {
        return "<t:" + epoch + ":R>";
    }

    public Aois init() {

        Aois.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Aois.aois.updateStatus(UserStatus.DO_NOT_DISTURB);
        Aois.aois.updateActivity(ActivityType.CUSTOM, prefix + "help");
        System.out.println(Aois.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Aois.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Aois init(String status) {

        Aois.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Aois.aois.updateStatus(UserStatus.DO_NOT_DISTURB);
        Aois.aois.updateActivity(ActivityType.CUSTOM, status);
        System.out.println(Aois.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Aois.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Aois init(ActivityType at, String status) {

        Aois.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Aois.aois.updateStatus(UserStatus.DO_NOT_DISTURB);
        Aois.aois.updateActivity(at, status);
        System.out.println(Aois.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Aois.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Aois init(ActivityType at, String status, UserStatus u) {

        Aois.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Aois.aois.updateStatus(u);
        Aois.aois.updateActivity(at, status);
        System.out.println(Aois.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Aois.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Aois init(String status, UserStatus u) {

        try {
            Aois.aois = new DiscordApiBuilder()
                    .setToken(token)
                    .login().join();

            Aois.aois.updateStatus(u);
            Aois.aois.updateActivity(ActivityType.CUSTOM, status);
            System.out.println(Aois.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Aois.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

            App.uptime = getUptime();
            App.bot = this;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return this;
    }

    public Aois init(UserStatus u) {

        Aois.aois = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        Aois.aois.updateStatus(u);
        Aois.aois.updateActivity(ActivityType.CUSTOM, prefix + "help");
        System.out.println(Aois.aois.getYourself().getName() + " is done loading!\n\nYou can invite the bot with: " + yellow + Aois.aois.createBotInvite(Permissions.fromBitmask(8)) + reset + ".");

        App.uptime = getUptime();
        App.bot = this;

        return this;
    }

    public Aois upsertCommands() {

        Aois.commands.add(new CreateChannel());
        Aois.commands.add(new ChannelInfo());
        Aois.commands.add(new Help());
        Aois.commands.add(new Latency());
        Aois.commands.add(new UserInfo());
        Aois.commands.add(new DeleteChannel());
        Aois.commands.add(new Command());
        Aois.commands.add(new BotInfo());
        Aois.commands.add(new ServerInfo());

        for (MessageCreateListener listener : Aois.commands) {

            Aois.aois.addListener(listener);

        }
        return this;

    }


    public Aois upsertCommands(List<MessageCreateListener> ignore) {

        if (!ignore.contains(new CreateChannel())) {
            Aois.commands.add(new CreateChannel());
        }
        Aois.commands.add(new ChannelInfo());
        if (!ignore.contains(new ChannelInfo())) {
            Aois.commands.add(new ChannelInfo());
        }
        Aois.commands.add(new Help());
        Aois.commands.add(new Latency());
        Aois.commands.add(new UserInfo());
        Aois.commands.add(new DeleteChannel());
        Aois.commands.add(new Command());

        for (MessageCreateListener listener : Aois.commands) {

            Aois.aois.addListener(listener);

        }

        return this;
    }

}
