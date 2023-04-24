package top.qoj.dao.user;

import com.baomidou.mybatisplus.extension.service.IService;
import top.qoj.pojo.entity.user.Session;

public interface SessionEntityService extends IService<Session> {

    public void checkRemoteLogin(String uid);

}
