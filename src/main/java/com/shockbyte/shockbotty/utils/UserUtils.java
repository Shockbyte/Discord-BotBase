package com.shockbyte.shockbotty.utils;

import com.shockbyte.shockbotty.Bot;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class UserUtils {

    // Steps to find a user:
    // 1. Check if it's a long
    // 2. Check guild
    // 3. Check everywhere

    private static Bot instance;

    public static User getUser(@Nonnull String s) {
        return getUser(s, null);
    }

    @Nullable
    public static User getUser(@Nonnull String s, @Nullable Guild g) {
        long id = -1;
        try {
            id = Long.parseLong(s.replaceAll("[<@!>]", "")); // Remove any chars which may be there if it's a mention
        } catch (NumberFormatException ignored) {
        }

        // if it is a number, this could still be a username but we should look for ID first.
        if (id != -1) {
            if (g != null) {
                Member member = g.getMemberCache().getElementById(id);
                if (member != null) return member.getUser();
            } else {
                User user = bot().getClient().getUserCache().getElementById(id);
                if (user != null) return user;
            }
        }

        if (g != null) {
            // Look for members with this username, if there's only one accept it.
            List<Member> members = g.getMemberCache().getElementsByUsername(s);
            if (members.size() == 1)
                return members.get(0).getUser();

            // Look for members with this nickname, if there's only one accept it.
            members = g.getMemberCache().getElementsByNickname(s);
            if (members.size() == 1) {
                return members.get(0).getUser();
            }

            // If the string contains a "#" usually used for a "tag" (Username#discrim) then search for an exact match.
            if (s.contains("#")) {
                for (Member m : g.getMemberCache())
                    if ((m.getUser().getName() + '#' + m.getUser().getDiscriminator()).equalsIgnoreCase(s))
                        return m.getUser();
            }
        }

        // If the guild is null or we can't find them, search all users by name
        List<User> users = bot().getClient().getUserCache().getElementsByName(s);
        if (users.size() == 1)
            return users.get(0);

        // If the guild is null or we can't find them, search all users by tag
        if (s.contains("#")) {
            for (User u : bot().getClient().getUserCache())
                if ((u.getName() + '#' + u.getDiscriminator()).equalsIgnoreCase(s))
                    return u;
        }

        // We'll get 'em next time!
        return null;
    }

    public static String getTag(User user) {
        return user.getName() + "#" + user.getDiscriminator();
    }

    public static String getIdentifier(User user) {
        return getTag(user) + " (" + user.getIdLong() + ")";
    }

    public static boolean hasRole(Member member, long roleId) {
        for (Role role : member.getRoles())
            if (role.getIdLong() == roleId)
                return true;

        return false;
    }

    private static Bot bot() {
        return instance == null ? instance = Bot.getInstance() : instance;
    }
}
