package com.example.service;

import java.util.List;

import com.example.model.Product;

public interface  ProductService {

	List<Product> getAllProducts();

	Product getProduct(Integer id);

	boolean deleteProduct(int id);

	Product addProduct(Product product);

	Product updateProduct(Product product, Integer id);

	Product editProduct(Product product, Integer id);

	List<Product> sortProductByPriceAsc();

	List<Product> sortProductByPriceDesc();

}
