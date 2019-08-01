package com.springboot.lipeng.model.shiroRole;

import java.io.Serializable;

public class Permit implements Serializable {
    private Integer id;
    /**
     * 权限名称
     * */
    private String permitName;
    /**
     * 标题
     * */
    private String title;

    private String icon;

    private String herf;
    /**
     * 父权限id
     * */
    private Integer parentId;
    /**
     * 菜单位置
     * */
    private Integer site;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName == null ? null : permitName.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getHerf() {
        return herf;
    }

    public void setHerf(String herf) {
        this.herf = herf == null ? null : herf.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSite() {
        return site;
    }

    public void setSite(Integer site) {
        this.site = site;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", permitName=").append(permitName);
        sb.append(", title=").append(title);
        sb.append(", icon=").append(icon);
        sb.append(", herf=").append(herf);
        sb.append(", parentId=").append(parentId);
        sb.append(", site=").append(site);
        sb.append("]");
        return sb.toString();
    }
}