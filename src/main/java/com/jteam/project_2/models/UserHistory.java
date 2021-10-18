package com.jteam.project_2.models;

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
@Data
@RequiredArgsConstructor
public class UserHistory implements Serializable {

    @Id
    @Column(name="user_id")
    private int userId;
    @Id
    @Column(name="recipe_id")
    private int recipeId;

    @Column(name="most_recent_view")
    private Date mostRecentView;

    @Column(name="cooked")
    private boolean cooked;

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
