package top.qoj.controller.admin;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.entity.user.Session;
import top.qoj.service.admin.system.DashboardService;


import java.util.Map;


@RestController
@RequestMapping("/api/admin/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;


    @PostMapping("/get-sessions")
    @RequiresAuthentication
    @RequiresRoles(value = {"root","admin","problem_admin"},logical = Logical.OR)
    public CommonResult<Session> getRecentSession(){

        return dashboardService.getRecentSession();
    }

    @GetMapping("/get-dashboard-info")
    @RequiresAuthentication
    @RequiresRoles(value = {"root","admin","problem_admin"},logical = Logical.OR)
    public CommonResult<Map<Object,Object>> getDashboardInfo(){

        return dashboardService.getDashboardInfo();
    }
}