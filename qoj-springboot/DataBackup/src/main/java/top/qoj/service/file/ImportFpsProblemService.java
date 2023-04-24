package top.qoj.service.file;

import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.result.CommonResult;


public interface ImportFpsProblemService {

    public CommonResult<Void> importFPSProblem(MultipartFile file);
}