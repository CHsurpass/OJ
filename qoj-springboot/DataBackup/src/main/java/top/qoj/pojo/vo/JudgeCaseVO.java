package top.qoj.pojo.vo;

import lombok.Data;
import top.qoj.pojo.entity.judge.JudgeCase;

import java.util.List;


@Data
public class JudgeCaseVO {

    /**
     * 当judgeCaseMode为default时
     */
    private List<JudgeCase> judgeCaseList;

    /**
     * 当judgeCaseMode为subtask_lowest,subtask_average时
     */
    private List<SubTaskJudgeCaseVO> subTaskJudgeCaseVoList;

    /**
     * default,subtask_lowest,subtask_average
     */
    private String judgeCaseMode;
}
