package top.qoj.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import top.qoj.pojo.entity.problem.CodeTemplate;
import top.qoj.pojo.entity.problem.Language;
import top.qoj.pojo.entity.problem.Problem;
import top.qoj.pojo.entity.problem.ProblemCase;

import java.util.List;



@Data
@Accessors(chain = true)
public class ProblemDTO {

    private Problem problem;

    private List<ProblemCase> samples;

    private Boolean isUploadTestCase;

    private String uploadTestcaseDir;

    private String judgeMode;

    private Boolean changeModeCode;

    private Boolean changeJudgeCaseMode;

    private List<Language> languages;

    private List<CodeTemplate> codeTemplates;

}