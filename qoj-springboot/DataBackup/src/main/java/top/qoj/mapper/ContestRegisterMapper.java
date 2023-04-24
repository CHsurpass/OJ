package top.qoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.qoj.pojo.entity.contest.ContestRegister;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface ContestRegisterMapper extends BaseMapper<ContestRegister> {

}
