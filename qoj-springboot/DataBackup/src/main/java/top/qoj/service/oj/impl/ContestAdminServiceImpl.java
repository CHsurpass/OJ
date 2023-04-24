package top.qoj.service.oj.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusForbiddenException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.oj.ContestAdminManager;
import top.qoj.pojo.dto.CheckACDTO;
import top.qoj.pojo.entity.contest.ContestPrint;
import top.qoj.pojo.entity.contest.ContestRecord;
import top.qoj.service.oj.ContestAdminService;

import javax.annotation.Resource;



@Service
public class ContestAdminServiceImpl implements ContestAdminService {

    @Resource
    private ContestAdminManager contestAdminManager;

    @Override
    public CommonResult<IPage<ContestRecord>> getContestACInfo(Long cid, Integer currentPage, Integer limit) {
        try {
            return CommonResult.successResponse(contestAdminManager.getContestACInfo(cid, currentPage, limit));
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<Void> checkContestACInfo(CheckACDTO checkACDto) {
        try {
            contestAdminManager.checkContestACInfo(checkACDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<IPage<ContestPrint>> getContestPrint(Long cid, Integer currentPage, Integer limit) {
        try {
            return CommonResult.successResponse(contestAdminManager.getContestPrint(cid, currentPage, limit));
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public CommonResult<Void> checkContestPrintStatus(Long id, Long cid) {
        try {
            contestAdminManager.checkContestPrintStatus(id, cid);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }
}