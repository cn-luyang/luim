//package cn.luim.platform.uac.remote;
//
//import cn.luim.boot.starter.base.enums.ResultEnum;
//import cn.luim.boot.starter.base.model.Result;
//import cn.luim.boot.starter.base.utils.ObjectUtil;
//import cn.luim.boot.starter.base.utils.StringUtil;
//import cn.luim.platform.api.uac.UserRpcService;
//import cn.luim.platform.api.uac.model.response.GetUserInfoResponse;
//import cn.luim.platform.uac.common.convert.UserConvert;
//import cn.luim.platform.uac.service.dto.UserDetailDTO;
//import cn.luim.platform.uac.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.apache.dubbo.config.annotation.DubboService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author yang.lu
// */
//@DubboService
//@RequiredArgsConstructor
//public class UserRpcServiceImpl implements UserRpcService {
//
//	private static final Logger logger = LoggerFactory.getLogger(UserRpcServiceImpl.class);
//
//	private final UserService userService;
//	private final UserConvert userConvert;
//
//	@Override
//	public Result<GetUserInfoResponse> getUserInfo(String userId) {
//
//		logger.info("[RPC-用户信息] 开始获取用户信息 | userId={}", userId);
//
//		if (StringUtil.isBlank(userId)) {
//			logger.warn("[RPC-用户信息] 请求参数为空");
//			return Result.failure(ResultEnum.PARAM_MISSING, "请求参数为空");
//		}
//
//		UserDetailDTO userDetailDTO = userService.getDetail(userId);
//		if (ObjectUtil.isNull(userDetailDTO)) {
//			logger.warn("[RPC-用户信息] 用户不存在 | userId={}", userId);
//			return Result.failure(ResultEnum.DATA_NOT_FOUND, "用户不存在");
//		}
//
//		logger.info("[RPC-用户信息] 获取成功 | userId={}, username={}", userId, userDetailDTO.getChineseName());
//
//		return Result.success(userConvert.toGetUserInfoResponse(userDetailDTO));
//	}
//}
