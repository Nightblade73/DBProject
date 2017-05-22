package Tables;

import java.sql.Date;

public class Party implements InterfaceTable {
	private int partynum;
	private Date partydate;
	private String providername;

	public Party(int partynum, Date partydate, String providername) {
		this.partynum = partynum;
		this.partydate = partydate;
		this.providername = providername;
	}

	public int getPartynum() {
		return partynum;
	}

	public void setPartynum(int partynum) {
		this.partynum = partynum;
	}

	public Date getPartydate() {
		return partydate;
	}

	public void setPartydate(Date partydate) {
		this.partydate = partydate;
	}

	public String getProvidername() {
		return providername;
	}

	public void setProvidername(String providername) {
		this.providername = providername;
	}

	@Override
	public int getPrimaryKey() {
		return getPartynum();
	}

	@Override
	public String getInfo() {
		return getPartynum() + "-" + getPartydate();
	}
}
