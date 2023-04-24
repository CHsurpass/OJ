package top.qoj.controller.file;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.result.CommonResult;
import top.qoj.service.file.ImageService;

import java.util.Map;


@Controller
@RequestMapping("/api/file")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/upload-avatar", method = RequestMethod.POST)
    @RequiresAuthentication
    @ResponseBody
    public CommonResult<Map<Object, Object>> uploadAvatar(@RequestParam("image") MultipartFile image) {
        return imageService.uploadAvatar(image);
    }

}