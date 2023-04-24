package top.qoj.service.oj;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.LastAcceptedCodeVO;
import top.qoj.pojo.dto.PidListDTO;
import top.qoj.pojo.vo.ProblemFullScreenListVO;
import top.qoj.pojo.vo.ProblemInfoVO;
import top.qoj.pojo.vo.ProblemVO;

import java.util.HashMap;
import java.util.List;


public interface ProblemService {

    public CommonResult<Page<ProblemVO>> getProblemList(Integer limit, Integer currentPage,
                                                        String keyword, List<Long> tagId, Integer difficulty, String oj);

    public CommonResult<HashMap<Long, Object>> getUserProblemStatus(PidListDTO pidListDto);

    public CommonResult<ProblemInfoVO> getProblemInfo(String problemId, Long gid);

    public CommonResult<LastAcceptedCodeVO> getUserLastAcceptedCode(Long pid, Long cid);

    public CommonResult<List<ProblemFullScreenListVO>> getFullScreenProblemList(Long tid, Long cid);

}