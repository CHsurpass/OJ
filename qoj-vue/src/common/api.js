import axios from 'axios'
import Vue from 'vue'
import mMessage from '@/common/message'
import router from '@/router'
import store from "@/store"
import utils from '@/common/utils'
import i18n from '@/i18n'
// import NProgress from 'nprogress' // nprogress插件
// import 'nprogress/nprogress.css' // nprogress样式

// // 配置NProgress进度条选项  —— 动画效果
// NProgress.configure({ ease: 'ease', speed: 1000,showSpinner: false})
Vue.prototype.$http = axios

const isMobile = /ipad|iphone|midp|rv:1.2.3.4|ucweb|android|windows ce|windows mobile/.test(navigator.userAgent.toLowerCase());


// 请求超时时间
axios.defaults.timeout = 90000;

axios.interceptors.request.use(

  config => {

    // NProgress.start();
    // 每次发送请求之前判断vuex中是否存在token
    // 如果存在，则统一在http请求的header都加上token，这样后台根据token判断你的登录情况
    // 即使本地存在token，也有可能token是过期的，所以在响应拦截器中要对返回状态进行判断
    const token = localStorage.getItem('token')
    if(config.url != '/api/login' && config.url != '/api/admin/login'){
      token && (config.headers.Authorization = token);
    }
    let type = config.url.split("/")[1];
    if (type === 'admin') { // 携带请求区别是否为admin
      config.headers['Url-Type'] = type
    } else {
      config.headers['Url-Type'] = 'general'
    }

    return config;
  },
  error => {
    // NProgress.done();
    mMessage.error(error.response.data.msg);
    if (!isMobile) {
      Vue.prototype.$notify.error({
        title: i18n.t('m.Error'),
        message: error.response.data.msg,
        duration: 5000,
        offset: 50
      });
    }
    return Promise.error(error);
  })

// 响应拦截器
axios.interceptors.response.use(
  response => {
    // NProgress.done();
    if (response.headers['refresh-token']) { // token续约！
      store.commit('changeUserToken', response.headers['authorization'])
    }
    if (response.data.status === 200 || response.data.status == undefined) {
      return Promise.resolve(response);
    } else {
      mMessage.error(response.data.msg);
      if (!isMobile) {
        Vue.prototype.$notify.error({
          title: i18n.t('m.Error'),
          message: response.data.msg,
          duration: 5000,
          offset: 50
        });
      }
      return Promise.reject(response);
    }

  },
  // 服务器状态码不是200的情况
  error => {
    // NProgress.done();
    if (error.response) {
      if (error.response.headers['refresh-token']) { // token续约！！
        store.commit('changeUserToken', error.response.headers['authorization'])
      }
      if (error.response.data instanceof Blob) { // 如果是文件操作的返回，由后续进行处理
        return Promise.resolve(error.response);
      }
      switch (error.response.status) {
        // 401: 未登录 token过期
        // 未登录则跳转登录页面，并携带当前页面的路径
        // 在登录成功后返回当前页面，这一步需要在登录页操作。
        case 401:
          if (error.response.data.msg) {
            mMessage.error(error.response.data.msg);
            if (!isMobile) {
              Vue.prototype.$notify.error({
                title: i18n.t('m.Error'),
                message: error.response.data.msg,
                duration: 5000,
                offset: 50
              });
            }
          }
          if (error.response.config.headers['Url-Type'] === 'admin') {
            router.push("/api/admin/login")
          } else {
            store.commit('changeModalStatus', { mode: 'Login', visible: true });
          }
          store.commit('clearUserInfoAndToken');
          break;
        // 403
        // 无权限访问或操作的请求
        case 403:
          if (error.response.data.msg) {
            mMessage.error(error.response.data.msg);
            if (!isMobile) {
              Vue.prototype.$notify.error({
                title: i18n.t('m.Error'),
                message: error.response.data.msg,
                duration: 5000,
                offset: 50
              });
            }
          }
          if(error.response.config.url.startsWith('/api/admin')){
            router.push("/admin")
          }
          break;
        // 404请求不存在
        case 404:
          mMessage.error(i18n.t('m.Query_error_unable_to_find_the_resource_to_request'));
          break;
        // 其他错误，直接抛出错误提示
        default:
          if (error.response.data) {
            if (error.response.data.msg) {
              mMessage.error(error.response.data.msg);
              if (!isMobile) {
                Vue.prototype.$notify.error({
                  title: i18n.t('m.Error'),
                  message: error.response.data.msg,
                  duration: 5000,
                  offset: 50
                });
              }
            } else {
              mMessage.error(i18n.t('m.Server_error_please_refresh_again'));
            }
          }
          break;
      }
      return Promise.reject(error);
    } else { //处理断网或请求超时，请求没响应
      if (error.code == 'ECONNABORTED' || error.message.includes('timeout')) {
        mMessage.error(i18n.t('m.Request_timed_out_please_try_again_later'));
      } else {
        mMessage.error(i18n.t('m.Network_error_abnormal_link_with_server_please_try_again_later'));
      }
      return Promise.reject(error);
    }
  }
);


