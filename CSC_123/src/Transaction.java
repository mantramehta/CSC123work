// Mantra Mehta(mmehta2@toromail.csudh.edu)
public class Transaction {
	private long tranNum;
	private String type;
	private double amount;

	public Transaction(long tranNum, String type, double amount) {
		super();
		this.tranNum = tranNum;
		this.type = type;
		this.amount = amount;
	}

	public String getTransactionLine() {
		return this.tranNum + " : " + this.type + " : " + this.amount;
	}
}


