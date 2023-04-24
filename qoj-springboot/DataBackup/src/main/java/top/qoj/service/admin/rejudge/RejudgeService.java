package top.qoj.service.admin.rejudge;

import top.qoj.common.result.CommonResult;
import top.qoj.pojo.entity.judge.Judge;

public interface RejudgeService {

    CommonResult<Judge> rejudge(Long submitId);

    CommonResult<Void> rejudgeContestProblem(Long cid, Long pid);

    CommonResult<Judge> manualJudge(Long submitId, Integer status, Integer score);

    CommonResult<Judge> cancelJudge(Long submitId);
}
