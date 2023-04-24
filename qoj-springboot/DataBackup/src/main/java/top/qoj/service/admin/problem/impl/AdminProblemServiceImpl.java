package top.qoj.service.admin.problem.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusForbiddenException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.admin.problem.AdminProblemManager;
import top.qoj.pojo.dto.CompileDTO;
import top.qoj.pojo.dto.ProblemDTO;
import top.qoj.pojo.entity.problem.Problem;
import top.qoj.pojo.entity.problem.ProblemCase;
import top.qoj.service.admin.problem.AdminProblemService;

import java.util.List;


@Service
public class AdminProblemServiceImpl implements AdminProblemService {

    @Autowired
    private AdminProblemManager adminProblemManager;

    @Override
    public CommonResult<IPage<Problem>> getProblemList(Integer limit, Integer currentPage, String keyword, Integer auth, String oj) {
        IPage<Problem> problemList = adminProblemManager.getProblemList(limit, currentPage, keyword, auth, oj);
        return CommonResult.successResponse(problemList);
    }

    @Override
    public CommonResult<Problem> getProblem(Long pid) {
        try {
            Problem problem = adminProblemManager.getProblem(pid);
            return CommonResult.successResponse(problem);
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> deleteProblem(Long pid) {
        try {
            adminProblemManager.deleteProblem(pid);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> addProblem(ProblemDTO problemDto) {
        try {
            adminProblemManager.addProblem(problemDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> updateProblem(ProblemDTO problemDto) {
        try {
            adminProblemManager.updateProblem(problemDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<List<ProblemCase>> getProblemCases(Long pid, Boolean isUpload) {

        List<ProblemCase> problemCaseList = adminProblemManager.getProblemCases(pid, isUpload);
        return CommonResult.successResponse(problemCaseList);
    }

    @Override
    public CommonResult compileSpj(CompileDTO compileDTO) {
        return adminProblemManager.compileSpj(compileDTO);
    }

    @Override
    public CommonResult compileInteractive(CompileDTO compileDTO) {
        return adminProblemManager.compileInteractive(compileDTO);
    }

    @Override
    public CommonResult<Void> importRemoteOJProblem(String name, String problemId) {
        try {
            adminProblemManager.importRemoteOJProblem(name, problemId);
            return CommonResult.successResponse("导入新题目成功");
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> changeProblemAuth(Problem problem) {
        try {
            adminProblemManager.changeProblemAuth(problem);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }
}