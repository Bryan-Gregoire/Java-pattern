package g53735.mentoring.dto;

import java.util.Objects;

/**
 *
 * @author g53735
 */
public class StudentDto {

    private int matricule;
    private String firstName;
    private String lastName;

    public StudentDto(int matricule, String firstName, String lastName) {
        this.matricule = matricule;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getMatricule() {
        return matricule;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentDto other = (StudentDto) obj;
        if (this.matricule != other.matricule) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentDto{" + "matricule=" + matricule + ", firstName="
                + firstName + ", lastName=" + lastName + '}';
    }

}
