//Mantra Mehta(mmehta2@toromail.csudh.edu)
public class User {
    private String firstName;
    private String lastName;
    private String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public String getEmail() {
        return email;
    }
}
