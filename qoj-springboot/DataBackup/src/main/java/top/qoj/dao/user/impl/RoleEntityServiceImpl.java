package top.qoj.dao.user.impl;

import top.qoj.dao.user.RoleEntityService;
import top.qoj.mapper.RoleMapper;
import top.qoj.pojo.entity.user.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class RoleEntityServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleEntityService {

}
