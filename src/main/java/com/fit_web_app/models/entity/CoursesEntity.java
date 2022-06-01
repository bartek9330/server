package com.fit_web_app.models.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courses", schema = "public", catalog = "fit_web_app")
public class CoursesEntity implements AbstractDomain {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "courseId", fetch = FetchType.EAGER)
    @OrderBy("stepNumber")
    private Set<StepsEntity> steps;

    public CoursesEntity() {
    }

    public CoursesEntity(int id) {
        this.id = id;
    }

    public CoursesEntity(String name, String description, byte[] image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StepsEntity> getSteps() {
        return steps;
    }

    public void setSteps(Set<StepsEntity> steps) {
        this.steps = steps;
    }

    public String getImage() {
        return new String(image);
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesEntity that = (CoursesEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
