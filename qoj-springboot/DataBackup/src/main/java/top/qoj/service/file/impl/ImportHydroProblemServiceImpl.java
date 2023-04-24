package top.qoj.service.file.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusSystemErrorException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.file.ImportHydroProblemManager;
import top.qoj.service.file.ImportHydroProblemService;

import javax.annotation.Resource;


@Component
public class ImportHydroProblemServiceImpl implements ImportHydroProblemService {

    @Resource
    private ImportHydroProblemManager importHydroProblemManager;

    @Override
    public CommonResult<Void> importHydroProblem(MultipartFile file) {
        try {
            importHydroProblemManager.importHydroProblem(file);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusSystemErrorException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.SYSTEM_ERROR);
        }
    }
}
