package cn.luim.support.idgen.remote;

import cn.luim.boot.starter.idgen.dubbo.IdGenRpcService;
import cn.luim.boot.starter.idgen.dubbo.response.SegmentResponse;

/**
 * @author yang.lu
 */
public class IdGenRpcServiceImpl implements IdGenRpcService {

	@Override
	public SegmentResponse getSegment(String businessCode) {
		return null;
	}
}
