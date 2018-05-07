package se.frost.falldetectionproxyapi.entities;

import javax.persistence.*;

@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"User_id", "email"}),
        @UniqueConstraint(columnNames = {"User_id", "phoneNumber"})
})
@Embeddable
public class Contact {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
