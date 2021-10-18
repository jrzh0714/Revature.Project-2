package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Table(name = "user_demographics")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDemographic {

    @Id
    @Column(name="user_id")
    private int id;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    @MapsId
    private User user;

    @Column(name="gender")
    private int gender;

    @Column(name="birthdate")
    private Date birthdate;

    public String toString() {
        return "UserDemographic{" +
                "id=" + id +
                ", gender=" + gender +
                ", birthdate=" + birthdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserDemographic that = (UserDemographic) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
