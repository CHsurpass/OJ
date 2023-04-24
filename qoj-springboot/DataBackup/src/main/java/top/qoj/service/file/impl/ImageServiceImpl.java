package top.qoj.service.file.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusSystemErrorException;
import top.qoj.common.result.CommonResult;
import top.qoj.common.result.ResultStatus;
import top.qoj.manager.file.ImageManager;
import top.qoj.service.file.ImageService;

import javax.annotation.Resource;
import java.util.Map;


@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageManager imageManager;

    @Override
    public CommonResult<Map<Object, Object>> uploadAvatar(MultipartFile image) {
        try {
            return CommonResult.successResponse(imageManager.uploadAvatar(image));
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        } catch (StatusSystemErrorException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.SYSTEM_ERROR);
        }
    }

}