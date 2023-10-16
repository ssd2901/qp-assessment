package com.management.grocery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.grocery.entity.OrderDertailsRepository;
import com.management.grocery.entity.OrderDetails;
import com.management.grocery.entity.Product;
import com.management.grocery.entity.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/grocery")
@Slf4j
public class GroceryController {
	
	@Resource
	private ProductRepository productRepository;
	
	@Resource
	private OrderDertailsRepository orderDertailsRepository;
	
	
	@GetMapping("/products/list")
	public ResponseEntity<List<Product>> getAllPRoducts() {
		return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/products/create")
	public ResponseEntity<String> addProduct(@RequestBody ProductVO productVO) {

		Product product = new Product(productVO);
		productRepository.save(product);

		return new ResponseEntity<>("Grocery item added successfully", HttpStatus.OK);
	}
	
	@PutMapping("/products/edit")
	public ResponseEntity<String> updateProduct(@RequestBody ProductVO productVO) {

		Optional<Product> product = productRepository.findById(productVO.getId());

		if (product.isPresent()) {
			Product updateProduct = product.get();
			updateProduct.setId(productVO.getId());
			updateProduct.setPrice(productVO.getPrice());
			updateProduct.setName(productVO.getName());
			updateProduct.setQuantity(productVO.getQuantity());
			productRepository.save(updateProduct);

			return new ResponseEntity<>("Grocery item updated successfully", HttpStatus.OK);

		}

		return new ResponseEntity<>("Grocery item is not found in our inventory", HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long id) {

		productRepository.deleteById(id);

		return new ResponseEntity<>("Grocery item deleted successfully", HttpStatus.OK);
	}
	
	@PostMapping("/orders")
    public ResponseEntity<String> createOrderWithProducts(@RequestBody List<ProductVO> productVOs) {
        // Create a new order
        OrderDetails order = new OrderDetails();

        // Save the order to the database to obtain its ID
        orderDertailsRepository.save(order);

        // Create and associate Product entities with the order
        List<Product> products = new ArrayList<>();
        for (ProductVO productVO : productVOs) {
            Product product = new Product(productVO);
            product.setOrderDetails(order);
            products.add(product);
        }

        // Save the products to the database
        productRepository.saveAll(products);

        return ResponseEntity.ok("Order created with products successfully.");
    }
	
	
	

}
