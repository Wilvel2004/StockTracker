package com.WilvelPineda.StockTracker.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuoteResponse(@JsonProperty("c")
                            double currentPrice,

                            @JsonProperty("h")
                            double highPrice,

                            @JsonProperty("l")
                            double lowPrice,

                            @JsonProperty("o")
                            double openPrice,

                            @JsonProperty("pc")
                            double previousClose
) {

}
