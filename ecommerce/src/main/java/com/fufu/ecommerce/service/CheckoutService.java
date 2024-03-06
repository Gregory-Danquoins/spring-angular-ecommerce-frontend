package com.fufu.ecommerce.service;

import com.fufu.ecommerce.dto.Purchase;
import com.fufu.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
    
} 