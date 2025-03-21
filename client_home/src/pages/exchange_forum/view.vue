<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','forum_title') || $check_field('add','forum_title') || $check_field('set','forum_title')" label="论坛标题" name="forum_title">
                            <uni-easyinput type="text" v-model="form['forum_title']" v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','forum_title')) || (!form['exchange_forum_id'] && $check_field('add','forum_title'))" :disabled="disabledObj['forum_title_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','forum_title')">
                  {{ form['forum_title'] }}
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
              <uni-forms-item v-if="$check_field('get','forum_label') || $check_field('add','forum_label') || $check_field('set','forum_label')" label="论坛标签" name="forum_label">
                            <uni-easyinput type="text" v-model="form['forum_label']" v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','forum_label')) || (!form['exchange_forum_id'] && $check_field('add','forum_label'))" :disabled="disabledObj['forum_label_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','forum_label')">
                  {{ form['forum_label'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','post_content') || $check_field('add','post_content') || $check_field('set','post_content')" label="帖子内容" name="post_content">
                    <uni-easyinput type="textarea" v-model="form['post_content']" v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','post_content')) || (!form['exchange_forum_id'] && $check_field('add','post_content'))" :disabled="disabledObj['post_content_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','post_content')">
                  {{ form['post_content'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','publishing_users') || $check_field('add','publishing_users') || $check_field('set','publishing_users')" label="发布用户" name="publishing_users">
                    <uni-data-select
                  id="form_publishing_users"
                  v-model="form['publishing_users']"
                  :localdata="list_user_publishing_users"
                  :clear="!disabledObj['publishing_users_isDisabled']"
                  :disabled="disabledObj['publishing_users_isDisabled']"
                  v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','publishing_users')) || (!form['exchange_forum_id'] && $check_field('add','publishing_users'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['publishing_users']"
                  :localdata="list_user_publishing_users"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','publishing_users')" id="publishing_users"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','release_date') || $check_field('add','release_date') || $check_field('set','release_date')" label="发布日期" name="release_date">
                    <uni-datetime-picker v-if="user_group === '管理员' || (form['exchange_forum_id'] && $check_field('set','release_date')) || (!form['exchange_forum_id'] && $check_field('add','release_date'))" v-model="form['release_date']" type="date" :disabled="disabledObj['release_date_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','release_date')">
                  {{ form['release_date'] }}
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
      field: "exchange_forum_id",
      url_add: "~/api/exchange_forum/add?",
      url_set: "~/api/exchange_forum/set?",
      url_get_obj: "~/api/exchange_forum/get_obj?",
      url_upload: "~/api/exchange_forum/upload?",

      query: {
        "exchange_forum_id": 0,
      },

      form: {
            "forum_title":  '', // 论坛标题
                    "cover":  '', // 封面
                    "forum_label":  '', // 论坛标签
                    "post_content":  '', // 帖子内容
                    "publishing_users": 0, // 发布用户
                    "release_date": this.$toTime(new Date().getTime(), "yyyy-MM-dd"),
                                "exchange_forum_id": 0, // ID
                
              },
          disabledObj:{
                        "forum_title_isDisabled": false,
                                "cover_isDisabled": false,
                                "forum_label_isDisabled": false,
                                "post_content_isDisabled": false,
                                "publishing_users_isDisabled": false,
                                "release_date_isDisabled": false,
                                  },
                                                                                                                // 用户列表
            list_user_publishing_users: [],
                        // 用户组
            group_user_publishing_users: "",
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
            url: _self.$fullUrl('/api/exchange_forum/upload?'),
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
            url: _self.$fullUrl('/api/exchange_forum/upload?'),
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
                                                      if (this.form["release_date"] && JSON.stringify(this.form["release_date"]).indexOf("-")===-1) {
        this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]), "yyyy-MM-dd")
      }
          uni.db.del("form");
      return param;
    },
        
            
            
            
                /**
     * 获取系统用户用户列表
     */
    async get_list_user_publishing_users() {
      // if(this.user_group !== "管理员" && this.form["publishing_users"] === 0) {
      //     this.form["publishing_users"] = this.user.user_id;
      // }
      var json = await this.$get("~/api/user/get_list?user_group=系统用户");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_user_publishing_users.push({value:o.user_id,text:o.nickname + '-' + o.username}));
      }
      else if(json.error){
        console.error(json.error);
      }
    },
            /**
     * 获取系统用户用户组
     */
    async get_group_user_publishing_users() {
      this.form["publishing_users"] = this.user.user_id;
      var json = await this.$get("~/api/user_group/get_obj?name=系统用户");
      if(json.result && json.result.obj){
        this.group_user_publishing_users = json.result.obj;
        this.get_user_session_publishing_users(this.form['publishing_users'])
      }
      else if(json.error){
        console.error(json.error);
      }
    },
    get_user_session_publishing_users(id){
      var _this = this;
      var user_id = {"user_id":id}
      var url = "~/api/"+_this.group_user_publishing_users.source_table+"/get_obj?"
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
                  if (arr[i] !== "publishing_users") {
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
                                                      if (this.form["release_date"] && JSON.stringify(this.form["release_date"]).indexOf("-")===-1) {
        this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
      }
        },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/exchange_forum/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/exchange_forum/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/exchange_forum/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/exchange_forum/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/exchange_forum/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
                                            this.get_list_user_publishing_users();
            this.get_group_user_publishing_users();
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
