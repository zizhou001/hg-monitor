package edu.nwu.anisc.hgmonitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 17:29
 */
@Data
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 头像图片路径
     */
    private String pic;

    /**
     * 状态 1 正常 0 无效
     */
    private Integer status;
}
