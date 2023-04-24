package top.qoj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.qoj.pojo.entity.problem.ProblemCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface ProblemCountMapper extends BaseMapper<ProblemCount> {
    ProblemCount getContestProblemCount(@Param("pid") Long pid, @Param("cpid") Long cpid, @Param("cid") Long cid);
}
