package de.fairstudy.backend.controller;

import de.fairstudy.backend.config.StripeProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/stripe")
public class StripeWebhookController {
    private final StripeProperties stripeProperties;

    public StripeWebhookController(StripeProperties stripeProperties){
        this.stripeProperties = stripeProperties;
    }
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String stripeSignature) {

        try {
            Event event = Webhook.constructEvent(
                    payload,
                    stripeSignature,
                    stripeProperties.getWebhookSecret()
            );

            if ("checkout.session.completed".equals(event.getType())) {
                System.out.println(
                        "Verified Stripe checkout completed: " + event.getId()
                );
            }

            return ResponseEntity.ok("Webhook received");

        } catch (SignatureVerificationException exception) {
            return ResponseEntity.badRequest().body("Invalid Stripe signature");
        }
    }
}

