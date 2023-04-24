package top.qoj.controller.oj;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.qoj.annotation.AnonApi;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.entity.problem.CodeTemplate;
import top.qoj.pojo.entity.problem.Language;
import top.qoj.pojo.vo.CaptchaVO;
import top.qoj.service.oj.CommonService;

import java.util.Collection;
import java.util.List;

/**
 * @Description: 通用的请求控制处理类
 */
@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    private CommonService commonService;


    @GetMapping("/captcha")
    @AnonApi
    public CommonResult<CaptchaVO> getCaptcha() {
        return commonService.getCaptcha();
    }


    @GetMapping("/languages")
    @AnonApi
    public CommonResult<List<Language>> getLanguages(@RequestParam(value = "pid", required = false) Long pid,
                                                     @RequestParam(value = "all", required = false) Boolean all) {
        return commonService.getLanguages(pid, all);
    }

    @GetMapping("/get-problem-languages")
    @AnonApi
    public CommonResult<Collection<Language>> getProblemLanguages(@RequestParam("pid") Long pid) {
        return commonService.getProblemLanguages(pid);
    }

    @GetMapping("/get-problem-code-template")
    @AnonApi
    public CommonResult<List<CodeTemplate>> getProblemCodeTemplate(@RequestParam("pid") Long pid) {
        return commonService.getProblemCodeTemplate(pid);
    }

}