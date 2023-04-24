package top.qoj.dao.judge.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import top.qoj.dao.judge.JudgeServerEntityService;
import top.qoj.mapper.JudgeServerMapper;
import top.qoj.pojo.entity.judge.JudgeServer;


@Service
public class JudgeServerEntityServiceImpl extends ServiceImpl<JudgeServerMapper, JudgeServer> implements JudgeServerEntityService {

}