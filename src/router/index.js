import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
 **/
export const constantRouterMap = [
  // path是指定路径，当用户访问path中的地址时，展示component属性指定的组件
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },

  { path: '/404', component: () => import('@/views/404'), hidden: true },
  {
    path: '',             // 如果用户访问的是根路径
    component: Layout,
    redirect: '/home',    // 重定向到home主页，即默认访问页面是首页
    meta: { title: '健康监测', icon: 'home' },
    children: [{
      path: 'home',
      name: 'home',
      component: () => import('@/views/home/index'),
      meta: { title: '健康监测', icon: 'home' }
    }]
  },
  {
    path: '/sms',
    component: Layout,
    redirect: '/sms/senior',
    name: 'sms',
    meta: { title: '老人管理', icon: 'product' },
    children: [
      {
        path: 'senior',
        name: 'senior',
        component: () => import('@/views/sms/index'),
        meta: { title: '老人列表', icon: 'product' },
      },
      {
        path: 'seniorDetail',
        name: 'seniorDetail',
        component: () => import('@/views/sms/SeniorDetail'),
        meta: { title: '老人信息详情', icon: 'product-add' },
      },
      {
        path: 'addSenior',
        name: 'addSenior',
        component: () => import('@/views/sms/addSenior'),
        meta: { title: '添加老人', icon: 'product-list' },
      },

    ]
  },
  {
    path: '/rms',
    component: Layout,
    redirect: '/rms/room',
    name: 'rms',
    meta: { title: '房间管理', icon: 'ums-admin' },
    children: [
      {
        path: 'room',
        name: 'room',
        component: () => import('@/views/rms/index'),
        meta: { title: '房间列表', icon: 'ums-admin' }
      },
      {
        path: 'roomDetail',
        name: 'roomDetail',
        component: () => import('@/views/rms/roomDetail'),
        meta: { title: '房间详情' }
      }
    ]
  },
  {
    path: '/dms',
    component: Layout,
    redirect: '/dms/device',
    name: 'dms',
    meta: { title: '设备管理', icon: 'order' },
    children: [
      {
        path: 'device',
        name: 'device',
        component: () => import('@/views/dms/index'),
        meta: { title: '设备列表' },
      },
    ]
  },
  {
    path: '/wms',
    component: Layout,
    redirect: '/wms/worker',
    name: 'wms',
    meta: { title: '护工管理', icon: 'sms' },
    children: [
      {
        path: 'worker',
        name: 'worker',
        component: () => import('@/views/wms/index'),
        meta: { title: '护工列表', icon: 'sms-flash' }
      },
      {
        path: 'workerDetail',
        name: 'workerDetail',
        component: () => import('@/views/wms/workerDetail'),
        meta: { title: '护工详情' },
      },
    ]
  },
  {
    path: '/ams',
    component: Layout,
    redirect: '/ams/account',
    name: 'ams',
    meta: { title: '账号管理', icon: 'order' },
    children: [
      {
        path: 'account',
        name: 'account',
        component: () => import('@/views/ams/index'),
        meta: { title: '我的账号' },
      },
    ]
  },
  { path: '*', redirect: '/404', hidden: true }

]

