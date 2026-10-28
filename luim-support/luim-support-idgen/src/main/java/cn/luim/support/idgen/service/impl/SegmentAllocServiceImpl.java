package cn.luim.support.idgen.service.impl;

import cn.luim.boot.starter.base.utils.StringUtil;
import cn.luim.support.idgen.mapper.entity.SegmentAllocDO;
import cn.luim.support.idgen.repository.SegmentAllocRepository;
import cn.luim.support.idgen.service.SegmentAllocService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class SegmentAllocServiceImpl implements SegmentAllocService {

	private final SegmentAllocRepository segmentAllocRepository;

	public void getSegment(String businessCode) {
		if (StringUtil.isBlank(businessCode)) {

		}

		/*
			1.查询号段分配表信息
		 */
	}
}
