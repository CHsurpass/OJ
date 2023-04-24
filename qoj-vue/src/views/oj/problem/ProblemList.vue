<template>
  <el-row>
      <el-card shadow>
        <div slot="header">
          <el-row>
            <Pagination
              :total="total"
              :page-size="limit"
              @on-change="pushRouter"
              :current.sync="query.currentPage"
              @on-page-size-change="onPageSizeChange"
              :layout="'total, jumper, prev, pager, next, sizes'"
            ></Pagination>
          </el-row>
          <el-row :gutter="20" style="margin-bottom: 0.5em;">
            <el-col :xs="24" :sm="6">
              <span class="problem-list-title">{{ $t('m.Problem_List') }}</span>
            </el-col>
            <el-col :xs="24" :sm="6">
              <vxe-input
                v-model="query.keyword"
                :placeholder="$t('m.Enter_keyword')"
                type="search"
                size="medium"
                @search-click="filterByKeyword"
                @keyup.enter.native="filterByKeyword"
                class="filter-mt"
              ></vxe-input>
            </el-col>
          </el-row>

          <section>
            <b class="problem-filter">{{ $t('m.Problem_Bank') }}</b>
            <div>
              <el-tag
                size="medium"
                class="filter-item"
                :effect="query.oj === 'All' ? 'dark' : 'plain'"
                @click="filterByOJ('All')"
                >{{ $t('m.All') }}</el-tag
              >
              <el-tag
                size="medium"
                class="filter-item"
                :effect="
                  query.oj === 'Mine' || query.oj === '' ? 'dark' : 'plain'
                "
                @click="filterByOJ('Mine')"
                >{{ $t('m.My_OJ') }}</el-tag
              >
              <el-tag
                size="medium"
                class="filter-item"
                v-for="(remoteOj, index) in REMOTE_OJ"
                :effect="query.oj == remoteOj.key ? 'dark' : 'plain'"
                :key="index"
                @click="filterByOJ(remoteOj.key)"
                >{{ remoteOj.name }}</el-tag
              >
            </div>
          </section>
        </div>
        <vxe-table
          border="inner"
          stripe
          ref="problemList"
          auto-resize
          :loading="loadings.table"
          @cell-mouseenter="cellHover"
          :data="problemList"
        >
          <vxe-table-column title="" width="30" v-if="isAuthenticated">
            <template v-slot="{ row }">
              <template v-if="isGetStatusOk">
                <el-tooltip
                  :content="JUDGE_STATUS[row.myStatus]['name']"
                  placement="top"
                >
                  <template v-if="row.myStatus == 0">
                    <i
                      class="el-icon-check"
                      :style="getIconColor(row.myStatus)"
                    ></i>
                  </template>

                  <template v-else-if="row.myStatus != -10">
                    <i
                      class="el-icon-minus"
                      :style="getIconColor(row.myStatus)"
                    ></i>
                  </template>
                </el-tooltip>
              </template>
            </template>
          </vxe-table-column>
          <vxe-table-column
            field="problemId"
            :title="$t('m.Problem_ID')"
            width="150"
            show-overflow
          ></vxe-table-column>

          <vxe-table-column
            field="title"
            :title="$t('m.Problem')"
            min-width="150"
            show-overflow
          >
            <template v-slot="{ row }">
              <a @click="getProblemUri(row.problemId)" class="title-a">{{
                row.title
              }}</a>
            </template>
          </vxe-table-column>
          <vxe-table-column
            field="total"
            :title="$t('m.Total')"
            min-width="80"
          ></vxe-table-column>
          <vxe-table-column
            field="ac"
            :title="$t('m.AC_Rate')"
            min-width="120"
            align="center"
          >
            <template v-slot="{ row }">
              <span>
                <el-tooltip
                  effect="dark"
                  :content="row.ac + '/' + row.total"
                  placement="top"
                  style="margin-top:0"
                >
                  <el-progress
                    :text-inside="true"
                    :stroke-width="20"
                    :color="customColors"
                    :percentage="getPassingRate(row.ac, row.total)"
                  ></el-progress>
                </el-tooltip>
              </span>
            </template>
          </vxe-table-column>
        </vxe-table>
      </el-card>
      <Pagination
        :total="total"
        :page-size="limit"
        @on-change="pushRouter"
        :current.sync="query.currentPage"
        @on-page-size-change="onPageSizeChange"
        :layout="'total, jumper, prev, pager, next, sizes'"
      ></Pagination>
  </el-row>
