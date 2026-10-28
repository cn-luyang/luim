package cn.luim.support.idgen.remote;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.idgen.dubbo.IdGenRpcService;
import cn.luim.boot.starter.idgen.dubbo.response.GetSegmentResponse;
import cn.luim.support.idgen.service.SegmentAllocService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author yang.lu
 */
@DubboService
@RequiredArgsConstructor
public class IdGenRpcServiceImpl implements IdGenRpcService {

	private final SegmentAllocService segmentAllocService;

	@Override
	public Result<GetSegmentResponse> getSegment(String businessCode) {
		return null;
	}
}
