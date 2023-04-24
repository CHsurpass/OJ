package top.qoj.controller.oj;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.CheckACDTO;
import top.qoj.pojo.entity.contest.ContestPrint;
import top.qoj.pojo.entity.contest.ContestRecord;
import top.qoj.service.oj.ContestAdminService;

/**
 * @Description: 处理比赛管理模块的相关数据请求
 */
@RestController
@RequestMapping("/api")
public class ContestAdminController {

    @Autowired
    private ContestAdminService contestAdminService;

    /**
     * @MethodName getContestACInfo
     * @Params * @param null
     * @Description 获取各个用户的ac情况，仅限于比赛管理者可查看
     * @Return
     */
    @GetMapping("/get-contest-ac-info")
    @RequiresAuthentication
    public CommonResult<IPage<ContestRecord>> getContestACInfo(@RequestParam("cid") Long cid,
                                                               @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                               @RequestParam(value = "limit", required = false) Integer limit) {

        return contestAdminService.getContestACInfo(cid, currentPage, limit);
    }


    /**
     * @MethodName checkContestACInfo
     * @Params * @param null
     * @Description 比赛管理员确定该次提交的ac情况
     * @Return
     */
    @PutMapping("/check-contest-ac-info")
    @RequiresAuthentication
    public CommonResult<Void> checkContestACInfo(@RequestBody CheckACDTO checkACDto) {

        return contestAdminService.checkContestACInfo(checkACDto);
    }


    @GetMapping("/get-contest-print")
    @RequiresAuthentication
    public CommonResult<IPage<ContestPrint>> getContestPrint(@RequestParam("cid") Long cid,
                                                             @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                             @RequestParam(value = "limit", required = false) Integer limit) {

        return contestAdminService.getContestPrint(cid, currentPage, limit);
    }

    /**
     * @param id
     * @param cid
     * @MethodName checkContestStatus
     * @Description 更新该打印为确定状态
     * @Return
     */
    @PutMapping("/check-contest-print-status")
    @RequiresAuthentication
    public CommonResult<Void> checkContestPrintStatus(@RequestParam("id") Long id,
                                                      @RequestParam("cid") Long cid) {

        return contestAdminService.checkContestPrintStatus(id, cid);
    }

}