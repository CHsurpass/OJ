package top.qoj.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
public class SubmitIdListDTO {
    @NotEmpty(message = "查询的提交id列表不能为空")
    private List<Long> submitIds;

    private Long cid;
}