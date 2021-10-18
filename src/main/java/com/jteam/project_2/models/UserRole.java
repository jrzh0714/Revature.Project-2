package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "user_roles")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    private int id;

    private int role_id;

    private int user_id;

    private Date from_date;




}
