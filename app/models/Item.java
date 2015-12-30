package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.avaje.ebean.Model;


@Entity
@Table(name="items")
public class Item extends Model{

	@Id
	public int id;
	@Column(length=10)
	public String itemname;
	public int price;
	public int noofitems;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="cus_item")
	public List <Customer> cust;
	
}
