package cn.luim.boot.starter.idgen.dubbo;

import cn.luim.boot.starter.base.model.Result;
import cn.luim.boot.starter.idgen.dubbo.response.GetSegmentResponse;

/**
 * 发号器远程服务
 *
 * @author yang.lu
 */
public interface IdGenRpcService {

	Result<GetSegmentResponse> getSegment(String businessCode);
}
