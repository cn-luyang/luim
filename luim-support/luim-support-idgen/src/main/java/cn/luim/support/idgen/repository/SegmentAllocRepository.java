package cn.luim.support.idgen.repository;

import cn.luim.support.idgen.mapper.SegmentAllocMapper;
import cn.luim.support.idgen.mapper.entity.SegmentAllocDO;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class SegmentAllocRepository extends ServiceImpl<SegmentAllocMapper, SegmentAllocDO> {
}
