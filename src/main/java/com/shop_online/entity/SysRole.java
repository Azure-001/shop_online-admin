package com.shop_online.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "")
public class SysRole {

	@ApiModelProperty("自增主键")
	@TableId(value = "pk_id", type = IdType.AUTO)
	private Integer pkId;

	@ApiModelProperty("角色名称")
	@TableField("name")
	private String name;

	@ApiModelProperty("角色描述")
	@TableField("remark")
	private String remark;

	@ApiModelProperty("逻辑删除(0：正常 1：已删除')")
	@TableField("delete_flag")
	@TableLogic
	private Integer deleteFlag;

	@ApiModelProperty("创建时间")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	@ApiModelProperty("更新时间")
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;
}
