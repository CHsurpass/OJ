package top.qoj.service.oj;

import top.qoj.common.result.CommonResult;
import top.qoj.pojo.vo.ContestScrollBoardInfoVO;
import top.qoj.pojo.vo.ContestScrollBoardSubmissionVO;

import java.util.List;


public interface ContestScrollBoardService{

    public CommonResult<ContestScrollBoardInfoVO> getContestScrollBoardInfo(Long cid);

    public CommonResult<List<ContestScrollBoardSubmissionVO>> getContestScrollBoardSubmission(Long cid);
}
