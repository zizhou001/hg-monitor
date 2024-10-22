package edu.nwu.anisc.hgmonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 18:45
 */
public class MenuWithPermissionIdDTO {
    @Schema(description = "菜单id" )
    private Long menuId;

    @Schema(description = "菜单下的权限id列表" )
    private List<Long> permissionIds;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "MenuWithPermissionIdDTO{" +
                "menuId=" + menuId +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
