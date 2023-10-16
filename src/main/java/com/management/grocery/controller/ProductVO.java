package com.management.grocery.controller;

import lombok.Data;

@Data
public class ProductVO {
	
    private Long id;
    private String name;
    private double price;
    private int quantity;

}
