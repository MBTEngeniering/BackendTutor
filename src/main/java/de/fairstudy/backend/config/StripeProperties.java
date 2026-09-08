package de.fairstudy.backend.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties( prefix= "stripe")
public class StripeProperties {
    private String secretKey;
    private StripePriceProperties prices = new StripePriceProperties();


    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public String getSecretKey() {
        return secretKey;
    }

    public void setPrices(StripePriceProperties prices) {
        this.prices = prices;
    }
    public StripePriceProperties getPrices() {
        return prices;
    }




}
