//package cn.luim.platform.uac.remote;
//
//import cn.luim.boot.starter.base.enums.ResultEnum;
//import cn.luim.boot.starter.base.model.Result;
//import cn.luim.boot.starter.base.utils.ObjectUtil;
//import cn.luim.platform.api.uac.AccountRpcService;
//import cn.luim.platform.api.uac.model.request.AccountAuthRequest;
//import cn.luim.platform.uac.service.AccountService;
//import cn.luim.platform.uac.service.PasswordService;
//import lombok.RequiredArgsConstructor;
//import org.apache.dubbo.config.annotation.DubboService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * Dubbo 账号相关远程 RPC 接口实现
// *
// * @author yang.lu
// */
//@DubboService
//@RequiredArgsConstructor
//public class AccountRpcServiceImpl implements AccountRpcService {
//
//	private static final Logger logger = LoggerFactory.getLogger(AccountRpcServiceImpl.class);
//
//	private final AccountService accountService;
//	private final PasswordService passwordService;
//
//	@Override
//	public Result<String> validateAccount(AccountAuthRequest request) {
//
//		if (ObjectUtil.isNull(request)) {
//			logger.warn("[RPC-账号验证] 请求参数为空");
//			return Result.failure(ResultEnum.PARAM_MISSING, "认证请求参数为空");
//		}
//
//		// 获取账号信息
//		AccountDetailDTO accountDetailDTO = accountService.getDetail(request.getAccount(), request.getAccountType());
//		if (ObjectUtil.isNull(accountDetailDTO)) {
//			logger.warn("[RPC-账号验证] 账号不存在 | account={}, accountType={}", request.getAccount(), request.getAccountType());
//			return Result.failure(ResultEnum.DATA_NOT_FOUND, "账号不存在");
//		}
//
//		// 验证账号密码
//		boolean correctPassword = passwordService.validatePassword(accountDetailDTO.getAccountId(), request.getCredential());
//		if (!correctPassword) {
//			logger.warn("[RPC-账号验证] 密码验证失败 | accountId={}", accountDetailDTO.getAccountId());
//			return Result.failure(ResultEnum.DATA_NOT_MATCH, "密码验证未通过");
//		}
//
//		logger.info("[RPC-账号验证] 验证成功 | account={}, userId={}", request.getAccount(), accountDetailDTO.getUserId());
//
//		return Result.success(accountDetailDTO.getUserId());
//	}
//}
