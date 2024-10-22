package edu.nwu.anisc.hgmonitor.entity;

import lombok.Data;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 17:04
 */
@Data
public class UserRole extends BaseEntity{
    /**
     * 关联id
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
