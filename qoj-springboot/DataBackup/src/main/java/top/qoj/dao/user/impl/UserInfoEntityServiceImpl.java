package top.qoj.dao.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.qoj.dao.user.UserInfoEntityService;
import top.qoj.mapper.UserInfoMapper;
import top.qoj.pojo.dto.RegisterDTO;
import top.qoj.pojo.entity.user.UserInfo;
import top.qoj.utils.Constants;
import top.qoj.utils.RedisUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class UserInfoEntityServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoEntityService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Boolean addUser(RegisterDTO registerDto) {
        return userInfoMapper.addUser(registerDto) == 1;
    }

    @Override
    public List<String> getSuperAdminUidList() {

        String cacheKey = Constants.Account.SUPER_ADMIN_UID_LIST_CACHE.getCode();
        List<String> superAdminUidList = (List<String>) redisUtils.get(cacheKey);
        if (superAdminUidList == null) {
            superAdminUidList = userInfoMapper.getSuperAdminUidList();
            redisUtils.set(cacheKey, superAdminUidList, 12 * 3600);
        }
        return superAdminUidList;
    }

    @Override
    public List<String> getProblemAdminUidList() {
        return userInfoMapper.getProblemAdminUidList();
    }

}
