package top.qoj.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.qoj.pojo.entity.judge.Judge;


@Data
public class SubmissionInfoVO {

    @ApiModelProperty(value = "提交详情")
    private Judge submission;

    @ApiModelProperty(value = "提交者是否可以分享该代码")
    private Boolean codeShare;
}