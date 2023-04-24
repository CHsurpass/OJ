package top.qoj.dao.user;

import top.qoj.pojo.entity.user.UserInfo;
import top.qoj.pojo.dto.RegisterDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface UserInfoEntityService extends IService<UserInfo> {

    public Boolean addUser(RegisterDTO registerDto);

    public List<String> getSuperAdminUidList();

    public List<String> getProblemAdminUidList();
}
