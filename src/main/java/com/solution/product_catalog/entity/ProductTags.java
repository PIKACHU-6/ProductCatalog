package com.solution.product_catalog.entity;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_TAGS")
public class ProductTags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_TAG_ID")
	private Long productTagId;

	@Column(name = "P_TAG_NAME")
	private String tagName;

	public Long getProductTagId() {
		return productTagId;
	}

	public void setProductTagId(Long productTagId) {
		this.productTagId = productTagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
