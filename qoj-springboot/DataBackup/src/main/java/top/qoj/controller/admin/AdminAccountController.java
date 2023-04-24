package top.qoj.controller.admin;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.LoginDTO;
import top.qoj.pojo.vo.UserInfoVO;

import top.qoj.service.admin.account.AdminAccountService;


@RestController
@RequestMapping("/api/admin")
public class AdminAccountController {

    @Autowired
    private AdminAccountService adminAccountService;

    @PostMapping("/login")
    public CommonResult<UserInfoVO> login(@Validated @RequestBody LoginDTO loginDto){
       return adminAccountService.login(loginDto);
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    @RequiresRoles(value = {"root","admin","problem_admin"},logical = Logical.OR)
    public CommonResult<Void> logout() {
        return adminAccountService.logout();
    }

}