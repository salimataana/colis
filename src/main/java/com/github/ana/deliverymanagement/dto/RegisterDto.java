package com.github.ana.deliverymanagement.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


//@Getter
//@Setter


public class RegisterDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    private String phone;

    private String address;

    //@Size(min = 6, message = "Minimum Password length is 6 characters")
    private String password;

    private String confirmPassword;

    private String role;

    public @NotEmpty String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty String lastName) {
        this.lastName = lastName;
    }

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
