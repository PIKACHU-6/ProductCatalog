package com.solution.product_catalog.serivce;

import com.solution.product_catalog.entity.Product;
import com.solution.product_catalog.entity.ProductTags;
import com.solution.product_catalog.model.ProductModel;
import com.solution.product_catalog.repository.ProductRepo;
import com.solution.product_catalog.service.Impl.ProductServiceImpl;
import com.solution.product_catalog.util.JacksonConvertor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepo repo;

    @Test
    void insertProduct() throws URISyntaxException, IOException {

        Product product = new Product();
        List<ProductTags> tags = new ArrayList<>();
        product.setProductId(UUID.fromString("179e8f4b-d4e9-4582-bb40-35aeeccdb91e"));
        product.setName("Green Shirt");
        product.setDescription("Red hugo boss shirt");
        product.setBrand("Hugo Boss");
        ProductTags tag1 = new ProductTags();
        tag1.setTagName("red");
        tags.add(tag1);
        ProductTags tag2 = new ProductTags();
        tag2.setTagName("shirt");
        tags.add(tag2);
        ProductTags tag3 = new ProductTags();
        tag3.setTagName("slim fit");
        tags.add(tag3);
        product.setTags(tags);
        product.setCategory("apparel");
        product.setCreatedAt(new Date());

        when(repo.save(Mockito.any())).thenReturn(product);

        ProductModel input = JacksonConvertor.jsonFileToObject("product_input_data.json", ProductModel.class);
        ProductModel output = service.insertProduct(input);
        Assertions.assertEquals("179e8f4b-d4e9-4582-bb40-35aeeccdb91e", output.getId());
        Assertions.assertEquals("Green Shirt", output.getName());
        Assertions.assertEquals("Red hugo boss shirt", output.getDescription());
        Assertions.assertEquals("Hugo Boss", output.getBrand());
        Assertions.assertEquals("red", output.getTags().get(0));
        Assertions.assertEquals("shirt", output.getTags().get(1));
        Assertions.assertEquals("slim fit", output.getTags().get(2));
        Assertions.assertEquals("apparel", output.getCategory());
    }

    @Test
    void getProducts() throws URISyntaxException, IOException {

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        List<ProductTags> tags = new ArrayList<>();
        product.setProductId(UUID.fromString("179e8f4b-d4e9-4582-bb40-35aeeccdb91e"));
        product.setName("Green Shirt");
        product.setDescription("Green hugo boss shirt");
        product.setBrand("Hugo Boss");
        ProductTags tag1 = new ProductTags();
        tag1.setTagName("red");
        tags.add(tag1);
        ProductTags tag2 = new ProductTags();
        tag2.setTagName("shirt");
        tags.add(tag2);
        ProductTags tag3 = new ProductTags();
        tag3.setTagName("slim fit");
        tags.add(tag3);
        product.setTags(tags);
        product.setCategory("apparel");
        product.setCreatedAt(new Date());

        Product product1 = new Product();
        List<ProductTags> tags1 = new ArrayList<>();
        product1.setProductId(UUID.fromString("179e8f4b-d4e9-4582-bb40-35aeeccdb91e"));
        product1.setName("Red Shirt");
        product1.setDescription("Red hugo boss shirt");
        product1.setBrand("Hugo Boss");
        ProductTags tag11 = new ProductTags();
        tag11.setTagName("red");
        tags1.add(tag11);
        ProductTags tag12 = new ProductTags();
        tag12.setTagName("shirt");
        tags1.add(tag12);
        product1.setTags(tags1);
        product1.setCategory("apparel");
        product1.setCreatedAt(new Date());

        products.add(product);
        products.add(product1);

        when(repo.findByCategory(anyString(), any())).thenReturn(Optional.of(products));

        List<ProductModel> productModels = service.getProducts("apparel", 1, 1);
        ProductModel output = productModels.get(0);

        Assertions.assertEquals("179e8f4b-d4e9-4582-bb40-35aeeccdb91e", output.getId());
        Assertions.assertEquals("Green Shirt", output.getName());
        Assertions.assertEquals("Green hugo boss shirt", output.getDescription());
        Assertions.assertEquals("Hugo Boss", output.getBrand());
        Assertions.assertEquals("red", output.getTags().get(0));
        Assertions.assertEquals("shirt", output.getTags().get(1));
        Assertions.assertEquals("slim fit", output.getTags().get(2));
        Assertions.assertEquals("apparel", output.getCategory());

        ProductModel output1 = productModels.get(1);

        Assertions.assertEquals("179e8f4b-d4e9-4582-bb40-35aeeccdb91e", output1.getId());
        Assertions.assertEquals("Red Shirt", output1.getName());
        Assertions.assertEquals("Red hugo boss shirt", output1.getDescription());
        Assertions.assertEquals("Hugo Boss", output1.getBrand());
        Assertions.assertEquals("red", output1.getTags().get(0));
        Assertions.assertEquals("shirt", output1.getTags().get(1));
        Assertions.assertEquals("apparel", output1.getCategory());
    }
}
