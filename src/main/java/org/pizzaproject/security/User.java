package org.pizzaproject.security;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {

    @Min(value = 0, message = "ID must be positive number (or zero)!")
    private int id;
    @NotNull(message = "Name cannot be NULL!")
    @Pattern(regexp = "\\S+", message = "There cannot be whitespaces in username!")
    private String name;
    @NotNull(message = "Password cannot be NULL!")
    @Pattern(regexp = "\\S+", message = "There cannot be whitespaces in password!")
    private String password;
    @Pattern(regexp = "(USER)|(ADMIN)", message = "Incorrect authority!")
    private String userAuthority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userAuthority='" + userAuthority + '\'' +
                '}';
    }
}
