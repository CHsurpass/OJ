package top.qoj.service.admin.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.qoj.common.result.CommonResult;
import top.qoj.pojo.dto.AdminEditUserDTO;
import top.qoj.pojo.vo.UserRolesVO;

import java.util.List;
import java.util.Map;


public interface AdminUserService {

    public CommonResult<IPage<UserRolesVO>> getUserList(Integer limit, Integer currentPage, Boolean onlyAdmin, String keyword);

    public CommonResult<Void> editUser(AdminEditUserDTO adminEditUserDto);

    public CommonResult<Void> deleteUser(List<String> deleteUserIdList);

    public CommonResult<Void> insertBatchUser(List<List<String>> users);

    public CommonResult<Map<Object,Object>> generateUser(Map<String, Object> params);

}