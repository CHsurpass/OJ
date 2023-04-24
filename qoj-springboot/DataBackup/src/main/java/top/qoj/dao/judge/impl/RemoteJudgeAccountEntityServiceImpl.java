package top.qoj.dao.judge.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.qoj.dao.judge.RemoteJudgeAccountEntityService;
import top.qoj.mapper.RemoteJudgeAccountMapper;
import top.qoj.pojo.entity.judge.RemoteJudgeAccount;


@Service
public class RemoteJudgeAccountEntityServiceImpl extends ServiceImpl<RemoteJudgeAccountMapper, RemoteJudgeAccount> implements RemoteJudgeAccountEntityService {
}