package se.frost.falldetectionproxyapi.entities;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User implements DbEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String username;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String password;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String firstName;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String lastName;

    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    List<Contact> contacts = new ArrayList<>();

    public User() {
    }

    public User(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password, @NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName, @NotNull List<Contact> contacts) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
