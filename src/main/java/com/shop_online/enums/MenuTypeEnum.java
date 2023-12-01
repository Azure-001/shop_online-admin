package com.shop_online.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举

 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    /**
     * 菜单目录
     */
    MENU_DIR,
    /**
     * 菜单项
     */
    MENU,
    /**
     * 按钮
     */
    BUTTON;

}
