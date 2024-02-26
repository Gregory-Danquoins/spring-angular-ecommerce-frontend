package com.fufu.ecommerce.dto;

import java.util.Set;

import com.fufu.ecommerce.entity.Address;
import com.fufu.ecommerce.entity.Customer;
import com.fufu.ecommerce.entity.Order;
import com.fufu.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {


    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;

    
}
