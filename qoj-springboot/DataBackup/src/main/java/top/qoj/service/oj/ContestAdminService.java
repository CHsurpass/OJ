package top.qoj.service.oj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.CheckACDTO;
import top.qoj.pojo.entity.contest.ContestPrint;
import top.qoj.pojo.entity.contest.ContestRecord;

public interface ContestAdminService {

    public CommonResult<IPage<ContestRecord>> getContestACInfo(Long cid, Integer currentPage, Integer limit);

    public CommonResult<Void> checkContestACInfo(CheckACDTO checkACDto);

    public CommonResult<IPage<ContestPrint>> getContestPrint(Long cid, Integer currentPage, Integer limit);

    public CommonResult<Void> checkContestPrintStatus(Long id, Long cid);
}
