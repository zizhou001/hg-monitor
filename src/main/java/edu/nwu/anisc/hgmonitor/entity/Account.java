package edu.nwu.anisc.hgmonitor.entity;

import lombok.Data;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 18:18
 */
@Data
public class Account extends BaseEntity{

    /**
     * 全平台用户唯一id
     */
    private Long uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态 1:启用 0:禁用 -1:删除
     */
    private Integer status;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 是否是管理员
     */
    private Integer isAdmin;
}
