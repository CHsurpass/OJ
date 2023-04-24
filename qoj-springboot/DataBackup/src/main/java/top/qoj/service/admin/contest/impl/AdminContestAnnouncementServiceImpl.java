package top.qoj.service.admin.contest.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.admin.contest.AdminContestAnnouncementManager;
import top.qoj.pojo.dto.AnnouncementDTO;
import top.qoj.pojo.vo.AnnouncementVO;
import top.qoj.service.admin.contest.AdminContestAnnouncementService;


@Service
public class AdminContestAnnouncementServiceImpl implements AdminContestAnnouncementService {

    @Autowired
    private AdminContestAnnouncementManager adminContestAnnouncementManager;

    @Override
    public CommonResult<IPage<AnnouncementVO>> getAnnouncementList(Integer limit, Integer currentPage, Long cid) {
        IPage<AnnouncementVO> announcementList = adminContestAnnouncementManager.getAnnouncementList(limit, currentPage, cid);
        return CommonResult.successResponse(announcementList);
    }

    @Override
    public CommonResult<Void> deleteAnnouncement(Long aid) {
        try {
            adminContestAnnouncementManager.deleteAnnouncement(aid);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> addAnnouncement(AnnouncementDTO announcementDto) {
        try {
            adminContestAnnouncementManager.addAnnouncement(announcementDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> updateAnnouncement(AnnouncementDTO announcementDto) {
        try {
            adminContestAnnouncementManager.updateAnnouncement(announcementDto);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }
}