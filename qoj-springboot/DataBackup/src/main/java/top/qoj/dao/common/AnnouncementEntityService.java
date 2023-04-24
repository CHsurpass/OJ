package top.qoj.dao.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.pojo.entity.common.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;
import top.qoj.pojo.vo.AnnouncementVO;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface AnnouncementEntityService extends IService<Announcement> {

    IPage<AnnouncementVO> getAnnouncementList(int limit, int currentPage, Boolean notAdmin);

    IPage<AnnouncementVO> getContestAnnouncement(Long cid, Boolean notAdmin, int limit, int currentPage);
}
