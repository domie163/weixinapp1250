<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','item_name') || $check_field('add','item_name') || $check_field('set','item_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品名称" prop="item_name">
												<el-input id="item_name" v-model="form['item_name']" placeholder="请输入物品名称"
							  v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','item_name')) || (!form['revelation_id'] && $check_field('add','item_name'))" :disabled="disabledObj['item_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_name')">{{form['item_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','item_type') || $check_field('add','item_type') || $check_field('set','item_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品类型" prop="item_type">
								<el-select id="item_type" v-model="form['item_type']"
						v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','item_type')) || (!form['revelation_id'] && $check_field('add','item_type'))">
						<el-option v-for="o in list_item_type" :key="o['item_type']" :label="o['item_type']"
							:value="o['item_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','item_type')">{{form['item_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','item_picture') || $check_field('add','item_picture') || $check_field('set','item_picture')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品图片" prop="item_picture">
								<el-upload :disabled="disabledObj['item_picture_isDisabled']" id="item_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_item_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','item_picture')) || (!form['revelation_id'] && $check_field('add','item_picture'))">
						<img v-if="form['item_picture']" :src="$fullUrl(form['item_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','item_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['item_picture'])" :preview-src-list="[$fullUrl(form['item_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','missing_address') || $check_field('add','missing_address') || $check_field('set','missing_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="丢失地址" prop="missing_address">
												<el-input id="missing_address" v-model="form['missing_address']" placeholder="请输入丢失地址"
							  v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','missing_address')) || (!form['revelation_id'] && $check_field('add','missing_address'))" :disabled="disabledObj['missing_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','missing_address')">{{form['missing_address']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','lost_time') || $check_field('add','lost_time') || $check_field('set','lost_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="丢失时间" prop="lost_time">
								<el-date-picker :disabled="disabledObj['lost_time_isDisabled']" v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','lost_time')) || (!form['revelation_id'] && $check_field('add','lost_time'))" id="lost_time"
						v-model="form['lost_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','lost_time')">{{form['lost_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','item_status') || $check_field('add','item_status') || $check_field('set','item_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品状态" prop="item_status">
								<el-select id="item_status" v-model="form['item_status']"
						v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','item_status')) || (!form['revelation_id'] && $check_field('add','item_status'))">
						<el-option v-for="o in list_item_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','item_status')">{{form['item_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','publishing_users') || $check_field('add','publishing_users') || $check_field('set','publishing_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布用户" prop="publishing_users">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_publishing_users(form['publishing_users']) }}
							<!--<el-input id="business_name" v-model="form['publishing_users']" placeholder="请输入发布用户"-->
							<!--v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','publishing_users')) || (!form['revelation_id'] && $check_field('add','publishing_users'))" :disabled="disabledObj['publishing_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','publishing_users')">{{form['publishing_users']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','publishing_users')) || (!form['revelation_id'] && $check_field('add','publishing_users'))" id="publishing_users" v-model="form['publishing_users']" :disabled="disabledObj['publishing_users_isDisabled']">
								<el-option v-for="o in list_user_publishing_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','publishing_users')" id="publishing_users" v-model="form['publishing_users']" :disabled="true">
								<el-option v-for="o in list_user_publishing_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="publishing_users" v-model="form['publishing_users']" :disabled="disabledObj['publishing_users_isDisabled']">
							<el-option v-for="o in list_user_publishing_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','details') || $check_field('add','details') || $check_field('set','details')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="详情内容" prop="details">
								<el-input type="textarea" id="details" v-model="form['details']" placeholder="请输入详情内容"
						v-if="user_group === '管理员' || (form['revelation_id'] && $check_field('set','details')) || (!form['revelation_id'] && $check_field('add','details'))" :disabled="disabledObj['details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','details')">{{form['details']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "revelation_id",
				url_add: "~/api/revelation/add?",
				url_set: "~/api/revelation/set?",
				url_get_obj: "~/api/revelation/get_obj?",
				url_upload: "~/api/revelation/upload?",

				query: {
					"revelation_id": 0,
				},

				form: {
								"item_name":  '', // 物品名称
										"item_type":  '', // 物品类型
										"item_picture":  '', // 物品图片
										"missing_address":  '', // 丢失地址
										"lost_time":  '', // 丢失时间
										"item_status":  '', // 物品状态
										"publishing_users": 0, // 发布用户
										"details":  '', // 详情内容
											"revelation_id": 0, // ID
						
				},
				disabledObj:{
								"item_name_isDisabled": false,
										"item_type_isDisabled": false,
										"item_picture_isDisabled": false,
										"missing_address_isDisabled": false,
										"lost_time_isDisabled": false,
										"item_status_isDisabled": false,
										"publishing_users_isDisabled": false,
										"details_isDisabled": false,
										},

	
						// 物品类型选项列表
				list_item_type: [""],
	
		
		
		
						// 物品状态选项列表
				list_item_status: ['未找回','已找回'],
	
		
					// 用户列表
				list_user_publishing_users: [],
						// 用户组
				group_user_publishing_users: "",
				
	
			}
		},
		methods: {


	
	
			
				/**
			 * 获取物品类型列表
			 */
			async get_list_item_type() {
				var json = await this.$get("~/api/item_type/get_list?");
				if(json.result && json.result.list){
					this.list_item_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
						/**
			 * 上传物品图片
			 * @param {Object} param 图片参数
			 */
			upload_item_picture(param){
						this.uploadFile(param.file, "item_picture");
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
                    this.list_user_publishing_users = json.result.list;
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
												_this.form["publishing_users"] = id
									_this.disabledObj['publishing_users' + '_isDisabled'] = true
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
					get_user_publishing_users(id){
				var obj = this.list_user_publishing_users.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
									
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
									        if (this.form["lost_time"].indexOf("-")===-1){
            this.form["lost_time"] = this.$toTime(parseInt(this.form["lost_time"]),"yyyy-MM-dd hh:mm:ss")
        }
												$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


													        if(this.form["lost_time"]=="0000-00-00 00:00:00"){
          this.form["lost_time"] = null;
        }
				if(parseInt(this.form["lost_time"]) > 9999){
					this.form["lost_time"] = this.$toTime(parseInt(this.form["lost_time"]),"yyyy-MM-dd hh:mm:ss")
				}
												

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																														return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/revelation/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/revelation/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/revelation/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/revelation/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/revelation/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
						this.get_list_item_type();
															this.get_list_user_publishing_users();
					this.get_group_user_publishing_users();
							},
	}
</script>

<style>
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

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
