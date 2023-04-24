package top.qoj.service.admin.account;


import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.LoginDTO;
import top.qoj.pojo.vo.UserInfoVO;


public interface AdminAccountService {

    public CommonResult<UserInfoVO> login(LoginDTO loginDto);

    public CommonResult<Void> logout();
}