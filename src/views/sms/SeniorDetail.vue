<template>
  <div class="app-container" shadow="never">
    <el-card class="operate-container" shadow="never" style="margin-top: 0px">
      <div class="title-wrapper" style="height: 20px">
        <div class="titleInfo">
          <i class="el-icon-tickets"></i>
          <span style="margin-left: 10px">老人信息详情</span>
        </div>
        <el-button
          class="btn-info"
          @click="isEdit ? saveChange() : editSenior()"
          type="primary"
        >
          {{ isEdit ? "保 存" : "编 辑" }}
        </el-button>

        <el-button
          class="btn-info"
          @click="resetInfoForm()"
          v-show="isEdit"
          style="margin-right: 10px"
        >
          取 消
        </el-button>
        <el-form
          class="senior-info-wrapper"
          :model="seniorInfoForm"
          :rules="seniorRules"
          ref="seniorInfoForm"
          label-position="right"
          label-width="100px"
          style="margin-top: 40px"
          :hide-required-asterisk="!isEdit"
        >
          <el-row :gutter="0">
            <el-col :span="19">
              <el-row :gutter="16">
                <el-col :span="7">
                  <el-form-item label="老人姓名" prop="name">
                    <el-input
                      v-model="seniorInfoForm.name"
                      placeholder="请输入老人姓名"
                      size="medium"
                      :disabled="!isEdit"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="10">
                  <el-form-item label="联系方式" prop="tel">
                    <el-input
                      v-model="seniorInfoForm.tel"
                      placeholder="请输入联系方式"
                      size="medium"
                      :disabled="!isEdit"
                    ></el-input> </el-form-item
                ></el-col>
                <el-col :span="7">
                  <el-form-item label="编号" prop="id" label-width="80px">
                    <el-input
                      v-model="seniorInfoForm.id"
                      placeholder="老人编号"
                      size="medium"
                      disabled
                    ></el-input> </el-form-item
                ></el-col>
              </el-row>
              <el-row :gutter="16">
                <el-col :span="12">
                  <el-form-item label="身份证号" prop="idnum">
                    <el-input
                      v-model="seniorInfoForm.idnum"
                      placeholder="请输入身份证号"
                      size="medium"
                      @change="handleIDNumChange"
                      :disabled="!isEdit"
                    ></el-input></el-form-item
                ></el-col>
                <el-col :span="6">
                  <el-form-item label="性别" label-width="70px">
                    <el-input
                      v-model="seniorInfoForm.gender"
                      placeholder=""
                      disabled
                      size="medium"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="年龄" label-width="70px">
                    <el-input
                      v-model="seniorInfoForm.age"
                      placeholder=""
                      disabled
                      size="medium"
                    ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="16">
                <el-col :span="6">
                  <el-form-item label="紧急联系人1" prop="cont1">
                    <el-input
                      v-model="seniorInfoForm.cont1"
                      placeholder="请输入姓名"
                      size="medium"
                      :disabled="!isEdit"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item prop="ctel1" label-width="0">
                    <el-input
                      v-model="seniorInfoForm.ctel1"
                      placeholder="请输入联系方式"
                      size="medium"
                      :disabled="!isEdit"
                    ></el-input> </el-form-item
                ></el-col>

                <el-col :span="6">
                  <el-form-item label="紧急联系人2">
                    <el-input
                      v-model="seniorInfoForm.cont2"
                      placeholder="请输入姓名"
                      size="medium"
                      :disabled="!isEdit"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label-width="0">
                    <el-input
                      v-model="seniorInfoForm.ctel2"
                      placeholder="请输入联系方式"
                      size="medium"
                      :disabled="!isEdit"
                    ></el-input> </el-form-item
                ></el-col>
              </el-row>
            </el-col>

            <el-col :span="5" class="image-wrapper">
              <el-form-item label-width="0">
                <el-upload
                  action="#"
                  :on-error="uploadError"
                  :on-preview="uploadPreview"
                  multiple
                  :limit="1"
                  :file-list="seniorInfoForm.image"
                  :disabled="!isEdit"
                >
                  <img
                    class="seniorImg"
                    src="./aa.jpg"
                    alt="点击上传图像"
                    :title="
                      isEdit
                        ? '点击更换老人图片。只能上传jpg/png文件，且不超过500kb'
                        : ''
                    "
                  />
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-card>

    <el-tabs type="card" style="margin: 20px 0px">
      <el-tab-pane>
        <span slot="label" style="font-size: 15px"
          ><i class="el-icon-data-line"></i> 健康日志</span
        >
        <el-card
          class="operate-container"
          shadow="never"
          style="margin-top: 5px; padding: 10px"
        >
          <el-row :gutter="50" style="margin-top: -30px">
            <el-col :span="12">
              <div class="realtime-wrapper">
                <line-charts
                  style="width: 600px; height: 380px"
                  :heartData="heartData"
                  :breathData="breathData"
                ></line-charts>
              </div>
            </el-col>

            <el-col :span="12">
              <el-descriptions
                style="margin: 60px 20px 0px 0px"
                :column="2"
                border
                :labelStyle="labelStyle"
                :contentStyle="{
                  height: '58px',
                  'font-size': '17px',
                  'text-align': 'center',
                }"
              >
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-time"></i>
                    平均心率
                  </template>
                  {{ seniorInfo.heart }}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-time"></i>
                    平均呼吸
                  </template>
                  {{ seniorInfo.breath }}
                </el-descriptions-item>
                <!-- 
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-time"></i>
                    状态
                  </template>
                  睡眠
                </el-descriptions-item> -->
              </el-descriptions>

              <el-descriptions
                style="margin: -1px 20px 20px 0"
                :column="1"
                border
                :labelStyle="labelStyle"
                :contentStyle="{
                  height: '58px',
                  'font-size': '15px',
                  'text-align': 'center',
                }"
              >
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-table-lamp"></i>
                    所住位置
                  </template>
                  {{ seniorInfo.location }}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-table-lamp"></i>
                    入住时间
                  </template>
                  {{
                    new Date(seniorInfo.registertime.time).toLocaleDateString()
                  }}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-user"></i>
                    负责护工
                  </template>
                  {{ seniorInfo.workername }}
                </el-descriptions-item>
                <el-descriptions-item>
                  <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    护工电话
                  </template>
                  {{ seniorInfo.workertel }}
                </el-descriptions-item>
              </el-descriptions>
              <!-- <el-button class="btn-report" type="primary">生成报告</el-button> -->
            </el-col>
          </el-row>
        </el-card>
      </el-tab-pane>

      <el-tab-pane>
        <span slot="label" style="font-size: 15px"
          ><i class="el-icon-date"></i> 异常记录</span
        >
        <div class="table-container" style="margin-top: 5px">
          <el-table
            ref="eventTable"
            :data="eventList"
            style="width: 100%"
            border
          >
            <el-table-column label="编号" width="120" align="center">
              <template slot-scope="scope">{{ scope.row.id }}</template>
            </el-table-column>

            <!-- <el-table-column label="老人姓名" width="140" align="center">
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column> -->

            <!-- <el-table-column label="所在位置" align="center">
              <template slot-scope="scope">
                {{ scope.row.location }}
              </template>
            </el-table-column> -->

            <el-table-column label="异常时间" align="center">
              <template slot-scope="scope">{{ scope.row.startTime }}</template>
            </el-table-column>

            <el-table-column label="异常类型" align="center">
              <template slot-scope="scope">{{
                scope.row.type == 2 ? "离床未归" : "数据异常"
              }}</template>
            </el-table-column>

            <el-table-column label="确认时长" align="center">
              <template slot-scope="scope">{{
                scope.row.enterDuration
              }}</template>
            </el-table-column>

            <el-table-column label="总时长" align="center">
              <template slot-scope="scope">{{ scope.row.duration }}</template>
            </el-table-column>

            <el-table-column label="处理结果" align="center">
              <template slot-scope="scope">
                <p>{{ scope.row.resultText }}</p>
              </template>
            </el-table-column>

            <el-table-column label="处理人" align="center">
              <template slot-scope="scope">{{ scope.row.handler }}</template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import {
  getSeniorDetail,
  getSeniorEvent,
  updateSenior,
  fecthSeniorLog,
} from "@/api/senior";
import lineCharts from "@/views/lineCharts";

