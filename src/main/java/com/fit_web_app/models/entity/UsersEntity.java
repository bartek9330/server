package com.fit_web_app.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "fit_web_app")
public class UsersEntity implements AbstractDomain {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "password")
    private String password;

    @ManyToOne(targetEntity = RolesEntity.class)
    @JoinColumn(name = "role_id")
    private RolesEntity rolesEntity;

    @Basic
    @Column(name = "email")
    private String email;

    public UsersEntity() {
    }

    public UsersEntity(String login, String password, String email, RolesEntity rolesEntity) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.rolesEntity = rolesEntity;
    }

    public UsersEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(RolesEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id && rolesEntity == that.rolesEntity && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, rolesEntity, email);
    }
}
