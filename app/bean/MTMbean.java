package bean;

import java.util.List;

import models.Customer;
import models.Item;

public class MTMbean {

	public int id;
	
	public String cusname;
	
	public long mno;
	
	public String itemname;
	
	public int price;
	
	public long totalcoast;
	
	public int noofitemsbuy;
	
	public int noofitems;
	
	public String pass;
	
    public List iid;
	
	public List<Item> items;
	
	public Customer cust;
	
	public String type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public long getMno() {
		return mno;
	}

	public void setMno(long mno) {
		this.mno = mno;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getTotalcoast() {
		return totalcoast;
	}

	public void setTotalcoast(long totalcoast) {
		this.totalcoast = totalcoast;
	}

	public int getNoofitemsbuy() {
		return noofitemsbuy;
	}

	public void setNoofitemsbuy(int noofitemsbuy) {
		this.noofitemsbuy = noofitemsbuy;
	}

	public int getNoofitems() {
		return noofitems;
	}

	public void setNoofitems(int noofitems) {
		this.noofitems = noofitems;
	}

	public List getIid() {
		return iid;
	}

	public void setIid(List iid) {
		this.iid = iid;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
