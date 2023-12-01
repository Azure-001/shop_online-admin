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
@TableName("sys_manager")
@ApiModel(value = "Manager对象", description = "")
public class SysManager {

	@ApiModelProperty("自增主键")
	@TableId(value = "pk_id", type = IdType.AUTO)
	private Integer pkId;

	@ApiModelProperty("账号")
	@TableField("username")
	private String username;

	@ApiModelProperty("密码")
	@TableField("password")
	private String password;

	@ApiModelProperty("真实姓名")
	@TableField("real_name")
	private String realName;

	@ApiModelProperty("头像")
	@TableField("avatar")
	private String avatar;

	@ApiModelProperty("超级管理员(0：否，1：是)")
	@TableField("super_admin")
	private Integer superAdmin;

	@ApiModelProperty("状态(0：停用，1：正常)")
	@TableField("status")
	private Integer status;

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
