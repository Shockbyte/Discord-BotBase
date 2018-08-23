import com.shockbyte.shockbotty.Command;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class ExampleCommand implements Command {

    @Override
    public void onCommand(User user, Member member, MessageChannel channel, Guild guild, String[] args) {
        ExampleBot.getInstance().getWHMCS().get("GetTicketCounts", (res, body) ->
                channel.sendMessage("Tickets Awaiting Reply: " + body.get("awaitingReply").getAsString()).queue());
    }

    @Override
    public String getCommand() {
        return "example";
    }
}
