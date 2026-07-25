package com.WilvelPineda.StockTracker.Controller;

import com.WilvelPineda.StockTracker.DTO.Response.StockResponse;
import com.WilvelPineda.StockTracker.Service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbol}")
    public StockResponse getStock(@PathVariable String symbol)
            throws IOException, InterruptedException {

        return stockService.getStock(symbol);
    }


}
