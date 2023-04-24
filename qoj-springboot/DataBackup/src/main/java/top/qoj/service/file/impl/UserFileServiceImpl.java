package top.qoj.service.file.impl;

import org.springframework.stereotype.Service;
import top.qoj.manager.file.UserFileManager;
import top.qoj.service.file.UserFileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class UserFileServiceImpl implements UserFileService {

    @Resource
    private UserFileManager userFileManager;


    @Override
    public void generateUserExcel(String key, HttpServletResponse response) throws IOException {
        userFileManager.generateUserExcel(key, response);
    }
}