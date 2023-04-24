package top.qoj.service.admin.announcement.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import top.qoj.common.exception.StatusFailException;
import top.qoj.common.result.CommonResult;
import top.qoj.manager.admin.announcement.AdminAnnouncementManager;
import top.qoj.pojo.entity.common.Announcement;
import top.qoj.pojo.vo.AnnouncementVO;
import top.qoj.service.admin.announcement.AdminAnnouncementService;

import javax.annotation.Resource;


@Service
public class AdminAnnouncementServiceImpl implements AdminAnnouncementService {

    @Resource
    private AdminAnnouncementManager adminAnnouncementManager;

    @Override
    public CommonResult<IPage<AnnouncementVO>> getAnnouncementList(Integer limit, Integer currentPage) {
        return CommonResult.successResponse(adminAnnouncementManager.getAnnouncementList(limit, currentPage));
    }

    @Override
    public CommonResult<Void> deleteAnnouncement(Long aid) {
        try {
            adminAnnouncementManager.deleteAnnouncement(aid);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> addAnnouncement(Announcement announcement) {
        try {
            adminAnnouncementManager.addAnnouncement(announcement);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }

    @Override
    public CommonResult<Void> updateAnnouncement(Announcement announcement) {
        try {
            adminAnnouncementManager.updateAnnouncement(announcement);
            return CommonResult.successResponse();
        } catch (StatusFailException e) {
            return CommonResult.errorResponse(e.getMessage());
        }
    }
}