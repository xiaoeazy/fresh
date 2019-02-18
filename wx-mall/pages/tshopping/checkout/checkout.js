var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
const pay = require('../../../services/pay.js');

var app = getApp();

Page({
  data: {
    colonelGroupId:0,
    checkedGoodsList: [],
    checkedAddress: {},
    isCustomerPickUp:false,
    goodsTotalPrice: 0.00, //商品总价
    freightPrice: 0.00,    //快递费
    couponPrice: 0.00,     //优惠券的价格
    orderTotalPrice: 0.00,  //订单总价
    actualPrice: 0.00,     //实际需要支付的总价
    addressId: 0,
   
  },
  onLoad: function (options) {
    this.data.colonelGroupId = options.colonelGroupId;
  },

  getCheckoutInfo: function () {
    let that = this;
    try{
      var selectgoods = JSON.parse(wx.getStorageSync('selectgoods'));
      console.log("selectgoods:" + selectgoods);
      console.log(" that.data.colonelGroupId:" + that.data.colonelGroupId);

      var url = api.SelectGroupGoodsCheckout + "?colonelGroupId=" + that.data.colonelGroupId;
      util.request(url, selectgoods, 'POST', 'application/json').then(function (res) {
        if (res.errno === 0) {
          that.setData({
            checkedGoodsList: res.data.checkedGoodsList,
            checkedAddress: res.data.checkedAddress,
            actualPrice: res.data.actualPrice,
            couponPrice: res.data.couponPrice,
            freightPrice: res.data.freightPrice,
            goodsTotalPrice: res.data.goodsTotalPrice,
            orderTotalPrice: res.data.orderTotalPrice,
            isCustomerPickUp: res.data.isCustomerPickUp
          });


          try {
            wx.getStorage({
              key: 'checkedAddress',
              success: function (res) {
                var checkedAddress = res.data;
                that.setData({
                  'checkedAddress': checkedAddress
                });
                wx.removeStorage({
                  key: 'checkedAddress',
                  success: function (res) { },
                })

              },
            })
          } catch (e) {
            // Do something when catch error
          }

          //设置默认收获地址
          if (that.data.checkedAddress.id) {
            let addressId = that.data.checkedAddress.id;
            if (addressId) {
              that.setData({ addressId: addressId });
            }
          } else {
            wx.showModal({
              title: '',
              content: '请添加默认收货地址!',
              success: function (res) {
                if (res.confirm) {
                  that.selectAddress();
                }
              }
            })
          }
        }
        wx.hideLoading();
      });

    }catch(e){
      util.showErrorToast(e.message);
      wx.hideLoading();
    }
    
   
  },
  selectAddress() {
    wx.navigateTo({
      url: '/pages/tshopping/address/address',
    })
  },
  addAddress() {
    wx.navigateTo({
      url: '/pages/tshopping/addressAdd/addressAdd',
    })
  },
  onReady: function () {
    // 页面渲染完成

  },
  onShow: function () {
    var that  = this;
    // 页面显示
    wx.showLoading({
      title: '加载中...',
    })
    this.getCheckoutInfo();
 
  },



  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭

  },


  submitOrder: function () {
    var that = this;
    if (that.data.addressId <= 0) {
      util.showErrorToast('请选择收货地址');
      return false;
    }
    var selectgoods = JSON.parse(wx.getStorageSync('selectgoods'));
    var url = api.OrderSubmit + "?colonelGroupId=" + that.data.colonelGroupId + "&addressId =" + this.data.addressId;
    util.request(url, selectgoods, 'POST', 'application/json').then(res => {
      if (res.errno === 0) {
        const orderId = res.data.orderInfo.id;
        pay.payOrder(parseInt(orderId)).then(res => {
          wx.redirectTo({
            url: '/pages/payResult/payResult?status=1&orderId=' + orderId
          });
        }).catch(res => {
          wx.redirectTo({
            url: '/pages/payResult/payResult?status=0&orderId=' + orderId
          });
        });
      } else {
        util.showErrorToast('下单失败');
      }
    });
  }
})