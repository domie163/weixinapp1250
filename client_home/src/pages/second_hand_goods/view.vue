<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','item_no') || $check_field('add','item_no') || $check_field('set','item_no')" label="物品序号" name="item_no">
                            <uni-easyinput type="text" v-model="form['item_no']" v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_no')) || (!form['second_hand_goods_id'] && $check_field('add','item_no'))"  :disabled="true" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_no')">
                  {{ form['item_no'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_name') || $check_field('add','item_name') || $check_field('set','item_name')" label="物品名称" name="item_name">
                            <uni-easyinput type="text" v-model="form['item_name']" v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_name')) || (!form['second_hand_goods_id'] && $check_field('add','item_name'))" :disabled="disabledObj['item_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_name')">
                  {{ form['item_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" label="封面" name="cover">
                    <!-- 修改权限 -->
                <view class="diy_field diy_img" v-if="form['cover'] && $check_field('set','cover')">
                  <image v-if="disabledObj['cover_isDisabled']" :src="$fullUrl(form['cover'])" />
                  <image v-if="!disabledObj['cover_isDisabled']" :src="$fullUrl(form['cover'])" @click="change_img('cover')" />
                </view>
                <!-- 添加权限 -->
                <view class="diy_field diy_img" v-else-if="!form['cover'] && $check_field('add','cover')">
                  <view v-if="disabledObj['cover_isDisabled']" class="btn_add_img">
                    <text>+</text>
                  </view>
                  <view v-if="!disabledObj['cover_isDisabled']" class="btn_add_img" @click="change_img('cover')">
                    <text>+</text>
                  </view>
                </view>
                <!-- 查询权限 -->
                <view class="diy_field diy_img" v-else-if="$check_field('get','cover')">
                  <image :src="$fullUrl(form['cover'])" />
                </view>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_type') || $check_field('add','item_type') || $check_field('set','item_type')" label="物品类型" name="item_type">
                    <uni-data-select
                  v-model="form.item_type"
                  :localdata="list_item_type"
                  :clear="!disabledObj['item_type_isDisabled']"
                  :disabled="disabledObj['item_type_isDisabled']"
                  v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_type')) || (!form['second_hand_goods_id'] && $check_field('add','item_type'))"
                ></uni-data-select>
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_type')">
                  {{ form['item_type'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_specification') || $check_field('add','item_specification') || $check_field('set','item_specification')" label="物品规格" name="item_specification">
                            <uni-easyinput type="text" v-model="form['item_specification']" v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_specification')) || (!form['second_hand_goods_id'] && $check_field('add','item_specification'))" :disabled="disabledObj['item_specification_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_specification')">
                  {{ form['item_specification'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_quantity') || $check_field('add','item_quantity') || $check_field('set','item_quantity')" label="物品数量" name="item_quantity">
                            <uni-easyinput type="text" v-model="form['item_quantity']" v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_quantity')) || (!form['second_hand_goods_id'] && $check_field('add','item_quantity'))" :disabled="disabledObj['item_quantity_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_quantity')">
                  {{ form['item_quantity'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_unit_price') || $check_field('add','item_unit_price') || $check_field('set','item_unit_price')" label="物品单价" name="item_unit_price">
                            <uni-easyinput type="text" v-model="form['item_unit_price']" v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_unit_price')) || (!form['second_hand_goods_id'] && $check_field('add','item_unit_price'))" :disabled="disabledObj['item_unit_price_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_unit_price')">
                  {{ form['item_unit_price'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','transaction_address') || $check_field('add','transaction_address') || $check_field('set','transaction_address')" label="交易地址" name="transaction_address">
                            <uni-easyinput type="text" v-model="form['transaction_address']" v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','transaction_address')) || (!form['second_hand_goods_id'] && $check_field('add','transaction_address'))" :disabled="disabledObj['transaction_address_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','transaction_address')">
                  {{ form['transaction_address'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','seller') || $check_field('add','seller') || $check_field('set','seller')" label="卖方" name="seller">
                    <uni-data-select
                  id="form_seller"
                  v-model="form['seller']"
                  :localdata="list_user_seller"
                  :clear="!disabledObj['seller_isDisabled']"
                  :disabled="disabledObj['seller_isDisabled']"
                  v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','seller')) || (!form['second_hand_goods_id'] && $check_field('add','seller'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['seller']"
                  :localdata="list_user_seller"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','seller')" id="seller"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','product_description') || $check_field('add','product_description') || $check_field('set','product_description')" label="商品描述" name="product_description">
                    <uni-easyinput type="textarea" v-model="form['product_description']" v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','product_description')) || (!form['second_hand_goods_id'] && $check_field('add','product_description'))" :disabled="disabledObj['product_description_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','product_description')">
                  {{ form['product_description'] }}
                </text>
                  </uni-forms-item>


            </uni-forms>
            <view class="form_button">
              <button size="mini" type="primary" @click="submit()" class="primary_btn">提交</button>
              <button size="mini" @click="cancel()">取消</button>
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import mixin from "@/libs/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      field: "second_hand_goods_id",
      url_add: "~/api/second_hand_goods/add?",
      url_set: "~/api/second_hand_goods/set?",
      url_get_obj: "~/api/second_hand_goods/get_obj?",
      url_upload: "~/api/second_hand_goods/upload?",

      query: {
        "second_hand_goods_id": 0,
      },

      form: {
            "item_no": this.$get_stamp(), // 物品序号
                    "item_name":  '', // 物品名称
                    "cover":  '', // 封面
                    "item_type":  '', // 物品类型
                    "item_specification":  '', // 物品规格
                    "item_quantity":  0 , // 物品数量
                    "item_unit_price":  0 , // 物品单价
                    "transaction_address":  '', // 交易地址
                    "seller": 0, // 卖方
                    "product_description":  '', // 商品描述
                                "second_hand_goods_id": 0, // ID
                
              },
          disabledObj:{
                        "item_no_isDisabled": false,
                                "item_name_isDisabled": false,
                                "cover_isDisabled": false,
                                "item_type_isDisabled": false,
                                "item_specification_isDisabled": false,
                                                                        "transaction_address_isDisabled": false,
                                "seller_isDisabled": false,
                                "product_description_isDisabled": false,
                                  },
                                                                                  // 物品类型选项列表
          list_item_type: [],
                                                                                                                                // 用户列表
            list_user_seller: [],
                        // 用户组
            group_user_seller: "",
                                                              }
  },
  methods: {
    changeLog(v,value){
      this.form[value] = v
    },
    /**
     * 上传文件
     * @param {Object} param文件参数
     */
    change_file(key_name){
      var _self = this;
      // 选择图像方法
      uni.chooseFile({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/second_hand_goods/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 上传图片
     * @param {Object} param文件参数
     */
    change_img(key_name){
      var _self = this;
      _self.upload_img_flag = false
      // 选择图像方法
      uni.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/second_hand_goods/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 获取对象后获取缓存表单
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_before(param){
      var form = uni.db.get("form");
      if (form) {
        delete(form.examine_state)
        delete(form.examine_reply)
        this.obj = uni.push(this.obj ,form);
        this.form = uni.push(this.form ,form);
      }
      var arr = []
      for (let key in form) {
        arr.push(key)
      }
      for (var i=0;i<arr.length;i++){
        this.disabledObj[arr[i] + '_isDisabled'] = true
      }
                                                                                      uni.db.del("form");
      return param;
    },
        
            
            
            /**
     * 获取物品类型列表
     */
    async get_list_item_type() {
              var json = await this.$get("~/api/item_type/get_list?");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_item_type.push({value:o.item_type,text:o.item_type}));
      }
      else if(json.error){
        console.error(json.error);
      }
        },
        
            
            
            
            
                /**
     * 获取系统用户用户列表
     */
    async get_list_user_seller() {
      // if(this.user_group !== "管理员" && this.form["seller"] === 0) {
      //     this.form["seller"] = this.user.user_id;
      // }
      var json = await this.$get("~/api/user/get_list?user_group=系统用户");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_user_seller.push({value:o.user_id,text:o.nickname + '-' + o.username}));
      }
      else if(json.error){
        console.error(json.error);
      }
    },
            /**
     * 获取系统用户用户组
     */
    async get_group_user_seller() {
      this.form["seller"] = this.user.user_id;
      var json = await this.$get("~/api/user_group/get_obj?name=系统用户");
      if(json.result && json.result.obj){
        this.group_user_seller = json.result.obj;
        this.get_user_session_seller(this.form['seller'])
      }
      else if(json.error){
        console.error(json.error);
      }
    },
    get_user_session_seller(id){
      var _this = this;
      var user_id = {"user_id":id}
      var url = "~/api/"+_this.group_user_seller.source_table+"/get_obj?"
      this.$get(url, user_id, function(res) {
        if (res.result && res.result.obj) {
          var arr = []
          for (let key in res.result.obj) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in _this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arr[i] === arrForm[j]) {
                  if (arr[i] !== "seller") {
                    _this.form[arrForm[j]] = res.result.obj[arr[i]]
                    _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                    break;
                  } else {
                    _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  }
                }
              }
            }
          }
        }
      });
    },
            
            
    
    /**
     * 获取对象之后
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_after(json, func){
                                                                                    },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/second_hand_goods/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/second_hand_goods/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/second_hand_goods/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/second_hand_goods/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/second_hand_goods/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
                                this.get_list_item_type();
                                                    this.get_list_user_seller();
            this.get_group_user_seller();
                      },
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.form_button{
  padding-bottom: 15px;
  display: flex;
}
.form_button button{
  width: 40%;
}
.query_select{
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline:none;
}

.query_option{
  width: 100%;
}

.btn_add_img{
  color: #D3D3D3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text{
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.form_button {
  padding-bottom: 15px;
  display: flex;
}
.form_button button {
  width: 40%;
}
.query_select {
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline: none;
}

.query_option {
  width: 100%;
}

.btn_add_img {
  color: #d3d3d3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text {
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
/*新样式*/
.uni-forms{
padding-top:1rem;
}
.uni-forms-item {
	padding: 6px 10px;
    background: #f8f6fc;
}
.uni-forms .is-input-border{
	border: 0;
}
.container{
	    -webkit-box-shadow: 0px 0px 0px #888888;
	    box-shadow: 0px 0px 0px #888888;
}
.form_button .primary_btn{
		background-color: #22B8B8;
		color: #FFFFFF;
	}
</style>
