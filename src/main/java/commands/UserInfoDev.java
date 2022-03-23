package commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static rcs.info.prefix;

public class UserInfoDev implements MessageCreateListener {

    public static String[] aliases = {prefix + "devinfo", prefix + "devui", prefix + "devwhois", prefix + "devuserinfo"};

    public static String description() {
        return "The developer equivalent of the `" + prefix + "ui` command. Has more technical information than the regular command.";
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

    }
}
