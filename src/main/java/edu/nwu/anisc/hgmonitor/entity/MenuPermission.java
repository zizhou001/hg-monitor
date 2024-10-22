package edu.nwu.anisc.hgmonitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 17:07
 */
@Data
public class MenuPermission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单资源id
     */
    private Long menuPermissionId;

    /**
     * 资源关联菜单
     */
    private Long menuId;

    /**
     * 权限对应的编码
     */
    private String permission;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源对应服务器路径
     */
    private String uri;

    /**
     * 请求方法 1.GET 2.POST 3.PUT 4.DELETE
     */
    private Integer method;
}
