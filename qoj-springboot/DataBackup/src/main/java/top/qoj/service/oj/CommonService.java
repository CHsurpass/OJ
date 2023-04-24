package top.qoj.service.oj;

import top.qoj.common.result.CommonResult;
import top.qoj.pojo.entity.problem.CodeTemplate;
import top.qoj.pojo.entity.problem.Language;
import top.qoj.pojo.vo.CaptchaVO;

import java.util.Collection;
import java.util.List;

public interface CommonService {

    public CommonResult<CaptchaVO> getCaptcha();

    public CommonResult<List<Language>> getLanguages(Long pid, Boolean all);

    public CommonResult<Collection<Language>> getProblemLanguages(Long pid);

    public CommonResult<List<CodeTemplate>> getProblemCodeTemplate(Long pid);
}
