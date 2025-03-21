import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},


	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},



	// 友情链接路由
	// {
	// 	path: '/link/table',
	// 	name: 'link_table',
	// 	component: () => import('../views/link/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接列表'
	// 	}
	// },
	// {
	// 	path: '/link/view',
	// 	name: 'link_view',
	// 	component: () => import('../views/link/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接详情'
	// 	}
	// },

	// 轮播图路由
	{
		path: '/slides/table',
		name: 'slides_table',
		component: () => import('../views/slides/table.vue'),
		meta: {
			index: 0,
			title: '轮播图列表'
		}
	},
	{
		path: '/slides/view',
		name: 'slides_view',
		component: () => import('../views/slides/view.vue'),
		meta: {
			index: 0,
			title: '轮播图详情'
		}
	},
		// 文章路由
	{
		path: '/article/table',
		name: 'article_table',
		component: () => import('../views/article/table.vue'),
		meta: {
			index: 0,
			title: '文章列表'
		}
	},
	{
		path: '/article/view',
		name: 'article_view',
		component: () => import('../views/article/view.vue'),
		meta: {
			index: 0,
			title: '文章详情'
		}
	},

	// 文章分类路由
	{
		path: '/article_type/table',
		name: 'article_type_table',
		component: () => import('../views/article_type/table.vue'),
		meta: {
			index: 0,
			title: '文章分类列表'
		}
	},
	{
		path: '/article_type/view',
		name: 'article_type_view',
		component: () => import('../views/article_type/view.vue'),
		meta: {
			index: 0,
			title: '文章分类详情'
		}
	},
	
	// 广告路由
	// {
	// 	path: '/ad/table',
	// 	name: 'ad_table',
	// 	component: () => import('../views/ad/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告列表'
	// 	}
	// },
	// {
	// 	path: '/ad/view',
	// 	name: 'ad_view',
	// 	component: () => import('../views/ad/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告详情'
	// 	}
	// },



	// 公告路由
	{
		path: '/notice/table',
		name: 'notice_table',
		component: () => import('../views/notice/table.vue'),
		meta: {
			index: 0,
			title: '公告信息列表'
		}
	},
	{
		path: '/notice/view',
		name: 'notice_view',
		component: () => import('../views/notice/view.vue'),
		meta: {
			index: 0,
			title: '公告信息详情'
		}
	},


	// 评论路由
	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue'),
		meta: {
			index: 0,
			title: '评论列表'
		}
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue'),
		meta: {
			index: 0,
			title: '评论详情'
		}
	},

	// 系统用户路由
	{
		path: '/system_user/table',
		name: 'system_user_table',
		component: () => import('../views/system_user/table.vue'),
		meta: {
			index: 0,
			title: '系统用户列表'
		}
	},
	{
		path: '/system_user/view',
		name: 'system_user_view',
		component: () => import('../views/system_user/view.vue'),
		meta: {
			index: 0,
			title: '系统用户详情'
		}
	},
	// 寻物启示路由
	{
		path: '/revelation/table',
		name: 'revelation_table',
		component: () => import('../views/revelation/table.vue'),
		meta: {
			index: 0,
			title: '寻物启示列表'
		}
	},
	{
		path: '/revelation/view',
		name: 'revelation_view',
		component: () => import('../views/revelation/view.vue'),
		meta: {
			index: 0,
			title: '寻物启示详情'
		}
	},
	// 失物招领路由
	{
		path: '/lost_and_found/table',
		name: 'lost_and_found_table',
		component: () => import('../views/lost_and_found/table.vue'),
		meta: {
			index: 0,
			title: '失物招领列表'
		}
	},
	{
		path: '/lost_and_found/view',
		name: 'lost_and_found_view',
		component: () => import('../views/lost_and_found/view.vue'),
		meta: {
			index: 0,
			title: '失物招领详情'
		}
	},
	// 交流论坛路由
	{
		path: '/exchange_forum/table',
		name: 'exchange_forum_table',
		component: () => import('../views/exchange_forum/table.vue'),
		meta: {
			index: 0,
			title: '交流论坛列表'
		}
	},
	{
		path: '/exchange_forum/view',
		name: 'exchange_forum_view',
		component: () => import('../views/exchange_forum/view.vue'),
		meta: {
			index: 0,
			title: '交流论坛详情'
		}
	},
	// 物品类型路由
	{
		path: '/item_type/table',
		name: 'item_type_table',
		component: () => import('../views/item_type/table.vue'),
		meta: {
			index: 0,
			title: '物品类型列表'
		}
	},
	{
		path: '/item_type/view',
		name: 'item_type_view',
		component: () => import('../views/item_type/view.vue'),
		meta: {
			index: 0,
			title: '物品类型详情'
		}
	},
	// 二手物品路由
	{
		path: '/second_hand_goods/table',
		name: 'second_hand_goods_table',
		component: () => import('../views/second_hand_goods/table.vue'),
		meta: {
			index: 0,
			title: '二手物品列表'
		}
	},
	{
		path: '/second_hand_goods/view',
		name: 'second_hand_goods_view',
		component: () => import('../views/second_hand_goods/view.vue'),
		meta: {
			index: 0,
			title: '二手物品详情'
		}
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "校园生活服务小程序-admin";
	document.title = title;
})

export default router
