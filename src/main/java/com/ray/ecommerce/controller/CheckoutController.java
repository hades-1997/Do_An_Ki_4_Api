package com.ray.ecommerce.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.ray.ecommerce.config.PaypalPaymentIntent;
import com.ray.ecommerce.config.PaypalPaymentMethod;
import com.ray.ecommerce.domain.HttpResponse;
import com.ray.ecommerce.dto.Purchase;
import com.ray.ecommerce.dto.PurchaseResponse;
import com.ray.ecommerce.entity.PlaylistCat;
import com.ray.ecommerce.service.CheckoutService;
import com.ray.ecommerce.service.PaypalServiceImpl;
import com.ray.ecommerce.utility.Paypal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private Logger log = LoggerFactory.getLogger(getClass());
    private CheckoutService checkoutService;
    private Paypal paypal;
    private PaypalServiceImpl paypalService;
    public static final String URL_PAYPAL_SUCCESS = "api/checkout/pay/success";
    public static final String URL_PAYPAL_CANCEL = "api/checkout/pay/cancel";

    @Autowired
    public CheckoutController(CheckoutService checkoutService, Paypal paypal, PaypalServiceImpl paypalService) {
        this.checkoutService = checkoutService;
        this.paypal = paypal;
        this.paypalService = paypalService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        return checkoutService.placeOrder(purchase);
    }


    @PostMapping("/pay")
    public ResponseEntity<PlaylistCat> pay(HttpServletRequest request, @RequestParam("price") double price ){
        String cancelUrl = paypal.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = paypal.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        try {
            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    PlaylistCat playlistCat = new PlaylistCat();
                    playlistCat.setAlias(links.getHref());
                    return new ResponseEntity<>(playlistCat, HttpStatus.OK);
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay(){
        return "cancel";
    }
//    URL_PAYPAL_SUCCESS
    @GetMapping("/pay/success")
    public String successPay( @RequestParam("paymentId") String paymentId,
                              @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){

                return "success";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
//        "redirect:products"
        return null;
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase(), message), httpStatus);
    }

}