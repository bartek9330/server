package com.fit_web_app.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "steps", schema = "public", catalog = "fit_web_app")
public class StepsEntity implements AbstractDomain {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "step_number")
    private int stepNumber;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "image")
    private byte[] image;

    @Basic
    @Column(name = "course_id")
    private int courseId;

    public StepsEntity() {
    }

    public StepsEntity(int stepNumber, String name, String description, byte[] image, int courseId) {
        this.stepNumber = stepNumber;
        this.name = name;
        this.description = description;
        this.image = image;
        this.courseId = courseId;
    }

    public String getImage() {
        return new String(image);
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepsEntity that = (StepsEntity) o;
        return id == that.id && stepNumber == that.stepNumber && name == that.name && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stepNumber, name, description);
    }
}
