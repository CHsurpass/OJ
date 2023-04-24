package top.qoj.service.file;

import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.result.CommonResult;

public interface ImportQDUOJProblemService {

    public CommonResult<Void> importQDOJProblem(MultipartFile file);
}
