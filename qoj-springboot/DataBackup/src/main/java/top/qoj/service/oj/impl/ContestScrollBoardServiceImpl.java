package top.qoj.service.oj.impl;

import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.oj.ContestScrollBoardManager;
import top.qoj.pojo.vo.ContestScrollBoardInfoVO;
import top.qoj.pojo.vo.ContestScrollBoardSubmissionVO;
import top.qoj.service.oj.ContestScrollBoardService;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ContestScrollBoardServiceImpl implements ContestScrollBoardService {

    @Resource
    private ContestScrollBoardManager contestScrollBoardManager;

    @Override
    public CommonResult<ContestScrollBoardInfoVO> getContestScrollBoardInfo(Long cid) {
        try {
            return CommonResult.successResponse(contestScrollBoardManager.getContestScrollBoardInfo(cid));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<List<ContestScrollBoardSubmissionVO>> getContestScrollBoardSubmission(Long cid) {
        try {
            return CommonResult.successResponse(contestScrollBoardManager.getContestScrollBoardSubmission(cid));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }
}
