package Tables;

import java.sql.Date;

public class Product implements InterfaceTable {
	private int productnum;
	private String producernum;
	private Date partynum;
	private String model;
	private double priceforone;

	public Product(int productnum, String producernum, Date partynum, String model, double priveforone) {
		super();
		this.productnum = productnum;
		this.producernum = producernum;
		this.partynum = partynum;
		this.model = model;
		this.priceforone = priveforone;
	}

	public int getProductnum() {
		return productnum;
	}

	public void setProductnum(int productnum) {
		this.productnum = productnum;
	}

	public String getProducernum() {
		return producernum;
	}

	public void setProducernum(String producernum) {
		this.producernum = producernum;
	}

	public Date getPartynum() {
		return partynum;
	}

	public void setPartynum(Date partynum) {
		this.partynum = partynum;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPriceforone() {
		return priceforone;
	}

	public void setPriceforone(double priveforone) {
		this.priceforone = priveforone;
	}

	@Override
	public int getPrimaryKey() {
		return getProductnum();
	}

	@Override
	public String getInfo() {
		return getProductnum() + "-" + getModel();
	}
}
