package edu.nwu.anisc.hgmonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 18:38
 */
@Data
public class MenuDTO {
    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单id" )
    private Long menuId;

    @NotNull(message = "parentId NotNull")
    @Schema(description = "父菜单ID，一级菜单为0" )
    private Long parentId;

    @Schema(description = "权限，需要有哪个权限才能访问该菜单" )
    private String permission;

    @Schema(description = "路径" )
    private String path;

    @NotBlank(message = "component NotBlank")
    @Schema(description = "组件如：1.'Layout' 为布局，不会跳页面 2.'components-demo/tinymce' 跳转到该页面" )
    private String component;


    @NotBlank(message = "name NotBlank")
    @Schema(description = "设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题" )
    private String name;
}
