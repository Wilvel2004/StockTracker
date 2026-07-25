package com.WilvelPineda.StockTracker.Service;

import com.WilvelPineda.StockTracker.Client.FinnhubClient;
import com.WilvelPineda.StockTracker.DTO.Response.QuoteResponse;
import com.WilvelPineda.StockTracker.DTO.Response.StockResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockService {
     private final FinnhubClient finnhubClient;

     public StockService(FinnhubClient finnhubClient){
         this.finnhubClient = finnhubClient;
     }

    public StockResponse getStock(String symbol)
            throws IOException, InterruptedException {

        QuoteResponse quote = finnhubClient.getQuote(symbol);

        return new StockResponse(
                symbol,
                quote.currentPrice(),
                quote.highPrice(),
                quote.lowPrice(),
                quote.openPrice(),
                quote.previousClose()
        );
    }
}
