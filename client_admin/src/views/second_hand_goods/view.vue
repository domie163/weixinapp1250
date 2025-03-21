<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','item_no') || $check_field('add','item_no') || $check_field('set','item_no')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品序号" prop="item_no">
												<el-input id="item_no" v-model="form['item_no']" placeholder="请输入物品序号"
							  v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_no')) || (!form['second_hand_goods_id'] && $check_field('add','item_no'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','item_no')">{{form['item_no']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','item_name') || $check_field('add','item_name') || $check_field('set','item_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品名称" prop="item_name">
												<el-input id="item_name" v-model="form['item_name']" placeholder="请输入物品名称"
							  v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_name')) || (!form['second_hand_goods_id'] && $check_field('add','item_name'))" :disabled="disabledObj['item_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_name')">{{form['item_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面" prop="cover">
								<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','cover')) || (!form['second_hand_goods_id'] && $check_field('add','cover'))">
						<img v-if="form['cover']" :src="$fullUrl(form['cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover'])" :preview-src-list="[$fullUrl(form['cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','item_type') || $check_field('add','item_type') || $check_field('set','item_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品类型" prop="item_type">
								<el-select id="item_type" v-model="form['item_type']"
						v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_type')) || (!form['second_hand_goods_id'] && $check_field('add','item_type'))">
						<el-option v-for="o in list_item_type" :key="o['item_type']" :label="o['item_type']"
							:value="o['item_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','item_type')">{{form['item_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','item_specification') || $check_field('add','item_specification') || $check_field('set','item_specification')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品规格" prop="item_specification">
												<el-input id="item_specification" v-model="form['item_specification']" placeholder="请输入物品规格"
							  v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_specification')) || (!form['second_hand_goods_id'] && $check_field('add','item_specification'))" :disabled="disabledObj['item_specification_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_specification')">{{form['item_specification']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','item_quantity') || $check_field('add','item_quantity') || $check_field('set','item_quantity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品数量" prop="item_quantity">
								<el-input-number id="item_quantity" v-model.number="form['item_quantity']"
						v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_quantity')) || (!form['second_hand_goods_id'] && $check_field('add','item_quantity'))" :disabled="disabledObj['item_quantity_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','item_quantity')">{{form['item_quantity']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','item_unit_price') || $check_field('add','item_unit_price') || $check_field('set','item_unit_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="物品单价" prop="item_unit_price">
								<el-input-number id="item_unit_price" v-model.number="form['item_unit_price']"
						v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','item_unit_price')) || (!form['second_hand_goods_id'] && $check_field('add','item_unit_price'))" :disabled="disabledObj['item_unit_price_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','item_unit_price')">{{form['item_unit_price']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','transaction_address') || $check_field('add','transaction_address') || $check_field('set','transaction_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="交易地址" prop="transaction_address">
												<el-input id="transaction_address" v-model="form['transaction_address']" placeholder="请输入交易地址"
							  v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','transaction_address')) || (!form['second_hand_goods_id'] && $check_field('add','transaction_address'))" :disabled="disabledObj['transaction_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','transaction_address')">{{form['transaction_address']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','seller') || $check_field('add','seller') || $check_field('set','seller')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="卖方" prop="seller">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_seller(form['seller']) }}
							<!--<el-input id="business_name" v-model="form['seller']" placeholder="请输入卖方"-->
							<!--v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','seller')) || (!form['second_hand_goods_id'] && $check_field('add','seller'))" :disabled="disabledObj['seller_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','seller')">{{form['seller']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','seller')) || (!form['second_hand_goods_id'] && $check_field('add','seller'))" id="seller" v-model="form['seller']" :disabled="disabledObj['seller_isDisabled']">
								<el-option v-for="o in list_user_seller" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','seller')" id="seller" v-model="form['seller']" :disabled="true">
								<el-option v-for="o in list_user_seller" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="seller" v-model="form['seller']" :disabled="disabledObj['seller_isDisabled']">
							<el-option v-for="o in list_user_seller" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','product_description') || $check_field('add','product_description') || $check_field('set','product_description')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品描述" prop="product_description">
								<el-input type="textarea" id="product_description" v-model="form['product_description']" placeholder="请输入商品描述"
						v-if="user_group === '管理员' || (form['second_hand_goods_id'] && $check_field('set','product_description')) || (!form['second_hand_goods_id'] && $check_field('add','product_description'))" :disabled="disabledObj['product_description_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','product_description')">{{form['product_description']}}</div>
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
										"item_quantity":  0, // 物品数量
										"item_unit_price":  0, // 物品单价
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
					          			"item_quantity_isDisabled": false,
					          			"item_unit_price_isDisabled": false,
										"transaction_address_isDisabled": false,
										"seller_isDisabled": false,
										"product_description_isDisabled": false,
										},

	
		
		
						// 物品类型选项列表
				list_item_type: [""],
	
		
		
		
		
		
					// 用户列表
				list_user_seller: [],
						// 用户组
				group_user_seller: "",
				
	
			}
		},
		methods: {


	
	
			
	
						/**
			 * 上传封面
			 * @param {Object} param 图片参数
			 */
			upload_cover(param){
						this.uploadFile(param.file, "cover");
					},
	
	
			
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
			 * 获取系统用户用户列表
			 */
			async get_list_user_seller() {
                // if(this.user_group !== "管理员" && this.form["seller"] === 0) {
                //     this.form["seller"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=系统用户");
                if(json.result && json.result.list){
                    this.list_user_seller = json.result.list;
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
												_this.form["seller"] = id
									_this.disabledObj['seller' + '_isDisabled'] = true
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
					get_user_seller(id){
				var obj = this.list_user_seller.getObj({"user_id":id});
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
																								$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																														

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
															this.get_list_user_seller();
					this.get_group_user_seller();
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
