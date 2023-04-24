package top.qoj.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.AdminEditUserDTO;
import top.qoj.pojo.vo.UserRolesVO;
import top.qoj.service.admin.user.AdminUserService;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;


    @GetMapping("/get-user-list")
    @RequiresAuthentication
    @RequiresPermissions("user_admin")
    public CommonResult<IPage<UserRolesVO>> getUserList(@RequestParam(value = "limit", required = false) Integer limit,
                                                        @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                        @RequestParam(value = "onlyAdmin", defaultValue = "false") Boolean onlyAdmin,
                                                        @RequestParam(value = "keyword", required = false) String keyword) {
        return adminUserService.getUserList(limit, currentPage, onlyAdmin, keyword);
    }

    @PutMapping("/edit-user")
    @RequiresPermissions("user_admin")
    @RequiresAuthentication
    public CommonResult<Void> editUser(@RequestBody AdminEditUserDTO adminEditUserDto) {
        return adminUserService.editUser(adminEditUserDto);
    }

    @DeleteMapping("/delete-user")
    @RequiresPermissions("user_admin")
    @RequiresAuthentication
    public CommonResult<Void> deleteUser(@RequestBody Map<String, Object> params) {
        return adminUserService.deleteUser((List<String>) params.get("ids"));
    }

    @PostMapping("/insert-batch-user")
    @RequiresPermissions("user_admin")
    @RequiresAuthentication
    public CommonResult<Void> insertBatchUser(@RequestBody Map<String, Object> params) {
        return adminUserService.insertBatchUser((List<List<String>>) params.get("users"));
    }

    @PostMapping("/generate-user")
    @RequiresPermissions("user_admin")
    @RequiresAuthentication
    public CommonResult<Map<Object, Object>> generateUser(@RequestBody Map<String, Object> params) {
        return adminUserService.generateUser(params);
    }

}