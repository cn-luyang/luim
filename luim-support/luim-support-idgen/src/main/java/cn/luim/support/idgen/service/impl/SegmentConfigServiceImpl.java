package cn.luim.support.idgen.service.impl;

import cn.luim.support.idgen.common.convert.SegmentConfigConvert;
import cn.luim.support.idgen.common.enums.ErrorCode;
import cn.luim.support.idgen.controller.request.SegmentConfigCreateRequest;
import cn.luim.support.idgen.mapper.entity.SegmentConfigDO;
import cn.luim.support.idgen.repository.SegmentConfigRepository;
import cn.luim.support.idgen.service.SegmentConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class SegmentConfigServiceImpl implements SegmentConfigService {

	private final SegmentConfigRepository segmentConfigRepository;
	private final SegmentConfigConvert segmentConfigConvert;


	@Override
	public void create(SegmentConfigCreateRequest request) {
		String businessCode = request.getBusinessCode();
		boolean businessCodeExist = segmentConfigRepository.isBusinessCodeExist(businessCode);
		ErrorCode.BUSINESS_CODE_EXISTS.isTrue(businessCodeExist);

		SegmentConfigDO segmentConfigDO = segmentConfigConvert.toSegmentConfigDO(request);
		segmentConfigRepository.save(segmentConfigDO);
	}
}
