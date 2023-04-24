package top.qoj.service.admin.announcement;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.entity.common.Announcement;
import top.qoj.pojo.vo.AnnouncementVO;


public interface AdminAnnouncementService {

    public CommonResult<IPage<AnnouncementVO>> getAnnouncementList(Integer limit, Integer currentPage);

    public CommonResult<Void> deleteAnnouncement(Long aid);

    public CommonResult<Void> addAnnouncement(Announcement announcement);

    public CommonResult<Void> updateAnnouncement(Announcement announcement);
}