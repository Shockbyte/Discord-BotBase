package com.shockbyte.shockbotty.context;

import com.shockbyte.shockbotty.Bot;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class GuildContext implements Context {

    private final long guild;
    private final long channel;
    private final long user;

    public GuildContext(long guild, long channel, long user) {
        this.guild = guild;
        this.channel = channel;
        this.user = user;
    }

    public Guild getGuild() {
        return Bot.getInstance().getClient().getGuildById(guild);
    }

    public TextChannel getChannel() {
        return getGuild().getTextChannelById(channel);
    }

    public Member getMember() {
        return getGuild().getMemberById(user);
    }

    public User getUser() {
        return getGuild().getMemberById(user).getUser();
    }

    public void sendMessage(String s) {
        getChannel().sendMessage(s).queue();
    }

    public void sendMessage(MessageEmbed embed) {
        getChannel().sendMessage(embed).queue();
    }
}
