package top.qoj.service.oj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.vo.ACMRankVO;
import top.qoj.pojo.vo.AnnouncementVO;
import top.qoj.pojo.vo.ContestVO;

import java.util.List;
import java.util.Map;


public interface HomeService {

    public CommonResult<List<ContestVO>> getRecentContest();

    public CommonResult<List<ACMRankVO>> getRecentSevenACRank();

    public CommonResult<IPage<AnnouncementVO>> getCommonAnnouncement(Integer limit, Integer currentPage);

    public CommonResult<Map<Object, Object>> getWebConfig();

}