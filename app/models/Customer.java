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
@Table(name="cust")
public class Customer extends Model{

	@Id
	public int id;
	@Column(length=20)
	public String cusname;
	public long mno;
	public String pass;
	public long totalcoast;
	public int noofitemsbuy;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="cus_item")
	public List<Item> items;
}
