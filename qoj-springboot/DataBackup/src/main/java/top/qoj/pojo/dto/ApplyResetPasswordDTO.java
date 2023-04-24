package top.qoj.pojo.dto;

import lombok.Data;


@Data
public class ApplyResetPasswordDTO {

    private String captcha;

    private String captchaKey;

    private String email;
}