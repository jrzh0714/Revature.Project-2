package com.jteam.project_2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Table(name = "steps")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Step implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="step_id")
    private int id;

    @JoinColumn(name = "recipe_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    @MapsId
    private Recipe recipe;

    @Column(name="step_desc")
    private String stepDescription;

    @Column(name="step_number")
    private int stepNumber;

    @Column(name="image")
    private byte[] image;

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", stepDescription='" + stepDescription + '\'' +
                ", stepNumber=" + stepNumber +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Step step = (Step) o;
        return Objects.equals(id, step.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
