package top.qoj.dao.user.impl;

import top.qoj.dao.user.RoleAuthEntityService;
import top.qoj.mapper.RoleAuthMapper;
import top.qoj.pojo.entity.user.RoleAuth;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class RoleAuthEntityServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuth> implements RoleAuthEntityService {

}
