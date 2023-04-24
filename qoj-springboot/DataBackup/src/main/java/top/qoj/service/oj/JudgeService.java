package top.qoj.service.oj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.SubmitIdListDTO;
import top.qoj.pojo.dto.SubmitJudgeDTO;
import top.qoj.pojo.dto.TestJudgeDTO;
import top.qoj.pojo.entity.judge.Judge;
import top.qoj.pojo.vo.JudgeCaseVO;
import top.qoj.pojo.vo.JudgeVO;
import top.qoj.pojo.vo.SubmissionInfoVO;
import top.qoj.pojo.vo.TestJudgeVO;

import java.util.HashMap;

public interface JudgeService {

    public CommonResult<Judge> submitProblemJudge(SubmitJudgeDTO judgeDto);

    public CommonResult<String> submitProblemTestJudge(TestJudgeDTO testJudgeDto);

    public CommonResult<Judge> resubmit(Long submitId);

    public CommonResult<SubmissionInfoVO> getSubmission(Long submitId);

    public CommonResult<TestJudgeVO> getTestJudgeResult(String testJudgeKey);

    public CommonResult<IPage<JudgeVO>> getJudgeList(Integer limit,
                                                     Integer currentPage,
                                                     Boolean onlyMine,
                                                     String searchPid,
                                                     Integer searchStatus,
                                                     String searchUsername,
                                                     Boolean completeProblemID,
                                                     Long gid);

    public CommonResult<Void> updateSubmission(Judge judge);

    public CommonResult<HashMap<Long, Object>> checkCommonJudgeResult(SubmitIdListDTO submitIdListDto);

    public CommonResult<HashMap<Long, Object>> checkContestJudgeResult(SubmitIdListDTO submitIdListDto);

    public CommonResult<JudgeCaseVO> getALLCaseResult(Long submitId);
}
