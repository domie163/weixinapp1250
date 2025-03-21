<template>
	<view class="diy_details diy_div_second_hand_goods">
		<view v-if="Object.keys(obj).length!==0" class="warp">
			<view class="container">
				<view class="row">
					<view v-if="$check_field('get','item_no')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>物品序号:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["item_no"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','item_name')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>物品名称:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["item_name"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','cover')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>封面:</span>
						</view>
							<view class="diy_field diy_img">
							<image style="width:100%;height:auto;" :src="$fullUrl(obj['cover'])" mode="widthFix"></image>
						</view>
						</view>
					<view v-if="$check_field('get','item_type')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>物品类型:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["item_type"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','item_specification')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>物品规格:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["item_specification"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','item_quantity')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>物品数量:</span>
						</view>
							<view class="diy_field diy_number">
							<text>
								{{ obj["item_quantity"] }}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','item_unit_price')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>物品单价:</span>
						</view>
							<view class="diy_field diy_number">
							<text>
								{{ obj["item_unit_price"] }}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','transaction_address')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>交易地址:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["transaction_address"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','seller')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>卖方:</span>
						</view>
							<view class="diy_field diy_uid">
							<text>
								{{ get_user_seller(obj['seller']) }}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','product_description')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>商品描述:</span>
						</view>
							<view class="diy_field diy_desc">
							<text>
								{{ obj["product_description"] }}
							</text>
						</view>
						</view>
				</view>
			</view>
		</view>

			<view class="warp">
			<view class="container">
				<view class="row">
					<view class="col">
						<button type="button" class="diy_btn" @click="to_form('/pages/order_information/edit')" v-if="$check_action('/order_information/edit','add') || $check_action('/order_information/edit','set')" ></button>
					</view>
				</view>
			</view>
		</view>
		</view>
</template>

<script>
	import mixin from "@/libs/mixins/component.js";
	export default {
		mixins: [mixin],
		props: {
			obj_goods: {
				type: Object,
				default(){
					return {}
				}
			},
			query:{
				type: Object,
				default(){
					return {
						second_hand_goods_id: 0
					}
				}
			}
		},
		data() {
			return {
				obj_goods_type: {},
				obj: {},
													// 用户列表
				list_user_seller: [],
					}
		},
		methods: {
			async get_obj_goods_type() {
				var res = await this.$get("~/api/goods_type/get_obj", {
					name: this.obj_goods.type
				})

				if (res.result.obj) {
					this.obj_goods_type = res.result.obj
				} else {
					console.log("没有请求到商品分类");
				}
			},
			async get_obj_by_goods() {
				var {
					source_table,
					source_field
				} = this.obj_goods_type
				var {
					source_id
				} = this.obj_goods
				var query = {}
				query[source_field] = source_id
				this.$get("~/api/" + source_table + "/get_obj", {}, (res) => {
					if (res.result.obj) {
						this.obj = res.result.obj
					} else {
						console.log("没有请求到商品分类");
					}
				})
			},
			async get_obj_by_id(){
				var res = await this.$get("~/api/second_hand_goods/get_obj", {
					second_hand_goods_id:this.query.second_hand_goods_id
				})

				if (res.result && res.result.obj) {
					this.obj = res.result.obj
				} else {
					console.log("没有请求到商品分类");
				}
			},
												/**
			 * 获取系统用户用户列表
			 */
			async get_list_user_seller() {
				var json = await this.$get("~/api/user/get_list?user_group=系统用户");
				if(json.result && json.result.list){
					this.list_user_seller = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_seller(id){
				let obj = this.list_user_seller;
				let ret = "";
				for(let i=0;i<obj.length;i++){
					if(obj[i].user_id==id){
						ret = obj[i].nickname+"-"+obj[i].username;
					}
				}
				return ret;
			},
				},
		created() {
												this.get_list_user_seller();
				},
		async onLoad(){
			if (Object.keys(this.obj_goods).length !== 0) {
				await this.get_obj_goods_type();
				await this.get_obj_by_goods();
			} else if (this.query["second_hand_goods_id"] !==0) {
				await this.get_obj_by_id();
			}
		}
	}
</script>

<style scoped>
	.div_second_hand_goods_details {}

	.details {
		background-color: #fff;
		margin-bottom: 0.5rem;
		padding: 1rem;
		font-size: 10px;
	}

	.item {
		display: flex;
		padding: 0.2rem 0;
		border-bottom: 1px solid #eee;
	}

	.left_text {
		flex: 0 0 25%;
	}

	.right_text {
		flex: 0 0 75%;
	}
	.edit_nav{
		text-align: center;
		background-color: #fff;
		padding: 0.3rem;
		margin: 0.1rem 1rem;
		border: 1px solid #eee;
		border-radius: 0.5rem;
	}
</style>
