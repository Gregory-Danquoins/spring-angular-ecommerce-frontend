package com.fufu.ecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fufu.ecommerce.dto.Purchase;
import com.fufu.ecommerce.dto.PurchaseResponse;
import com.fufu.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins={"http://localhost:4200","http://localhost:55175"})
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse purchaseResponse(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse= checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }
    

    
}
