package main;

import org.javacord.api.entity.user.UserStatus;
import rcs.Aois;

public class App {

    public static String uptime;
    public static Aois bot;

    public static void main(String[] args) {
        Aois aois = new Aois();
        aois.init("I'm back", UserStatus.DO_NOT_DISTURB);
        aois.upsertCommands();
    }

}
