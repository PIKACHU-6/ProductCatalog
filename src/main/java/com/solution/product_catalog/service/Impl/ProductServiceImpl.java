package com.solution.product_catalog.service.Impl;

import com.solution.product_catalog.entity.Product;
import com.solution.product_catalog.entity.ProductTags;
import com.solution.product_catalog.exception.GlobalException;
import com.solution.product_catalog.exception.ResourceNotFoundException;
import com.solution.product_catalog.model.ProductModel;
import com.solution.product_catalog.repository.ProductRepo;
import com.solution.product_catalog.service.ProductService;
import com.solution.product_catalog.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo repo;

    @Override
    public ProductModel insertProduct(ProductModel inputProduct) {

        if (inputProduct == null || inputProduct.getName() == null || inputProduct.getDescription() == null ||
                inputProduct.getBrand() == null || inputProduct.getTags() == null || inputProduct.getCategory() == null) {
            throw new ResourceNotFoundException("Invalid Parameter");
        }

        ProductModel response = null;
        Product product = new Product();
        List<ProductTags> tags = new ArrayList<>();

        try {
            product.setName(inputProduct.getName());
            product.setDescription(inputProduct.getDescription());
            product.setBrand(inputProduct.getBrand());
            for (String tag : inputProduct.getTags()) {
                ProductTags productTag = new ProductTags();
                productTag.setTagName(tag);
                tags.add(productTag);
            }
            product.setTags(tags);
            product.setCategory(inputProduct.getCategory());
            Product updatedProduct = repo.save(product);
            response = getProductToModel(updatedProduct);

        } catch (Exception e) {
            throw new GlobalException("Something went wrong");
        }

        return response;
    }

    @Override
    public List<ProductModel> getProducts(String category, Integer pageNo, Integer pageSize) {

        if (category == null) {
            throw new ResourceNotFoundException("Invalid Parameter");
        }

        List<ProductModel> productModels = null;
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        Optional<List<Product>> optionalProducts = repo.findByCategory(category, paging);

        if (optionalProducts.isPresent() && !isEmpty(optionalProducts.get())) {
            productModels = new ArrayList<>();
            List<Product> products = optionalProducts.get();
            for (Product product : products) {
                ProductModel model = getProductToModel(product);
                productModels.add(model);
            }
        } else {
            throw new ResourceNotFoundException("Product not found");
        }

        return productModels;
    }

    private ProductModel getProductToModel(Product product) {
        ProductModel model = new ProductModel();
        model.setId(product.getProductId().toString());
        model.setName(product.getName());
        model.setDescription(product.getDescription());
        model.setBrand(product.getBrand());
        List<String> tagList = new ArrayList<>();
        for (ProductTags tag : product.getTags()) {
            tagList.add(tag.getTagName());
        }
        model.setTags(tagList);
        model.setCategory(product.getCategory());
        model.setCreatedAt(DateUtils.getISODate(product.getCreatedAt()));
        return model;
    }

}
