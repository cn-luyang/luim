package cn.luim.boot.starter.security.context;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * 当前认证用户信息
 *
 * @author yang.lu
 */
@Data
public class UserContext implements Serializable {

	@Serial
	private static final long serialVersionUID = 6743032079745351401L;

	private String userId;
	private String cnName;
	private Set<String> roles;             // 角色集合: ["ADMIN", "USER"]
	private Set<String> permissions;       // 权限集合: ["order:read", "order:write"]
	private long permissionVersion;        // 权限版本号（用于变更检测）
	private long authTimestamp;            // 认证时间戳
	private long expireAt;                 // 过期时间戳

	public boolean isValid() {
		return System.currentTimeMillis() - authTimestamp < 30_000
			&& System.currentTimeMillis() < expireAt;
	}
}
