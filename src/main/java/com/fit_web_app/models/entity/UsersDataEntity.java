package com.fit_web_app.models.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users_data", schema = "public", catalog = "fit_web_app")
public class UsersDataEntity implements AbstractDomain {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "height_cm")
    private int heightCm;

    @Basic
    @Column(name = "weight_g")
    private int weightG;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @ManyToOne(targetEntity = UsersEntity.class)
    @JoinColumn(name = "user_id")
    private UsersEntity userEntity;

    public UsersDataEntity() {

    }

    public UsersDataEntity(int heightCm, int weightG, Date date, UsersEntity userEntity) {
        this.heightCm = heightCm;
        this.weightG = weightG;
        this.date = date;
        this.userEntity = userEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(int heightCm) {
        this.heightCm = heightCm;
    }

    public int getWeightG() {
        return weightG;
    }

    public void setWeightG(int weightG) {
        this.weightG = weightG;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UsersEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UsersEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDataEntity that = (UsersDataEntity) o;
        return id == that.id && heightCm == that.heightCm && weightG == that.weightG && userEntity == that.userEntity && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heightCm, weightG, date, userEntity);
    }
}
