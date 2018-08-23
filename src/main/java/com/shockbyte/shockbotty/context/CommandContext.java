package com.shockbyte.shockbotty.context;

import com.shockbyte.shockbotty.Utils;
import net.dv8tion.jda.core.entities.Role;

public class CommandContext extends GuildContext {

    private final String command;
    private final String[] args;

    public CommandContext(long guild, long channel, long user, String command, String[] args) {
        super(guild, channel, user);
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public String getMessage() {
        return Utils.getMessage(args);
    }

    public String getMessage(int min) {
        return Utils.getMessage(args, min);
    }

    public String getMessage(int min, int max) {
        return Utils.getMessage(args, min, max);
    }

    public boolean hasRole(long roleId) {
        for (Role r : getMember().getRoles())
            if (r.getIdLong() == roleId)
                return true;

        return false;
    }
}
