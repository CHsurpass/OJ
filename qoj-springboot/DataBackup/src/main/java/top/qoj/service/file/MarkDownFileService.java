package top.qoj.service.file;

import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.result.CommonResult;

import java.util.Map;

public interface MarkDownFileService {

    public CommonResult<Map<Object,Object>> uploadMDImg(MultipartFile image, Long gid);

    public CommonResult<Void> deleteMDImg(Long fileId);

    public CommonResult<Map<Object,Object>> uploadMd(MultipartFile file, Long gid);
}