// 处理oj前台的请求
const ojApi = {
  // Home页的请求
  getWebsiteConfig() {
    return ajax('/api/get-website-config', 'get', {
    })
  },
  getRecentContests() {
    return ajax('/api/get-recent-contest', 'get', {
    })
  },
  getRecentOtherContests() {
    return ajax('/api/get-recent-other-contest', 'get', {
    })
  },
  getAnnouncementList(currentPage, limit) {
    let params = {
      currentPage: currentPage,
      limit: limit
    }
    return ajax('/api/get-common-announcement', 'get', {
      params
    })
  },
  getRecent7ACRank() {
    return ajax('/api/get-recent-seven-ac-rank', 'get', {
    })
  },

  // 用户账户的相关请求
  getRegisterEmail(email) {
    let params = {
      email: email
    }
    return ajax('/api/get-register-code', 'get', {
      params
    })
  },

  login(data) {
    return ajax('/api/login', 'post', {
      data
    })
  },
  checkUsernameOrEmail(username, email) {
    return ajax('/api/check-username-or-email', 'post', {
      data: {
        username,
        email
      }
    })
  },
  // 获取验证码
  getCaptcha() {
    return ajax('/api/captcha', 'get')
  },
  // 注册
  register(data) {
    return ajax('/api/register', 'post', {
      data
    })
  },
  logout() {
    return ajax('/api/logout', 'get')
  },

  // 账户的相关操作
  applyResetPassword(data) {
    return ajax('/api/apply-reset-password', 'post', {
      data
    })
  },
  resetPassword(data) {
    return ajax('/api/reset-password', 'post', {
      data
    })
  },

  getProblemList(limit, searchParams) {
    let params = {
      limit
    }
    Object.keys(searchParams).forEach((element) => {
      if (searchParams[element] !== '' && searchParams[element] !== null && searchParams[element] !== undefined) {
        params[element] = searchParams[element]
      }
    })
    return ajax('/api/get-problem-list', 'get', {
      params: params
    })
  },

  // 查询当前登录用户对题目的提交状态
  getUserProblemStatus(pidList, isContestProblemList, cid, gid) {
    return ajax("/api/get-user-problem-status", 'post', {
      data: {
        pidList,
        isContestProblemList,
        cid,
        gid
      }
    })
  },


  // Problem详情页的相关请求
  getProblem(problemId, cid, gid) {
    return ajax('/api/get-problem-detail', 'get', {
      params: {
        problemId,
        gid
      }
    })
  },

  // 获取题目代码模板
  getProblemCodeTemplate(pid) {
    return ajax('/api/get-problem-code-template', 'get', {
      params: {
        pid
      }
    })
  },

  // 提交评测模块
  submitCode(data) {
    return ajax('/api/submit-problem-judge', 'post', {
      data
    })
  },
  // 获取单个提交的信息
  getSubmission(submitId) {
    return ajax('/api/get-submission-detail', 'get', {
      params: {
        submitId
      }
    })
  },
  // 在线调试
  submitTestJudge(data) {
    return ajax('/api/submit-problem-test-judge', 'post', {
      data
    })
  },
  // 获取调试结果
  getTestJudgeResult(testJudgeKey) {
    return ajax('/api/get-test-judge-result', 'get', {
      params: {
        testJudgeKey
      }
    })
  },
  // 获取最近一次通过的代码
  getUserLastAccepetedCode(pid, cid){
    let params = {
      pid
    }
    if(cid){
      params.cid = cid
    }
    return ajax('/api/get-last-ac-code', 'get', {
      params: params
    })
  },
  // 获取题目专注模式底部题目列表
  getFullScreenProblemList(tid, cid){
    let params = {tid, cid}
    return ajax('/api/get-full-screen-problem-list', 'get', {
      params: params
    })
  },
  // 获取单个提交的全部测试点详情
  getAllCaseResult(submitId) {
    return ajax('/api/get-all-case-result', 'get', {
      params: {
        submitId,
      }
    })
  },
  // 远程虚拟判题失败进行重新提交
  reSubmitRemoteJudge(submitId) {
    return ajax("/api/resubmit", 'get', {
      params: {
        submitId,
      }
    })
  },
  // 更新提交详情
  updateSubmission(data) {
    return ajax('/api/submission', 'put', {
      data
    })
  },
  getSubmissionList(limit, params) {
    params.limit = limit
    return ajax('/api/get-submission-list', 'get', {
      params
    })
  },
  checkSubmissonsStatus(submitIds, cid) {
    return ajax('/api/check-submissions-status', 'post', {
      data: { submitIds, cid }
    })
  },
  checkContestSubmissonsStatus(submitIds, cid) {
    return ajax('/api/check-contest-submissions-status', 'post', {
      data: { submitIds, cid }
    })
  },

  submissionRejudge(submitId) {
    return ajax('/api/admin/judge/rejudge', 'get', {
      params: {
        submitId
      }
    })
  },

  admin_manualJudge(submitId, status, score) {
    return ajax('/api/admin/judge/manual-judge', 'get', {
      params: {
        submitId,
        status,
        score
      }
    })
  },

  admin_cancelJudge(submitId) {
    return ajax('/api/admin/judge/cancel-judge', 'get', {
      params: {
        submitId
      }
    })
  },


  // ------------------------------------------------------------------------------------------------


  // 比赛列表页的请求
  getContestList(currentPage, limit, query) {
    let params = {
      currentPage,
      limit
    }
    if (query !== undefined) {
      Object.keys(query).forEach((element) => {
        if (query[element]) {
          params[element] = query[element]
        }
      })
    }
    return ajax('/api/get-contest-list', 'get', {
      params: params
    })
  },

  // 比赛详情的请求
  getContest(cid) {
    return ajax('/api/get-contest-info', 'get', {
      params: { cid }
    })
  },
  // 获取赛外榜单比赛的信息
  getScoreBoardContestInfo(cid) {
    return ajax('/api/get-contest-outsize-info', 'get', {
      params: { cid }
    })
  },
  // 提供比赛外榜排名数据
  getContestOutsideScoreboard(data) {
    return ajax('/api/get-contest-outside-scoreboard', 'post', {
      data
    })
  },
  // 注册私有比赛权限
  registerContest(cid, password) {
    return ajax('/api/register-contest', 'post', {
      data: {
        cid,
        password
      }
    })
  },
  // 获取注册比赛权限
  getContestAccess(cid) {
    return ajax('/api/get-contest-access', 'get', {
      params: { cid }
    })
  },
  // 获取比赛题目列表
  getContestProblemList(cid) {
    return ajax('/api/get-contest-problem', 'get', {
      params: { cid }
    })
  },
  // 获取比赛题目详情
  getContestProblem(displayId, cid) {
    return ajax('/api/get-contest-problem-details', 'get', {
      params: { displayId, cid }
    })
  },
  // 获取比赛提交列表
  getContestSubmissionList(limit, params) {
    params.limit = limit
    return ajax('/api/contest-submissions', 'get', {
      params
    })
  },

  getContestRank(data) {
    return ajax('/api/get-contest-rank', 'post', {
      data
    })
  },

  // 获取比赛公告列表
  getContestAnnouncementList(currentPage, limit, cid) {
    let params = {
      currentPage,
      limit,
      cid
    }
    return ajax('/api/get-contest-announcement', 'get', {
      params
    })
  },

  // 获取比赛未阅读公告列表
  getContestUserNotReadAnnouncement(data) {
    return ajax('/api/get-contest-not-read-announcement', 'post', {
      data
    })
  },

  // 获取acm比赛ac信息
  getACMACInfo(params) {
    return ajax('/api/get-contest-ac-info', 'get', {
      params
    })
  },
  // 确认ac信息
  updateACInfoCheckedStatus(data) {
    return ajax('/api/check-contest-ac-info', 'put', {
      data
    })
  },

  // 提交打印文本
  submitPrintText(data) {
    return ajax('/api/submit-print-text', 'post', {
      data
    })
  },

  // 获取比赛打印文本列表
  getContestPrintList(params) {
    return ajax('/api/get-contest-print', 'get', {
      params
    })
  },

  // 更新比赛打印的状态
  updateContestPrintStatus(params) {
    return ajax('/api/check-contest-print-status', 'put', {
      params
    })
  },


  // 比赛题目对应的提交重判
  ContestRejudgeProblem(params) {
    return ajax('/api/admin/judge/rejudge-contest-problem', 'get', {
      params
    })
  },

  // ACM赛制或OI赛制的排行榜
  getUserRank(currentPage, limit, type, searchUser) {
    return ajax('/api/get-rank-list', 'get', {
      params: {
        currentPage,
        limit,
        type,
        searchUser
      }
    })
  },

  // about页部分请求
  getAllLanguages(all) {
    return ajax("/api/languages", 'get', {
      params: {
        all
      }
    })
  },
  // userhome页的请求
  getUserInfo(uid, username) {
    return ajax("/api/get-user-home-info", 'get', {
      params: { uid, username }
    })
  },

  getUserCalendarHeatmap(uid, username) {
    return ajax("/api/get-user-calendar-heatmap", 'get', {
      params: { uid, username }
    })
  },

  // setting页的请求
  changePassword(data) {
    return ajax("/api/change-password", 'post', {
      data
    })
  },
  getChangeEmailCode(email) {
    return ajax("/api/get-change-email-code", 'get',  {
      params: { email }
    })
  },
  changeEmail(data) {
    return ajax("/api/change-email", 'post', {
      data
    })
  },
  changeUserInfo(data) {
    return ajax("/api/change-userInfo", 'post', {
      data
    })
  }

}

