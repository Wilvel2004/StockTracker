package com.WilvelPineda.StockTracker.Client;

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


    public double getPrice(String marketId)
            throws IOException, InterruptedException {


        String url =
                "https://api.coingecko.com/api/v3/simple/price?ids="
                        + marketId
                        + "&vs_currencies=usd";


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


        Object price = result
                .get(marketId)
                .get("usd");

        return Double.parseDouble(price.toString());
    }
}
