package top.qoj.service.file.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusSystemErrorException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.file.ImportQDUOJProblemManager;
import top.qoj.service.file.ImportQDUOJProblemService;


@Service
public class ImportQDUOJProblemServiceImpl implements ImportQDUOJProblemService {

    @Autowired
    private ImportQDUOJProblemManager importQDUOJProblemManager;

    @Override
    public CommonResult<Void> importQDOJProblem(MultipartFile file) {
        try {
            importQDUOJProblemManager.importQDOJProblem(file);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusSystemErrorException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.SYSTEM_ERROR);
        }
    }
}