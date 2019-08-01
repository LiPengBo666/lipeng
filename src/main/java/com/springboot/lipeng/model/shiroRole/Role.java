package com.springboot.lipeng.model.shiroRole;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private Integer id;

    private String roleName;

    private String type;

    private static final long serialVersionUID = 1L;
    /**
     * 权限列表
     * */
    private List<Permit> Permits;


    public List<Permit> getPermits() {

        return Permits;
    }

    public void setPermits(List<Permit> permits) {

        Permits = permits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }
}