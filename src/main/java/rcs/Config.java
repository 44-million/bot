package rcs;

public class Config {

    private String token;
    private long id;
    private String prefix;
    private boolean allowCustomPrefix;

    public Config() {
    }

    public boolean isAllowCustomPrefix() {
        return allowCustomPrefix;
    }

    public String getToken() {
        return token;
    }

    public long getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }
}
