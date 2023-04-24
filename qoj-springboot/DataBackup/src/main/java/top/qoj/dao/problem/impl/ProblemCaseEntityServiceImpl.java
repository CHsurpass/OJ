package top.qoj.dao.problem.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.qoj.dao.problem.ProblemCaseEntityService;
import top.qoj.mapper.ProblemCaseMapper;
import top.qoj.pojo.entity.problem.ProblemCase;


@Service
public class ProblemCaseEntityServiceImpl extends ServiceImpl<ProblemCaseMapper, ProblemCase> implements ProblemCaseEntityService {
}