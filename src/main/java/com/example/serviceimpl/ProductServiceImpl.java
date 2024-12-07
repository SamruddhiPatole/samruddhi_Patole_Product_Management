package com.example.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Integer id) {
		if (productRepository.existsById(id)) {
			return productRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public boolean deleteProduct(int id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product, Integer id) {
		if (productRepository.existsById(id)) {
			return productRepository.save(product);
		}
		return null;
	}
	
	@Override
	public Product editProduct(Product product, Integer id) {
		if(productRepository.existsById(id))
		{
			Product pd=productRepository.findById(id).get();
			if(product.getName()!=null)
			{
				pd.setName(product.getName());
			}
			if(product.getCategory()!=null)
			{
				pd.setCategory(product.getCategory());
			}
			if(product.getPrice()!=null)
			{
				pd.setPrice(product.getPrice());
			}
			Product prod=productRepository.save(pd);
			return prod;
		}
		return null;
	}
	
	
	@Override
	public List<Product> sortProductByPriceAsc() {
		List<Product> products = productRepository.findAllByOrderByPriceAsc();
		return products;
	}
	
	@Override
	public List<Product> sortProductByPriceDesc() {
		List<Product> products = productRepository.findAllByOrderByPriceDesc();
		return products;
	}
}
