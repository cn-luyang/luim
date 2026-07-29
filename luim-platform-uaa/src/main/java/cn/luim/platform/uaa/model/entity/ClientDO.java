package cn.luim.platform.uaa.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_client", autoResultMap = true)
public class ClientDO {

	@TableId
	private Long id;
	private String clientId;
	private String clientName;
	private String clientSecret;
	@TableField(typeHandler = JacksonTypeHandler.class)
	private List<String> redirectUris;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private String description;
}
