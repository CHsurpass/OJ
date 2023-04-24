package top.qoj.pojo.dto;

import lombok.Data;


@Data
public class LastAcceptedCodeVO {

    private Long submitId;

    private String code;

    private String language;
}
