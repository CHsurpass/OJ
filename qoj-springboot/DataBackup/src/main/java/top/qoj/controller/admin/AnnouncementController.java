package top.qoj.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.entity.common.Announcement;
import top.qoj.pojo.vo.AnnouncementVO;
import top.qoj.service.admin.announcement.AdminAnnouncementService;


@RestController
@RequiresAuthentication
@RequestMapping("/api/admin")
public class AnnouncementController {

    @Autowired
    private AdminAnnouncementService adminAnnouncementService;

    @GetMapping("/announcement")
    @RequiresPermissions("announcement_admin")
    public CommonResult<IPage<AnnouncementVO>> getAnnouncementList(@RequestParam(value = "limit", required = false) Integer limit,
                                                                   @RequestParam(value = "currentPage", required = false) Integer currentPage) {
        return adminAnnouncementService.getAnnouncementList(limit, currentPage);
    }

    @DeleteMapping("/announcement")
    @RequiresPermissions("announcement_admin")
    public CommonResult<Void> deleteAnnouncement(@RequestParam("aid") Long aid) {
        return adminAnnouncementService.deleteAnnouncement(aid);
    }

    @PostMapping("/announcement")
    @RequiresRoles("root")  // 只有超级管理员能操作
    @RequiresPermissions("announcement_admin")
    public CommonResult<Void> addAnnouncement(@RequestBody Announcement announcement) {
        return adminAnnouncementService.addAnnouncement(announcement);
    }

    @PutMapping("/announcement")
    @RequiresPermissions("announcement_admin")
    public CommonResult<Void> updateAnnouncement(@RequestBody Announcement announcement) {
        return adminAnnouncementService.updateAnnouncement(announcement);
    }
}