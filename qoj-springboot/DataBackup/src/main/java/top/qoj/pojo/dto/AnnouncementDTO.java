package top.qoj.pojo.dto;

import lombok.Data;
import top.qoj.pojo.entity.common.Announcement;

import javax.validation.constraints.NotBlank;


@Data
public class AnnouncementDTO {
    @NotBlank(message = "比赛id不能为空")
    private Long cid;

    private Announcement announcement;
}