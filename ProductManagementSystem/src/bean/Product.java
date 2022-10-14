package bean;

import java.util.Date;

public class Product {
	int pid;
	String pname;
	String pcat;
	Date manufacture_date;
	double price;
	Date expiry_date;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int pid, String pname, String pcat, Date manufacture_date, double price, Date expiry_date) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pcat = pcat;
		this.manufacture_date = manufacture_date;
		this.price = price;
		this.expiry_date = expiry_date;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcat() {
		return pcat;
	}
	public void setPcat(String pcat) {
		this.pcat = pcat;
	}
	public Date getManufacture_date() {
		return manufacture_date;
	}
	public void setManufacture_date(Date manufacture_date) {
		this.manufacture_date = manufacture_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

}
