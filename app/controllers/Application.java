package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//import org.apache.xalan.xsltc.compiler.sym;
import javafx.scene.layout.Background;
import models.Customer;
import models.Item;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import bean.MTMbean;

import com.avaje.ebean.Model;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.prism.paint.Color;

public class Application extends Controller {

    public Result index() {
        return ok(views.html.index.render(""));
    }
    public Result reg()
    {
    	return ok(views.html.reg.render(""));
    }
    public Result item()
    {
    	return ok(views.html.item.render(""));
    }  
  public Result itemlogin(String name){
    	String tempname="jagadeesh";
    	Logger.info("item login rechable statement -------->");
    	if(tempname.equals(name)){
    		Logger.info("if block rechable statement-------->");
    	   return ok(views.html.item.render("your autherized persion add items please add items "));
    	}
    	else{
    		  Logger.info("rechable statement-------->");
    		  return ok(views.html.index.render("")); 
    	}
    	 
    }
    public Result addItem()
    {
    	MTMbean bn = Form.form(MTMbean.class).bindFromRequest().get();
    	Logger.info("cust id-------->" + bn.getId());
    	Customer cus =  (Customer) new Model.Finder(Integer.class,Customer.class).byId(bn.getId());
    	List items = cus.items;
    	Logger.info("number of items  customer having now -------->" + items);
    	List<Item> item  =  new Model.Finder(Integer.class,Item.class).all();
    	Logger.info("total number of items  currently we are having now -------->" + items);
    	Iterator it = items.iterator();
    	while(it.hasNext())
    	{  
    		Item i1 = (Item)it.next();	
    		item.remove(i1);
    	}
    	return ok(views.html.additem.render("",bn.getId(),item));
    }
    public Result removeItem()
    {
    	MTMbean bn = Form.form(MTMbean.class).bindFromRequest().get();
    	Logger.info("-------->" + bn.getId());
    	Customer cus =  (Customer) new Model.Finder(Integer.class,Customer.class).byId(bn.getId());
    	List items = cus.items;
    	Logger.info("number of items  customer having now -------->" + items);
    	List<Item> add = new ArrayList<Item>();
    	Iterator it = items.iterator();
    	while(it.hasNext())
    	{
    		Item i1 = (Item)it.next();
    		Item item =  (Item) new Model.Finder(Integer.class,Item.class).byId(i1.id);
    	
    		add.add(item);
    	}
    	Logger.info("these are the items now he is removing -------->" + add);
    	return ok(views.html.removeitem.render("",bn.getId(),add));
    }
    
     public Result register()   // regsitration for both custmer and item 
    {
    	 MTMbean bn = Form.form(MTMbean.class).bindFromRequest().get();
    	try
    	{
    		if(bn.getType().equals("cust"))    
    		{
    			try
    	    	{
    			Customer c = new Customer();
    			c.cusname=bn.getCusname();
    			c.mno=bn.getMno();
    			c.pass=bn.getPass();
    			c.save();
                int id= (int ) new Model.Finder(Integer.class,Customer.class).orderBy("id desc ").findIds().get(0);	  		
    			return ok(views.html.all.render("remeber your id "+id, id));
    	    	}
    	    	catch(Exception e)
    	    	{
    	    	 return ok(views.html.reg.render("registration failed"));
    	    	}
    		}
    		else   
    		{
    			try
    	    	{
    			Item i1 = new Item();
    			i1.itemname=bn.getItemname();
    			i1.price=bn.getPrice();
    			i1.noofitems=bn.getNoofitems();
    			i1.save();
    			Logger.info("item added by the autherized persion-------->" + i1);
    			return ok(views.html.item.render("Item Added successfully."));
    	    	}
    	    	catch(Exception e)
    	    	{
    	    	 return ok(views.html.item.render("Item  Not Added successfully."));
    	    	}
    		}
    	}
    	catch(Exception e)
    	{
    	 return ok(views.html.index.render("Try Again......"));
    	}
     }
    	
    	public Result log()
        {
        	try
        	{
        	   MTMbean bn = Form.form(MTMbean.class).bindFromRequest().get();
        	   Customer c1 =  (Customer) new Model.Finder(Integer.class,Customer.class).byId(bn.getId());
        	   Logger.debug("-------->"+c1.id);
        		if(bn.getId() == c1.id)
        		{  
        			Logger.info("checking for id  -------->");
        			return ok(views.html.all.render("", c1.id));
        		}
        		else{
        			return ok(views.html.index.render("previous login not sucessful"));
        		}
        	}
        	catch(Exception e)
        	{
        	 return ok(views.html.index.render("Your new application is ready."));
        	}
        	
        }
    	
