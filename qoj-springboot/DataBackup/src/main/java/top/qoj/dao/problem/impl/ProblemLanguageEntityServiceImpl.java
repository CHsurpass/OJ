package top.qoj.dao.problem.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.qoj.dao.problem.ProblemLanguageEntityService;
import top.qoj.mapper.ProblemLanguageMapper;
import top.qoj.pojo.entity.problem.ProblemLanguage;


@Service
public class ProblemLanguageEntityServiceImpl extends ServiceImpl<ProblemLanguageMapper, ProblemLanguage> implements ProblemLanguageEntityService {
}