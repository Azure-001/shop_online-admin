package com.shop_online.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shop_online.serializer.MenuSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
/**
 * <p>
 *
 * </p>
 *
 * @author ycshang
 * @since 2023-11-28
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "")
public class SysMenu {

    @ApiModelProperty("自增主键")
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;

    @ApiModelProperty("父级id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty("菜单类型 menu: 菜单 button: 按钮")
    @TableField("type")
    private String type;

    @ApiModelProperty("打开类型 tab: 选项卡 url: 外链")
    @TableField("open_type")
    private String openType;

    @ApiModelProperty("外链地址")
    @TableField("url")
    private String url;

    @ApiModelProperty("菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)")
    @TableField("auth")
    private String auth;

    @ApiModelProperty("是否缓存 0:true 1:false")
    @TableField("keepalive")
    @JsonSerialize(using = MenuSerializer.class)
    private Integer keepalive;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "是否隐藏 0:true 1:false")
    @JsonSerialize(using = MenuSerializer.class)
    private Integer hide;

    @ApiModelProperty("逻辑删除(0-未删除，1-删除)")
    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "SysMenu{" +
                "pkId=" + pkId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", type='" + type + '\'' +
                ", openType='" + openType + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", auth='" + auth + '\'' +
                ", keepalive=" + keepalive +
                ", sort=" + sort +
                ", hide=" + hide +
                ", deleteFlag=" + deleteFlag +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