    	 public Result add()
    	    {
    		 	MTMbean bn = Form.form(MTMbean.class).bindFromRequest().get();
    		 	List ii = bn.getIid();
    	    	Customer cus =  (Customer) new Model.Finder(Integer.class,Customer.class).byId(bn.id);
    	    	int total=0; 
    	    	if(cus.totalcoast==0){
    	    	for(int j=0;j<ii.size();j++){
    	    	Item iit= (Item) new Model.Finder(Integer.class, Item.class).byId(ii.get(j));
    	    	int p=iit.price;
    	    	   total=total+p;
    	    	  }
    	    	cus.totalcoast=total;
    	    	}
    	    	else{
    	    		
    	    		for(int j=0;j<ii.size();j++)
    	    		{
    	    	    	Item iit= (Item) new Model.Finder(Integer.class, Item.class).byId(ii.get(j));
    	    	    	int p=iit.price;
    	    	    	total=total+p;
    	    	    }
    	    		cus.totalcoast=cus.totalcoast+total;
    	    		Logger.info("total items cost-------->" +  cus.totalcoast);
    	    	}
    	    	Logger.info("total items cost-------->" +  cus.totalcoast);
    	    	 Logger.info("total items added-------->" +  cus.noofitemsbuy);
    	    	List items = cus.items;   
    	    	   Iterator it=ii.iterator();
    	    	 while(it.hasNext())
    	    	 {
            	  String num = (String)it.next();
            	  int id = Integer.parseInt(num);
            	  Item item =  (Item) new Model.Finder(Integer.class,Item.class).byId(id);
            	  items.add(item);
    	    	  	 
    	    		}
    	      
   	           cus.noofitemsbuy=cus.items.size();
    	       cus.update();
    	    	  return ok(views.html.all.render("",bn.id));
              }
    	  public Result delete()
    	  {
    		  MTMbean bn = Form.form(MTMbean.class).bindFromRequest().get();
  		 	List ii = bn.getIid();
  	    	Customer cus =  (Customer) new Model.Finder(Integer.class,Customer.class).byId(bn.id);
  	    	List items = cus.items;
  	    	int total=0; 
	    	if(cus.totalcoast==0){
	    	//for(int j=0;j<ii.size();j++){
	    /*	Item iit= (Item) new Model.Finder(Integer.class, Item.class).byId(ii.get(j));
	    	int p=iit.price;
	    	   total=total+p;
	    	*/ // }
	    	}
	    	else{
	    		
	    		for(int j=0;j<ii.size();j++)
	    		   {
	    	    	Item iit= (Item) new Model.Finder(Integer.class, Item.class).byId(ii.get(j));
	    	    	int p=iit.price;
	    	    	total=total+p;
	    	    	}
	    		cus.totalcoast=cus.totalcoast-total;
	    		Logger.info("total items cost-------->" +  cus.totalcoast);
	    	}
	    	Logger.info("total items cost-------->" +  cus.totalcoast);
  	    	Iterator it=ii.iterator();
            while(it.hasNext()){
          	  String num = (String)it.next();
          	  int id = Integer.parseInt(num);
          	  Item item =  (Item) new Model.Finder(Integer.class,Item.class).byId(id);
          	  items.remove(item);
  	    	  	 
  	    	 }
             cus.noofitemsbuy=cus.items.size();
             Logger.info("total items buy-------->" +  cus.noofitemsbuy);
  	    	 cus.update();
     	     return ok(views.html.all.render("",bn.id));
     	    }
    	  
    	  public Result deleteCustomer()
    	    {
    		    MTMbean bn = Form.form(MTMbean.class).bindFromRequest().get();    	
    	     	Customer c1 =  (Customer) new Model.Finder(Integer.class,Customer.class).byId(bn.getId());
    	     	c1.delete();
    	    	return ok(views.html.deletecus.render("Deleted Customet .",bn.getId()));
    	    }
    	    
    public Result genbill(){
    	MTMbean bn = Form.form(MTMbean.class).bindFromRequest().get();
     	Customer c1 =  (Customer) new Model.Finder(Integer.class,Customer.class).byId(bn.getId());
     	List<Item> items = c1.items;
     	
     	Logger.debug("===items======"+items);
       // Logger.info("cust bill gen-------->" +  bn.getId());
     	String name=c1.cusname;
     	long number=c1.mno;
     	int noofitems=c1.noofitemsbuy;
     	long totalcost=c1.totalcoast;
    	    	
    	try{
    		        File file =new File("/home/jagadeeshwara/j1.pdf");
    	    	    FileOutputStream pdfFile = new FileOutputStream(file);
    	            Document doc = new Document();
    	          try{
    	            PdfWriter.getInstance(doc, pdfFile);
    	            doc.addAuthor("jagadeesh");
    				doc.addTitle("bill generation");
    	            doc.open();      
    				Paragraph pg1=new Paragraph();	
    			    pg1.add("              Bill generated  On the Date: "+ new Date()+"\n \n");
    				pg1.add("                                         Name of customer: "+name+""
    						+ " \n     \n                                                 No of items: "+noofitems+"\n \n");
    				doc.add(pg1);
    	            Paragraph pg2=new Paragraph();
    	            PdfPTable table=null;
    	            table=new PdfPTable(2);
    	            table.setWidthPercentage(100);
    	            table.setWidths(new float[] {2f,2f});
    	            table.addCell("               Item Name  ");
    	            table.addCell("               Item Price ");
    	            for (Item  item : items) {
    	            table.addCell(item.itemname);
    	            table.addCell(""+item.price);
    	            }
    	            doc.add(table);
    	            pg2.add("                                                 total amount:"+totalcost);
    	            pg2.add("\n");
    	            pg2.add("\n \n \n \n");
    	            pg2.add("Thank you visit Again ");
    	            pg2.add("                                                                                        please sign on bill");
    	            doc.add(pg2);	
    	            doc.close();
    	          }
                   catch(DocumentException ex){
                	   ex.printStackTrace();
                   }
    	            }
    	catch(IOException e)
    	{
			e.printStackTrace();
        }
    	return ok(views.html.genbill.render("your bill is generated please check "));
    	//return ok(views.html.all.render("your bill is generated please logout ",bn.id));
          }
}
