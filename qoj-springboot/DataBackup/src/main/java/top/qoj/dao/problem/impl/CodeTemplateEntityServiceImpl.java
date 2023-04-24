package top.qoj.dao.problem.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.qoj.dao.problem.CodeTemplateEntityService;
import top.qoj.mapper.CodeTemplateMapper;
import top.qoj.pojo.entity.problem.CodeTemplate;


@Service
public class CodeTemplateEntityServiceImpl extends ServiceImpl<CodeTemplateMapper, CodeTemplate> implements CodeTemplateEntityService {
}