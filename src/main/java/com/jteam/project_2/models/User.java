package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.sql.Date;
import com.jteam.project_2.models.UserDemographic;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;

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

    @Column(name="user_password")
    private String hash;

    @JsonIgnore
    @JsonManagedReference(value="userdemographic")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY)
    private UserDemographic demographic;

    @JsonIgnore
    @JsonManagedReference(value="useraddress")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY)
    private UserAddress address;

    @JsonIgnore
    @JsonManagedReference(value="userrecipe")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY)
    private List<Recipe> userRecipeList;

    @JsonIgnore
    @JsonManagedReference(value="userhistory")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY)
    private List<UserHistory> userHistory;

    /*
    *
    *
    * @JsonManagedReference
    @OneToMany
    //composite foreign key
    private List<UserRole> userRoleList;


    * */

    @Override
    public String toString() {
        return "User{" +
                "id=" + user_id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", hash='" + hash + '\'' +
                ", demographic=" + demographic +
                '}';
    }
    


}
