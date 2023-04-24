package top.qoj.service.file;

import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusForbiddenException;
import top.qoj.common.result.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface TestCaseService {

    public CommonResult<Map<Object, Object>> uploadTestcaseZip(MultipartFile file, Long gid, String mode);

    public void downloadTestcase(Long pid, HttpServletResponse response) throws StatusFailException, StatusForbiddenException;
}