package top.qoj.controller.oj;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.qoj.annotation.AnonApi;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.SubmitIdListDTO;
import top.qoj.pojo.dto.SubmitJudgeDTO;
import top.qoj.pojo.dto.TestJudgeDTO;
import top.qoj.pojo.entity.judge.Judge;
import top.qoj.pojo.vo.JudgeCaseVO;
import top.qoj.pojo.vo.JudgeVO;
import top.qoj.pojo.vo.SubmissionInfoVO;
import top.qoj.pojo.vo.TestJudgeVO;
import top.qoj.service.oj.JudgeService;

import java.util.HashMap;

/**
 * @Description: 处理代码评判相关业务
 */
@RestController
@RequestMapping("/api")
public class JudgeController {


    @Autowired
    private JudgeService judgeService;

    /**
     * @param limit
     * @param currentPage
     * @param onlyMine
     * @param searchPid
     * @param searchStatus
     * @param searchUsername
     * @param completeProblemID
     * @MethodName getJudgeList
     * @Description 通用查询判题记录列表
     * @Return CommonResult
     */
    @GetMapping("/get-submission-list")
    @AnonApi
    public CommonResult<IPage<JudgeVO>> getJudgeList(@RequestParam(value = "limit", required = false) Integer limit,
                                                     @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                     @RequestParam(value = "onlyMine", required = false) Boolean onlyMine,
                                                     @RequestParam(value = "problemID", required = false) String searchPid,
                                                     @RequestParam(value = "status", required = false) Integer searchStatus,
                                                     @RequestParam(value = "username", required = false) String searchUsername,
                                                     @RequestParam(value = "completeProblemID", defaultValue = "false") Boolean completeProblemID,
                                                     @RequestParam(value = "gid", required = false) Long gid) {

        return judgeService.getJudgeList(limit, currentPage, onlyMine, searchPid, searchStatus, searchUsername, completeProblemID, gid);
    }

    /**
     * @MethodName getSubmission
     * @Description 获取单个提交记录的详情
     * @Return CommonResult
     */
    @GetMapping("/get-submission-detail")
    @AnonApi
    public CommonResult<SubmissionInfoVO> getSubmission(@RequestParam(value = "submitId", required = true) Long submitId) {
        return judgeService.getSubmission(submitId);
    }

    /**
     * @MethodName submitProblemJudge
     * @Description 核心方法 判题就此开始
     * @Return CommonResult
     */
    @RequiresAuthentication
    @RequiresPermissions("submit")
    @RequestMapping(value = "/submit-problem-judge", method = RequestMethod.POST)
    public CommonResult<Judge> submitProblemJudge(@RequestBody SubmitJudgeDTO judgeDto) {
        return judgeService.submitProblemJudge(judgeDto);
    }

    @RequiresAuthentication
    @RequiresPermissions("submit")
    @RequestMapping(value = "/submit-problem-test-judge", method = RequestMethod.POST)
    public CommonResult<String> submitProblemTestJudge(@RequestBody TestJudgeDTO testJudgeDto) {
        return judgeService.submitProblemTestJudge(testJudgeDto);
    }


    @RequiresAuthentication
    @GetMapping("/get-test-judge-result")
    public CommonResult<TestJudgeVO> getTestJudgeResult(@RequestParam("testJudgeKey") String testJudgeKey) {
        return judgeService.getTestJudgeResult(testJudgeKey);
    }

    /**
     * @MethodName resubmit
     * @Description 调用判题服务器提交失败超过60s后，用户点击按钮重新提交判题进入的方法
     * @Return
     */
    @RequiresAuthentication
    @GetMapping(value = "/resubmit")
    public CommonResult<Judge> resubmit(@RequestParam("submitId") Long submitId) {
        return judgeService.resubmit(submitId);
    }

    /**
     * @MethodName updateSubmission
     * @Description 修改单个提交详情的分享权限
     * @Return CommonResult
     */
    @PutMapping("/submission")
    @RequiresAuthentication
    public CommonResult<Void> updateSubmission(@RequestBody Judge judge) {
        return judgeService.updateSubmission(judge);
    }

    /**
     * @MethodName checkJudgeResult
     * @Description 对提交列表状态为Pending和Judging的提交进行更新检查
     * @Return
     */
    @RequestMapping(value = "/check-submissions-status", method = RequestMethod.POST)
    @AnonApi
    public CommonResult<HashMap<Long, Object>> checkCommonJudgeResult(@RequestBody SubmitIdListDTO submitIdListDto) {
        return judgeService.checkCommonJudgeResult(submitIdListDto);
    }

    /**
     * @param submitIdListDto
     * @MethodName checkContestJudgeResult
     * @Description 需要检查是否为封榜，是否可以查询结果，避免有人恶意查询
     * @Return
     */
    @RequestMapping(value = "/check-contest-submissions-status", method = RequestMethod.POST)
    @RequiresAuthentication
    public CommonResult<HashMap<Long, Object>> checkContestJudgeResult(@RequestBody SubmitIdListDTO submitIdListDto) {
        return judgeService.checkContestJudgeResult(submitIdListDto);
    }


    /**
     * @param submitId
     * @MethodName getJudgeCase
     * @Description 获得指定提交id的测试样例结果，暂不支持查看测试数据，只可看测试点结果，时间，空间，或者IO得分
     * @Return
     */
    @GetMapping("/get-all-case-result")
    @AnonApi
    public CommonResult<JudgeCaseVO> getALLCaseResult(@RequestParam(value = "submitId", required = true) Long submitId) {
        return judgeService.getALLCaseResult(submitId);
    }
}