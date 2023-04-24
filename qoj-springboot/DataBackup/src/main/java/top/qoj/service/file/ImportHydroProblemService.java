package top.qoj.service.file;

import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.result.CommonResult;


public interface ImportHydroProblemService {

    public CommonResult<Void> importHydroProblem(MultipartFile file);
}
