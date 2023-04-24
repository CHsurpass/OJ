package top.qoj.dao.contest.impl;

import top.qoj.dao.contest.ContestScoreEntityService;
import top.qoj.mapper.ContestScoreMapper;
import top.qoj.pojo.entity.contest.ContestScore;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class ContestScoreEntityServiceImpl extends ServiceImpl<ContestScoreMapper, ContestScore> implements ContestScoreEntityService {

}
