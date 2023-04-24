package top.qoj.controller.file;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.result.CommonResult;
import top.qoj.service.file.ImportFpsProblemService;

import javax.annotation.Resource;


@Controller
@RequestMapping("/api/file")
public class ImportFpsProblemController {


    @Resource
    private ImportFpsProblemService importFpsProblemService;

    /**
     * @param file
     * @MethodName importFpsProblem
     * @Description zip文件导入题目 仅超级管理员可操作
     * @Return
     */
    @RequiresRoles("root")
    @RequiresAuthentication
    @ResponseBody
    @PostMapping("/import-fps-problem")
    public CommonResult<Void> importFPSProblem(@RequestParam("file") MultipartFile file) {
        return importFpsProblemService.importFPSProblem(file);
    }


}