package com.solution.product_catalog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.solution.product_catalog.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product, UUID> {

    Optional<List<Product>> findByCategory(String category, Pageable pageable);
}
