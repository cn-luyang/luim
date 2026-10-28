package cn.luim.support.idgen.common.convert;

import cn.luim.support.idgen.controller.request.SegmentConfigCreateRequest;
import cn.luim.support.idgen.mapper.entity.SegmentConfigDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SegmentConfigConvert {

	SegmentConfigDO toSegmentConfigDO(SegmentConfigCreateRequest request);
}
