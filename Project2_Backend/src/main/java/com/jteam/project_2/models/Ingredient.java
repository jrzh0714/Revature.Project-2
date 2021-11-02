package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @JsonManagedReference(value="stepingredientsI")
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "ingredient",fetch = FetchType.LAZY)
    private List<StepIngredient> stepIngredients;

    @Column(name="ingredient_name")
    private String name;

    @Column(name="image")
    private byte[] image;
}
