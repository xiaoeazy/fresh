var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({
  data: {
    colonelGroupList: [],
    page: 1,
    size: 6,
    loadmoreText: '正在加载更多数据',
    nomoreText: '全部加载完成',
    nomore: false,
    totalPages: 1
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    // 页面显示

    wx.showLoading({
      title: '加载中...',
      success: function () {

      }
    });
    this.getColonelGroup();
  },

  /**
       * 页面上拉触底事件的处理函数
       */
  onReachBottom: function () {
    this.getColonelGroup()
  },

  getColonelGroup() {
    let that = this;

    if (that.data.totalPages <= that.data.page - 1) {
      that.setData({
        nomore: true
      })
      return;
    }

    util.request(api.groupcolonelList, { page: that.data.page, size: that.data.size }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          colonelGroupList: that.data.colonelGroupList.concat(res.data.data),
          page: res.data.currentPage + 1,
          totalPages: res.data.totalPages
        });
        wx.hideLoading();
      }
    });
  },
  toDetail(event) {
    let that = this;
    let colonelGroupIndex = event.currentTarget.dataset.colonelgroupIndex;
    let colonelGroup = that.data.colonelGroupList[colonelGroupIndex];
    console.log("colonelGroup:" + colonelGroup.colonelGroupId);
    wx.redirectTo({
      url: '/pages/group/colonelGroupDetail/colonelGroupDetail?colonelGroupId=' + colonelGroup.colonelGroupId,
    })
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {

  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})