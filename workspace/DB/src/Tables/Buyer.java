package Tables;

public class Buyer implements InterfaceTable{
	private int buyernum;
	private String surname;
	private String name;
	private String patronymic;
	private String telephone;

	public Buyer(int buyernum, String surname, String name, String patronymic, String telephone) {
		super();
		this.buyernum = buyernum;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.telephone = telephone;
	}

	public int getBuyernum() {
		return buyernum;
	}

	public void setBuyernum(int buyernum) {
		this.buyernum = buyernum;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public int getPrimaryKey() {
		return getBuyernum();
	}

	@Override
	public String getInfo() {		
		return getBuyernum() + "-" + getSurname();
	}
}
