package top.qoj.service.file.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusSystemErrorException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.file.ProblemFileManager;
import top.qoj.service.file.ProblemFileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Service
public class ProblemFileServiceImpl implements ProblemFileService {

    @Resource
    private ProblemFileManager problemFileManager;

    @Override
    public CommonResult<Void> importProblem(MultipartFile file) {
        try {
            problemFileManager.importProblem(file);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusSystemErrorException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.SYSTEM_ERROR);
        }
    }

    @Override
    public void exportProblem(List<Long> pidList, HttpServletResponse response) {
        problemFileManager.exportProblem(pidList, response);
    }
}