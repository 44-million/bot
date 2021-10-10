package main;

import main.SQL.create;
import org.javacord.api.entity.user.UserStatus;
import rcs.Aois;

public class App {

    public static String uptime;
    public static Aois bot;

    public static void main(String[] args) {

        Aois aois = new Aois()
                .init(UserStatus.INVISIBLE)
                .upsertCommands();

        try {
            new create();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
