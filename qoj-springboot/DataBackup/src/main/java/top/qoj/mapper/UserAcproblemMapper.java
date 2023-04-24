package top.qoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.qoj.pojo.entity.user.UserAcproblem;


/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface UserAcproblemMapper extends BaseMapper<UserAcproblem> {

}
