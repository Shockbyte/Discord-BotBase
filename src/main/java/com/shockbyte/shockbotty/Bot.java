package com.shockbyte.shockbotty;

import com.shockbyte.shockbotty.mysql.DatabaseManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Bot {

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    private static Bot instance;
    private String prefix;
    private JDA client;

    private Set<Command> commands = new HashSet<>();

    public JDA init(String token, String botPrefix) {
        return init(new JDABuilder(AccountType.BOT).setToken(token), botPrefix);
    }

    public JDA init(JDABuilder builder, String botPrefix) {
        instance = this;
        this.prefix = botPrefix;
        try {
            this.client = builder.addEventListener(new BotListener()).build();
        } catch (LoginException e) {
            logger.error("There was an issue logging in: {}", e.getMessage(), e);
        }

        return this.client;
    }

    public abstract void run();

    public void setupMySQL(String host, int port, String username, String password) {
        DatabaseManager.init(host, port, username, password);
    }

    public void registerCommand(Command command) {
        this.commands.add(command);
    }

    public Set<Command> getCommands() {
        return commands;
    }

    public static Bot getInstance() {
        return instance;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
