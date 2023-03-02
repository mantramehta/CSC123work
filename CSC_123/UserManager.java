//Mantra Mehta(mmehta2@toromail.csudh.edu)
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
	private ArrayList<User> users;

	public UserManager() {
		users = new ArrayList<User>();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		int choice;
		while (true) {
			System.out.println("1 - Register User");
			System.out.println("2 - List Users");
			System.out.println("3 - Exit");
			System.out.print("\n");
			System.out.print("Please enter your choice: ");
			choice = sc.nextInt();
			sc.nextLine(); // consume the newline character

			switch (choice) {
			case 1:
				System.out.print("Enter the first name: ");
				String firstName = sc.nextLine();
				System.out.print("Enter last name: ");
				String lastName = sc.nextLine();
				System.out.print("Enter email address: ");
				String email = sc.nextLine();
				User user = new User(firstName, lastName, email);
				users.add(user);
				System.out.println("Thank you, user " + user.getFullName() + " " + "(" + user.getEmail() +")" + " has been registered");
				System.out.print("\n");
				break;
			case 2:
				for (int i = 0; i < users.size(); i++) {

					System.out.print(
							(" ") + (i + 1) + " - " + users.get(i).getFullName() + " " + "(" + users.get(i).getEmail() + ")");
				}
				System.out.println("\n\nTotal users: " + users.size() + "\n");
				break;

			case 3:
				System.out.println("Thank you for using User Manager, Goodbye");
				System.exit(0); // exit the program
				break;

			default:
				System.out.println("Invalid choice, please enter a number between 1 and 3");
			}
		}
	}
	public static void main(String[] args) {
		UserManager ums = new UserManager();
		ums.run();
	}
}
