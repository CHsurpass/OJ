package top.qoj.crawler.problem;

import lombok.Data;
import lombok.experimental.Accessors;
import top.qoj.pojo.entity.problem.Problem;
import top.qoj.utils.Constants;

import java.util.List;

public abstract class ProblemStrategy {

    public abstract RemoteProblemInfo getProblemInfo(String problemId, String author) throws Exception;

    public RemoteProblemInfo getProblemInfoByLogin(String problemId, String author, String username, String password) throws Exception {
        return null;
    }

    @Data
    @Accessors(chain = true)
    public static
    class RemoteProblemInfo {
        private Problem problem;
        private List<String> langIdList;
        private Constants.RemoteOJ remoteOJ;
    }
}
