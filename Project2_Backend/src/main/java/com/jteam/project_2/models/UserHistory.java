package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Table(name = "user_history")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(UserHistoryID.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class UserHistory implements Serializable {

    @Id
    @Column(name="user_id")
    private int userId;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    @MapsId
    private User user;

    @Id
    @Column(name="recipe_id")
    private int recipeId;

    @JoinColumn(name = "recipe_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    @MapsId
    private Recipe recipe;

    @Column(name="most_recent_view")
    private Date mostRecentView;

    @Column(name="cooked")
    private boolean cooked;

    public String toString() {
        return "UserHistory{" +
                "userId=" + userId +
                ", recipeId=" + recipeId +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserHistory that = (UserHistory) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
