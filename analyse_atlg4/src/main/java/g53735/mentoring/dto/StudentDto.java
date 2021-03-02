package g53735.mentoring.dto;

/**
 *
 * @author g53735
 */
public class StudentDto extends Dto<Integer> {

    private String firstName;
    private String lastName;

    public StudentDto(int matricule, String firstName, String lastName) {
        this.key = matricule;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "StudentDto{" + "matricule=" + key + ", firstName="
                + firstName + ", lastName=" + lastName + '}';
    }

}
