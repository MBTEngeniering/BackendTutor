package de.fairstudy.backend.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import de.fairstudy.backend.config.StripeProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
public class StripeCheckoutService {
    private final StripeProperties stripeProperties;

    public StripeCheckoutService (StripeProperties stripeProperties)
    {
        this.stripeProperties = stripeProperties;
    }
  private static final Map<Integer, Function<StripeProperties,String>> PRICE_MAP =
          Map.of(
                  1,p -> p.getPrices().getSingle(),
                  2,p -> p.getPrices().getFour(),
                  3,p -> p.getPrices().getEight(),
                  4,p -> p.getPrices().getTwelve(),
                  5,p -> p.getPrices().getTwenty(),
                  6,p -> p.getPrices().getTwentyFive(),
                  7,p -> p.getPrices().getFifty()
          );


    public String createCheckoutUrl(int packageId, String successUrl, String cancelUrl) throws StripeException {

        Function<StripeProperties,String> priceGetter = PRICE_MAP.get(packageId);

        if(priceGetter == null){
           throw new IllegalArgumentException("Unknown package id: " + packageId);
        }

        String priceId = priceGetter.apply(stripeProperties);

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .addLineItem(SessionCreateParams.
                        LineItem.
                        builder()
                        .setPrice(priceId)
                        .setQuantity(1L)
                        .build())
                        .build();

        Session session = Session.create(params);
        return session.getUrl();
    }
}
