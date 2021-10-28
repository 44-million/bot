package rcs;

import org.javacord.api.listener.message.MessageCreateListener;

public class Cmd {

    private String name;
    private String description;
    private MessageCreateListener listener;

    public Cmd(String name) {
        this.name = name;
    }

    public Cmd(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cmd(String name, String description, MessageCreateListener lis) {
       this.name = name;
       this.description = description;
       this.listener = lis;
    }

}
