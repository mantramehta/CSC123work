// Mantra Mehta(mmehta2@toromail.csudh.edu)
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> account;
	long accountNumber;
	AcctHolder AcctHolderObj;
	Account accountObj;
	Transaction TranObj;
	long TransactionNum = 1000;
	String type;
	double Amount;
	double od;

	public Bank() {
		account = new ArrayList<Account>();
	}

	public String createAccount(String firstName, String lastName, String SSN, double od) {
		AcctHolderObj = new AcctHolder(firstName, lastName, SSN);
		if (od == 0) {
			type = "Saving";
		} else {
			type = "Checking";
		}
		accountNumber = account.size() + 1;
		accountObj = new Account(accountNumber, type, true, 0.0, od, AcctHolderObj);
		account.add(accountObj);
		return "\nThank you, the account number is " + accountNumber;
	}

	public ArrayList listAcounts() {
		ArrayList<String> accountArray;
		accountArray = new ArrayList<String>();
		String accountLine;
		for (int i = 0; i < account.size(); i++) {
			accountLine = account.get(i).GetAcctNum() + "(" + account.get(i).getType() + ")" + " : "
					+ account.get(i).getFirstName() + " : " + account.get(i).getLastName() + " : "
					+ account.get(i).getSSN() + " : " + account.get(i).getBalance() + " : "
					+ account.get(i).getaccountStatus() + "\n";
			accountArray.add(accountLine);
		}
		return accountArray;
	}

	public ArrayList accountStatements(int actNum) {
		ArrayList<String> Tranarray1;
		Tranarray1 = new ArrayList<String>();
		if (actNum > account.size() || actNum == 0) {
			String x = "Account not found";
			Tranarray1.add(x);
		} else {
			Tranarray1 = account.get(actNum - 1).mymethod();
		}
		return Tranarray1;
	}

	public String accountStatementsFinal(int actNum) {
		if (!(actNum > account.size() || actNum == 0)) {
			return "\n\nBalance: " + account.get(actNum - 1).getBalance() + "\n\n";
		}
		return "\n\n";
	}

	public String deposit(int actNum, double Amount) throws AccountClosedException, NoSuchAccountException {
		if (actNum > account.size() || actNum == 0) {
			throw new NoSuchAccountException("\nAccount not found\n\n");
			// return "Account not found";
		} else if (account.get(actNum - 1).getaccountStatus() == "Account Open") {
			account.get(actNum - 1).AddAmount(Amount);
			TransactionNum = TransactionNum + 1;
			TranObj = new Transaction(TransactionNum, "Credit", Amount);
			account.get(actNum - 1).AddTransaction(TranObj);
			return "Deposit successful, the new balance is: " + account.get(actNum - 1).getBalance();
		} else if (account.get(actNum - 1).getaccountStatus() == "Account Closed") {
			throw new AccountClosedException(
					"\nDeposit failed, the balance is: " + account.get(actNum - 1).getBalance() + "\n\n");
		}
		return "Deposit failed, the balance is: " + account.get(actNum - 1).getBalance() + "\n";
	}

	public String withdraw(int actNum, double Amount)
			throws AccountClosedException, InsufficientBalanceException, NoSuchAccountException {
		if (actNum > account.size() || actNum == 0) {
			throw new NoSuchAccountException("\nAccount not found\n\n");
			// return "Account not found";
		} else if (!(account.get(actNum - 1).getDebit() >= Amount)) {
			throw new InsufficientBalanceException("\nSorry, the balance is insufficient\n\n");
		} else if (account.get(actNum - 1).getaccountStatus() == "Account Closed"
				&& (account.get(actNum - 1).getBalance() == 0 || account.get(actNum - 1).getBalance() < 0)) {
			throw new AccountClosedException(
					"\nWithdrawal failed, the balance is: " + account.get(actNum - 1).getBalance() + "\n\n");
		} else if (account.get(actNum - 1).getDebit() >= Amount) {
			account.get(actNum - 1).SubAmount(Amount);
			TransactionNum = TransactionNum + 1;
			TranObj = new Transaction(TransactionNum, "Debit", Amount);
			account.get(actNum - 1).AddTransaction(TranObj);
			return "Withdrawal successful, the new balance is: " + account.get(actNum - 1).getBalance();
		}
		return "Withdrawal failed, the balance is:  " + account.get(actNum - 1).getBalance();
	}

	public String closeAccount(int actNum) throws NoSuchAccountException {
		if (actNum > account.size() || actNum == 0) {
			throw new NoSuchAccountException("\nAccount not found\n\n");
			// return "Account not found\n";
		}
		account.get(actNum - 1).CloseAccont();
		return "Account closed, current balance is " + account.get(actNum - 1).getBalance()
				+ " deposits are no longer possible";
	}

	public void saveTransactions(int actNum) throws IOException {
		ArrayList<String> Tranarray1;
		Tranarray1 = new ArrayList<String>();
		Tranarray1 = account.get(actNum - 1).mymethod();
		File file = new File("transactions.txt");
		file.createNewFile();
		PrintWriter p = new PrintWriter(file);
		for (int i = 0; i < Tranarray1.size(); i++) {
			p.print(Tranarray1.get(i));
		}
		p.print("\n\nBalance: " + account.get(actNum - 1).getBalance());
		p.close();
	}
}
