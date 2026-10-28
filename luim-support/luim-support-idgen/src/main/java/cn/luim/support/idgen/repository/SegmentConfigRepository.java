package cn.luim.support.idgen.repository;

import cn.luim.support.idgen.mapper.SegmentConfigMapper;
import cn.luim.support.idgen.mapper.entity.SegmentConfigDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class SegmentConfigRepository extends ServiceImpl<SegmentConfigMapper, SegmentConfigDO> {

	@Override
	public boolean save(SegmentConfigDO entity) {
		return super.save(entity);
	}

	public boolean isBusinessCodeExist(String businessCode) {
		return true;
	}
}
