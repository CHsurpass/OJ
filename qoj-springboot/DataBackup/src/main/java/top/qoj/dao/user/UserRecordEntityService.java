package top.qoj.dao.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.qoj.pojo.entity.judge.Judge;
import top.qoj.pojo.entity.user.UserRecord;
import top.qoj.pojo.vo.ACMRankVO;
import com.baomidou.mybatisplus.extension.service.IService;
import top.qoj.pojo.vo.OIRankVO;
import top.qoj.pojo.vo.UserHomeVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface UserRecordEntityService extends IService<UserRecord> {

    List<ACMRankVO> getRecent7ACRank();

    UserHomeVO getUserHomeInfo(String uid, String username);

    List<Judge> getLastYearUserJudgeList(String uid, String username);

    IPage<OIRankVO> getOIRankList(Page<OIRankVO> page, List<String> uidList);

    IPage<ACMRankVO> getACMRankList(Page<ACMRankVO> page, List<String> uidList);

    IPage<OIRankVO> getGroupRankList(Page<OIRankVO> page, Long gid, List<String> uidList, String rankType, Boolean useCache);

}
