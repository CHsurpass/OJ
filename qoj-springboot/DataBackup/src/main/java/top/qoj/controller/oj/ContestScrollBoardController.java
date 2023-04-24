package top.qoj.controller.oj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.qoj.annotation.AnonApi;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.vo.ContestScrollBoardInfoVO;
import top.qoj.pojo.vo.ContestScrollBoardSubmissionVO;
import top.qoj.service.oj.ContestScrollBoardService;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/api")
@AnonApi
public class ContestScrollBoardController {

    @Resource
    private ContestScrollBoardService contestScrollBoardService;

    @GetMapping("/get-contest-scroll-board-info")
    public CommonResult<ContestScrollBoardInfoVO> getContestScrollBoardInfo(@RequestParam(value = "cid", required = true) Long cid) {
        return contestScrollBoardService.getContestScrollBoardInfo(cid);
    }


    @GetMapping("/get-contest-scroll-board-submission")
    public CommonResult<List<ContestScrollBoardSubmissionVO>> getContestScrollBoardSubmission(
            @RequestParam(value = "cid", required = true) Long cid) {
        return contestScrollBoardService.getContestScrollBoardSubmission(cid);
    }


}
