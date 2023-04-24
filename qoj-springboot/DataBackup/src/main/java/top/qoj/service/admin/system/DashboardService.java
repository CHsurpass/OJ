package top.qoj.service.admin.system;

import top.qoj.common.result.CommonResult;
import top.qoj.pojo.entity.user.Session;

import java.util.Map;


public interface DashboardService {

    public CommonResult<Session> getRecentSession();

    public CommonResult<Map<Object,Object>> getDashboardInfo();
}