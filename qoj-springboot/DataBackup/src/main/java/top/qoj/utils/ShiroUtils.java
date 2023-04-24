package top.qoj.utils;

import org.apache.shiro.SecurityUtils;
import top.qoj.shiro.AccountProfile;


public class ShiroUtils {

    private ShiroUtils() {
    }

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}