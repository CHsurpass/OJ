package top.qoj.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.qoj.config.NacosSwitchConfig;
import top.qoj.config.SwitchConfig;
import top.qoj.annotation.QOJAccessEnum;
import top.qoj.exception.AccessException;


@Component
public class AccessValidator {

    @Autowired
    private NacosSwitchConfig nacosSwitchConfig;

    public void validateAccess(QOJAccessEnum QOJAccessEnum) throws AccessException {
        SwitchConfig switchConfig = nacosSwitchConfig.getSwitchConfig();
        switch (QOJAccessEnum) {
            case PUBLIC_DISCUSSION:
                if (!switchConfig.getOpenPublicDiscussion()) {
                    throw new AccessException("网站当前未开启公开讨论区的功能，不可访问！");
                }
                break;
            case GROUP_DISCUSSION:
                if (!switchConfig.getOpenGroupDiscussion()) {
                    throw new AccessException("网站当前未开启团队讨论区的功能，不可访问！");
                }
                break;
            case CONTEST_COMMENT:
                if (!switchConfig.getOpenContestComment()) {
                    throw new AccessException("网站当前未开启比赛评论区的功能，不可访问！");
                }
                break;
            case PUBLIC_JUDGE:
                if (!switchConfig.getOpenPublicJudge()) {
                    throw new AccessException("网站当前未开启题目评测的功能，禁止提交或调试！");
                }
                break;
            case GROUP_JUDGE:
                if (!switchConfig.getOpenGroupJudge()) {
                    throw new AccessException("网站当前未开启团队内题目评测的功能，禁止提交或调试！");
                }
                break;
            case CONTEST_JUDGE:
                if (!switchConfig.getOpenContestJudge()) {
                    throw new AccessException("网站当前未开启比赛题目评测的功能，禁止提交或调试！");
                }
                break;
            case HIDE_NON_CONTEST_SUBMISSION_CODE:
                if (switchConfig.getHideNonContestSubmissionCode()) {
                    throw new AccessException("网站当前开启了隐藏非比赛提交代码不显示的功能！");
                }
        }
    }
}
