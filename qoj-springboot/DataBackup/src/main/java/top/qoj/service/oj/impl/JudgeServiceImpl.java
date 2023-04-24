package top.qoj.service.oj.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.exception.AccessException;
import top.qoj.manager.oj.JudgeManager;
import top.qoj.pojo.dto.SubmitIdListDTO;
import top.qoj.pojo.dto.SubmitJudgeDTO;
import top.qoj.pojo.dto.TestJudgeDTO;
import top.qoj.pojo.entity.judge.Judge;
import top.qoj.pojo.vo.JudgeCaseVO;
import top.qoj.pojo.vo.JudgeVO;
import top.qoj.pojo.vo.SubmissionInfoVO;
import top.qoj.pojo.vo.TestJudgeVO;
import top.qoj.service.oj.JudgeService;
import top.qoj.common.exception.*;

import javax.annotation.Resource;
import java.util.HashMap;


@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private JudgeManager judgeManager;

    @Override
    public CommonResult<Judge> submitProblemJudge(SubmitJudgeDTO judgeDto) {
        try {
            return CommonResult.successResponse(judgeManager.submitProblemJudge(judgeDto));
        } catch (StatusForbiddenException | AccessException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        } catch (StatusNotFoundException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.NOT_FOUND);
        } catch (StatusAccessDeniedException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.ACCESS_DENIED);
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<String> submitProblemTestJudge(TestJudgeDTO testJudgeDto) {
        try {
            return CommonResult.successResponse(judgeManager.submitProblemTestJudge(testJudgeDto), "success");
        } catch (StatusForbiddenException | AccessException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusSystemErrorException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.SYSTEM_ERROR);
        }
    }

    @Override
    public CommonResult<Judge> resubmit(Long submitId) {
        try {
            return CommonResult.successResponse(judgeManager.resubmit(submitId));
        } catch (StatusNotFoundException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.NOT_FOUND);
        }
    }

    @Override
    public CommonResult<SubmissionInfoVO> getSubmission(Long submitId) {
        try {
            return CommonResult.successResponse(judgeManager.getSubmission(submitId));
        } catch (StatusNotFoundException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.NOT_FOUND);
        } catch (StatusAccessDeniedException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.ACCESS_DENIED);
        }
    }

    @Override
    public CommonResult<TestJudgeVO> getTestJudgeResult(String testJudgeKey) {
        try {
            return CommonResult.successResponse(judgeManager.getTestJudgeResult(testJudgeKey));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<IPage<JudgeVO>> getJudgeList(Integer limit,
                                                     Integer currentPage,
                                                     Boolean onlyMine,
                                                     String searchPid,
                                                     Integer searchStatus,
                                                     String searchUsername,
                                                     Boolean completeProblemID,
                                                     Long gid) {
        try {
            return CommonResult.successResponse(judgeManager.getJudgeList(limit,
                    currentPage,
                    onlyMine,
                    searchPid,
                    searchStatus,
                    searchUsername,
                    completeProblemID,
                    gid));
        } catch (StatusAccessDeniedException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.ACCESS_DENIED);
        }
    }

    @Override
    public CommonResult<Void> updateSubmission(Judge judge) {
        try {
            judgeManager.updateSubmission(judge);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<HashMap<Long, Object>> checkCommonJudgeResult(SubmitIdListDTO submitIdListDto) {
        return CommonResult.successResponse(judgeManager.checkCommonJudgeResult(submitIdListDto));
    }

    @Override
    public CommonResult<HashMap<Long, Object>> checkContestJudgeResult(SubmitIdListDTO submitIdListDto) {
        try {
            return CommonResult.successResponse(judgeManager.checkContestJudgeResult(submitIdListDto));
        } catch (StatusNotFoundException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.NOT_FOUND);
        }
    }

    @Override
    public CommonResult<JudgeCaseVO> getALLCaseResult(Long submitId) {
        try {
            return CommonResult.successResponse(judgeManager.getALLCaseResult(submitId));
        } catch (StatusNotFoundException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.NOT_FOUND);
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }
}