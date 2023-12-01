package com.shop_online.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeleteFlagEnum {
	NOT_DELETE(0, "未删除"), HAD_DELETED(1, "已删除");
	private final Integer value;
	private final String name;
}
