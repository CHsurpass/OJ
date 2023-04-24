package top.qoj.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.qoj.pojo.entity.contest.Contest;
import top.qoj.pojo.vo.ContestRegisterCountVO;
import top.qoj.pojo.vo.ContestVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface ContestMapper extends BaseMapper<Contest> {

    List<ContestVO> getContestList(IPage page,
                                   @Param("type") Integer type,
                                   @Param("status") Integer status,
                                   @Param("keyword") String keyword);

    List<ContestRegisterCountVO> getContestRegisterCount(@Param("cidList") List<Long> cidList);

    ContestVO getContestInfoById(@Param("cid") long cid);

    List<ContestVO> getWithinNext14DaysContests();
}
