package top.qoj.dao.judge;


import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.pojo.entity.judge.Judge;
import com.baomidou.mybatisplus.extension.service.IService;
import top.qoj.pojo.vo.ContestScrollBoardSubmissionVO;
import top.qoj.pojo.vo.JudgeVO;
import top.qoj.pojo.vo.ProblemCountVO;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 */

public interface JudgeEntityService extends IService<Judge> {

    IPage<JudgeVO> getCommonJudgeList(Integer limit,
                                      Integer currentPage,
                                      String searchPid,
                                      Integer status,
                                      String username,
                                      String uid,
                                      Boolean completeProblemID,
                                      Long gid);

    IPage<JudgeVO> getContestJudgeList(Integer limit,
                                       Integer currentPage,
                                       String displayId,
                                       Long cid,
                                       Integer status,
                                       String username,
                                       String uid,
                                       Boolean beforeContestSubmit,
                                       String rule,
                                       Date startTime,
                                       Date sealRankTime,
                                       String sealTimeUid,
                                       Boolean completeProblemID);


    void failToUseRedisPublishJudge(Long submitId, Long pid, Boolean isContest);

    ProblemCountVO getContestProblemCount(Long pid,
                                          Long cpid,
                                          Long cid,
                                          Date startTime,
                                          Date sealRankTime,
                                          List<String> adminList);

    ProblemCountVO getProblemCount(Long pid, Long gid);

    public int getTodayJudgeNum();

    public List<ProblemCountVO> getProblemListCount(List<Long> pidList);

    public List<ContestScrollBoardSubmissionVO> getContestScrollBoardSubmission(Long cid, List<String> uidList);
}
