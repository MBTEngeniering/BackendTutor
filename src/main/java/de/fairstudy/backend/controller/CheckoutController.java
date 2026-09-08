package de.fairstudy.backend.controller;


import com.stripe.exception.StripeException;
import de.fairstudy.backend.service.StripeCheckoutService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final StripeCheckoutService stripeCheckoutService;

    public CheckoutController(StripeCheckoutService stripeCheckoutService) {
        this.stripeCheckoutService = stripeCheckoutService;
    }
    @Value("${frontend.url}")
    private String frontendUrl;
    @PostMapping("/{packageId}")
    public ResponseEntity<String> checkout(@PathVariable int packageId){
        try{
            String successUrl = frontendUrl + "/success";
            String cancelUrl = frontendUrl + "/preise";
            String url = stripeCheckoutService.createCheckoutUrl(packageId, successUrl, cancelUrl);
            return ResponseEntity.ok(url);

        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Stripe error: " + e.getMessage());
        }
    }

}
