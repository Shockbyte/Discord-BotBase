import com.shockbyte.shockbotty.Bot;
import com.shockbyte.shockbotty.utils.whmcs.WHMCS;
import io.github.binaryoverload.JSONConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

@SuppressWarnings("ConstantConditions")
public class ExampleBot extends Bot {

    private static final Logger logger = LoggerFactory.getLogger(ExampleBot.class);

    private static ExampleBot instance;

    private JSONConfig config;
    private WHMCS whmcs;

    public static void main(String[] args) {
        (instance = new ExampleBot()).init();
    }

    private void init() {
        try {
            config = new JSONConfig("config.json");
        } catch (FileNotFoundException e) {
            logger.error("Config does not exist! Create a `config.json` file!");
        }

        if (config.getElement("whmcs").isPresent()) {
            whmcs = new WHMCS(config.getString("whmcs.url").get(), config.getString("whmcs.identifier").get(),
                    config.getString("whmcs.secret").get(), "json");
        }

        init(config.getString("token").get(), config.getString("prefix").get());
    }

    @Override
    public void run() {
        registerCommand(new ExampleCommand());
    }

    public static ExampleBot getInstance() {
        return instance;
    }

    public WHMCS getWHMCS() {
        return whmcs;
    }
}
