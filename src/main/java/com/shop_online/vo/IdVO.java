package com.shop_online.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 当参数只有id时
 *
 * @Author ycshang
 */
@Data
@Schema(description = "ID参数")
public class IdVO {

    @Schema(description = "pkID")
    private Integer id;
}
