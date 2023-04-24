package top.qoj.service.oj.impl;

import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusAccessDeniedException;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusForbiddenException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.oj.PassportManager;
import top.qoj.pojo.dto.ApplyResetPasswordDTO;
import top.qoj.pojo.dto.LoginDTO;
import top.qoj.pojo.dto.RegisterDTO;
import top.qoj.pojo.dto.ResetPasswordDTO;
import top.qoj.pojo.vo.RegisterCodeVO;
import top.qoj.pojo.vo.UserInfoVO;
import top.qoj.service.oj.PassportService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class PassportServiceImpl implements PassportService {

    @Resource
    private PassportManager passportManager;

    @Override
    public CommonResult<UserInfoVO> login(LoginDTO loginDto, HttpServletResponse response, HttpServletRequest request) {
        try {
            return CommonResult.successResponse(passportManager.login(loginDto, response, request));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<RegisterCodeVO> getRegisterCode(String email) {
        try {
            return CommonResult.successResponse(passportManager.getRegisterCode(email));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusAccessDeniedException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.ACCESS_DENIED);
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<Void> register(RegisterDTO registerDto) {
        try {
            passportManager.register(registerDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusAccessDeniedException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.ACCESS_DENIED);
        }
    }

    @Override
    public CommonResult<Void> applyResetPassword(ApplyResetPasswordDTO applyResetPasswordDto) {
        try {
            passportManager.applyResetPassword(applyResetPasswordDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> resetPassword(ResetPasswordDTO resetPasswordDto) {
        try {
            passportManager.resetPassword(resetPasswordDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> logout() {
        passportManager.logout();
        return CommonResult.successResponse("登出成功");
    }
}