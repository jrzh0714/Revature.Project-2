package com.jteam.project_2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="user_roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole implements Serializable {
    @Id
    @Column(name="user_id")
    private int userId;

    @Id
    @Column(name="role_id")
    private int roleId;
}
