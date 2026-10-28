package cn.luim.support.idgen.controller;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.support.idgen.controller.request.SegmentConfigCreateRequest;
import cn.luim.support.idgen.service.SegmentConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/segment-config")
public class SegmentConfigController {

	private final SegmentConfigService segmentConfigService;

	@PostMapping
	public Result<Void> create(@Valid SegmentConfigCreateRequest request) {
		segmentConfigService.create(request);
		return Result.success();
	}
}
