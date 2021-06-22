package io.fraud.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealMessage {

    @JsonProperty("data")
    private String data;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("source")
    private String source;

    @JsonProperty("target")
    private String target;

    private Double rate;

    @JsonProperty("base_currency")
    private String baseCurrency;
}
