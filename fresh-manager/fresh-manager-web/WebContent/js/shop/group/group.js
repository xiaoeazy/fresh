$(function() {
	$("#jqGrid").Grid({
		url : _basePath + '/group/list',
		colModel : [ {
			label : 'id',
			name : 'id',
			index : 'id',
			key : true,
			hidden : true
		}, {
			label : '团购名称',
			name : 'groupName',
			index : 'group_name',
			width : 80
		}, {
			label : '团购标识',
			name : 'groupSn',
			index : 'group_sn',
			width : 80
		}, {
			label : '创建用户',
			name : 'createUserId',
			index : 'create_user_id',
			width : 80
		}, {
			label : '创建时间',
			name : 'createTime',
			index : 'create_time',
			width : 80
		} ]
	});
});

let vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		group : {},
		ruleValidate : {
			groupName : [ {
				required : true,
				message : '团购名称不能为空',
				trigger : 'blur'
			} ]
		},
		q : {
			name : ''
		}
	},
	methods : {
		getGroupGoods : function(id) {
			var url1 = "";
			var url2 = "";
			if(id!=null){
				url1 =  _basePath + '/goods/listWhereNoInGroupByGroupId?groupId='+id;
				url2 =  _basePath + '/goods/listWhereInGroupByGroupId?groupId='+id;
			}else{
				url1 =  _basePath + '/goods/listWhereNoInGroupByGroupId';
				url2 =  _basePath + '/goods/listWhereInGroupByGroupId';
			}
			$("#groodsTable").Grid({
				caption:'所有商品',
				url : url1,
				colModel : [ {
					label : 'id',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : '商品序列号',
					name : 'goodsSn',
					index : 'goods_sn',
					width : 100
				}, {
					label : '名称',
					name : 'name',
					index : 'name',
					width : 400
				} ],
				rowNum : -1,
				 rownumWidth: 55
			});
			
			$("#groupGoodsTable").Grid({
				caption:'已经选择商品',
				url :  url2,
				colModel : [ {
					label : 'id',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : '商品序列号',
					name : 'goodsSn',
					index : 'goods_sn',
					width : 100
				}, {
					label : '名称',
					name : 'name',
					index : 'name',
					width : 400
				} ],
				rowNum : -1,
				 rownumWidth: 55
			});

		},
		toDown : function(){
			var ids=$('#groodsTable').jqGrid('getGridParam','selarrrow');
			var deleteIds = [];
			for(var i =0 ;i<ids.length;i++ ){
				var data = $("#groodsTable").jqGrid("getRowData",ids[i]);
				$("#groupGoodsTable").jqGrid("addRowData", data.id,data);
				deleteIds.push(data.id);
			}
			for(var i =0;i<deleteIds.length;i++){
				$("#groodsTable").jqGrid("delRowData", deleteIds[i]);
			}
		},
		toTop : function(){
			var ids=$('#groupGoodsTable').jqGrid('getGridParam','selarrrow');
			var deleteIds = [];
			for(var i =0 ;i<ids.length;i++ ){
				var data = $("#groupGoodsTable").jqGrid("getRowData",ids[i]);
				$("#groodsTable").jqGrid("addRowData", data.id,data);
				deleteIds.push(data.id);
			}
			for(var i =0;i<deleteIds.length;i++){
				$("#groupGoodsTable").jqGrid("delRowData", deleteIds[i]);
			}
		},
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.group = {};
			vm.getGroupGoods();
		},
		update : function(event) {
			let id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.getInfo(id);
			vm.getGroupGoods(id);
		},
		saveOrUpdate : function(event) {
			let url = vm.group.id == null ? _basePath + "/group/save"
					: _basePath + "/group/update";
			vm.group.groupGoodsList = [];
			var rowIds =$('#groupGoodsTable').getDataIDs();
			for(var i =0 ;i<rowIds.length;i++ ){
				var data = $('#groupGoodsTable').getRowData(rowIds[i]);
				vm.group.groupGoodsList.push({goodsId:data.id});
			}
			Ajax.request({
				url : url,
				params : JSON.stringify(vm.group),
				type : "POST",
				contentType : "application/json",
				successCallback : function(r) {
					alert('操作成功', function(index) {
						vm.reload();
					});
				}
			});
		},
		del : function(event) {
			let ids = getSelectedRows("#jqGrid");
			if (ids == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				Ajax.request({
					url : _basePath + "/group/delete",
					params : JSON.stringify(ids),
					type : "POST",
					contentType : "application/json",
					successCallback : function() {
						alert('操作成功', function(index) {
							vm.reload();
						});
					}
				});
			});
		},
		getInfo : function(id) {
			Ajax.request({
				url : _basePath + "/group/info/" + id,
				async : true,
				successCallback : function(r) {
					vm.group = r.group;
				}
			});
		},
		reload : function(event) {
			vm.showList = true;
			let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'groupName' : vm.q.name
				},
				page : page
			}).trigger("reloadGrid");
			vm.handleReset('formValidate');
		},
		reloadSearch : function() {
			vm.q = {
				groupName : ''
			}
			vm.reload();
		},
		handleSubmit : function(name) {
			handleSubmitValidate(this, name, function() {
				vm.saveOrUpdate()
			});
		},
		handleReset : function(name) {
			handleResetForm(this, name);
		}
	}
});