package api;

import java.util.Map;
import com.google.gson.annotations.SerializedName;

public class ExchangeRateResponse {
    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public ExchangeRateResponse() {

    }
    public ExchangeRateResponse(String baseCode, Map<String, Double> conversionRates) {
        this.baseCode = baseCode;
        this.conversionRates = conversionRates;
    }

    public String getBaseCode() {return baseCode;}
    public Map<String, Double> getConversionRates() {return conversionRates;}
}
