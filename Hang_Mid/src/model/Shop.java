package model;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

// 1- add @ManagedBean annotation
@ManagedBean
@ApplicationScoped
public class Shop {

	private List<Product> shopList;


	
	
	  public Shop() { loadSampleData(); }
	 
	
	
	  public void loadSampleData() {
	  
	  shopList = new ArrayList<>();
	  
	  shopList.add(new Product("INTRODUCTION TO JAVA","BOOK","PAPER BOOK"));
	  shopList.add(new Product("ECLIPSE","SOFTWARE","EDUCATION"));
	  shopList.add(new Product("SAMSUNG GALAXY 8","MOBILE","4G"));
	  shopList.add(new Product("MAC 8","COMPUTER","LAPTOP"));
	  shopList.add(new Product("CANNON EOS 8","CAMERA","DIGITAL")); 
	  shopList.add(new Product("IPAD 8","COMPUTER","IPAD")); 
	  shopList.add(new Product("INTELLIJ","SOFTWARE","IDE"));
	  shopList.add(new Product("MYSQL","SOFTWARE","EDUCATION")); 
	  shopList.add(new Product("ADVANCED JAVA","BOOK","ELECTRONIC BOOK")); 
	  shopList.add(new Product("HUAWEI P10","MOBILE","5G")); 
	  }



	public List<Product> getShopList() {
		return shopList;
	}



	public void setShopList(List<Product> shopList) {
		this.shopList = shopList;
	}




	
	
}

