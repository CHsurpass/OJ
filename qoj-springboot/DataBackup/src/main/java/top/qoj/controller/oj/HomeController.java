package top.qoj.controller.oj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.qoj.common.result.CommonResult;
import top.qoj.annotation.AnonApi;
import top.qoj.pojo.vo.ACMRankVO;
import top.qoj.pojo.vo.AnnouncementVO;
import top.qoj.pojo.vo.ContestVO;
import top.qoj.service.oj.HomeService;

import java.util.List;
import java.util.Map;

;

/**
 * @Description: 处理首页的请求
 */
@RestController
@RequestMapping("/api")
@AnonApi
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * @MethodName getRecentContest
     * @Params
     * @Description 获取最近14天的比赛信息列表
     * @Return CommonResult
     */

    @GetMapping("/get-recent-contest")
    public CommonResult<List<ContestVO>> getRecentContest() {
        return homeService.getRecentContest();
    }

    /**
     * @MethodName getRecentSevenACRank
     * @Params * @param null
     * @Description 获取最近7天用户做题榜单
     * @Return
     */
    @GetMapping("/get-recent-seven-ac-rank")
    public CommonResult<List<ACMRankVO>> getRecentSevenACRank() {
        return homeService.getRecentSevenACRank();
    }

    /**
     * @MethodName getCommonAnnouncement
     * @Params
     * @Description 获取主页公告列表
     * @Return CommonResult
     */
    @GetMapping("/get-common-announcement")
    public CommonResult<IPage<AnnouncementVO>> getCommonAnnouncement(@RequestParam(value = "limit", required = false) Integer limit,
                                                                     @RequestParam(value = "currentPage", required = false) Integer currentPage) {
        return homeService.getCommonAnnouncement(limit, currentPage);
    }

    /**
     * @MethodName getWebConfig
     * @Params
     * @Description 获取网站的基础配置。例如名字，缩写名字等等。
     * @Return CommonResult
     */
    @GetMapping("/get-website-config")
    public CommonResult<Map<Object, Object>> getWebConfig() {
        return homeService.getWebConfig();
    }

}