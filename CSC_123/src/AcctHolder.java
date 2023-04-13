// Mantra Mehta(mmehta2@toromail.csudh.edu)
public class AcctHolder {
	private String firstName;
	private String lastName;
	private String SSN;

	public AcctHolder(String firstName, String lastName, String sSN) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		SSN = sSN;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getSSN() {
		return this.SSN;
	}

}
