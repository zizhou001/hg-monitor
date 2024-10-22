package edu.nwu.anisc.hgmonitor.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 16:36
 */
@Data
public class Menu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 权限，需要有哪个权限才能访问该菜单
     */
    private String permission;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件如：1.'Layout' 为布局，不会跳页面 2.'components-demo/tinymce' 跳转到该页面
     */
    private String component;

    /**
     * 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
     */
    private String name;
}