</template>

<script>
import { mapGetters } from 'vuex';
import api from '@/common/api';
import {
  PROBLEM_LEVEL,
  JUDGE_STATUS,
  JUDGE_STATUS_RESERVE,
  REMOTE_OJ,
} from '@/common/constants';
import utils from '@/common/utils';
import myMessage from '@/common/message';
import 'element-ui/lib/theme-chalk/display.css';
import Pagination from '@/components/oj/common/Pagination';
export default {
  name: 'ProblemList',
  components: {
    Pagination,
  },
  data() {
    return {
      PROBLEM_LEVEL: {},
      JUDGE_STATUS: {},
      JUDGE_STATUS_RESERVE: {},
      REMOTE_OJ: {},
      currentProblemTitle: '',
      problemRecord: [],
      problemList: [],
      limit: 30,
      total: 100,
      isGetStatusOk: false,
      loadings: {
        table: true,
      },
      filterConfig: { remote: true },
      routeName: '',
      query: {
        keyword: '',
        difficulty: 'All',
        oj: '',
        currentPage: 1,
      },
      customColors: [
        { color: '#909399', percentage: 20 },
        { color: '#f56c6c', percentage: 40 },
        { color: '#e6a23c', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#67c23a', percentage: 100 },
      ]
    };
  },
  created() {
    this.init();
  },
  mounted() {
    this.PROBLEM_LEVEL = Object.assign({}, PROBLEM_LEVEL);
    this.JUDGE_STATUS_RESERVE = Object.assign({}, JUDGE_STATUS_RESERVE);
    this.JUDGE_STATUS = Object.assign({}, JUDGE_STATUS);
    this.REMOTE_OJ = Object.assign({}, REMOTE_OJ);
    // 初始化
    this.problemRecord = [
      { status: 0, count: 100 },
      { status: -1, count: 100 },
      { status: 1, count: 100 },
      { status: 2, count: 100 },
      { status: 3, count: 100 },
      { status: -3, count: 100 },
      { status: -2, count: 100 },
      { status: 4, count: 100 },
    ];
    this.loadings.table = true;
    setTimeout(() => {
      // 将指定列设置为隐藏状态
      this.$refs.problemList.refreshColumn();
      this.loadings.table = false;
    }, 200);
    this.getData();
  },
  methods: {
    init() {
      this.routeName = this.$route.name;
      let query = this.$route.query;
      this.query.difficulty = query.difficulty || '';
      this.query.oj = query.oj || 'Mine';
      this.query.keyword = query.keyword || '';
      this.query.currentPage = parseInt(query.currentPage) || 1;
      if (this.query.currentPage < 1) {
        this.query.currentPage = 1;
      }
    },

    getData() {
      this.getProblemList();
    },

    pushRouter() {
      this.$router.push({
        path: '/problem',
        query: this.query,
      });
    },
    onPageSizeChange(pageSize) {
      this.limit = pageSize;
      this.getProblemList();
    },
    getPercentage(partNumber, total) {
      return partNumber == 0
        ? 0
        : Math.round((partNumber / total) * 10000) / 100.0;
    },
    getPassingRate(ac, total) {
      if (!total) {
        return 0;
      }
      return ((ac / total) * 100).toFixed(2);
    },
    getProblemList() {
      let queryParams = Object.assign({}, this.query);
      if (queryParams.difficulty == 'All') {
        queryParams.difficulty = '';
      }
      if (queryParams.oj == 'All') {
        queryParams.oj = '';
      } else if (!queryParams.oj) {
        queryParams.oj = 'Mine';
      }
      this.loadings.table = true;
      api.getProblemList(this.limit, queryParams).then(
        (res) => {
          this.total = res.data.data.total;
          this.problemList = res.data.data.records;
          if (this.isAuthenticated) {
            // 如果已登录，则需要查询对当前页面题目列表中各个题目的提交情况
            let pidList = [];
            for (let index = 0; index < this.problemList.length; index++) {
              pidList.push(this.problemList[index].pid);
            }
            if (pidList.length > 0) {
              // 必须当前页有显示题目才发送查询请求
              this.isGetStatusOk = false;
              let isContestProblemList = false; // 为了与比赛题目区分
              api
                .getUserProblemStatus(pidList, isContestProblemList)
                .then((res) => {
                  let result = res.data.data;
                  for (
                    let index = 0;
                    index < this.problemList.length;
                    index++
                  ) {
                    this.problemList[index]['myStatus'] = result[this.problemList[index].pid].status;
                  }
                  this.isGetStatusOk = true;
                });
            }
          }
          this.loadings.table = false;
        },
        (res) => {
          this.loadings.table = false;
        }
      );
    },
    onReset() {
      if (JSON.stringify(this.$route.query) != '{}') {
        this.$router.push({ name: 'ProblemList' });
      }
    },
    filterByDifficulty(difficulty) {
      this.query.difficulty = difficulty;
      this.query.currentPage = 1;
      this.pushRouter();
    },
    filterByOJ(oj) {
      this.query.oj = oj;
      if (oj != 'All') {
      }
      this.query.currentPage = 1;
      this.pushRouter();
    },
    filterByKeyword() {
      this.query.currentPage = 1;
      this.pushRouter();
    },
    getProblemUri(problemId) {
      this.$router.push({
        name: 'ProblemDetails',
        params: {
          problemID: problemId,
        },
      });
    },
    getLevelColor(difficulty) {
      return utils.getLevelColor(difficulty);
    },
    getLevelName(difficulty) {
      return utils.getLevelName(difficulty);
    },
    getIconColor(status) {
      return (
        'font-weight: 600;font-size: 16px;color:' +
        this.JUDGE_STATUS[status].rgb
      );
    },
    getLevelBlockColor(difficulty) {
      if (difficulty == this.query.difficulty) {
        return this.getLevelColor(difficulty);
      }
    }
  },
  computed: {
    ...mapGetters(['isAuthenticated']),
    OJName() {
      if (this.query.oj == 'Mine' || !this.$route.query.oj) {
        return this.$i18n.t('m.My_OJ');
      } else if (this.query.oj == 'All') {
        return this.$i18n.t('m.All');
      } else {
        return this.query.oj;
      }
    }
  },
  watch: {
    $route(newVal, oldVal) {
      if (newVal !== oldVal) {
        this.init();
        this.getData();
      }
    },
    isAuthenticated(newVal) {
      if (newVal === true) {
        this.init();
        this.getData();
      }
    },
  },
};
</script>

<style scoped>
.problem-list-title {
  font-size: 2em;
  font-weight: 500;
  line-height: 30px;
}

.taglist-title {
  font-size: 21px;
  font-weight: 500;
}

section {
  display: flex;
  align-items: baseline;
  margin-bottom: 0.8em;
}
.problem-filter {
  margin-right: 1em;
  font-weight: bolder;
  white-space: nowrap;
  font-size: 16px;
  margin-top: 8px;
}
.filter-item {
  margin-right: 1em;
  margin-top: 0.5em;
  font-size: 13px;
}
.filter-item:hover {
  cursor: pointer;
}

@media only screen and (max-width: 767px) {
  .filter-mt {
    margin-top: 8px;
  }
}

/deep/.el-tag--dark {
  border-color: #d9ecff;
}
/deep/.tag-btn {
  margin-left: 4px !important;
  margin-top: 4px;
}
/deep/.vxe-checkbox .vxe-checkbox--label {
  overflow: unset !important;
}
/deep/ .vxe-input {
  width: 100%;
}
#pick-one {
  margin-top: 10px;
}
/deep/ .el-card__header {
  border-bottom: 0px;
  padding-bottom: 0px;
}
@media screen and (min-width: 1200px) {
  /deep/ .el-card__body {
    padding-top: 0px;
    margin-top: 5px;
  }
}
ul {
  float: right;
}
.title-a {
  color: #495060;
  font-family: inherit;
  font-size: 14px;
  font-weight: 500;
}
.el-progress {
  margin-top: 15px;
}

@media screen and (min-width: 1050px) {
  /deep/ .vxe-table--body-wrapper {
    overflow-x: hidden !important;
  }
}

/deep/.el-collapse-item__header{
  font-weight: bolder !important;
  height: 38px !important;
  line-height: 38px !important;
  font-size: 15px !important;
}
/deep/.el-collapse-item__content {
  padding-bottom: 10px !important;
}
</style>
