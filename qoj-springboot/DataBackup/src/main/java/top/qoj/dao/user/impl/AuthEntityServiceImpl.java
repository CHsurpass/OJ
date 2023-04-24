package top.qoj.dao.user.impl;

import top.qoj.dao.user.AuthEntityService;
import top.qoj.mapper.AuthMapper;
import top.qoj.pojo.entity.user.Auth;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class AuthEntityServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthEntityService {

}
