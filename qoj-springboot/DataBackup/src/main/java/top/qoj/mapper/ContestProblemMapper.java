package top.qoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.qoj.pojo.entity.contest.ContestProblem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.qoj.pojo.vo.ContestProblemVO;
import top.qoj.pojo.vo.ProblemFullScreenListVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface ContestProblemMapper extends BaseMapper<ContestProblem> {
    List<ContestProblemVO> getContestProblemList(@Param("cid") Long cid, @Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime, @Param("sealTime") Date sealTime,
                                                 @Param("isAdmin") Boolean isAdmin, @Param("adminList") List<String> adminList);

    List<ProblemFullScreenListVO> getContestFullScreenProblemList(@Param("cid") Long cid);
}
