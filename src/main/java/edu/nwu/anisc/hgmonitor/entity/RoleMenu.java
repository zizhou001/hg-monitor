package edu.nwu.anisc.hgmonitor.entity;

import lombok.Data;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 17:06
 */
@Data
public class RoleMenu extends BaseEntity{
    /**
     * 关联id
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单资源用户id
     */
    private Long menuPermissionId;
}
