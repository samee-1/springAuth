package com.example.auth.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Table(name = "userdb", uniqueConstraints= @UniqueConstraint(columnNames={"email"}))
@Table(name = "userdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "email")
    public String email;


}
