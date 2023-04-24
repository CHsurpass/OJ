package top.qoj.service.oj;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;

public interface RankService {

    public CommonResult<IPage> getRankList(Integer limit, Integer currentPage, String searchUser, Integer type);
}
