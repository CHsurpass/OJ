package top.qoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.qoj.pojo.entity.user.UserInfo;
import top.qoj.pojo.dto.RegisterDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    int addUser(RegisterDTO registerDto);

    List<String> getSuperAdminUidList();

    List<String> getProblemAdminUidList();
}
