package cn.luim.platform.uac.repository;

import cn.luim.platform.uac.mapper.UserMapper;
import cn.luim.platform.uac.model.entity.UserDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 用户数据仓储
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class UserRepository extends ServiceImpl<UserMapper, UserDO> {

	private final UserMapper userMapper;
}
