package com.shop_online.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum GenderEnum {
	MAN(0, "男"), WOMAN(1, "女"), SECRET(2, "保密"), UNKNOWN(23, "未知");

	private final Integer value;
	private final String name;


	public static GenderEnum convert(Integer value) {
		return Stream.of(values()).filter(bean -> bean.value.equals(value))
				.findAny().orElse(UNKNOWN);
	}

	public static GenderEnum convert(String name) {
		return Stream.of(values()).filter(bean -> bean.name.equals(name))
				.findAny().orElse(UNKNOWN);

	}
}
