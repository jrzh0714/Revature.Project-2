package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.sql.Date;
import com.jteam.project_2.models.UserDemographic;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="fname")
    private String firstname;

    @Column(name="lname")
    private String lastname;

    @Column(name="email")
    private String email;

    @Column(name="avatar_URL")
    private String avatarURL;

    @Column(name="hash")
    private String hash;

    @JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private UserDemographic demographic;

    @OneToMany
    private List<UserHistory> userHistory;
}
