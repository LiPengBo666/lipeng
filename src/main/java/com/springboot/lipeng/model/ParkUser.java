package com.springboot.lipeng.model;

public class ParkUser {
    private int id;
    private int userIdLp;
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserIdLp() {
        return userIdLp;
    }

    public void setUserIdLp(int userIdLp) {
        this.userIdLp = userIdLp;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ParkUser{" +
                "id=" + id +
                ", userIdLp=" + userIdLp +
                ", roleId=" + roleId +
                '}';
    }
}
