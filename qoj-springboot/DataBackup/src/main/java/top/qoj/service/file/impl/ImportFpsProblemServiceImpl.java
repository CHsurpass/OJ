package top.qoj.service.file.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.file.ImportFpsProblemManager;
import top.qoj.service.file.ImportFpsProblemService;

import javax.annotation.Resource;
import java.io.IOException;


@Service
public class ImportFpsProblemServiceImpl implements ImportFpsProblemService {

    @Resource
    private ImportFpsProblemManager importFpsProblemManager;

    @Override
    public CommonResult<Void> importFPSProblem(MultipartFile file) {
        try {
            importFpsProblemManager.importFPSProblem(file);
            return CommonResult.successResponse();
        } catch (IOException | StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }
}