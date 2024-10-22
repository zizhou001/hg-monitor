package edu.nwu.anisc.hgmonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 18:33
 */
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id" )
    private Long roleId;

    @Schema(description = "角色名称" )
    private String roleName;

    @Schema(description = "备注" )
    private String remark;

    /**
     * 表示角色可以访问的菜单项的ID。
     * 这些菜单项构成了系统的导航结构，
     * 用户可以通过这些菜单项访问不同的功能模块。
     */
    @Schema(description = "菜单id列表" )
    private List<Long> menuIds;

    /**
     *用于表示角色对特定菜单项下的具体操作权限。
     *这些权限可以是增删改查等细粒度的操作。
     */
    @Schema(description = "菜单资源id列表" )
    private List<Long> menuPermissionIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    public List<Long> getMenuPermissionIds() {
        return menuPermissionIds;
    }

    public void setMenuPermissionIds(List<Long> menuPermissionIds) {
        this.menuPermissionIds = menuPermissionIds;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                ", menuIds=" + menuIds +
                ", menuPermissionIds=" + menuPermissionIds +
                '}';
    }
}
