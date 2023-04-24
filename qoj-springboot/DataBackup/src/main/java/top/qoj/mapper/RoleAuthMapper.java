package top.qoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.qoj.pojo.entity.user.RoleAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.qoj.pojo.vo.RoleAuthsVO;


/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface RoleAuthMapper extends BaseMapper<RoleAuth> {
    RoleAuthsVO getRoleAuths(@Param("rid") long rid);
}
