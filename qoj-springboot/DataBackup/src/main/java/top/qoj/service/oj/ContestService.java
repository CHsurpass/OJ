package top.qoj.service.oj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.ContestPrintDTO;
import top.qoj.pojo.dto.ContestRankDTO;
import top.qoj.pojo.dto.RegisterContestDTO;
import top.qoj.pojo.dto.UserReadContestAnnouncementDTO;
import top.qoj.pojo.entity.common.Announcement;
import top.qoj.pojo.vo.*;

import java.util.List;

public interface ContestService {

    public CommonResult<IPage<ContestVO>> getContestList(Integer limit, Integer currentPage, Integer status, Integer type, String keyword);

    public CommonResult<ContestVO> getContestInfo(Long cid);

    public CommonResult<Void> toRegisterContest(RegisterContestDTO registerContestDto);

    public CommonResult<AccessVO> getContestAccess(Long cid);

    public CommonResult<List<ContestProblemVO>> getContestProblem(Long cid);

    public CommonResult<ProblemInfoVO> getContestProblemDetails(Long cid, String displayId);

    public CommonResult<IPage<JudgeVO>> getContestSubmissionList(Integer limit,
                                                                 Integer currentPage,
                                                                 Boolean onlyMine,
                                                                 String displayId,
                                                                 Integer searchStatus,
                                                                 String searchUsername,
                                                                 Long searchCid,
                                                                 Boolean beforeContestSubmit,
                                                                 Boolean completeProblemID);

    public CommonResult<IPage> getContestRank(ContestRankDTO contestRankDto);

    public CommonResult<IPage<AnnouncementVO>> getContestAnnouncement(Long cid, Integer limit, Integer currentPage);

    public CommonResult<List<Announcement>> getContestUserNotReadAnnouncement(UserReadContestAnnouncementDTO userReadContestAnnouncementDto);

    public CommonResult<Void> submitPrintText(ContestPrintDTO contestPrintDto);

}
