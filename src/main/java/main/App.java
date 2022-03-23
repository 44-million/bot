package main;

import org.javacord.api.entity.user.UserStatus;
import rcs.Bot;
import rcs.CLI;

public class App {

    public static String uptime;
    public static Bot bot;

    public static void main(String[] args) {
        Bot aois = new Bot();
        aois.init("I'm back", UserStatus.DO_NOT_DISTURB);
        aois.upsertCommands();
        new CLI().init();
    }

}
