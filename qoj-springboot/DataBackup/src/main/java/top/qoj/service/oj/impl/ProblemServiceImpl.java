package top.qoj.service.oj.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusAccessDeniedException;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusForbiddenException;
import top.qoj.common.exception.StatusNotFoundException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.oj.ProblemManager;
import top.qoj.pojo.dto.LastAcceptedCodeVO;
import top.qoj.pojo.dto.PidListDTO;
import top.qoj.pojo.vo.ProblemFullScreenListVO;
import top.qoj.pojo.vo.ProblemInfoVO;
import top.qoj.pojo.vo.ProblemVO;
import top.qoj.service.oj.ProblemService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemManager problemManager;

    @Override
    public CommonResult<Page<ProblemVO>> getProblemList(Integer limit, Integer currentPage, String keyword, List<Long> tagId, Integer difficulty, String oj) {
        return CommonResult.successResponse(problemManager.getProblemList(limit, currentPage, keyword, tagId, difficulty, oj));
    }


    @Override
    public CommonResult<HashMap<Long, Object>> getUserProblemStatus(PidListDTO pidListDto) {
        try {
            return CommonResult.successResponse(problemManager.getUserProblemStatus(pidListDto));
        } catch (StatusNotFoundException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.NOT_FOUND);
        }
    }

    @Override
    public CommonResult<ProblemInfoVO> getProblemInfo(String problemId, Long gid) {
        try {
            return CommonResult.successResponse(problemManager.getProblemInfo(problemId, gid));
        } catch (StatusNotFoundException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.NOT_FOUND);
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<LastAcceptedCodeVO> getUserLastAcceptedCode(Long pid, Long cid) {
        return CommonResult.successResponse(problemManager.getUserLastAcceptedCode(pid, cid));
    }

    @Override
    public CommonResult<List<ProblemFullScreenListVO>> getFullScreenProblemList(Long tid, Long cid) {
        try {
            return CommonResult.successResponse(problemManager.getFullScreenProblemList(tid, cid));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        } catch (StatusAccessDeniedException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.ACCESS_DENIED);
        }
    }
}