export default {
  name: "SeniorDetail",
  components: { lineCharts },
  props: {
    edit: {
      type: Boolean,
      default: false,
    },
  },
  created() {
    const id = this.$route.query.id;
    this.getDetail(id);

    fecthSeniorLog(id).then((res) => {
      console.log("fecthSeniorLog:", res);
      this.logData = res.data;
    });

    getSeniorEvent(id).then((res) => {
      console.log("getSeniorEvent:", res);
      this.eventList = res.data;
    });
  },

  data() {
    return {
      logData: null,
      heartData: [
        51, 47, 53, 50, 51, 51, 47, 53, 50, 53, 50, 53, 50, 51, 47, 51, 47, 53,
        50, 53, 50, 53, 50, 47, 53, 50, 51, 47, 53, 50, 51, 47, 53, 50, 53, 50,
      ],
      breathData: [
        18, 20, 19, 18, 20, 19, 22, 18, 20, 19, 22, 18, 20, 19, 22, 22, 18, 20,
        19, 22, 18, 20, 19, 18, 20, 19, 22, 18, 20, 19, 22, 18, 20, 19, 22, 22,
      ],
      isEdit: this.edit,
      breathRate: 0,
      heartRate: 0,

      seniorInfo: null,
      seniorInfoForm: null,
      seniorRules: {
        name: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        tel: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        idnum: [
          { required: true, message: "该项不能为空", trigger: "blur" },
          {
            max: 18,
            min: 18,
            message: "请输入18位有效身份证号",
            trigger: "blur",
          },
        ],
        cont1: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        ctel1: [{ required: true, message: "该项不能为空", trigger: "blur" }],
        type: [{ required: true, message: "请选择类型", trigger: "change" }],
      },

      labelStyle: {
        width: "150px",
        height: "50px",
        "font-size": "15px",
      },

      eventList: [],
    };
  },

  methods: {
    getDetail(id) {
      getSeniorDetail(id).then((response) => {
        console.log(response);
        this.seniorInfo = response.data;
        this.seniorInfoForm = Object.assign({}, this.seniorInfo);
        this.seniorInfoForm.age = 2022 - this.seniorInfoForm.idnum.slice(6, 10);
      });
    },

    resetInfoForm() {
      this.isEdit = false;
      this.seniorInfoForm = Object.assign({}, this.seniorInfo);
      this.seniorInfoForm.age = 2022 - this.seniorInfoForm.idnum.slice(6, 10);
      this.$refs.seniorInfoForm.clearValidate();
    },

    // 编辑
    editSenior() {
      this.isEdit = !this.isEdit;
      console.log(this.isEdit);
    },

    handleIDNumChange() {
      var idnum = this.seniorInfoForm.idnum;
      var len = idnum.length;
      if (len == 18) {
        if (idnum[len - 2] % 2) {
          this.seniorInfoForm.gender = "男";
        } else {
          this.seniorInfoForm.gender = "女";
        }
        this.seniorInfoForm.age = 2022 - idnum.slice(6, 10);
      } else {
        this.seniorInfoForm.gender = "";
        this.seniorInfoForm.age = "";
      }
    },

    uploadError() {
      this.$message({
        message: "上传失败",
        type: "error",
      });
    },

    uploadPreview(file) {
      console.log(file);
    },

    // 保存更改
    saveChange() {
      console.log("this.seniorInfo", this.seniorInfo);
      console.log("this.seniorInfoForm", this.seniorInfoForm);
      this.$refs.seniorInfoForm.validate((valid) => {
        if (!valid) {
          console.log("error submit!");
          this.$message({
            message: "修改失败",
            type: "error",
          });
        } else {
          const id = this.seniorInfoForm.id;
          var formObj = {
            name: this.seniorInfoForm.name,
            gender: this.seniorInfoForm.gender,
            tel: this.seniorInfoForm.tel,
            heart: this.seniorInfoForm.heart,
            breath: this.seniorInfoForm.breath,
            idnum: this.seniorInfoForm.idnum,
            pic: "",
            cont1: this.seniorInfoForm.cont1,
            ctel1: this.seniorInfoForm.ctel1,
            cont2: this.seniorInfoForm.cont2,
            ctel2: this.seniorInfoForm.ctel2,
            registertime: this.seniorInfoForm.registertime,
            instid: this.seniorInfoForm.instid,
          };
          console.log(formObj);
          updateSenior(id, formObj)
            .then((res) => {
              this.$message({
                message: "信息修改成功",
                type: "success",
              });
              this.getDetail(id);
              this.isEdit = false;
            })
            .catch((err) => {
              this.$message({
                message: "修改失败",
                type: "error",
              });
            });
        }
      });
    },
  },
};
</script>

<style scope>
.titleInfo {
  display: inline-block;
}

.image-wrapper .el-form-item__content {
  padding-top: 4px;
  padding-left: 60px;
}

.seniorImg {
  object-fit: cover;
  width: 120px;
  height: 160px;
  border-radius: 5px;
}

.btn-report {
  float: right;
  margin-right: 20px;
  /* width:150px; */
}

.btn-info {
  float: right;
}

.btn-cancel {
  float: right;
}

.el-input.is-disabled .el-input__inner {
  /* background-color: #fcfcfc; */
  border-color: #e4e7ed;
  color: #5c5c5c;
  cursor: auto;
}
</style>


