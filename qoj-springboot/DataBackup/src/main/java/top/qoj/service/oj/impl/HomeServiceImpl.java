package top.qoj.service.oj.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.oj.HomeManager;
import top.qoj.pojo.vo.ACMRankVO;
import top.qoj.pojo.vo.AnnouncementVO;
import top.qoj.pojo.vo.ContestVO;
import top.qoj.service.oj.HomeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private HomeManager homeManager;

    @Override
    public CommonResult<List<ContestVO>> getRecentContest() {
        return CommonResult.successResponse(homeManager.getRecentContest());
    }

    @Override
    public CommonResult<List<ACMRankVO>> getRecentSevenACRank() {
        return CommonResult.successResponse(homeManager.getRecentSevenACRank());
    }


    @Override
    public CommonResult<IPage<AnnouncementVO>> getCommonAnnouncement(Integer limit, Integer currentPage) {
        return CommonResult.successResponse(homeManager.getCommonAnnouncement(limit, currentPage));
    }

    @Override
    public CommonResult<Map<Object, Object>> getWebConfig() {
        return CommonResult.successResponse(homeManager.getWebConfig());
    }


}