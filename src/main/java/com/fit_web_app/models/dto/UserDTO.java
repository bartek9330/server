package com.fit_web_app.models.dto;

public class UserDTO {
    private int id;
    private String login;
    private String email;
    private int roleId;

    public UserDTO(int id, String login, String email, int roleId) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.roleId = roleId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}