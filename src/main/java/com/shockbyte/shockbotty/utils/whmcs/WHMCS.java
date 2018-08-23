package com.shockbyte.staffbot.whmcs;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WHMCS {

    private static final Logger logger = LoggerFactory.getLogger(WHMCS.class);

    private final String url;
    private final String identifier;
    private final String secret;
    private final String responseType;

    private final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).build();

    public WHMCS(String url, String identifier, String secret, String responseType) {
        this.url = url;
        this.identifier = identifier;
        this.secret = secret;
        this.responseType = responseType;
    }


    public void get(String action, ResponseCallback callback) {
        get(action, null, callback);
    }

    public void get(String action, Map<String, String> params, ResponseCallback callback) {
        try {
            //https://shockbyte.com/billing/includes/api.php?identifier={{whmcs-identifier}}&secret={{whmcs-secret}}
            // &action=GetTicketCounts&responsetype=json
            StringBuilder sb = new StringBuilder(url + "/includes/api.php")
                    .append("?identifier=").append(identifier)
                    .append("&secret=").append(secret)
                    .append("&action=").append(action)
                    .append("&responsetype=").append(responseType);

            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> param : params.entrySet())
                    sb.append("&").append(param.getKey()).append("=").append(param.getValue());
            }

            Request request = new Request.Builder().url(new URL(sb.toString()))
                    .addHeader("User-Agent", "Mozilla/5.0 Shockbyte (StaffBot)")
                    .build();

            client.newCall(request).enqueue(callback);
        } catch (MalformedURLException e) {
            logger.error("Failed to request " + url);
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
