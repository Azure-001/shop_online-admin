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
@TableName("sys_manager_role")
@ApiModel(value = "ManagerRole对象", description = "")
public class SysManagerRole {

	@ApiModelProperty("自增主键")
	@TableId(value = "pk_id", type = IdType.AUTO)
	private Integer pkId;

	@ApiModelProperty("管理员id")
	@TableField("manager_id")
	private Integer managerId;

	@ApiModelProperty("角色id")
	@TableField("role_id")
	private Integer roleId;

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
}
