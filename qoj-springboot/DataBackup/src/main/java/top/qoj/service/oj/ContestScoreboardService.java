package top.qoj.service.oj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.ContestRankDTO;
import top.qoj.pojo.vo.ContestOutsideInfoVO;


public interface ContestScoreboardService {

    public CommonResult<ContestOutsideInfoVO> getContestOutsideInfo(Long cid);

    public CommonResult<IPage> getContestOutsideScoreboard(ContestRankDTO contestRankDto);

}