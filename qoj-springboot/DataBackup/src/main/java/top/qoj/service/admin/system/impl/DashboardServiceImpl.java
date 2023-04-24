package top.qoj.service.admin.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.admin.system.DashboardManager;
import top.qoj.pojo.entity.user.Session;
import top.qoj.service.admin.system.DashboardService;

import java.util.Map;


@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardManager dashboardManager;

    @Override
    public CommonResult<Session> getRecentSession() {
        return CommonResult.successResponse(dashboardManager.getRecentSession());
    }

    @Override
    public CommonResult<Map<Object, Object>> getDashboardInfo() {
        return CommonResult.successResponse(dashboardManager.getDashboardInfo());
    }
}