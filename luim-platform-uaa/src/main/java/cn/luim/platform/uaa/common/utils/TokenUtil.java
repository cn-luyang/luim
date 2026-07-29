package cn.luim.platform.uaa.common.utils;

import cn.luim.boot.starter.base.utils.constant.StringPool;
import cn.luim.boot.starter.base.utils.id.IdUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

/**
 * Token 工具类
 *
 * @author yang.lu
 */
@UtilityClass
public class TokenUtil {

	/** 令牌后缀 */
	private static final String SUFFIX = "__";
	/** 访问令牌总长度 */
	private static final int ACCESS_TOKEN_LENGTH = 32;
	/** 刷新令牌总长度 */
	private static final int REFRESH_TOKEN_LENGTH = 64;

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum TokenStyle {

		ACCESS_TOKEN, REFRESH_TOKEN, CLIENT_TOKEN
	}

	public String generateAccessToken(){
		return generateToken(TokenStyle.ACCESS_TOKEN);
	}

	public String generateRefreshToken(){
		return generateToken(TokenStyle.REFRESH_TOKEN);
	}

	/**
	 * 生成 Token 令牌
	 *
	 * @param tokenStyle 令牌风格枚举
	 * @return String
	 * @author yang.lu
	 */
	public static String generateToken(TokenStyle tokenStyle){
		String prefix;
		int totalLength = switch (tokenStyle) {
			case ACCESS_TOKEN -> {
				prefix = "AT_";
				yield ACCESS_TOKEN_LENGTH;
			}
			case REFRESH_TOKEN -> {
				prefix = "RT_";
				yield REFRESH_TOKEN_LENGTH;
			}
			case CLIENT_TOKEN -> {
				prefix = "CT_";
				yield ACCESS_TOKEN_LENGTH;
			}
			default -> throw new IllegalArgumentException("不支持的令牌类型: " + tokenStyle.name());
		};

		int remainingLength = totalLength
			- prefix.length()
			- SUFFIX.length()
			- 2 * StringPool.UNDERLINE.length();

		int segmentLength = remainingLength / 3;
		int lastSegmentLength = remainingLength - 2 * segmentLength;

		return prefix
			+ IdUtil.nanoId(segmentLength)
			+ StringPool.UNDERLINE
			+ IdUtil.nanoId(segmentLength)
			+ StringPool.UNDERLINE
			+ IdUtil.nanoId(lastSegmentLength)
			+ SUFFIX;
	}
}
