package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "user_demographics")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDemographic {

    @Id
    private int id;

    @JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

}
