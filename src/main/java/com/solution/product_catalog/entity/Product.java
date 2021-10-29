package com.solution.product_catalog.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT_CATALOG_MASTER")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private UUID productId;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @Column(name = "PRODUCT_BRAND")
    private String brand;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_TAG", joinColumns = {@JoinColumn(name = "PRODUCT_ID")}, inverseJoinColumns = {@JoinColumn(name = "P_TAG_ID")})
    private List<ProductTags> tags;

    @Column(name = "PRODUCT_CATEGORY")
    private String category;

    @Column(name = "PRODUCT_CREATED_DATE")
    @CreationTimestamp
    private Date createdAt;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<ProductTags> getTags() {
        return tags;
    }

    public void setTags(List<ProductTags> tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
