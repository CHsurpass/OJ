package top.qoj.service.oj.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.oj.RankManager;
import top.qoj.service.oj.RankService;

import javax.annotation.Resource;


@Service
public class RankServiceImpl implements RankService {

    @Resource
    private RankManager rankManager;

    @Override
    public CommonResult<IPage> getRankList(Integer limit, Integer currentPage, String searchUser, Integer type) {
        try {
            return CommonResult.successResponse(rankManager.getRankList(limit, currentPage, searchUser, type));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }
}