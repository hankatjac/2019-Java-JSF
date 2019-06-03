package model;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class Product {
	
	private String productName;
	private String category;
	private String description;
	
	
	public Product() {
		
	}


	public Product(String productName, String category, String description) {
		this.productName = productName;
		this.category = category;
		this.description = description;
	}
	
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	

}
