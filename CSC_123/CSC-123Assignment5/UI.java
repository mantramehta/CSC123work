// Mantra Mehta(mmehta2@toromail.csudh.edu)
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class UI {
	private String[] menuOptions;
	private String prompt;
	private Scanner scanner;
	private InputStream in;
	private OutputStream out;
	private String intErrorMessage = "\nInvalid input, please type an integer\n";
	private String doubleErrorMessage = "\nInvalid input, please type in a number with decimal point\n";

	public UI(InputStream in, OutputStream out, String[] options, String prompt) {
		this.in = in;
		this.out = out;
		this.scanner = new Scanner(in);
		this.menuOptions = options;
		this.prompt = prompt;
	}

	public String getIntErrorMessage() {
		return intErrorMessage;
	}

	public void setIntErrorMessage(String intErrorMessage) {
		this.intErrorMessage = intErrorMessage;
	}

	public String getDoubleErrorMessage() {
		return doubleErrorMessage;
	}

	public void setDoubleErrorMessage(String doubleErrorMessage) {
		this.doubleErrorMessage = doubleErrorMessage;
	}

	public int getMainOption() throws IOException {
		int choice;
		int menuIndex = 1;
		int totalOptions = menuOptions.length;

		while (true) {
			for (String option : menuOptions)
				print((menuIndex++) + " - " + option, null);

			choice = readInt(String.format("%n%s [1-%d]: ", new Object[] { this.prompt, totalOptions }));
			if (choice > 0 && choice <= totalOptions)
				break;
			else
				System.out.println("Invalid Input");
			System.out.print("\n");
			menuIndex = 1;
		}
		return choice;
	}

	public String readLine(String msg) throws IOException {
		print(msg, null);
		return this.scanner.nextLine();
	}

	public String readToken(String msg) throws IOException {
		print(msg, null);
		return this.scanner.next();
	}

	public double readDouble(String msg) throws IOException {

		while (true) {
			try {
				return Double.parseDouble(readToken(msg));
			} catch (Exception e) {
				print(doubleErrorMessage, null);
			}
		}
	}

	public int readInt(String msg) throws IOException {
		while (true) {
			try {
				return Integer.parseInt(readToken(msg));
			} catch (Exception e) {
				print(intErrorMessage, new Object[] {});
			}
		}
	}

	public void print(String s, Object[] variables) throws IOException {
		s = String.format(s, variables);
		this.out.write(s.getBytes());
		this.out.flush();
	}

	public void exc1(int actNum, double Amount, Bank bank) throws AccountClosedException, NoSuchAccountException {
		try {
			System.out.print("\n" + bank.deposit(actNum, Amount) + "\n\n");
		} catch (AccountClosedException e) {
			System.out.print(e.getMessage());
		} catch (NoSuchAccountException e) {
			System.out.print(e.getMessage());
		}
	}

	public void exc2(int actNum, double Amount, Bank bank)
			throws AccountClosedException, InsufficientBalanceException, NoSuchAccountException {
		try {
			System.out.print("\n" + bank.withdraw(actNum, Amount) + "\n\n");
		} catch (AccountClosedException e) {
			System.out.print(e.getMessage());
		} catch (InsufficientBalanceException e) {
			System.out.print(e.getMessage());
		} catch (NoSuchAccountException e) {
			System.out.print(e.getMessage());
		}
	}

	public void exc3(int actNum, Bank bank) throws NoSuchAccountException {
		try {
			System.out.print("\n" + bank.closeAccount(actNum) + "\n\n");
		} catch (NoSuchAccountException e) {
			System.out.print(e.getMessage());
		}
	}
}

