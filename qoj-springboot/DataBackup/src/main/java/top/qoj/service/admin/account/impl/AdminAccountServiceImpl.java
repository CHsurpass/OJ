package top.qoj.service.admin.account.impl;

import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusAccessDeniedException;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.admin.account.AdminAccountManager;
import top.qoj.pojo.dto.LoginDTO;
import top.qoj.pojo.vo.UserInfoVO;
import top.qoj.service.admin.account.AdminAccountService;

import javax.annotation.Resource;



@Service
public class AdminAccountServiceImpl implements AdminAccountService {

    @Resource
    private AdminAccountManager adminAccountManager;

    @Override
    public CommonResult<UserInfoVO> login(LoginDTO loginDto) {
        try {
            return CommonResult.successResponse(adminAccountManager.login(loginDto));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusAccessDeniedException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.ACCESS_DENIED);
        }
    }

    @Override
    public CommonResult<Void> logout() {
        adminAccountManager.logout();
        return CommonResult.successResponse("退出登录成功！");
    }
}