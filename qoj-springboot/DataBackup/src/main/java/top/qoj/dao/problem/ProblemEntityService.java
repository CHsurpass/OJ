package top.qoj.dao.problem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.qoj.pojo.entity.problem.Problem;
import top.qoj.pojo.dto.ProblemDTO;
import top.qoj.pojo.vo.ImportProblemVO;
import top.qoj.pojo.vo.ProblemVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 */

public interface ProblemEntityService extends IService<Problem> {
    Page<ProblemVO> getProblemList(int limit, int currentPage, Long pid, String title,
                                   Integer difficulty, List<Long> tid, String oj);

    boolean adminUpdateProblem(ProblemDTO problemDto);

    boolean adminAddProblem(ProblemDTO problemDto);

    ImportProblemVO buildExportProblem(Long pid, List<HashMap<String, Object>> problemCaseList, HashMap<Long, String> languageMap);
}
