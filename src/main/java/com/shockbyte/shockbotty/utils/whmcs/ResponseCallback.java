package com.shockbyte.staffbot.whmcs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;

@FunctionalInterface
public interface ResponseCallback extends Callback {

    JsonParser parser = new JsonParser();

    @Override
    @ParametersAreNonnullByDefault
    default void onFailure(Call call, IOException e) {
        WHMCS.getLogger().error("Failed to call '" + call.request().url() + "'", e);
    }

    @ParametersAreNonnullByDefault
    default void onResponse(Call call, Response response) throws IOException {
        ResponseBody body = response.body();

        this.response(response, (body != null ? parser.parse(body.string()).getAsJsonObject() : null));

        // Cleanup so we don't leak
        if (body != null)
            body.close();
        response.close();
    }

    void response(Response response, JsonObject object) throws IOException;
}
