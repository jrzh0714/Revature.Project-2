package com.jteam.project_2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "user_history")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserHistory implements Serializable {
    @Id
    private int userId;
    @Id
    private int recipeId;

    @Column(name="most_recent_view")
    private Date mostRecentView;

    @Column(name="cooked")
    private boolean cooked;
}
