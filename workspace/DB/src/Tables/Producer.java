package Tables;

public class Producer implements InterfaceTable {
	private int producernum;
	private String producername;
	private String email;
	private String telephone;
	private String address;

	public Producer(int producernum, String producername, String email, String telephone, String address) {
		this.producernum = producernum;
		this.producername = producername;
		this.email = email;
		this.telephone = telephone;
		this.address = address;
	}

	public int getProducernum() {
		return producernum;
	}

	public void setProducernum(int producernum) {
		this.producernum = producernum;
	}

	public String getProducername() {
		return producername;
	}

	public void setProducername(String producername) {
		this.producername = producername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int getPrimaryKey() {
		return getProducernum();
	}

	@Override
	public String getInfo() {
		return getProducernum() + "-" + getProducername();
	}
}
