// Mantra Mehta(mmehta2@toromail.csudh.edu)
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static final String[] menuOptions = { "Open a Checking account%n", "Open Saving Account%n",
			"List Accounts%n", "Account Statement%n", "Deposit funds%n", "Withdraw funds%n", "Close an account%n",
			"Save Transactions%n", "Exit%n" };
	public static final String INPUT_PROMPT = "Enter choice";

	public static void main(String[] args) throws IOException, AccountClosedException, InsufficientBalanceException, NoSuchAccountException {

		UI ui = new UI(System.in, System.out, menuOptions, INPUT_PROMPT);
		Bank bank = new Bank();
		while (true) {
			int actNum = 0;
			String firstName;
			String lastName;
			String SSN;
			double od;
			double Amount;
			int option = ui.getMainOption(); // to render main options(user interface)
			if (option == 9) { // exit from the program
				System.out.print("Goodbye!");
				System.exit(0);
			} else if (option < 3) { // creating a bank account "Checking" or "Saving"
				firstName = ui.readToken("Enter first name: ");
				lastName = ui.readToken("Enter last name: ");
				SSN = ui.readToken("Enter SSN: ");
				if (option == 1) {
					od = ui.readDouble("Enter overdraft limit: ");
				} else {
					od = 0.0;
				}
				System.out.print(bank.createAccount(firstName, lastName, SSN, od) + "\n\n");
			} else if (option == 3) { // listing accounts previously made
				ArrayList<String> accountArray;
				accountArray = new ArrayList<String>();
				accountArray = bank.listAcounts();
				for (int i = 0; i < accountArray.size(); i++) {
					System.out.print(accountArray.get(i));
				}
				System.out.println();
			} else if (option == 4) { // Account Statement
				actNum = ui.readInt("Please enter your account number: ");
				ArrayList<String> Tranarray1;
				Tranarray1 = new ArrayList<String>();
				Tranarray1 = bank.accountStatements(actNum);
				for (int i = 0; i < Tranarray1.size(); i++) {
					System.out.print(Tranarray1.get(i));
				}
				System.out.print(bank.accountStatementsFinal(actNum));
			}
			  else if (option == 5) { // deposit amount
				actNum = ui.readInt("Please enter your account number: ");
				Amount = ui.readDouble("Enter amount: ");
				ui.exc1(actNum, Amount, bank);
				
			} else if (option == 6) { // withdraw amount
				actNum = ui.readInt("Please enter your account number: ");
				Amount = ui.readDouble("Enter amount: ");
				ui.exc2(actNum, Amount, bank);
			} else if (option == 7) { // close account
				actNum = ui.readInt("Please enter your account number: ");
				//System.out.print(bank.closeAccount(actNum) + "\n\n");
				ui.exc3(actNum, bank);
			} else if (option == 8) { //save transactions
				actNum = ui.readInt("Please enter your account number: ");
				bank.saveTransactions(actNum);
			}
		}
	}
}