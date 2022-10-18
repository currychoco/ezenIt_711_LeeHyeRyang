package money;

public class MoneyDto {

	private int custno;
	private int salenol;
	private int pcost;
	private int amount;
	private int price;
	private String pcode;
	private String sdate;

	public MoneyDto(int custno, int salenol, int pcost, int amount, int price, String pcode, String sdate) {
		super();
		this.custno = custno;
		this.salenol = salenol;
		this.pcost = pcost;
		this.amount = amount;
		this.price = price;
		this.pcode = pcode;
		this.sdate = sdate;
	}

	public int getCustno() {
		return custno;
	}

	public int getSalenol() {
		return salenol;
	}

	public int getPcost() {
		return pcost;
	}

	public int getAmount() {
		return amount;
	}

	public int getPrice() {
		return price;
	}

	public String getPcode() {
		return pcode;
	}

	public String getSdate() {
		return sdate;
	}

}
