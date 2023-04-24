package top.qoj.dao.contest.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.qoj.dao.contest.ContestAnnouncementEntityService;
import top.qoj.mapper.ContestAnnouncementMapper;
import top.qoj.pojo.entity.contest.ContestAnnouncement;


@Service
public class ContestAnnouncementEntityServiceImpl extends ServiceImpl<ContestAnnouncementMapper, ContestAnnouncement> implements ContestAnnouncementEntityService {
}