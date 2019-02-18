const util = require('../../../utils/util.js');
const api = require('../../../config/api.js');
const user = require('../../../services/user.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    colonelGroupId: 0,
    group: '',
    //无图的地址
    nopic: api.nopic,
    //是否用户自提
    isCustomerPickUp: true,

    //地址参数
    latitude: '',
    longitude: '',


    adminDesIsHidden: false,

    colonelGroupType : 0 ,

    colonelGroupName: '',
    colonelGroupContent: '',
    groupEndTime: '',
    pickUpTime: '',
    groupAddress: '',
    groupDeliveryTime:'',

    allprice:0,

    overdue:true
    

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      colonelGroupId: parseInt(options.colonelGroupId),
      //colonelGroupId: 24
    });
    this.getGroupByIdData();
  },
  /**
   * 根据groupId获取团购内容信息
   */
  getGroupByIdData: function () {
    let that = this;
    var data = new Object();
    util.request(api.groupcolonelQuery, { colonelGroupId: that.data.colonelGroupId }).then(function (res) {
      if (res.errno === 0) {
        data.group = res.data.group;
        data.colonelGroupType = res.data.colonelGroupType;
        if (data.colonelGroupType==0){
          data.isCustomerPickUp = true;
        }else{
          data.isCustomerPickUp = false;
        }
        that.setData(data);

        that.setData({
          colonelGroupName: res.data.colonelGroupName,
          colonelGroupContent: res.data.colonelGroupContent,
          groupEndTime: res.data.groupEndTime,
          pickUpTime: res.data.pickUpTime,
          groupAddress: res.data.groupAddress,
          latitude: res.data.latitude,
          longitude: res.data.longitude,
          groupDeliveryTime: res.data.groupDeliveryTime,
          overdue: res.data.overdue
        })
      }
    });
  },

  //打开地图
  openMap() {
    var that = this;
    console.log("aaa:" + that.data.latitude + ":" + that.data.longitude);
    if (that.data.latitude != null && that.data.longitude != null){
      console.log("bbb");
      wx.openLocation({
        latitude: that.data.latitude,
        longitude: that.data.longitude,
        name: that.data.groupAddress
      })
    }else{
      console.log("ccc");
    }
  },

  bindMinus: function (e) {
    var that = this;
    var index = parseInt(e.currentTarget.dataset.index);
    var num = that.data.group.goodsList[index].buyNum;
    var price = that.data.group.goodsList[index].retailPrice;
    // 如果只有1件了，就不允许再减了
    if (num > 0) {
      num--;
    }else{
      return;
    }
    var group = that.data.group;
    group.goodsList[index].buyNum = num;
    
    var allprice = that.data.allprice;
    allprice = allprice -  price;
    console.log("-:" + num)
    this.setData({
      group: group,
      allprice: allprice
    });
  },
  bindPlus: function (e) {
    var that =this;
    var index = parseInt(e.currentTarget.dataset.index);
    var num = that.data.group.goodsList[index].buyNum;
    var price = that.data.group.goodsList[index].retailPrice;
    // 自增
    num++;
    var group = that.data.group;
    var allprice = that.data.allprice;
    allprice = allprice + price;
    group.goodsList[index].buyNum = num;
    console.log("+:" + num)
    this.setData({
      group: group,
      allprice: allprice
    });
  },

  /* 输入框事件 
  bindManual: function (e) {
    var that =this;
    var num = e.detail.value;
    
    var index = parseInt(e.currentTarget.dataset.index);
    var price = that.data.group.goodsList[index].retailPrice;

    var group = that.data.group;
    group.goodsList[index].buyNum = num;
    // 将数值与状态写回 
    this.setData({
      group: group
    });
  } ,*/

  checkoutOrder: function () {
    //获取已选择的商品
    let that = this;
    if (that.data.allprice==0){
      return;
    }
    if (that.data.overdue==true){
      util.showErrorToast("该团购已经结束");
      return;
    }

    var goodsList = that.data.group.goodsList;
    var selectgoods = [];
    for(var i =0;i<goodsList.length;i++){
      var good = goodsList[i];
      console.log(good);
      var num = good.buyNum;
      if (num!=0){
        selectgoods.push(good);
      }
    }
    var selectgoodsStr = JSON.stringify(selectgoods);

    wx.setStorage({
      key: 'selectgoods',
      data: selectgoodsStr,
      success: function (res) {
        wx.navigateTo({
          url: '../../tshopping/checkout/checkout?colonelGroupId=' + that.data.colonelGroupId
        })
      }
    })
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