export const asyncRouterMap = [
  // {
  //   path: '/pms',
  //   component: Layout,
  //   redirect: '/pms/product',
  //   name: 'pms',
  //   meta: { title: '老人管理', icon: 'product' },
  //   children: [
  //     {
  //       path: 'product',
  //       name: 'product',
  //       component: () => import('@/views/pms/product/index'),
  //       meta: { title: '老人列表', icon: 'product' },
  //       hidden: true,

  //     },
  //     {
  //       path: 'productDetail',
  //       name: 'productDetail',
  //       component: () => import('@/views/pms/product/components/ProductDetail'),
  //       meta: { title: '老人信息详情', icon: 'product-add' },
  //       hidden: true,
  //     },
  //     {
  //       path: 'addProduct',
  //       name: 'addProduct',
  //       component: () => import('@/views/pms/product/addSenior'),
  //       meta: { title: '添加老人', icon: 'product-list' },
  //       hidden: true,
  //     },

  //   ]
  // },
  // {
  //   path: '/oms',
  //   component: Layout,
  //   redirect: '/oms/order',
  //   name: 'oms',
  //   meta: { title: '设备管理', icon: 'order' },
  //   children: [
  //     {
  //       path: 'order1',
  //       name: 'order1',
  //       component: () => import('@/views/dms/index'),
  //       meta: { title: '设备列表' },
  //     },
  //     {
  //       path: 'orderDetail',
  //       name: 'orderDetail',
  //       component: () => import('@/views/oms/order/orderDetail'),
  //       meta: { title: '订单详情' },
  //       hidden: true,
  //     },
  //     {
  //       path: 'deliverOrderList',
  //       name: 'deliverOrderList',
  //       component: () => import('@/views/oms/order/deliverOrderList'),
  //       meta: { title: '发货列表' },
  //       hidden: true,
  //     },
  //     {
  //       path: 'orderSetting',
  //       name: 'orderSetting',
  //       component: () => import('@/views/oms/order/setting'),
  //       meta: { title: '订单设置', icon: 'order-setting' },
  //       hidden: true,
  //     },
  //     {
  //       path: 'returnApply',
  //       name: 'returnApply',
  //       component: () => import('@/views/oms/apply/index'),
  //       meta: { title: '退货申1请处理', icon: 'order-return' },
  //       hidden: true,
  //     },


  //   ]
  // },
  // {
  //   path: '/sms',
  //   component: Layout,
  //   redirect: '/sms/flash',
  //   name: 'sms',
  //   meta: { title: '护工管理', icon: 'sms' },
  //   children: [
  //     {
  //       path: 'flash',
  //       name: 'flash',
  //       component: () => import('@/views/sms/flash/index'),
  //       meta: { title: '护工列表', icon: 'sms-flash' }
  //     },
  //     {
  //       path: 'flashSession',
  //       name: 'flashSession',
  //       component: () => import('@/views/sms/flash/sessionList'),
  //       meta: { title: '秒杀时间段列表' },
  //       hidden: true
  //     },
  //     {
  //       path: 'selectSession',
  //       name: 'selectSession',
  //       component: () => import('@/views/sms/flash/selectSessionList'),
  //       meta: { title: '秒杀时间段选择' },
  //       hidden: true
  //     },
  //     {
  //       path: 'flashProductRelation',
  //       name: 'flashProductRelation',
  //       component: () => import('@/views/sms/flash/productRelationList'),
  //       meta: { title: '秒杀商品列表' },
  //       hidden: true
  //     },
  //     {
  //       path: 'coupon',
  //       name: 'coupon',
  //       component: () => import('@/views/sms/coupon/index'),
  //       meta: { title: '优惠券列表', icon: 'sms-coupon' },
  //       hidden: true
  //     },
  //     {
  //       path: 'addCoupon',
  //       name: 'addCoupon',
  //       component: () => import('@/views/sms/coupon/add'),
  //       meta: { title: '添加优惠券' },
  //       hidden: true
  //     },
  //     {
  //       path: 'updateCoupon',
  //       name: 'updateCoupon',
  //       component: () => import('@/views/sms/coupon/update'),
  //       meta: { title: '修改优惠券' },
  //       hidden: true
  //     },
  //     {
  //       path: 'couponHistory',
  //       name: 'couponHistory',
  //       component: () => import('@/views/sms/coupon/history'),
  //       meta: { title: '优惠券领取详情' },
  //       hidden: true
  //     },
  //     {
  //       path: 'brand',
  //       name: 'homeBrand',
  //       component: () => import('@/views/sms/brand/index'),
  //       meta: { title: '品牌推荐', icon: 'product-brand' },
  //       hidden: true

  //     },
  //     {
  //       path: 'new',
  //       name: 'homeNew',
  //       component: () => import('@/views/sms/new/index'),
  //       meta: { title: '新品推荐', icon: 'sms-new' },
  //       hidden: true

  //     },
  //     {
  //       path: 'hot',
  //       name: 'homeHot',
  //       component: () => import('@/views/sms/hot/index'),
  //       meta: { title: '人气推荐', icon: 'sms-hot' },
  //       hidden: true

  //     },
  //     {
  //       path: 'subject',
  //       name: 'homeSubject',
  //       component: () => import('@/views/sms/subject/index'),
  //       meta: { title: '专题推荐', icon: 'sms-subject' },
  //       hidden: true

  //     },
  //     {
  //       path: 'advertise',
  //       name: 'homeAdvertise',
  //       component: () => import('@/views/sms/advertise/index'),
  //       meta: { title: '广告列表', icon: 'sms-ad' },
  //       hidden: true

  //     },
  //     {
  //       path: 'addAdvertise',
  //       name: 'addHomeAdvertise',
  //       component: () => import('@/views/sms/advertise/add'),
  //       meta: { title: '添加广告' },
  //       hidden: true
  //     },
  //     {
  //       path: 'updateAdvertise',
  //       name: 'updateHomeAdvertise',
  //       component: () => import('@/views/sms/advertise/update'),
  //       meta: { title: '编辑广告' },
  //       hidden: true
  //     }
  //   ]
  // },
  // {
  //   path: '/ums',
  //   component: Layout,
  //   redirect: '/ums/admin',
  //   name: 'ums',
  //   meta: { title: '房间管理', icon: 'ums-admin' },
  //   children: [
  //     {
  //       path: 'admin',
  //       name: 'admin',
  //       component: () => import('@/views/ums/admin/index'),
  //       meta: { title: '房间列表', icon: 'ums-admin' }
  //     },
  //     {
  //       path: 'role',
  //       name: 'role',
  //       component: () => import('@/views/ums/role/index'),
  //       meta: { title: '角色列表', icon: 'ums-role' }
  //     },
  //     {
  //       path: 'allocMenu',
  //       name: 'allocMenu',
  //       component: () => import('@/views/ums/role/allocMenu'),
  //       meta: { title: '分配菜单' },
  //       hidden: true
  //     },
  //     {
  //       path: 'allocResource',
  //       name: 'allocResource',
  //       component: () => import('@/views/ums/role/allocResource'),
  //       meta: { title: '分配资源' },
  //       hidden: true
  //     },
  //     {
  //       path: 'menu',
  //       name: 'menu',
  //       component: () => import('@/views/ums/menu/index'),
  //       meta: { title: '菜单列表', icon: 'ums-menu' }
  //     },
  //     {
  //       path: 'addMenu',
  //       name: 'addMenu',
  //       component: () => import('@/views/ums/menu/add'),
  //       meta: { title: '添加菜单' },
  //       hidden: true
  //     },
  //     {
  //       path: 'updateMenu',
  //       name: 'updateMenu',
  //       component: () => import('@/views/ums/menu/update'),
  //       meta: { title: '修改菜单' },
  //       hidden: true
  //     },
  //     {
  //       path: 'resource',
  //       name: 'resource',
  //       component: () => import('@/views/ums/resource/index'),
  //       meta: { title: '资源列表', icon: 'ums-resource' }
  //     },
  //     {
  //       path: 'resourceCategory',
  //       name: 'resourceCategory',
  //       component: () => import('@/views/ums/resource/categoryList'),
  //       meta: { title: '资源分类' },
  //       hidden: true
  //     }
  //   ]
  // },
  // { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

