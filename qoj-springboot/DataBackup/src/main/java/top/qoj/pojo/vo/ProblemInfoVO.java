package top.qoj.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.qoj.pojo.entity.problem.Problem;

import java.util.HashMap;
import java.util.List;


@Data
@AllArgsConstructor
public class ProblemInfoVO {
    /**
     * 题目内容
     */
    private Problem problem;
    /**
     * 题目可用编程语言
     */
    private List<String> languages;
    /**
     * 题目提交统计情况
     */
    private ProblemCountVO problemCount;
    /**
     * 题目默认模板
     */
    private HashMap<String, String> codeTemplate;
}