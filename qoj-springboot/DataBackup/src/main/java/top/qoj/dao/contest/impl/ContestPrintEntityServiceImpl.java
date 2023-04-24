package top.qoj.dao.contest.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import top.qoj.dao.contest.ContestPrintEntityService;
import top.qoj.mapper.ContestPrintMapper;
import top.qoj.pojo.entity.contest.ContestPrint;


@Service
public class ContestPrintEntityServiceImpl extends ServiceImpl<ContestPrintMapper, ContestPrint> implements ContestPrintEntityService {
}