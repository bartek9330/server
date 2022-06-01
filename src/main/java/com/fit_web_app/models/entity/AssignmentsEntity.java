package com.fit_web_app.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "assignments", schema = "public", catalog = "fit_web_app")
public class AssignmentsEntity implements AbstractDomain {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(targetEntity = UsersEntity.class)
    @JoinColumn(name = "user_id")
    private UsersEntity userEntity;

    @ManyToOne(targetEntity = CoursesEntity.class)
    @JoinColumn(name = "cours_id")
    private CoursesEntity coursesEntity;

    public AssignmentsEntity(UsersEntity userEntity, CoursesEntity coursesEntity) {
        this.userEntity = userEntity;
        this.coursesEntity = coursesEntity;
    }

    public AssignmentsEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsersEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UsersEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CoursesEntity getCoursesEntity() {
        return coursesEntity;
    }

    public void setCoursesEntity(CoursesEntity coursesEntity) {
        this.coursesEntity = coursesEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentsEntity that = (AssignmentsEntity) o;
        return id == that.id && userEntity == that.userEntity && coursesEntity == that.coursesEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userEntity, coursesEntity);
    }
}