package com.shop_online.convert;


import com.shop_online.entity.SysMenu;
import com.shop_online.vo.SysMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *
 */
@Mapper
public interface SysMenuConvert {
	SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

	SysMenu convert(SysMenuVO vo);

	SysMenuVO convert(SysMenu entity);

	List<SysMenuVO> convertList(List<SysMenu> list);

}
