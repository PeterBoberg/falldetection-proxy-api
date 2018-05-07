package se.frost.falldetectionproxyapi.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"User_id", "email"}),
        @UniqueConstraint(columnNames = {"User_id", "phoneNumber"})
})
@Embeddable
public class Contact {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    public Contact() {
    }

    public Contact(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName, @Email String email, @NotNull @NotEmpty String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

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
