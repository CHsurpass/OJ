package top.qoj.service.oj.impl;

import org.springframework.stereotype.Service;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.oj.CommonManager;
import top.qoj.pojo.entity.problem.CodeTemplate;
import top.qoj.pojo.entity.problem.Language;
import top.qoj.pojo.vo.CaptchaVO;
import top.qoj.service.oj.CommonService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private CommonManager commonManager;

    @Override
    public CommonResult<CaptchaVO> getCaptcha() {
        return CommonResult.successResponse(commonManager.getCaptcha());
    }

    @Override
    public CommonResult<List<Language>> getLanguages(Long pid, Boolean all) {
        return CommonResult.successResponse(commonManager.getLanguages(pid, all));
    }

    @Override
    public CommonResult<Collection<Language>> getProblemLanguages(Long pid) {
        return CommonResult.successResponse(commonManager.getProblemLanguages(pid));
    }

    @Override
    public CommonResult<List<CodeTemplate>> getProblemCodeTemplate(Long pid) {
        return CommonResult.successResponse(commonManager.getProblemCodeTemplate(pid));
    }
}