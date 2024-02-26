package com.fufu.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fufu.ecommerce.dao.CustomerRepository;
import com.fufu.ecommerce.dto.Purchase;
import com.fufu.ecommerce.dto.PurchaseResponse;
import com.fufu.ecommerce.entity.Customer;
import com.fufu.ecommerce.entity.Order;
import com.fufu.ecommerce.entity.OrderItem;

import jakarta.transaction.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService{
    
    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase){

        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item-> order.add(item));
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer= purchase.getCustomer();
        customer.add(order);

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);


    }

    // Genetate random UUID number (UUID version-4)
    private String generateOrderTrackingNumber(){

        return UUID.randomUUID().toString();

    }


}
