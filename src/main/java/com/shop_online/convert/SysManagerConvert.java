package com.shop_online.convert;


import com.shop_online.entity.SysManager;
import com.shop_online.security.user.ManagerDetail;
import com.shop_online.vo.SysManagerVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysManagerConvert {
	SysManagerConvert INSTANCE = Mappers.getMapper(SysManagerConvert.class);

	SysManager convert(SysManagerVO vo);

	ManagerDetail convertDetail(SysManager entity);

	List<SysManagerVO> convertList(List<SysManager> list);
}
