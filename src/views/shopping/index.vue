<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="商品编号" prop="shoppingNumber">
        <el-input
          v-model="queryParams.shoppingNumber"
          placeholder="请输入编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="shoppingName">
        <el-input
          v-model="queryParams.shoppingName"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户" prop="shoppingCreateBy">
        <el-input
          v-model="queryParams.shoppingCreateBy"
          placeholder="请输入用户"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
        >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['shopping:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shopping:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shopping:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :disabled="single"
          @click="handleExport"
          v-hasPermi="['shopping:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getShoppingList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="shoppingList"
      @selection-change="handleSelectionChange"
      border
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="selection" width="45" align="center" />
      <el-table-column label="序号" width="50" align="center" prop="shoppingId" />
      <el-table-column label="商品编号" align="center" prop="shoppingNumber" />
      <el-table-column label="商品名称" align="center" prop="shoppingName" />
      <el-table-column label="用户" align="center" prop="shoppingCreateBy" />
      <el-table-column label="所需积分" align="center" prop="shoppingIntegral">
        <template v-if="scope.row.shoppingIntegral != null" slot-scope="scope">
          {{scope.row.shoppingIntegral+'分'}}
        </template>
        <template v-else slot-scope="scope">
          {{scope.row.shoppingIntegral}}
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="shoppingCreateTime"
        width="100"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.shoppingCreateTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="库存" align="center" prop="shoppingStock" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="120"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:shopping:edit']"
          >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-cloudy"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:shopping:edit']"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getShoppingList()"
    />

    <!-- 添加或修改设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="编号" prop="shoppingNumber">
          <el-input
            v-model="form.shoppingNumber"
            placeholder="请输入编号，必须唯一"
          />
        </el-form-item>
        <el-form-item label="商品名称" prop="shoppingName">
          <el-input
            v-model="form.shoppingName"
            placeholder="请输入商品名称,例如旺旺碎冰冰"
          />
        </el-form-item>
        <el-form-item label="所需积分" prop="shoppingIntegral">
          <el-input v-model="form.shoppingIntegral" placeholder="请输入该商品所需要多少的积分" />
        </el-form-item>
        <el-form-item label="当前库存" prop="shoppingStock">
          <el-input v-model="form.shoppingStock" placeholder="请输入该商品目前仓库中的存量" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>

<script>
import {
  exportDevice
} from "@/api/system/device";
import { getNewStatus, updateStatus } from "@/api/system/status";
import { getNewSet, updateSet } from "@/api/system/set";
import { listCategory } from "@/api/system/category";
import {shoppingList, getShopping, updateShopping, addShopping, deleteShopping} from "../../api/system/shopping";

export default {
  name: "Shopping",
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 设备表格数据
      deviceList: [],
      //商品表格数据
      shoppingList: [],
      // 弹出层标题
      title: "",
      statusTitle: "",
      setTitle: "",
      // 是否显示弹出层
      open: false,
      statusOpen: false,
      setOpen: false,
      // 创建时间时间范围
      daterangeCreateTime: [],
      // 分类
      categoryList: [],
      // 继电器字典
      openCloseOptions: [
        {
          label: "打开",
          value: 1
        },
        {
          label: "关闭",
          value: 0
        }
      ],
      // 灯状态字典
      yesNoOptions: [
        {
          label: "是",
          value: 1
        },
        {
          label: "否",
          value: 0
        }
      ],
      // 在线字典
      isOnlineOptions: [
        {
          label: "在线",
          value: 1
        },
        {
          label: "离线",
          value: 0
        }
      ],
      // 触发源字典
      triggerSourceOptions: [],
      // 彩灯模式字典
      lightModeOptions: [],

      // 按键字典
      rfFuncOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupId:0,
        shoppingNumber: null,
        shoppingId: null,
        shoppingName: null,
        shoppingCreateBy: null,
        shoppingStock: null,
        shoppingRemark: null,
        shoppingIntegral: null,
        shoppingCreateTime: null,
        remark: null
      },
      // 表单参数
      form: {},
      statusForm: {},
      setForm: {},
      // 表单校验
      rules: {
        shoppingNumber: [
          { required: true, message: "编号不能为空", trigger: "blur" }
        ],
        shoppingName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        shoppingStock: [
          { required:true, message:"库存不能为空",trigger: "blur"}
        ],
        shoppingIntegral: [
          {required:true,message:"所需积分不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getCategoryList();
    this.getShoppingList();
    this.getDicts("iot_trigger_source").then(response => {
      this.triggerSourceOptions = response.data;
    });
    this.getDicts("light_mode").then(response => {
      this.lightModeOptions = response.data;
    });
    this.getDicts("rf_function").then(response => {
      this.rfFuncOptions = response.data;
    });
  },
  methods: {
    /**行颜色 */
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "success-row";
      } else if (rowIndex === 3) {
        return "warning-row";
      }
      return "";
    },
    /** 查询商品列表 */
    getShoppingList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && "" !== this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      shoppingList(this.queryParams).then(response =>{
        this.shoppingList = response.rows;
        console.log(this.shoppingList);
        this.total = response.total;
        this.loading = false;
      })
    },

    // 查询设备分类
    getCategoryList() {
      listCategory(this.queryCategoryParams).then(response => {
        this.categoryList = response.rows;
      });
    },
    // 分类翻译
    categoryFormat(row, column) {
      var name = "";
      for (var i = 0; i < this.categoryList.length; i++) {
        if (this.categoryList[i].categoryId == row.categoryId) {
          name = this.categoryList[i].categoryName;
          break;
        }
      }
      return name;
    },
    // 触发源字典翻译
    triggerSourceFormat(row, column) {
      return this.selectDictLabel(this.triggerSourceOptions, row.triggerSource);
    },
    // 彩灯模式字典翻译
    lightModeFormat(row, column) {
      return this.selectDictLabel(this.lightModeOptions, row.lightMode);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    statusCancel() {
      this.statusOpen = false;
      this.statusReset();
    },
    setCancel() {
      this.setOpen = false;
      this.setReset();
    },
    // 表单重置
    reset() {
      this.form = {
        shoppingNumber: null,
        shoppingId: null,
        shoppingName: null,
        shoppingCreateBy: null,
        shoppingStock: null,
        shoppingRemark: null,
        shoppingIntegral: null,
        shoppingCreateTime: null
      };
    },
    statusReset() {
      this.statusForm = {
        shoppingNumber: null,
        shoppingId: null,
        shoppingName: null,
        shoppingCreateBy: null,
        shoppingStock: null,
        shoppingRemark: null,
        shoppingIntegral: null,
        shoppingCreateTime: null
      };
      this.resetForm("statusForm");
    },
    setReset() {
      this.setForm = {
        shoppingNumber: null,
        shoppingId: null,
        shoppingName: null,
        shoppingCreateBy: null,
        shoppingStock: null,
        shoppingRemark: null,
        shoppingIntegral: null,
        shoppingCreateTime: null
      };
      this.resetForm("setForm");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getShoppingList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.deviceId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const shoppingId = row.shoppingId;
      getShopping(shoppingId).then(response => {
        console.log("121212--"+response.data);
        this.form = response.data;
        this.open = true;
        this.title = "修改商品信息";
      });
    },
    /** 状态按钮操作 */
    handleStatus(row) {
      this.reset();
      const deviceId = row.deviceId || this.ids;
      getNewStatus(deviceId).then(response => {
        this.statusForm = response.data;
        this.statusOpen = true;
        this.statusTitle = "设备状态";
      });
    },
    /** 配置按钮操作 */
    handleSet(row) {
      this.reset();
      const deviceId = row.deviceId || this.ids;
      getNewSet(deviceId).then(response => {
        this.setForm = response.data;
        this.setOpen = true;
        this.setTitle = "设备配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.shoppingId != null) {
            updateShopping(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getShoppingList();
            });
          } else {
            addShopping(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getShoppingList();
            });
          }
        }
      });
    },
    /** 状态提交按钮 */
    statusSubmitForm(isApply) {
      this.$refs["statusForm"].validate(valid => {
        if (valid) {
          if (this.statusForm.deviceStatusId != null) {
            updateStatus(this.statusForm).then(response => {
              this.msgSuccess("更新成功");
              if(!isApply){
                this.statusOpen = false;
                this.getShoppingList();
              }
            });
          }
        }
      });
    },
    /** 设置提交按钮 */
    setSubmitForm(isApply) {
      this.$refs["setForm"].validate(valid => {
        if (valid) {
          if (this.setForm.deviceSetId != null) {
            updateSet(this.setForm).then(response => {
              this.msgSuccess("更新成功");
              if(!isApply){
                this.setOpen = false;
                this.getShoppingList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const shoppingId = row.shoppingId;
      const shoppingName = row.shoppingName;
      this.$confirm(
        '是否确认删除商品名称为"' + shoppingName + '"的所有数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return deleteShopping(shoppingId);
        })
        .then(() => {
          this.getShoppingList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有设备数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportDevice(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        });
    }
  }
};
</script>
