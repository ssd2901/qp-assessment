package com.management.grocery.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.management.grocery.controller.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String name;
    private double price;
    private int quantity;
    
    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;
    
    public Product(ProductVO productVO) {
    	this.name = productVO.getName();
    	this.price = productVO.getPrice();
    	this.quantity = productVO.getQuantity();
	}
    	
}
