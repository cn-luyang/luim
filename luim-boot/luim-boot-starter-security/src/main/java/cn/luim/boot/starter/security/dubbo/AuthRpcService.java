package cn.luim.boot.starter.security.dubbo;

import cn.luim.boot.starter.security.context.UserContext;

public interface AuthRpcService {

	UserContext validateToken(String token);
}
