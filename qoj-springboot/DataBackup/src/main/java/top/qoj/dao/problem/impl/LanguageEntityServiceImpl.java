package top.qoj.dao.problem.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.qoj.dao.problem.LanguageEntityService;
import top.qoj.mapper.LanguageMapper;
import top.qoj.pojo.entity.problem.Language;


@Service
public class LanguageEntityServiceImpl extends ServiceImpl<LanguageMapper, Language> implements LanguageEntityService {
}