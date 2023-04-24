package top.qoj.pojo.vo;

import lombok.Data;


@Data
public class ProblemFullScreenListVO {

    private Long pid;

    private String problemId;

    private String title;

    private Integer status;

    private Integer score;
}
