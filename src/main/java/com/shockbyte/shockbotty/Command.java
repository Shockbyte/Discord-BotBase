package com.shockbyte.shockbotty;

import com.shockbyte.shockbotty.context.CommandContext;

public interface Command {

    // TODO: Decide if I want to go Flare2 route and have a CommandContext

    void onCommand(CommandContext context);

    String getCommand();

    default String[] getAliases() {
        return new String[0];
    }

    // I need to think of the best way to handle permissions - this will be done with roles but obviously can't be
    // predefined, especially if we open-source this.
}
