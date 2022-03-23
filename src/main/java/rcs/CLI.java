package rcs;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class CLI {

    public CLI() {}

    public void init() {

        Scanner sc = new Scanner(System.in);

        for (;;) {

            String s = sc.nextLine();
            String command[] = s.split(" ");
            String subCommand = command[1];

            if (command[0].equals("list")) {

                if (subCommand.equals("channels")) {
                    String id = command[2];

                    Optional<Server> ser = Bot.aois.getServerById(id);

                    try {
                        ser.get().getChannels().forEach((key) -> {
                            System.out.println(key.getName());
                        });
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                } else if (subCommand.equals("members")) {
                    String id = command[2];

                    Optional<Server> ser = Bot.aois.getServerById(id);

                    try {
                        ser.get().getMembers().forEach((key) -> System.out.println(key.getName()));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

            }

        }

    }

}
