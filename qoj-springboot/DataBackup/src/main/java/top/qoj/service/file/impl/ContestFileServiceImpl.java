package top.qoj.service.file.impl;

import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.exception.StatusForbiddenException;
import top.qoj.manager.file.ContestFileManager;
import top.qoj.service.file.ContestFileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class ContestFileServiceImpl implements ContestFileService {

    @Resource
    private ContestFileManager contestFileManager;

    @Override
    public void downloadContestRank(Long cid, Boolean forceRefresh, Boolean removeStar, HttpServletResponse response) throws StatusFailException, IOException, StatusForbiddenException {
        contestFileManager.downloadContestRank(cid, forceRefresh, removeStar, response);
    }

    @Override
    public void downloadContestACSubmission(Long cid, Boolean excludeAdmin, String splitType, HttpServletResponse response) throws StatusFailException, StatusForbiddenException {
        contestFileManager.downloadContestACSubmission(cid, excludeAdmin, splitType, response);
    }

    @Override
    public void downloadContestPrintText(Long id, HttpServletResponse response) throws StatusForbiddenException {
        contestFileManager.downloadContestPrintText(id, response);
    }
}