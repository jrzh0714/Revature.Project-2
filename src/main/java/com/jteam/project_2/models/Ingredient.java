package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "ingredients")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Ingredient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ingredient_id")
    private int id;

    @Column(name="ingredient_name")
    private String name;

    @Column(name="image")
    private byte[] image;
}
