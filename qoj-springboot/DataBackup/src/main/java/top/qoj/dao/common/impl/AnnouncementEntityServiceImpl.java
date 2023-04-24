package top.qoj.dao.common.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.qoj.dao.common.AnnouncementEntityService;
import top.qoj.mapper.AnnouncementMapper;
import top.qoj.pojo.entity.common.Announcement;
import top.qoj.pojo.vo.AnnouncementVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class AnnouncementEntityServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementEntityService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public IPage<AnnouncementVO> getAnnouncementList(int limit, int currentPage, Boolean notAdmin) {
        //新建分页
        Page<AnnouncementVO> page = new Page<>(currentPage, limit);
        return announcementMapper.getAnnouncementList(page,notAdmin);
    }

    @Override
    public IPage<AnnouncementVO> getContestAnnouncement(Long cid, Boolean notAdmin, int limit, int currentPage) {
        Page<AnnouncementVO> page = new Page<>(currentPage, limit);
        return announcementMapper.getContestAnnouncement(page,cid,notAdmin);
    }
}
