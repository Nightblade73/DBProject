package Tables;

import java.sql.Date;

public class Sale implements InterfaceTable {
	private int salenum;
	private String productnum;
	private String buyernum;
	private int count;
	private Date saledate;
	private double price;

	public Sale(int salenum, String productnum, String buyernum, int count, Date saledate, double price) {
		super();
		this.salenum = salenum;
		this.productnum = productnum;
		this.buyernum = buyernum;
		this.count = count;
		this.saledate = saledate;
		this.price = price;
	}

	public int getSalenum() {
		return salenum;
	}

	public void setSalenum(int salenum) {
		this.salenum = salenum;
	}

	public String getProductnum() {
		return productnum;
	}

	public void setProductnum(String productnum) {
		this.productnum = productnum;
	}

	public String getBuyernum() {
		return buyernum;
	}

	public void setBuyernum(String buyernum) {
		this.buyernum = buyernum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getSaledate() {
		return saledate;
	}

	public void setSaledate(Date saledate) {
		this.saledate = saledate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int getPrimaryKey() {
		return getSalenum();
	}

	@Override
	public String getInfo() {
		return null;
	}
}
