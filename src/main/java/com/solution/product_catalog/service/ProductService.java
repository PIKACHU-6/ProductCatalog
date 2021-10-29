package com.solution.product_catalog.service;

import java.util.List;

import com.solution.product_catalog.model.ProductModel;

public interface ProductService {

	public ProductModel insertProduct(ProductModel model);
	public List<ProductModel> getProducts(String category, Integer pageNo, Integer pageSize);
	
}
