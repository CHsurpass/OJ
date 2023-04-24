package top.qoj.manager.oj;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.qoj.dao.problem.CodeTemplateEntityService;
import top.qoj.dao.problem.LanguageEntityService;
import top.qoj.dao.problem.ProblemEntityService;
import top.qoj.dao.problem.ProblemLanguageEntityService;
import top.qoj.pojo.entity.problem.CodeTemplate;
import top.qoj.pojo.entity.problem.Language;
import top.qoj.pojo.entity.problem.Problem;
import top.qoj.pojo.entity.problem.ProblemLanguage;
import top.qoj.pojo.vo.CaptchaVO;
import top.qoj.utils.RedisUtils;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class CommonManager {

    @Autowired
    private LanguageEntityService languageEntityService;

    @Autowired
    private ProblemLanguageEntityService problemLanguageEntityService;

    @Autowired
    private RedisUtils redisUtil;

    @Autowired
    private ProblemEntityService problemEntityService;

    @Autowired
    private CodeTemplateEntityService codeTemplateEntityService;


    public CaptchaVO getCaptcha() {
        ArithmeticCaptcha specCaptcha = new ArithmeticCaptcha(90, 30, 4);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        // 2位数运算
        specCaptcha.setLen(2);
        String verCode = specCaptcha.text().toLowerCase();
        String key = IdUtil.simpleUUID();
        // 存入redis并设置过期时间为30分钟
        redisUtil.set(key, verCode, 1800);
        // 将key和base64返回给前端
        CaptchaVO captchaVo = new CaptchaVO();
        captchaVo.setImg(specCaptcha.toBase64());
        captchaVo.setCaptchaKey(key);
        return captchaVo;
    }



    public List<Language> getLanguages(Long pid, Boolean all) {

        String oj = "ME";
        if (pid != null) {
            Problem problem = problemEntityService.getById(pid);
            if (problem.getIsRemote()) {
                oj = problem.getProblemId().split("-")[0];
            }
        }

        if (oj.equals("GYM")) {  // GYM用与CF一样的编程语言列表
            oj = "CF";
        }

        QueryWrapper<Language> queryWrapper = new QueryWrapper<>();
        // 获取对应OJ支持的语言列表
        queryWrapper.eq(all != null && !all, "oj", oj);
        return languageEntityService.list(queryWrapper);
    }

    public Collection<Language> getProblemLanguages(Long pid) {
        QueryWrapper<ProblemLanguage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid).select("lid");
        List<Long> idList = problemLanguageEntityService.list(queryWrapper)
                .stream().map(ProblemLanguage::getLid).collect(Collectors.toList());
        return languageEntityService.listByIds(idList);

    }

    public List<CodeTemplate> getProblemCodeTemplate(Long pid) {
        QueryWrapper<CodeTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        return codeTemplateEntityService.list(queryWrapper);
    }

}