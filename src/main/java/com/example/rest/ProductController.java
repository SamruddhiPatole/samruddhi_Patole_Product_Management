package com.example.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
		Product pro = productService.getProduct(id);
		if (pro != null) {
			return new ResponseEntity<Product>(pro, HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
		boolean flag = productService.deleteProduct(id);
		if (flag) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product prod = productService.addProduct(product);
		return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
	}

	@PutMapping(value = "/products/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		Product pd = productService.updateProduct(product, id);
		if (pd != null) {
			return new ResponseEntity<Product>(pd, HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@PatchMapping(value = "/products/{id}")
	public ResponseEntity<Product> editProduct(@RequestBody Product product, @PathVariable Integer id) {
		Product pd = productService.editProduct(product, id);
		if (pd != null) {
			pd.setId(id);
			return new ResponseEntity<Product>(pd, HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/products/sort-by-price")
	public ResponseEntity<List<Product>> sortProduct() {
		List<Product> products = productService.sortProductByPriceAsc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	
	@GetMapping(value = "/products/sort-by-price-desc")
	public ResponseEntity<List<Product>> sortProductDesc() {
		List<Product> products = productService.sortProductByPriceDesc();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

}
