package top.qoj.service.file.impl;

import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusForbiddenException;
import top.qoj.common.exception.StatusSystemErrorException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.file.TestCaseManager;
import top.qoj.service.file.TestCaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Service
public class TestCaseServiceImpl implements TestCaseService {

    @Resource
    private TestCaseManager testCaseManager;

    @Override
    public CommonResult<Map<Object, Object>> uploadTestcaseZip(MultipartFile file, Long gid, String mode) {
        try {
            return CommonResult.successResponse(testCaseManager.uploadTestcaseZip(file, gid, mode));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusSystemErrorException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.SYSTEM_ERROR);
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
        }
    }

    @Override
    public void downloadTestcase(Long pid, HttpServletResponse response) throws StatusFailException, StatusForbiddenException {
        testCaseManager.downloadTestcase(pid, response);
    }
}