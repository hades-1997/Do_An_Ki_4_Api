package com.ray.videos.service;

import com.ray.videos.dto.Purchase;
import com.ray.videos.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
