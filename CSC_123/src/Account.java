// Mantra Mehta(mmehta2@toromail.csudh.edu)
import java.util.ArrayList;

public class Account {
	private long accountNumber;
	private String acctType;
	private boolean accountStatus;
	private double balance;
	private double od;
	private ArrayList<Transaction> transactions;
	private AcctHolder acctHolder;
	String a;

	public Account(long accountNumber, String acctType, boolean accountStatus, double balance, double od,
			AcctHolder acctHolder) {
		super();
		this.accountNumber = accountNumber;
		this.acctType = acctType;
		this.accountStatus = accountStatus;
		this.balance = balance;
		this.od = od;
		this.acctHolder = acctHolder;
		this.transactions = new ArrayList<Transaction>();
	}

	public long GetAcctNum() {
		return this.accountNumber;
	}

	public String getType() {
		return this.acctType;
	}

	public String getaccountStatus() {
		if (this.accountStatus == true) {
			a = "Account Open";
		} else
			a = "Account Closed";
		return a;
	}

	public String getFirstName() {
		return this.acctHolder.getFirstName();
	}

	public String getLastName() {
		return this.acctHolder.getLastName();
	}

	public String getSSN() {
		return this.acctHolder.getSSN();
	}

	public double getBalance() {
		return this.balance;
	}

	public double getDebit() {
		return this.balance + this.od;
	}

	public void AddAmount(double Amount) {
		this.balance = this.balance + Amount;
	}

	public void AddTransaction(Transaction TranObj) {
		this.transactions.add(TranObj);
	}

	public void SubAmount(double Amount) {
		this.balance = this.balance - Amount;
	}

	public void CloseAccont() {
		this.accountStatus = false;
	}

	public ArrayList mymethod() {
		ArrayList<String> Tranarray1;
		Tranarray1 = new ArrayList<String>();
		String tranLine;
		for (int i = 0; i < transactions.size(); i++) {
			tranLine = transactions.get(i).getTransactionLine() + "\n";
			Tranarray1.add(tranLine);
		}
		return Tranarray1;
	}
}