// 处理admin后台管理的请求
const adminApi = {
  // 登录
  admin_login(username, password) {
    return ajax('/api/admin/login', 'post', {
      data: {
        username,
        password
      }
    })
  },
  admin_logout() {
    return ajax('/api/admin/logout', 'get')
  },
  admin_getDashboardInfo() {
    return ajax('/api/admin/dashboard/get-dashboard-info', 'get')
  },
  getSessions(data) {
    return ajax('/api/admin/dashboard/get-sessions', 'post', {
      data
    })
  },
  //获取数据后台服务和nacos相关详情
  getJudgeServer() {
    return ajax('/api/admin/config/get-judge-service-info', 'get')
  },

  // 获取用户列表
  admin_getUserList(currentPage, limit, keyword, onlyAdmin) {
    let params = { currentPage, limit }
    if (keyword) {
      params.keyword = keyword
    }
    params.onlyAdmin = onlyAdmin
    return ajax('/api/admin/user/get-user-list', 'get', {
      params: params
    })
  },
  // 编辑用户
  admin_editUser(data) {
    return ajax('/api/admin/user/edit-user', 'put', {
      data
    })
  },
  admin_deleteUsers(ids) {
    return ajax('/api/admin/user/delete-user', 'delete', {
      data: { ids }
    })
  },
  admin_importUsers(users) {
    return ajax('/api/admin/user/insert-batch-user', 'post', {
      data: {
        users
      }
    })
  },
  admin_generateUser(data) {
    return ajax('/api/admin/user/generate-user', 'post', {
      data
    })
  },
  // 获取公告列表
  admin_getAnnouncementList(currentPage, limit) {
    return ajax('/api/admin/announcement', 'get', {
      params: {
        currentPage,
        limit
      }
    })
  },
  // 删除公告
  admin_deleteAnnouncement(aid) {
    return ajax('/api/admin/announcement', 'delete', {
      params: {
        aid
      }
    })
  },
  // 修改公告
  admin_updateAnnouncement(data) {
    return ajax('/api/admin/announcement', 'put', {
      data
    })
  },
  // 添加公告
  admin_createAnnouncement(data) {
    return ajax('/api/admin/announcement', 'post', {
      data
    })
  },


  // 系统配置
  admin_getSMTPConfig() {
    return ajax('/api/admin/config/get-email-config', 'get')
  },
  admin_editSMTPConfig(data) {
    return ajax('/api/admin/config/set-email-config', 'put', {
      data
    })
  },

  admin_deleteHomeCarousel(id) {
    return ajax('/api/admin/config/home-carousel', 'delete', {
      params: {
        id
      }
    })
  },

  admin_testSMTPConfig(email) {
    return ajax('/api/admin/config/test-email', 'post', {
      data: {
        email
      }
    })
  },
  admin_getWebsiteConfig() {
    return ajax('/api/admin/config/get-web-config', 'get')
  },
  admin_editWebsiteConfig(data) {
    return ajax('/api/admin/config/set-web-config', 'put', {
      data
    })
  },
  admin_getDataBaseConfig() {
    return ajax('/api/admin/config/get-db-and-redis-config', 'get')
  },
  admin_editDataBaseConfig(data) {
    return ajax('/api/admin/config/set-db-and-redis-config', 'put', {
      data
    })
  },

  // 系统开关
  admin_getSwitchConfig() {
    return ajax('/api/admin/switch/info', 'get')
  },

  admin_saveSwitchConfig(data) {
    return ajax('/api/admin/switch/update', 'put', {
      data
    })
  },

  getLanguages(pid, all) {
    return ajax('/api/languages', 'get', {
      params: {
        pid,
        all
      }
    })
  },
  getProblemLanguages(pid) {
    return ajax('/api/get-problem-languages', 'get', {
      params: {
        pid: pid
      }
    })
  },

  admin_getProblemList(params) {
    params = utils.filterEmptyValue(params)
    return ajax('/api/admin/problem/get-problem-list', 'get', {
      params
    })
  },

  admin_addRemoteOJProblem(name, problemId) {
    return ajax("/api/admin/problem/import-remote-oj-problem", "get", {
      params: {
        name,
        problemId
      }
    })
  },

  admin_addContestRemoteOJProblem(name, problemId, cid, displayId) {
    return ajax("/api/admin/contest/import-remote-oj-problem", "get", {
      params: {
        name,
        problemId,
        cid,
        displayId
      }
    })
  },

  admin_createProblem(data) {
    return ajax('/api/admin/problem', 'post', {
      data
    })
  },
  admin_editProblem(data) {
    return ajax('/api/admin/problem', 'put', {
      data
    })
  },
  admin_deleteProblem(pid) {
    return ajax('/api/admin/problem', 'delete', {
      params: {
        pid
      }
    })
  },
  admin_changeProblemAuth(data) {
    return ajax('/api/admin/problem/change-problem-auth', 'put', {
      data
    })
  },
  admin_getProblem(pid) {
    return ajax('/api/admin/problem', 'get', {
      params: {
        pid
      }
    })
  },

  admin_getProblemCases(pid, isUpload) {
    return ajax('/api/admin/problem/get-problem-cases', 'get', {
      params: {
        pid,
        isUpload
      }
    })
  },
  compileSPJ(data) {
    return ajax('/api/admin/problem/compile-spj', 'post', {
      data
    })
  },
  compileInteractive(data) {
    return ajax('/api/admin/problem/compile-interactive', 'post', {
      data
    })
  },


  admin_getContestProblemInfo(pid, cid) {
    return ajax('/api/admin/contest/contest-problem', 'get', {
      params: {
        cid,
        pid
      }
    })
  },
  admin_setContestProblemInfo(data) {
    return ajax('/api/admin/contest/contest-problem', 'put', {
      data
    })
  },

  admin_getContestProblemList(params) {
    params = utils.filterEmptyValue(params)
    return ajax('/api/admin/contest/get-problem-list', 'get', {
      params
    })
  },

  admin_getContestProblem(pid) {
    return ajax('/api/admin/contest/problem', 'get', {
      params: {
        pid,
      }
    })
  },
  admin_createContestProblem(data) {
    return ajax('/api/admin/contest/problem', 'post', {
      data
    })
  },
  admin_editContestProblem(data) {
    return ajax('/api/admin/contest/problem', 'put', {
      data
    })
  },
  admin_deleteContestProblem(pid, cid) {
    return ajax('/api/admin/contest/problem', 'delete', {
      params: {
        pid,
        cid
      }
    })
  },
  admin_addContestProblemFromPublic(data) {
    return ajax('/api/admin/contest/add-problem-from-public', 'post', {
      data
    })
  },

  exportProblems(data) {
    return ajax('export_problem', 'post', {
      data
    })
  },

  admin_createContest(data) {
    return ajax('/api/admin/contest', 'post', {
      data
    })
  },
  admin_getContest(cid) {
    return ajax('/api/admin/contest', 'get', {
      params: {
        cid
      }
    })
  },
  admin_editContest(data) {
    return ajax('/api/admin/contest', 'put', {
      data
    })
  },
  admin_deleteContest(cid) {
    return ajax('/api/admin/contest', 'delete', {
      params: {
        cid
      }
    })
  },
  admin_changeContestVisible(cid, visible, uid) {
    return ajax('/api/admin/contest/change-contest-visible', 'put', {
      params: {
        cid,
        visible,
        uid
      }
    })
  },
  admin_getContestList(currentPage, limit, keyword) {
    let params = { currentPage, limit }
    if (keyword) {
      params.keyword = keyword
    }
    return ajax('/api/admin/contest/get-contest-list', 'get', {
      params: params
    })
  },
  admin_getContestAnnouncementList(cid, currentPage, limit) {
    return ajax('/api/admin/contest/announcement', 'get', {
      params: {
        cid,
        currentPage,
        limit
      }
    })
  },
  admin_createContestAnnouncement(data) {
    return ajax('/api/admin/contest/announcement', 'post', {
      data
    })
  },
  admin_deleteContestAnnouncement(aid) {
    return ajax('/api/admin/contest/announcement', 'delete', {
      params: {
        aid
      }
    })
  },
  admin_updateContestAnnouncement(data) {
    return ajax('/api/admin/contest/announcement', 'put', {
      data
    })
  }
}

// 集中导出oj前台的api和admin管理端的api
let api = Object.assign(ojApi, adminApi)
export default api
/**
 * @param url
 * @param method get|post|put|delete...
 * @param params like queryString. if a url is index?a=1&b=2, params = {a: '1', b: '2'}
 * @param data post data, use for method put|post
 * @returns {axios}
 */
function ajax(url, method, options) {
  if (options !== undefined) {
    var { params = {}, data = {} } = options
  } else {
    params = data = {}
  }
  return new Promise((resolve, reject) => {
    axios({
      url,
      method,
      params,
      data
    }).then((res) => {
      resolve(res)
    }).catch(error => {
      reject(error)
    })
  })
}

