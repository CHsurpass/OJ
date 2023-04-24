package top.qoj.service.oj.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusForbiddenException;
import top.qoj.common.exception.StatusNotFoundException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.oj.ContestManager;
import top.qoj.pojo.dto.ContestPrintDTO;
import top.qoj.pojo.dto.ContestRankDTO;
import top.qoj.pojo.dto.RegisterContestDTO;
import top.qoj.pojo.dto.UserReadContestAnnouncementDTO;
import top.qoj.pojo.entity.common.Announcement;
import top.qoj.service.oj.ContestService;
import top.qoj.pojo.vo.*;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ContestServiceImpl implements ContestService {

    @Resource
    private ContestManager contestManager;

    @Override
    public CommonResult<IPage<ContestVO>> getContestList(Integer limit, Integer currentPage, Integer status, Integer type, String keyword) {
        return CommonResult.successResponse(contestManager.getContestList(limit, currentPage, status, type, keyword));
    }

    @Override
    public CommonResult<ContestVO> getContestInfo(Long cid) {
        try {
            return CommonResult.successResponse(contestManager.getContestInfo(cid));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<Void> toRegisterContest(RegisterContestDTO registerContestDto) {
        try {
            contestManager.toRegisterContest(registerContestDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<AccessVO> getContestAccess(Long cid) {
        try {
            return CommonResult.successResponse(contestManager.getContestAccess(cid));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<List<ContestProblemVO>> getContestProblem(Long cid) {
        try {
            return CommonResult.successResponse(contestManager.getContestProblem(cid));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<ProblemInfoVO> getContestProblemDetails(Long cid, String displayId) {
        try {
            return CommonResult.successResponse(contestManager.getContestProblemDetails(cid, displayId));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        } catch (StatusNotFoundException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.NOT_FOUND);
        }
    }

    @Override
    public CommonResult<IPage<JudgeVO>> getContestSubmissionList(Integer limit,
                                                                 Integer currentPage,
                                                                 Boolean onlyMine,
                                                                 String displayId,
                                                                 Integer searchStatus,
                                                                 String searchUsername,
                                                                 Long searchCid,
                                                                 Boolean beforeContestSubmit,
                                                                 Boolean completeProblemID) {
        try {
            return CommonResult.successResponse(contestManager.getContestSubmissionList(limit,
                    currentPage,
                    onlyMine,
                    displayId,
                    searchStatus,
                    searchUsername,
                    searchCid,
                    beforeContestSubmit,
                    completeProblemID));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<IPage> getContestRank(ContestRankDTO contestRankDto) {
        try {
            return CommonResult.successResponse(contestManager.getContestRank(contestRankDto));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<IPage<AnnouncementVO>> getContestAnnouncement(Long cid, Integer limit, Integer currentPage) {
        try {
            return CommonResult.successResponse(contestManager.getContestAnnouncement(cid, limit, currentPage));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<List<Announcement>> getContestUserNotReadAnnouncement(UserReadContestAnnouncementDTO userReadContestAnnouncementDto) {

        return CommonResult.successResponse(contestManager.getContestUserNotReadAnnouncement(userReadContestAnnouncementDto));
    }

    @Override
    public CommonResult<Void> submitPrintText(ContestPrintDTO contestPrintDto) {
        try {
            contestManager.submitPrintText(contestPrintDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }
}