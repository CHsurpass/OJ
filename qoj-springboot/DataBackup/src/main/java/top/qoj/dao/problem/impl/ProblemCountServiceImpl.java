package top.qoj.dao.problem.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.qoj.dao.problem.ProblemCountEntityService;
import top.qoj.mapper.ProblemCountMapper;
import top.qoj.pojo.entity.problem.ProblemCount;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class ProblemCountServiceImpl extends ServiceImpl<ProblemCountMapper, ProblemCount> implements ProblemCountEntityService {

    @Autowired
    private ProblemCountMapper problemCountMapper;

    @Override
    public ProblemCount getContestProblemCount(Long pid, Long cpid, Long cid) {
        return problemCountMapper.getContestProblemCount(pid,cpid, cid);
    }
}
