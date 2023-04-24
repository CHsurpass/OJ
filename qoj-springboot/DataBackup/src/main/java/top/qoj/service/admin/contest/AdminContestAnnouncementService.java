package top.qoj.service.admin.contest;


import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.AnnouncementDTO;
import top.qoj.pojo.vo.AnnouncementVO;


public interface AdminContestAnnouncementService {

    public CommonResult<IPage<AnnouncementVO>> getAnnouncementList(Integer limit, Integer currentPage, Long cid);

    public CommonResult<Void> deleteAnnouncement(Long aid);

    public CommonResult<Void> addAnnouncement(AnnouncementDTO announcementDto);

    public CommonResult<Void> updateAnnouncement(AnnouncementDTO announcementDto);
}
