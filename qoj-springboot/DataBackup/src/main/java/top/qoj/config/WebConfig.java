package top.qoj.config;

import lombok.Data;
import top.qoj.utils.IpUtils;


@Data
public class WebConfig {

    // 邮箱配置
    private String emailUsername;

    private String emailPassword;

    private String emailHost;

    private Integer emailPort;

    private Boolean emailSsl = true;

    private String emailBGImg = "https://i.jpg.dog/451cc12f8dd99531a3f66e4602346862.jpeg";

    // 网站前端显示配置
    private String baseUrl = "http://" + IpUtils.getServiceIp();

    private String name = "Qcode Online Judge";

    private String shortName = "QOJ";

    private String description;

    private Boolean register = true;

    private String recordName;

    private String recordUrl;

    private String projectName = "QOJ";

    private String projectUrl = "https://github.com/CHsurpass/QOJ";
}
