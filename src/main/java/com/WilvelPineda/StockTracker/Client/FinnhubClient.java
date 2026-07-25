package com.WilvelPineda.StockTracker.Client;

import com.WilvelPineda.StockTracker.DTO.QuoteResponse;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class FinnhubClient {

    @Value("${finnhub.api.key}")
    private String apiKey;

    private static final HttpClient CLIENT = HttpClient.newHttpClient();


    private final ObjectMapper objectMapper;

    public FinnhubClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public QuoteResponse getQuote(String symbol)
            throws IOException, InterruptedException {


        String url =
                "https://finnhub.io/api/v1/quote?symbol="
                        + symbol
                        + "&token="
                        + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(
                response.body(),
                QuoteResponse.class
        );
    }
}
