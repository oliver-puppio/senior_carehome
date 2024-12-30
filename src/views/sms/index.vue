<template>
   
  <div class="app-container">
    <el-card class="operate-container" shadow="never" style="margin-top: 0px">
      <div class="title-wrapper" style="height: 20px">
        <div class="titleInfo">
          <i class="el-icon-tickets"></i>
          <span>老人信息</span>
        </div>
        <el-button class="btn-add" @click="handleAddSenior()" type="primary">
          办理入住
        </el-button>
      </div>
      <div class="senior-info-wrapper">
        <el-tabs style="margin-top: 20px">
          <el-tab-pane label="院内老人">
            <senior-in></senior-in>
          </el-tab-pane>
          <el-tab-pane label="社区老人">
            <senior-out></senior-out>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-card>
  </div>
</template>
<script>
import SeniorIn from '@/views/sms/SeniorIn';
import SeniorOut from '@/views/sms/SeniorOut';

const defaultListQuery = {
  keyword: null,
  pageNum: 1,
  pageSize: 5,
  publishStatus: null,
  verifyStatus: null,
  productSn: null,
  productCategoryId: null,
  brandId: null,
};

export default {
  name: "productList",
  components: { SeniorIn, SeniorOut },
  data() {
    return {
      operateType: null,
      listQuery: Object.assign({}, defaultListQuery),
      list: null,
      total: null,
      listLoading: true,
      selectProductCateValue: null,
      multipleSelection: [],
      productCateOptions: [],
      brandOptions: [],
      publishStatusOptions: [
        {
          value: 1,
          label: "上架",
        },
        {
          value: 0,
          label: "下架",
        },
      ],
      verifyStatusOptions: [
        {
          value: 1,
          label: "审核通过",
        },
        {
          value: 0,
          label: "未审核",
        },
      ],

      current: null,
    };
  },
  created() {
    // this.getList();
    // this.getBrandList();
    // this.getProductCateList();
  },
  watch: {
    selectProductCateValue: function (newValue) {
      if (newValue != null && newValue.length == 2) {
        this.listQuery.productCategoryId = newValue[1];
      } else {
        this.listQuery.productCategoryId = null;
      }
    },
  },
  filters: {
    verifyStatusFilter(value) {
      if (value === 1) {
        return "审核通过";
      } else {
        return "未审核";
      }
    },
  },
  methods: {
    // 办理老人入住（新增）
    handleAddSenior() {
      this.$router.push({ path: "/sms/addSenior" });
    },
  },
};
</script>
<style scope>
.operate-container .title-wrapper .titleInfo {
  display: inline-block;
}
</style>


