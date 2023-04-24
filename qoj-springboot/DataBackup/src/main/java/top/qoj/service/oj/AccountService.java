package top.qoj.service.oj;

import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.ChangeEmailDTO;
import top.qoj.pojo.dto.ChangePasswordDTO;
import top.qoj.pojo.dto.CheckUsernameOrEmailDTO;
import top.qoj.pojo.vo.*;


public interface AccountService {

    public CommonResult<CheckUsernameOrEmailVO> checkUsernameOrEmail(CheckUsernameOrEmailDTO checkUsernameOrEmailDto);

    public CommonResult<UserHomeVO> getUserHomeInfo(String uid, String username);

    public CommonResult<UserCalendarHeatmapVO> getUserCalendarHeatmap(String uid, String username);

    public CommonResult<ChangeAccountVO> changePassword(ChangePasswordDTO changePasswordDto);

    public CommonResult<Void> getChangeEmailCode(String email);

    public CommonResult<ChangeAccountVO> changeEmail(ChangeEmailDTO changeEmailDto);

    public CommonResult<UserInfoVO> changeUserInfo(UserInfoVO userInfoVo);

}
