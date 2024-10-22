package edu.nwu.anisc.hgmonitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 16:53
 */
@Data
public class Role extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;
}
