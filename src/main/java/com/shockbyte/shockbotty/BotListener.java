package com.shockbyte.shockbotty;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.util.regex.Pattern;

public class BotListener implements EventListener {

    private final Pattern spacePattern = Pattern.compile(" +");

    public void onEvent(Event event) {
        if (event instanceof GuildMessageReceivedEvent)
            onGuildMessage((GuildMessageReceivedEvent) event);
        else if (event instanceof ReadyEvent)
            onReady((ReadyEvent) event);
    }

    private void onGuildMessage(GuildMessageReceivedEvent e) {
        // Message without the prefix
        if (!e.getMessage().getContentDisplay().startsWith(Bot.getInstance().getPrefix()))
            return;
        String message = e.getMessage().getContentRaw().substring(Bot.getInstance().getPrefix().length());
        String command = message;
        String[] args = new String[0];

        if (command.contains(" ")) {
            args = spacePattern.split(message.substring(message.indexOf(" ")+1));
            command = command.substring(command.indexOf(" ")-1);
        }

        for (Command cmd : Bot.getInstance().getCommands()) {
            if (cmd.getCommand().equalsIgnoreCase(command)) {
                cmd.onCommand(e.getMember().getUser(), e.getMember(), e.getChannel(), e.getGuild(), args);
                break;
            }
            for (String alias : cmd.getAliases()) {
                if (alias.equalsIgnoreCase(command)) {
                    cmd.onCommand(e.getMember().getUser(), e.getMember(), e.getChannel(), e.getGuild(), args);
                    break;
                }
            }
        }
    }

    private void onReady(ReadyEvent e) {
        Bot.getInstance().run();
    }
}