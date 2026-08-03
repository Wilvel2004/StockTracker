package com.WilvelPineda.StockTracker.Client;

import com.WilvelPineda.StockTracker.DTO.Response.CryptoPriceResponse;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Component
public class CoinGeckoClient {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private final ObjectMapper objectMapper;


    public CoinGeckoClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public CryptoPriceResponse getPrice(String marketId)
            throws IOException, InterruptedException {


        String url =
                "https://api.coingecko.com/api/v3/simple/price?ids="
                        + marketId
                        + "&vs_currencies=usd"
                        + "&include_24hr_change=true";


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();


        HttpResponse<String> response =
                CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );


        Map<String, Map<String, Object>> result =
                objectMapper.readValue(
                        response.body(),
                        Map.class
                );


        Map<String, Object> crypto = result.get(marketId);


        if (crypto == null) {

            throw new IllegalArgumentException(
                    "Crypto not found in CoinGecko: " + marketId
            );
        }


        Object price = crypto.get("usd");
        Object change = crypto.get("usd_24h_change");


        if (price == null) {

            throw new IllegalArgumentException(
                    "USD price not found for: " + marketId
            );
        }


        if (change == null) {

            throw new IllegalArgumentException(
                    "24h change not found for: " + marketId
            );
        }


        return new CryptoPriceResponse(
                Double.parseDouble(price.toString()),
                Double.parseDouble(change.toString())
        );
    }
}
