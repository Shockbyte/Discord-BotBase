import com.shockbyte.shockbotty.Command;
import com.shockbyte.shockbotty.context.CommandContext;

public class ExampleCommand implements Command {

    @Override
    public void onCommand(CommandContext context) {
        ExampleBot.getInstance().getWHMCS().get("GetTicketCounts", (res, body) ->
                context.getChannel().sendMessage("Tickets Awaiting Reply: " + body.get("awaitingReply").getAsString())
                        .queue());
    }

    @Override
    public String getCommand() {
        return "example";
    }
}
