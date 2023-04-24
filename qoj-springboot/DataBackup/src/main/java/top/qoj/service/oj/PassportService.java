package top.qoj.service.oj;


import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.ApplyResetPasswordDTO;
import top.qoj.pojo.dto.LoginDTO;
import top.qoj.pojo.dto.RegisterDTO;
import top.qoj.pojo.dto.ResetPasswordDTO;
import top.qoj.pojo.vo.RegisterCodeVO;
import top.qoj.pojo.vo.UserInfoVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface PassportService {

    public CommonResult<UserInfoVO> login(LoginDTO loginDto, HttpServletResponse response, HttpServletRequest request);

    public CommonResult<RegisterCodeVO> getRegisterCode(String email);

    public CommonResult<Void> register(RegisterDTO registerDto);

    public CommonResult<Void> applyResetPassword(ApplyResetPasswordDTO applyResetPasswordDto);

    public CommonResult<Void> resetPassword(ResetPasswordDTO resetPasswordDto);

    public CommonResult<Void> logout();
}