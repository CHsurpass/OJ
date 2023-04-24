package top.qoj.dao.user.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.qoj.dao.user.SessionEntityService;
import top.qoj.mapper.SessionMapper;
import top.qoj.pojo.entity.user.Session;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class SessionEntityServiceImpl extends ServiceImpl<SessionMapper, Session> implements SessionEntityService {

    @Resource
    private SessionMapper sessionMapper;

    @Override
    @Async
    public void checkRemoteLogin(String uid) {
        QueryWrapper<Session> sessionQueryWrapper = new QueryWrapper<>();
        sessionQueryWrapper.eq("uid", uid)
                .orderByDesc("gmt_create")
                .last("limit 2");
        List<Session> sessionList = sessionMapper.selectList(sessionQueryWrapper);
        if (sessionList.size() < 2) {
            return;
        }
        Session nowSession = sessionList.get(0);
        Session lastSession = sessionList.get(1);
    }

    private String getRemoteLoginContent(String oldIp, String newIp, Date loginDate) {
        String dateStr = DateUtil.format(loginDate, "yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("亲爱的用户，您好！您的账号于").append(dateStr);
        String addr = null;
        try {
            String newRes = HttpUtil.get("https://whois.pconline.com.cn/ipJson.jsp?ip=" + newIp + "&json=true");
            JSONObject newResJson = JSONUtil.parseObj(newRes);
            addr = newResJson.getStr("addr");

            String newCityCode = newResJson.getStr("cityCode");

            String oldRes = HttpUtil.get("https://whois.pconline.com.cn/ipJson.jsp?ip=" + oldIp + "&json=true");
            JSONObject oldResJson = JSONUtil.parseObj(oldRes);

            String oldCityCode = oldResJson.getStr("cityCode");

            if (newCityCode == null || oldCityCode == null || newCityCode.equals(oldCityCode)) {
                return null;
            }

        } catch (Exception ignored) {
            return null;
        }
        if (!StringUtils.isEmpty(addr)) {
            sb.append("在【")
                    .append(addr)
                    .append("】");
        }
        sb.append("登录，登录IP为：【")
                .append(newIp)
                .append("】，若非本人操作，请立即修改密码。")
                .append("\n\n")
                .append("Hello! Dear user, Your account was logged in in");

        if (!StringUtils.isEmpty(addr)) {
            sb.append(" 【")
                    .append(addr)
                    .append("】 on ")
                    .append(dateStr)
                    .append(". If you do not operate by yourself, please change your password immediately.");
        }

        return sb.toString();
    }
}