package cn.luim.platform.uaa.model.entity;

import cn.luim.boot.starter.mybatis.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_token", autoResultMap = true)
public class TokenDO extends BaseEntity {

	@TableId
	private Long id;
	private String clientId;
	private String userId;
	private String accessToken;
	private String refreshToken;
	private LocalDateTime accessTokenIssuedTime;
	private LocalDateTime accessTokenExpiresTime;
	private LocalDateTime refreshTokenExpiresTime;
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Map<String, Object> extraInfo;
}
