package com.product.details.Product.Helper;

import java.util.Objects;

public class Product {
	
	private String productName;
	private String productPrice;
	private String itemNumber;
	private String modelNumber;
	private String productCategory;
	private String productDescription;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemNumber, modelNumber, productCategory, productDescription, productName, productPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(itemNumber, other.itemNumber) && Objects.equals(modelNumber, other.modelNumber)
				&& Objects.equals(productCategory, other.productCategory)
				&& Objects.equals(productDescription, other.productDescription)
				&& Objects.equals(productName, other.productName) && Objects.equals(productPrice, other.productPrice);
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productPrice=" + productPrice + ", itemNumber=" + itemNumber
				+ ", modelNumber=" + modelNumber + ", productCategory=" + productCategory + ", productDescription="
				+ productDescription + "]";
	}


}
