package top.qoj.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.qoj.pojo.entity.user.UserRecord;
import top.qoj.pojo.vo.ACMRankVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.qoj.pojo.vo.OIRankVO;
import top.qoj.pojo.vo.UserHomeVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface UserRecordMapper extends BaseMapper<UserRecord> {
    IPage<ACMRankVO> getACMRankList(Page<ACMRankVO> page, @Param("uidList") List<String> uidList);

    List<ACMRankVO> getRecent7ACRank();

    IPage<OIRankVO> getOIRankList(Page<OIRankVO> page, @Param("uidList") List<String> uidList);

    UserHomeVO getUserHomeInfo(@Param("uid") String uid, @Param("username") String username);

    IPage<OIRankVO> getGroupRankList(Page<OIRankVO> page,
                                     @Param("gid") Long gid,
                                     @Param("uidList") List<String> uidList,
                                     @Param("rankType") String rankType);

}
