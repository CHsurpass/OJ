package top.qoj.service.admin.user.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.admin.user.AdminUserManager;
import top.qoj.pojo.dto.AdminEditUserDTO;
import top.qoj.pojo.vo.UserRolesVO;
import top.qoj.service.admin.user.AdminUserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserManager adminUserManager;

    @Override
    public CommonResult<IPage<UserRolesVO>> getUserList(Integer limit, Integer currentPage, Boolean onlyAdmin, String keyword) {
        return CommonResult.successResponse(adminUserManager.getUserList(limit, currentPage, onlyAdmin, keyword));
    }

    @Override
    public CommonResult<Void> editUser(AdminEditUserDTO adminEditUserDto) {
        try {
            adminUserManager.editUser(adminEditUserDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> deleteUser(List<String> deleteUserIdList) {
        try {
            adminUserManager.deleteUser(deleteUserIdList);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> insertBatchUser(List<List<String>> users) {
        try {
            adminUserManager.insertBatchUser(users);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Map<Object, Object>> generateUser(Map<String, Object> params) {
        try {
            return CommonResult.successResponse(adminUserManager.generateUser(params));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }
}