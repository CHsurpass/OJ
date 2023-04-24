package top.qoj.manager.oj;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.UnicodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.qoj.config.NacosSwitchConfig;
import top.qoj.config.SwitchConfig;
import top.qoj.config.WebConfig;
import top.qoj.dao.common.AnnouncementEntityService;
import top.qoj.dao.common.FileEntityService;
import top.qoj.dao.contest.ContestEntityService;
import top.qoj.dao.judge.JudgeEntityService;
import top.qoj.dao.problem.ProblemEntityService;
import top.qoj.dao.user.UserRecordEntityService;
import top.qoj.pojo.entity.judge.Judge;
import top.qoj.utils.Constants;
import top.qoj.utils.RedisUtils;
import top.qoj.pojo.vo.*;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class HomeManager {

    @Autowired
    private ContestEntityService contestEntityService;


    @Autowired
    private AnnouncementEntityService announcementEntityService;

    @Autowired

    private UserRecordEntityService userRecordEntityService;

    @Autowired
    private NacosSwitchConfig nacosSwitchConfig;

    /**
     * @MethodName getRecentContest
     * @Params
     * @Description 获取最近14天的比赛信息列表
     * @Return CommonResult
     */
    public List<ContestVO> getRecentContest() {
        return contestEntityService.getWithinNext14DaysContests();
    }

    /**
     * @MethodName getRecentSevenACRank
     * @Params
     * @Description 获取最近7天用户做题榜单
     * @Return
     */
    public List<ACMRankVO> getRecentSevenACRank() {
        return userRecordEntityService.getRecent7ACRank();
    }

    /**
     * @MethodName getCommonAnnouncement
     * @Params
     * @Description 获取主页公告列表
     * @Return CommonResult
     */
    public IPage<AnnouncementVO> getCommonAnnouncement(Integer limit, Integer currentPage) {
        if (currentPage == null || currentPage < 1) currentPage = 1;
        if (limit == null || limit < 1) limit = 10;
        return announcementEntityService.getAnnouncementList(limit, currentPage, true);
    }

    /**
     * @MethodName getWebConfig
     * @Params
     * @Description 获取网站的基础配置。例如名字，缩写名字等等。
     * @Return
     */
    public Map<Object, Object> getWebConfig() {
        SwitchConfig switchConfig = nacosSwitchConfig.getSwitchConfig();
        WebConfig webConfig = nacosSwitchConfig.getWebConfig();
        return MapUtil.builder().put("baseUrl", UnicodeUtil.toString(webConfig.getBaseUrl()))
                .put("name", UnicodeUtil.toString(webConfig.getName()))
                .put("shortName", UnicodeUtil.toString(webConfig.getShortName()))
                .put("register", webConfig.getRegister())
                .put("recordName", UnicodeUtil.toString(webConfig.getRecordName()))
                .put("recordUrl", UnicodeUtil.toString(webConfig.getRecordUrl()))
                .put("description", UnicodeUtil.toString(webConfig.getDescription()))
                .put("email", UnicodeUtil.toString(webConfig.getEmailUsername()))
                .put("projectName", UnicodeUtil.toString(webConfig.getProjectName()))
                .put("projectUrl", UnicodeUtil.toString(webConfig.getProjectUrl()))
                .put("openPublicDiscussion", switchConfig.getOpenPublicDiscussion())
                .put("openGroupDiscussion", switchConfig.getOpenGroupDiscussion())
                .put("openContestComment", switchConfig.getOpenContestComment())
                .map();
    }

}