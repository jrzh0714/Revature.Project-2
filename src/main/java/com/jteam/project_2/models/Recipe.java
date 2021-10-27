package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Table(name = "recipes")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)

public class Recipe implements Serializable,Cloneable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="recipe_id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @JoinColumn(name = "user_id",insertable = false, updatable = false )
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe", fetch = FetchType.LAZY)
    @JsonManagedReference(value="steps")
    private List<Step> recipeSteps;

    @ManyToMany(mappedBy = "likedRecipes")
    @JsonIgnore
    private List<User> likers;

    @Column(name="recipe_name")
    private String name;

    @Column(name="rating")
    private double rating;

    @JsonProperty("thumbnail")
    @Column(name="thumbnail")
    private byte[] thumbnail;

    @Column(name="likes")
    private int likes;

    @Column(name="view_count")
    private int viewCount;

    @Column(name="publish_date")
    private Date publishDate;

    @Column(name="difficulty")
    private int Difficulty;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeSteps=" + recipeSteps +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", likes=" + likes +
                ", viewCount=" + viewCount +
                ", publishDate=" + publishDate +
                ", Difficulty=" + Difficulty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }


}
