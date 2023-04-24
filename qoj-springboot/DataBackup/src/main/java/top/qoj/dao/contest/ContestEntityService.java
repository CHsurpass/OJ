package top.qoj.dao.contest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.pojo.entity.contest.Contest;
import top.qoj.pojo.vo.ContestVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface ContestEntityService extends IService<Contest> {

    List<ContestVO> getWithinNext14DaysContests();

    IPage<ContestVO> getContestList(Integer limit, Integer currentPage, Integer type, Integer status, String keyword);

    ContestVO getContestInfoById(long cid);
}
