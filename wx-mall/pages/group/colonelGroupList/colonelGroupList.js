const util = require('../../../utils/util.js');
const api = require('../../../config/api.js');
const user = require('../../../services/user.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    colonelGroup: [],
    scrollTop: 0,
    scrollHeight: 0,
    nopic: api.nopic
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.getColonelGroupData();

    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          scrollHeight: res.windowHeight
        });
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    var self = this;
    this.getColonelGroupData();
  },

  getColonelGroupData: function () {
    let that = this;
    var data = new Object();
    util.request(api.groupColonelAllList).then(function (res) {
      if (res.errno === 0) {
        data.colonelGroup = res.data.data;
        console.log(res.data.data);
        that.setData(data);
      }
    });
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})