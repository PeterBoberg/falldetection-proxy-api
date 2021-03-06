package se.frost.falldetectionproxyapi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;


@Entity
public class User implements DbEntity, UserDetails, Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    @Size(min = 4, message = "Username must be at least 4 characters long")
    private String username;

    @Column(nullable = false)
    @Size(min = 6, message = "password must be at least 6 characters long")
    private String password;

    @Column(nullable = false)
    @Size(min = 1, message = "First name can not be empty")
    private String firstName;

    @Column(nullable = false)
    @Size(min = 1, message = "Last name can not be empty")
    private String lastName;

    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    private List<Contact> contacts = new ArrayList<>();

    public User() {
    }

    public User(String username,
                String password,
                String firstName,
                String lastName,
                List<Contact> contacts) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @JsonIgnore
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void copyValuesFrom(User other) {
        this.username = other.username;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.contacts = new ArrayList<>(other.contacts);
    }
}
