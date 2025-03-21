<template>
	<view class="diy_details diy_div_exchange_forum">
		<view v-if="Object.keys(obj).length!==0" class="warp">
			<view class="container">
				<view class="row">
					<view v-if="$check_field('get','forum_title')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>论坛标题:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["forum_title"]}}
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
					<view v-if="$check_field('get','forum_label')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>论坛标签:</span>
						</view>
							<view class="diy_field diy_text">
							<text>
								{{obj["forum_label"]}}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','post_content')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>帖子内容:</span>
						</view>
							<view class="diy_field diy_desc">
							<text>
								{{ obj["post_content"] }}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','publishing_users')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>发布用户:</span>
						</view>
							<view class="diy_field diy_uid">
							<text>
								{{ get_user_publishing_users(obj['publishing_users']) }}
							</text>
						</view>
						</view>
					<view v-if="$check_field('get','release_date')" class="col-12 col-md-6">
						<view class="diy_title">
							<span>发布日期:</span>
						</view>
							<view class="diy_field diy_date">
							<text>
								{{ $toTime(obj["release_date"],"yyyy-MM-dd") }}
							</text>
						</view>
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
						exchange_forum_id: 0
					}
				}
			}
		},
		data() {
			return {
				obj_goods_type: {},
				obj: {},
									// 用户列表
				list_user_publishing_users: [],
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
				var res = await this.$get("~/api/exchange_forum/get_obj", {
					exchange_forum_id:this.query.exchange_forum_id
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
			async get_list_user_publishing_users() {
				var json = await this.$get("~/api/user/get_list?user_group=系统用户");
				if(json.result && json.result.list){
					this.list_user_publishing_users = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_publishing_users(id){
				let obj = this.list_user_publishing_users;
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
								this.get_list_user_publishing_users();
				},
		async onLoad(){
			if (Object.keys(this.obj_goods).length !== 0) {
				await this.get_obj_goods_type();
				await this.get_obj_by_goods();
			} else if (this.query["exchange_forum_id"] !==0) {
				await this.get_obj_by_id();
			}
		}
	}
</script>

<style scoped>
	.div_exchange_forum_details {}

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
