package top.qoj.dao.contest;

import top.qoj.pojo.entity.contest.ContestProblem;
import com.baomidou.mybatisplus.extension.service.IService;
import top.qoj.pojo.vo.ContestProblemVO;
import top.qoj.pojo.vo.ProblemFullScreenListVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface ContestProblemEntityService extends IService<ContestProblem> {
    List<ContestProblemVO> getContestProblemList(Long cid,
                                                 Date startTime,
                                                 Date endTime,
                                                 Date sealTime,
                                                 Boolean isAdmin,
                                                 String contestAuthorUid,
                                                 List<String> groupRootUidList);

    List<ProblemFullScreenListVO> getContestFullScreenProblemList(Long cid);

    void syncContestRecord(Long pid, Long cid, String displayId);
}
