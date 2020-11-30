package com.rsh.tkachenko.microservice.AppToDo.entities;


//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="todos")
//@AllArgsConstructor @NoArgsConstructor
public class ToDo {

    @Id
    //@Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;

    //@Getter @Setter
    @Column(name="DESCRIPTION")
    @NotNull
    @NotEmpty @NotBlank
    private String description;

    @Column(name="DATE")
    //@Getter @Setter
    private Date date;

    @Column(name="PRIORITY")
    //@Getter @Setter
    @NotNull @NotEmpty @NotBlank
    private String priority;

    @Column(name="FK_USER")
    //@Getter @Setter
    @NotNull @NotEmpty @NotBlank
    private String fkUser;

    @PrePersist
    void getTimeOperation(){
        this.date = new Date();
    }

    public ToDo() {
    }

    public ToDo(Integer id, @NotEmpty @NotBlank String description, Date date, @NotEmpty @NotBlank String priority, @NotEmpty @NotBlank String fkUser) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.fkUser = fkUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getFkUser() {
        return fkUser;
    }

    public void setFkUser(String fkUser) {
        this.fkUser = fkUser;
    }
}
