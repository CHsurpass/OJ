<template>
  <div>
    <el-row :gutter="20">
      <el-col :xs="24" :md="10" :lg="8">
        <el-card class="admin-info">
          <div slot="header">
            <el-row :gutter="10">
              <el-col :span="8">
              <avatar
                  :username="userInfo.username"
                  :inline="true"
                  :size="100"
                  color="#FFF"
                  :src="userInfo.avatar"
                ></avatar>
              </el-col>
              <el-col :span="16">
                <span class="panel-title admin-info-name">{{
                  userInfo.username
                }}</span>
                <p>
                  <el-tag effect="dark" size="small" type="warning">
                    {{
                      isSuperAdmin == true
                        ? $t('m.Super_Admin')
                        : isProblemAdmin == true
                        ? $t('m.All_Problem_Admin')
                        : $t('m.Admin')
                    }}
                  </el-tag>
                </p>
              </el-col>
            </el-row>
          </div>
          <div class="last-info">
            <p class="last-info-title home-title">{{ $t('m.Last_Login') }}</p>
            <el-form label-width="80px" class="last-info-body">
              <el-form-item label="Time:">
                <span>{{ session.gmtCreate | localtime }}</span>
              </el-form-item>
              <el-form-item label="IP:">
                <span>{{ session.ip }}</span>
              </el-form-item>
              <el-form-item label="OS:">
                <span>{{ os }}</span>
              </el-form-item>
              <el-form-item label="Browser:">
                <span>{{ browser }}</span>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>

      <!-- <el-col :md="14" :lg="16" v-if="isSuperAdmin"> -->
      <el-col :xs="24" :md="14" :lg="16">
        <div class="info-container">
          <info-card
            color="#909399"
            icon="fa fa-users"
            :message="$t('m.Total_Users')"
            iconSize="30px"
            class="info-item"
            :value="infoData.userNum"
          ></info-card>
          <info-card
            color="#67C23A"
            icon="fa fa-list"
            :message="$t('m.Today_Submissions')"
            class="info-item"
            :value="infoData.todayJudgeNum"
          ></info-card>
          <info-card
            color="#409EFF"
            icon="fa fa-trophy"
            :message="$t('m.Recent_14_Days_Contests')"
            class="info-item"
            :value="infoData.recentContestNum"
          ></info-card>
        </div>

        <el-card style="margin-top:10px">
          <div slot="header">
            <span class="panel-title home-title">{{ $t('m.Judge_Server') }}</span>
          </div>
          <div style="margin-bottom: 10px;font-size: 15px;">
            {{ $t('m.Server_Number') }}：
            <el-tag effect="dark" color="rgb(25, 190, 107)" size="mini">
              {{ judgeInfo.length }}
            </el-tag>
          </div>
          <vxe-table stripe auto-resize :data="judgeInfo" align="center">
            <vxe-table-column type="seq" width="50"></vxe-table-column>
            <vxe-table-column :title="$t('m.Name')" min-width="150">
              <template v-slot="{ row }">
                <span>{{ row.service.metadata.judgeName }}</span>
              </template>
            </vxe-table-column>
            <vxe-table-column :title="$t('m.Host')" min-width="80">
              <template v-slot="{ row }">
                <span>{{ row.service.host }}</span>
              </template>
            </vxe-table-column>
            <vxe-table-column :title="$t('m.Port')" min-width="80">
              <template v-slot="{ row }">
                <span>{{ row.service.port }}</span>
              </template>
            </vxe-table-column>

            <vxe-table-column
                    min-width="100"
                    field="percentCpuLoad"
                    :title="$t('m.CPU_Usage')"
            >
            </vxe-table-column>

            <vxe-table-column
                    min-width="110"
                    field="percentMemoryLoad"
                    :title="$t('m.Mem_Usage')"
            >
            </vxe-table-column>
          </vxe-table>
        </el-card>

      </el-col>
    </el-row>

  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import browserDetector from 'browser-detect';
const InfoCard = () => import('@/components/admin/infoCard.vue');
import api from '@/common/api';
import Avatar from 'vue-avatar';
export default {
  name: 'dashboard',
  components: {
    InfoCard,
    Avatar
  },
  data() {
    return {
      infoData: {
        userNum: 0,
        recentContestNum: 0,
        todayJudgeNum: 0,
      },
      generalInfo: {
        backupCores: 0,
        backupPercentCpuLoad: '0%',
        backupPercentMemoryLoad: '0%',
        backupService: [],
        nacos: {},
      },
      judgeInfo: [],
      session: {},
    };
  },
  mounted() {
    this.refreshJudgeServerList();
    // 每5秒刷新判题机服务和后台服务的情况
    this.intervalId = setInterval(() => {
      this.refreshJudgeServerList();
    }, 5000);
    api.admin_getDashboardInfo().then(
      (resp) => {
        this.infoData = resp.data.data;
      },
      () => {}
    );

    api.getSessions(this.userInfo.uid).then(
      (resp) => {
        this.session = resp.data.data;
      },
      () => {}
    );
  },
  methods: {
    refreshJudgeServerList() {
      api.getJudgeServer().then(
        (res) => {
          this.judgeInfo = res.data.data;
        },
        (err) => {
          clearInterval(this.intervalId);
        }
      );
    },
  },
  computed: {
    ...mapGetters(['userInfo', 'isSuperAdmin', 'isProblemAdmin']),
    https() {
      return document.URL.slice(0, 5) === 'https';
    },
    browser() {
      let b = browserDetector(this.session.userAgent);
      if (b.name && b.version) {
        return b.name + ' ' + b.version;
      } else {
        return 'Unknown';
      }
    },
    os() {
      let b = browserDetector(this.session.userAgent);
      return b.os ? b.os : 'Unknown';
    },
  },
  beforeRouteLeave(to, from, next) {
    clearInterval(this.intervalId);
    next();
  },
};
</script>

<style scoped>
.admin-info {
  margin-bottom: 20px;
}
.admin-info-name {
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 10px;
  color: #409eff;
}
.admin-info .last-info-title {
  font-size: 16px;
}
.el-form-item {
  margin-bottom: 5px;
}

.info-container {
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
}
.info-container .info-item {
  flex: 1 0 auto;
  min-width: 200px;
  margin-bottom: 10px;
}
/deep/ .el-tag--dark {
  border-color: #fff;
}
/deep/.el-card__header {
  padding-bottom: 0;
}
@media screen and (min-width: 1150px) {
  /deep/ .vxe-table--body-wrapper {
    overflow-x: hidden !important;
  }
}
</style>
