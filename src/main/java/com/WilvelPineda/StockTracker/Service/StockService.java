package com.WilvelPineda.StockTracker.Service;

import com.WilvelPineda.StockTracker.Client.FinnhubClient;
import com.WilvelPineda.StockTracker.DTO.QuoteResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockService {
     private final FinnhubClient finnhubClient;

     public StockService(FinnhubClient finnhubClient){
         this.finnhubClient = finnhubClient;
     }

    public QuoteResponse getStock(String symbol)
            throws IOException, InterruptedException {

        return finnhubClient.getQuote(symbol);
    }
}
