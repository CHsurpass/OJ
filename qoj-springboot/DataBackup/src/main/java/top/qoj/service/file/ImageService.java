package top.qoj.service.file;

import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.result.CommonResult;

import java.util.Map;

public interface ImageService {

    public CommonResult<Map<Object, Object>> uploadAvatar(MultipartFile image);

}
