package com.shockbyte.shockbotty.utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;

import java.awt.Color;

public class MessageUtils {

    public static void sendErrorEmbed(String message, TextChannel channel) {
        channel.sendMessage(new EmbedBuilder().setColor(Color.red)
                .setDescription(message)
                .build()
        ).queue();
    }
}
