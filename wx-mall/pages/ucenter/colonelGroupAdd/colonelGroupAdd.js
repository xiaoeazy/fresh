const util = require('../../../utils/util.js');
const api = require('../../../config/api.js');
const user = require('../../../services/user.js');
var dateTimePicker = require('../../../utils/dateTimePicker.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:0,
    radioitems: [
      { name: '自提', value: '0', checked: true },
      { name: '送货上门', value: '1', checked: false }
    ],
    group:'',
    //无图的地址
    nopic: api.nopic,
    //是否用户自提
    isCustomerPickUp: true,

    //时间参数
    dateTime1:null,
    dateTimeArray1:null,
    startYear: 2000,
    endYear: 2050,
    
    //地址参数
    latitude:'',
    longitude:'',

    maxWordSize:300,
    currentWordNumber:0,

    adminDesIsHidden:false,


    colonelGroupName:'',
    colonelGroupContent:'',
    groupEndTime: '',
    pickUpTime:'',
    groupDeliveryTime:'',
    groupAddress: '',

    groupEndTimeOri: '',
    pickUpTimeOri:'',
    groupDeliveryTimeOri:''
    
    

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var obj1 = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
    this.setData({
      id: parseInt(options.id),
     //id: 1,
      dateTimeArray1: obj1.dateTimeArray,
      pickUpTimeOri: obj1.dateTime,
      groupEndTimeOri: obj1.dateTime,
      groupDeliveryTimeOri:obj1.dateTime
    });
    this.getGroupByIdData();
  },
  /**
   * 根据groupId获取团购内容信息
   */
  getGroupByIdData: function () {
    let that = this;
    var data = new Object();
    util.request(api.groupQuery ,{ id: that.data.id }).then(function (res) {
      if (res.errno === 0) {
        data.group = res.data;
        console.log(res.data);
        that.setData(data);
        if (data.group.groupDesc == "" || data.group.groupDesc == null){
          that.setData({ adminDesIsHidden:true})
        }
      }
    });
  },

  radioChange(e) {
    let that = this;
    var value = e.detail.value;
    if (value == 0) {
      that.setData({ 
        isCustomerPickUp: true ,
        groupDeliveryTime: '',
      })
    } else {
      that.setData({
         isCustomerPickUp: false ,
         pickUpTime: '',
         groupAddress: '',
      })
    }
    console.log(that.data.isCustomerPickUp);
  },


  colonelGroupNameInput(e){
    this.setData({
      colonelGroupName: e.detail.value
    });
  },


  colonelGroupContentInput(e) {
    this.setData({
      currentWordNumber: e.detail.value.length,
      colonelGroupContent:e.detail.value
    })
  },


  changeGroupEndTime(e) {
    var that = this;
    var thegroupEndTimeOri = e.detail.value;
    this.setData({ groupEndTimeOri: thegroupEndTimeOri });
    var dateTimeArray1 = that.data.dateTimeArray1;
    var time = dateTimeArray1[0][thegroupEndTimeOri[0]] + "-" + dateTimeArray1[1][thegroupEndTimeOri[1]] + "-" + dateTimeArray1[2][thegroupEndTimeOri[2]] + " "
      + dateTimeArray1[3][thegroupEndTimeOri[3]] + ":" + dateTimeArray1[4][thegroupEndTimeOri[4]]
      + ":" + dateTimeArray1[5][thegroupEndTimeOri[5]];
    this.setData({ groupEndTime: time });

  },

  changePickUpTime(e) {
    var that = this;
    var thePickUpTimeOri = e.detail.value;
    this.setData({ pickUpTimeOri: thePickUpTimeOri });
    var dateTimeArray1 = that.data.dateTimeArray1;
    var time = dateTimeArray1[0][thePickUpTimeOri[0]] + "-" + dateTimeArray1[1][thePickUpTimeOri[1]] + "-" + dateTimeArray1[2][thePickUpTimeOri[2]] + " "
      + dateTimeArray1[3][thePickUpTimeOri[3]] + ":" + dateTimeArray1[4][thePickUpTimeOri[4]]
      + ":" + dateTimeArray1[5][thePickUpTimeOri[5]];
    this.setData({ pickUpTime: time });
  },

  changeGroupDeliveryTime(e) {
    var that = this;
    var groupDeliveryTimeOri = e.detail.value;
    this.setData({ groupDeliveryTimeOri: groupDeliveryTimeOri });
    var dateTimeArray1 = that.data.dateTimeArray1;
    var time = dateTimeArray1[0][groupDeliveryTimeOri[0]] + "-" + dateTimeArray1[1][groupDeliveryTimeOri[1]] + "-" + dateTimeArray1[2][groupDeliveryTimeOri[2]] + " "
      + dateTimeArray1[3][groupDeliveryTimeOri[3]] + ":" + dateTimeArray1[4][groupDeliveryTimeOri[4]]
      + ":" + dateTimeArray1[5][groupDeliveryTimeOri[5]];
    this.setData({ groupDeliveryTime: time });
  },

  
  //打开地图
  openMap(){
    var that = this;
    wx.getLocation({
      type: 'gcj02', // 返回可以用于wx.openLocation的经纬度
      success(res) {
        const latitude = res.latitude
        const longitude = res.longitude
        console.log(latitude,longitude);
       // wx.openLocation({
       //    latitude,
       //   longitude,
       //   scale: 18
       // })
        wx.chooseLocation({
          success: function (res) {
            // success
            console.log(res, "location")
            that.setData({
              groupAddress: res.name,
              latitude: res.latitude,
              longitude: res.longitude
            })
          },
          fail: function () {
            // fail
          },
          complete: function () {
            // complete
          }
        })
      }
    })


  },
  submitData:function(){
    var that = this;
    var isCustomerPickUp = that.data.isCustomerPickUp;
    var colonelGroupType = 0;
    var colonelGroupName = that.data.colonelGroupName;
    var colonelGroupContent = that.data.colonelGroupContent;
    var groupEndTime = that.data.groupEndTime;
    
    var pickUpTime = that.data.pickUpTime;
    var groupAddress = that.data.groupAddress;
    var latitude = that.data.latitude;
    var longitude = that.data.longitude;


    var groupDeliveryTime = that.data.groupDeliveryTime;
    var groupId = that.data.id;

    if (colonelGroupName == "") {
      util.showErrorToast("请设置拼团主题");
      return;
    }
    if (groupEndTime == "") {
      util.showErrorToast("请选择截单时间");
      return;
    }

    if (isCustomerPickUp==true){
      colonelGroupType = 0 ;
      if (isCustomerPickUp) {
        if (groupAddress == "") {
          util.showErrorToast("请选择自提地址");
          return;
        }
      }
      if (pickUpTime == "") {
        util.showErrorToast("请选择提货时间");
        return;
      }
    }else{
      colonelGroupType = 1;
      if (groupDeliveryTime == "") {
        util.showErrorToast("请选择发货时间");
        return;
      }
    }
    
  

    var obj ={
      colonelGroupType: colonelGroupType,
      colonelGroupName: colonelGroupName,
      colonelGroupContent: colonelGroupContent,
      groupEndTime: groupEndTime,
    
      pickUpTime: pickUpTime,

      groupAddress: groupAddress,
      latitude: latitude,
      longitude: longitude,

      groupId: groupId,
      groupDeliveryTime: groupDeliveryTime
    };
    console.log(obj);
    // var obj = {
    //   colonelGroupName: "HH", 
    //   groupEndTime: "2019-01-07 13:44:20", 
    //   groupAddress: "青浦区政府(公园路北)", 
    //   pickUpTime: "2019-01-08 13:44:12", 
    //   colonelGroupContent: "",
    //   groupId:1,
    //   latitude: "31.14979",
    //   longitude: "121.12426"
    //   };

      // return;
    util.request(api.groupcolonelSave, obj, "POST", "application/json")
      .then(function (res) {
        let _res = res;
        if (_res.errno == 0) {
          wx.redirectTo({
            url:'../colonelGroup/colonelGroup'
          })
        } else {
          util.showErrorToast(_res.errmsg,true);
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