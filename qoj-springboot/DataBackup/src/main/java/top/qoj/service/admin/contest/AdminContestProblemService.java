package top.qoj.service.admin.contest;

import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.ContestProblemDTO;
import top.qoj.pojo.dto.ProblemDTO;
import top.qoj.pojo.entity.contest.ContestProblem;
import top.qoj.pojo.entity.problem.Problem;

import java.util.HashMap;
import java.util.Map;


public interface AdminContestProblemService {

    public CommonResult<HashMap<String, Object>> getProblemList(Integer limit, Integer currentPage, String keyword,
                                                                Long cid, Integer problemType, String oj);

    public CommonResult<Problem> getProblem(Long pid);

    public CommonResult<Void> deleteProblem(Long pid, Long cid);

    public CommonResult<Map<Object, Object>> addProblem(ProblemDTO problemDto);

    public CommonResult<Void> updateProblem(ProblemDTO problemDto);

    public CommonResult<ContestProblem> getContestProblem(Long cid, Long pid);

    public CommonResult<ContestProblem> setContestProblem(ContestProblem contestProblem);

    public CommonResult<Void> addProblemFromPublic(ContestProblemDTO contestProblemDto);

    public CommonResult<Void> importContestRemoteOJProblem(String name, String problemId, Long cid, String displayId);

}
