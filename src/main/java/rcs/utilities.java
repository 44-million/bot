package rcs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import main.App;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class utilities {

    public static boolean equalsAny(String[] aliases, String s, boolean ignoreCase) {

        for (String alias : aliases) {

            if (ignoreCase) {

                if (s.toLowerCase().startsWith(alias)) {
                    return true;
                }

            } else {

                if (s.startsWith(alias)) {
                    return true;
                }

            }

        }
        return false;

    }

    public static boolean discardable(MessageCreateEvent event, String[] aliases) {

        if (event.getMessageAuthor().isBotUser() || !equalsAny(aliases, event.getMessageContent(), true)) {
            return true;
        }

        return false;
    }

    public static boolean equalsAny(String[] aliases, String reg) {

        for (String alias : aliases) {

            if (alias.equalsIgnoreCase(reg)) {
                return true;
            }

        }
        return false;
    }

    public static final String reset = "\u001B[0m";
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String white = "\u001B[37m";

    public static Color getRndColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r, g, b);
    }

    public static Config getConfig() {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        Config config = null;
        try {
            config = mapper.readValue(new File("src/main/resources/config.yml"), Config.class);
        } catch (IOException e) {
            System.out.println("There was an error reading the input file!");
        }
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return config;
    }

    public static String getCreationTime(User user) {

        String hour = DateTimeFormatter.ofPattern("HH:mm a").format(user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")));

        int minute = user.getCreationTimestamp().atZone(ZoneId.of("America/Denver"))
                .toLocalTime()
                .getMinute();

//        return hour;

        return "<t:" + user.getCreationTimestamp().getEpochSecond() + ":t>";

    }

    public static String getCreationTimeServer(Server server) {

        String hour = DateTimeFormatter.ofPattern("HH:mm a").format(server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")));

        int minute = server.getCreationTimestamp().atZone(ZoneId.of("America/Denver"))
                .toLocalTime()
                .getMinute();

//        return hour;

        return "<t:" + server.getCreationTimestamp().getEpochSecond() + ":t>";

    }

    public static String getCreationDateServer(Server server) {


        String longAgo = "";

        String fullDate = server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getDayOfWeek().name() + " " + server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getMonth().name() + " " + server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getDayOfMonth() + " " + server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getYear();
        fullDate = fullDate.toLowerCase()
                .replaceAll("monday", "Monday")
                .replaceAll("tuesday", "Tuesday")
                .replaceAll("wednesday", "Wednesday")
                .replaceAll("thursday", "Thursday")
                .replaceAll("friday", "Friday")
                .replaceAll("saturday", "Saturday")
                .replaceAll("sunday", "Sunday")
                .replaceAll("january", "January")
                .replaceAll("februrary", "February")
                .replaceAll("march", "March")
                .replaceAll("april", "April")
                .replaceAll("may", "May")
                .replaceAll("june", "June")
                .replaceAll("july", "July")
                .replaceAll("august", "August")
                .replaceAll("september", "September")
                .replaceAll("october", "October")
                .replaceAll("november", "November")
                .replaceAll("december", "December");

        if (server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).isAfter(Instant.now().atZone(ZoneId.of("America/Denver")))) {

            var userCreationTime = server.getCreationTimestamp().atZone(ZoneId.of("America/Denver"));

            TemporalAccessor middleMan = LocalTime.now(ZoneId.of("America/Denver"));

            TemporalAmount ta1 = (TemporalAmount) middleMan;

            userCreationTime = userCreationTime.minus(ta1);

            longAgo += DateTimeFormatter.ofPattern("MM/dd/yyyy : HH:mm").format(userCreationTime);

        }

        long epoch = server.getCreationTimestamp().getEpochSecond();
        String main = "<t:" + epoch + ":R>";

//        return fullDate;
        return main;

    }

    public static String getCreationDate(User user) {


        String longAgo = "";

        String fullDate = user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getDayOfWeek().name() + " " + user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getMonth().name() + " " + user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getDayOfMonth() + " " + user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getYear();
        fullDate = fullDate.toLowerCase()
                .replaceAll("monday", "Monday")
                .replaceAll("tuesday", "Tuesday")
                .replaceAll("wednesday", "Wednesday")
                .replaceAll("thursday", "Thursday")
                .replaceAll("friday", "Friday")
                .replaceAll("saturday", "Saturday")
                .replaceAll("sunday", "Sunday")
                .replaceAll("january", "January")
                .replaceAll("februrary", "February")
                .replaceAll("march", "March")
                .replaceAll("april", "April")
                .replaceAll("may", "May")
                .replaceAll("june", "June")
                .replaceAll("july", "July")
                .replaceAll("august", "August")
                .replaceAll("september", "September")
                .replaceAll("october", "October")
                .replaceAll("november", "November")
                .replaceAll("december", "December");

        if (user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).isAfter(Instant.now().atZone(ZoneId.of("America/Denver")))) {

            var userCreationTime = user.getCreationTimestamp().atZone(ZoneId.of("America/Denver"));

            TemporalAccessor middleMan = LocalTime.now(ZoneId.of("America/Denver"));

            TemporalAmount ta1 = (TemporalAmount) middleMan;

            userCreationTime = userCreationTime.minus(ta1);

            longAgo += DateTimeFormatter.ofPattern("MM/dd/yyyy : HH:mm").format(userCreationTime);

        }

        long epoch = user.getCreationTimestamp().getEpochSecond();
        String main = "<t:" + epoch + ":R>";

//        return fullDate;
        return main;

    }

    public static String getExactCreationTime(User user) {

        String hour = DateTimeFormatter.ofPattern("HH:mm a").format(user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")));

        int minute = user.getCreationTimestamp().atZone(ZoneId.of("America/Denver"))
                .toLocalTime()
                .getMinute();

        return hour;

    }

    public static String getExactCreationTimeServer(Server server) {

        String hour = DateTimeFormatter.ofPattern("HH:mm a").format(server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")));

        int minute = server.getCreationTimestamp().atZone(ZoneId.of("America/Denver"))
                .toLocalTime()
                .getMinute();

        return hour;

    }

    public static String getExactCreationDateServer(Server server) {


        String longAgo = "";

        String fullDate = server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getDayOfWeek().name() + " " + server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getMonth().name() + " " + server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getDayOfMonth() + " " + server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getYear();
        fullDate = fullDate.toLowerCase()
                .replaceAll("monday", "Monday")
                .replaceAll("tuesday", "Tuesday")
                .replaceAll("wednesday", "Wednesday")
                .replaceAll("thursday", "Thursday")
                .replaceAll("friday", "Friday")
                .replaceAll("saturday", "Saturday")
                .replaceAll("sunday", "Sunday")
                .replaceAll("january", "January")
                .replaceAll("februrary", "February")
                .replaceAll("march", "March")
                .replaceAll("april", "April")
                .replaceAll("may", "May")
                .replaceAll("june", "June")
                .replaceAll("july", "July")
                .replaceAll("august", "August")
                .replaceAll("september", "September")
                .replaceAll("october", "October")
                .replaceAll("november", "November")
                .replaceAll("december", "December");

        if (server.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).isAfter(Instant.now().atZone(ZoneId.of("America/Denver")))) {

            var userCreationTime = server.getCreationTimestamp().atZone(ZoneId.of("America/Denver"));

            TemporalAccessor middleMan = LocalTime.now(ZoneId.of("America/Denver"));

            TemporalAmount ta1 = (TemporalAmount) middleMan;

            userCreationTime = userCreationTime.minus(ta1);

            longAgo += DateTimeFormatter.ofPattern("MM/dd/yyyy : HH:mm").format(userCreationTime);

        }

        long epoch = server.getCreationTimestamp().getEpochSecond();
        String main = "<t:" + epoch + ":R>";

        return fullDate;

    }

    public static String getExactCreationDate(User user) {


        String longAgo = "";

        String fullDate = user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getDayOfWeek().name() + " " + user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getMonth().name() + " " + user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getDayOfMonth() + " " + user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).getYear();
        fullDate = fullDate.toLowerCase()
                .replaceAll("monday", "Monday")
                .replaceAll("tuesday", "Tuesday")
                .replaceAll("wednesday", "Wednesday")
                .replaceAll("thursday", "Thursday")
                .replaceAll("friday", "Friday")
                .replaceAll("saturday", "Saturday")
                .replaceAll("sunday", "Sunday")
                .replaceAll("january", "January")
                .replaceAll("februrary", "February")
                .replaceAll("march", "March")
                .replaceAll("april", "April")
                .replaceAll("may", "May")
                .replaceAll("june", "June")
                .replaceAll("july", "July")
                .replaceAll("august", "August")
                .replaceAll("september", "September")
                .replaceAll("october", "October")
                .replaceAll("november", "November")
                .replaceAll("december", "December");

        if (user.getCreationTimestamp().atZone(ZoneId.of("America/Denver")).isAfter(Instant.now().atZone(ZoneId.of("America/Denver")))) {

            var userCreationTime = user.getCreationTimestamp().atZone(ZoneId.of("America/Denver"));

            TemporalAccessor middleMan = LocalTime.now(ZoneId.of("America/Denver"));

            TemporalAmount ta1 = (TemporalAmount) middleMan;

            userCreationTime = userCreationTime.minus(ta1);

            longAgo += DateTimeFormatter.ofPattern("MM/dd/yyyy : HH:mm").format(userCreationTime);

        }

        long epoch = user.getCreationTimestamp().getEpochSecond();
        String main = "<t:" + epoch + ":R>";

        return fullDate;

    }

    public static String getPermissions(User user, Server server) {

        StringBuilder s = new StringBuilder();

        for (PermissionType permission : server.getAllowedPermissions(user)) {

            s.append(permission.toString().toLowerCase()).append(", ");

        }

        return s.toString();

    }

    public static String status(User user) {

        if (user.getActivities().isEmpty()) {
            return "This user has no status... for now";
        } else {
            return "This user's status is: **" + user.getActivities().toArray()[0].toString() + "**";
        }

    }

    public static String checkBot(User user) {

        if (user.isBot()) {

            return "This user is a bot!";
        } else {
            return "This user is **not** a bot!";
        }


    }

    public static String getRoles(User user, Server server) {

        StringBuilder s = new StringBuilder();

        if (user.getRoles(server).isEmpty()) {
            s.append("This user has no roles!");
            return s.toString();
        }

        for (Role role : user.getRoles(server)) {

                s.append(role.getMentionTag()).append("\n");

        }
        return s.toString();

    }

    public static String getServerRoles(Server server) {

        StringBuilder s = new StringBuilder();

        if (server.getRoles().isEmpty()) {
            s.append("This server has no roles!");
            return s.toString();
        }

        for (Role role : server.getRoles()) {

            s.append(role.getMentionTag()).append(" ");

        }
        return s.toString();

    }

    public static String voice(Server server, User user) {

        if (server.getConnectedVoiceChannel(user).isEmpty()) {
            return "This user is **not** in a voice channel right now!";
        } else {
            return "This user is connected to: <#" + server.getConnectedVoiceChannel(user).get().getId() + "> right now...";
        }


    }

    public static String nickname(User user, Server server) {

        if (server.getNickname(user).isEmpty()) {
            return "This user doesn't have a nickname in this server!";
        } else {
            return "This user's nickname is **" + user.getNickname(server) + "**";
        }

    }

    public static String serverIcon(Server server) {

        if (server.getIcon().isPresent()) {
            return server.getIcon().get().getUrl().toString();
        } else {
            return "https://www.discord.com/assets/1f0bfc0865d324c2587920a7d80c609b.png";
        }

    }

    public static String colors(User user, Server server) {

        if (user.getRoleColor(server).isEmpty()) {
            return "This user has no colored roles...";
        } else {
            return "This user's color (in RGB):\n**" + user.getRoleColor(server).get().getRed()
                    + "**\n**" + user.getRoleColor(server).get().getGreen()
                    + "**\n**" + user.getRoleColor(server).get().getBlue() + "**";
        }
    }

    public static String nsfw(Channel channel) {

        if (channel.asServerTextChannel().get().isNsfw()) {
            return "This channel is **NSFW**... stay outta there kids";
        } else {
            return "This channel isn't **NSFW**, for now...";
        }

    }

    public static String perms(Channel channel, MessageAuthor user) {
        String s = "";

        if (channel.asServerTextChannel().get().canSee(user.asUser().get())) {
            s += "You **can** see this channel!\n\n";
        } else if (!channel.asServerTextChannel().get().canSee(user.asUser().get())) {
            s += "You **cannot** see this channel...\n\n";
        }

        if (channel.asServerTextChannel().get().canAttachFiles(user.asUser().get())) {
            s += "You **can** send files in this channel\n\n";
        } else if (!channel.asServerTextChannel().get().canAttachFiles(user.asUser().get())) {
            s += "You **cannot** send files in this channel\n\n";
        }

        if (channel.asServerTextChannel().get().canEmbedLinks(user.asUser().get())) {
            s += "You **can** *embed* links in this channel!\n\n";
        } else if (!channel.asServerTextChannel().get().canEmbedLinks(user.asUser().get())) {
            s += "You **cannot** currently *embed* links in this channel...\n\n";
        }

        if (channel.asServerTextChannel().get().canReadMessageHistory(user.asUser().get())) {
            s += "You **can** read the message history of this channel!\n\n";
        } else if (!channel.asServerTextChannel().get().canReadMessageHistory(user.asUser().get())) {
            s += "You **cannot** read the message history of this channel...\n\n";
        }

        if (channel.asServerTextChannel().get().canWrite(user.asUser().get())) {
            s += "You **can** send messages to this channel!\n\n";
        } else if (!channel.asServerTextChannel().get().canWrite(user.asUser().get())) {
            s += "You **cannot** send messages to this channel...\n\n";
        }

        if (channel.asServerTextChannel().get().canAddNewReactions(user.asUser().get())) {
            s += "You **can** add new reactions to stuff in this channel!\n\n";
        } else if (!channel.asServerTextChannel().get().canAddNewReactions(user.asUser().get())) {
            s += "You **cannot** currently add new reactions to messages in this channel...\n\n";
        }

        return s;

    }

    public static String webhooks(Channel channel) {

        int webhookCount;

        try {
            webhookCount = channel.asServerTextChannel().get().getWebhooks().get().size();
        } catch (InterruptedException | ExecutionException e) {
            webhookCount = 0;
        }

        if (webhookCount == 1) {
            return "This channel has 1 *webhook* in it...";
        } else if (webhookCount == 0) {
            return "This channel has no webhooks :face_with_raised_eyebrow:";
        } else {
            return "This channel has " + webhookCount + " *webhooks* in it...";
        }

    }
}
