package com.rsh.tkachenko.microservice.AppToDo.entities;



//import com.sun.istack.NotNull;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//LOMBOK
//JPA
//VALIDATION JSR-303 > Hibernate validator DATA BINDING (email name, password) -> new User(email, name, password)


@Entity
@Table(name = "user")
//@AllArgsConstructor
//@NoArgsConstructor
public class User {

    @Id
    @Column(name = "EMAIL")
    //@Getter
    //@Setter
    @NotNull @NotBlank @NotEmpty
    private String email;

    @Column(name = "NAME")
    //@Getter
    //@Setter
    @NotNull @NotBlank @NotEmpty
    private String name;

    @Column(name = "PASSWORD")
    //@Getter
    //@Setter
    @NotNull @NotBlank @NotEmpty
    private String password;







//Use Lombok instead
    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
