package com.example.FOODHUB.Order;

import java.util.List;

public class PlaceOrderRequest {

    private Long cartId;

    private List<OrderItem> items;

    public PlaceOrderRequest() {
    }


        public Long getCartId () {
            return cartId;
        }

        public void setCartId (Long cartId){
            this.cartId = cartId;
        }

        public List<OrderItem> getItems () {
            return items;
        }

        public void setItems (List < OrderItem > items) {
            this.items = items;
        }
    }

