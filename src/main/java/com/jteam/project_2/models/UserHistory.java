package com.jteam.project_2.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Table(name = "user_history")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserHistory {
    // composite key
    @Id
    private int userId;
    @Id
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
