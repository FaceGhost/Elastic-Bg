Ext.onReady(function(){
	var sys_user_panel_searh_form_org =  Ext.create('Ext.ux.ComboBoxTree',{
		 fieldLabel: '所属组织',
	     labelAlign: 'right',
	     name:'systemOrgId',
	     id:"sys_user_panel_searh_form_org",
	     allowBlank:true,
	     editable: false,
	     displayField: 'text',	
	     value:'全部',
	     multiCascade:false,
	     treeHeight:150,
	     rootVisible:true,
	     store: Ext.create('Ext.data.TreeStore',{
	         fields: ['id','text'],
	         proxy: {
	           type: 'ajax',
	           url: base + 'systemOrg/getSystemOrgTreeVo?showAll=true',
	           reader: {
	             type: 'json'
	           }
	         }
	       }),
           listeners: {
               change:function(record){
               }
          }
	});
	var sys_user_panel_searh_form_name = Ext.create('Ext.form.field.Text', {
		name: 'sys_user_panel_searh_form_name',
        fieldLabel: '用户账号',
        labelAlign: 'right'
	});
	var sys_user_panel_searh_form_realName = Ext.create('Ext.form.field.Text', {
		name: 'sys_user_panel_searh_form_realName',
        fieldLabel: '账号名称',
        labelAlign: 'right'
	});
	var sys_user_panel_searh_form_status = Ext.create('Ext.form.field.ComboBox',{
			fieldLabel: '状态',
			store: new Ext.data.SimpleStore({
				fields: ['value','text'],
				data: [['0','冻结'],['1','正常'],['','所有']]			
			}),
			value: '1',
			mode: 'local',
			editable: false,
			triggerAction: "all",
			valueField: 'value',
			displayField: 'text',
			labelAlign: 'right'
		});
	var sys_user_panel_searh_form_sdate = Ext.create('Ext.form.field.Date', {
		anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '登记起始日期',
        editable: false,
        name: 'sys_user_panel_searh_form_sdate',
        format:'Y-m-d'
	});
	var sys_user_panel_searh_form_edate = Ext.create('Ext.form.field.Date', {
		anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '登记起始日期',
        editable: false,
        name: 'sys_user_panel_searh_form_edate',
        format:'Y-m-d'
	});
	var sys_user_panel_searh_form  = Ext.create('Ext.form.Panel', {
		id:'sys_user_panel_searh_form',
		title:'查询条件',
        region: 'north',
	    buttonAlign: 'left',
	    frame: true,
	    border: false,
	    layout: {
	    	type: 'table',
	    	columns: 4
	    },
	    items: [sys_user_panel_searh_form_org,
	    		sys_user_panel_searh_form_name,
	    		sys_user_panel_searh_form_realName,
	    		sys_user_panel_searh_form_status,
	            sys_user_panel_searh_form_sdate,
	            sys_user_panel_searh_form_edate
	    ],
	    buttons : [{
	    	text : '查询',
	    	handler : function (){
	    		sys_user_panel_searh_grid_store.removeAll(true); 
	    		sys_user_panel_searh_grid_store.load();
	    	}
	    },{
	    	text : '新增',
            hidden : true,
            id : 'system:user:add',
	    	handler : function (){
	    		system_user_oper('',sys_user_panel_right,sys_user_panel_searh_grid);
	    	}
	    },{
	    	text : '修改',
            hidden : true,
            id : 'system:user:update',
	    	handler : function (){
	    		var record = sys_user_panel_searh_grid.getSelectionModel().getSelection();
	 		    if (record == null || record.length == 0) {
		 			Ext.MessageBox.alert("提示","请选择需要操作的记录！");
		 			return false;
	 		   }
	 		   var id= record[0].data.id;
	 		   system_user_oper(id,sys_user_panel_right,sys_user_panel_searh_grid);
	    	}
	    },{
	    	text : '重置密码',
	    	hidden : true,
	    	id : 'system:user:resetPwd',
	    	handler : function (){
	    		var record = sys_user_panel_searh_grid.getSelectionModel().getSelection();
	 		    if (record == null || record.length == 0) {
		 			Ext.MessageBox.alert("提示","请选择需要操作的记录！");
		 			return false;
	 		    }
	 		    var id= record[0].data.id;
                var url = base + 'systemUser/resetSystemUserPwd';
                var params = {'id':id};
                var alertMsg = '确认重置账号【' + record[0].data.name + '】的密码？';
                appDoAjaxRequest(null,url,params,true,true,alertMsg,this,function(response){
                    var result = Ext.decode(response.responseText);
                    if(result.success){
                        Ext.Msg.alert('提示','已重置');
                        sys_user_panel_searh_grid.getStore().reload();
                    }else{
                        Ext.Msg.alert('提示',result.msg);
                    }
                });
	    	}
	    }]
	});
	Ext.util.CSS.createStyleSheet('#sys_user_panel_searh_form {border-left: none;}');
	Ext.define("elasticBg.model.SystemUserVo", {
	    extend: "Ext.data.Model",
	    fields: [
	        { name: 'id', type: 'string' },
	        { name: 'name', type: 'string' },
	        { name: 'initPassword', type: 'initPassword' },
	        { name: 'realName', type: 'realName' },
	        { name: 'orgName', type: 'string' },
	        { name: 'status', type: 'status' },
	        { name: 'telPhone', type: 'telPhone' },
	        { name: 'fixedPhone', type: 'fixedPhone' },
	        { name: 'email', type: 'email' },
	        { name: 'qq', type: 'qq' },
	        { name: 'address', type: 'address' },
	        { name: 'insertT', type: 'string' },
	        { name: 'insertU', type: 'string' },
	        { name: 'updateT', type: 'string' },
	        { name: 'updateU', type: 'string' }
	    ]
	});
	var sys_user_panel_searh_grid_store =  Ext.create("Ext.data.Store", {
	    model: "elasticBg.model.SystemUserVo",
	    autoLoad: true,
	    pageSize: 20,
	    proxy: {
	        type: "ajax",
	        url: base + "systemUser/getSystemUserPageVo",
	        actionMethods : {  
                read : 'POST' 
            },
	        reader: {
	            root: "list",
	        }
	    }
	});
	var sys_user_panel_searh_grid = Ext.create('Ext.grid.Panel', {
		id:'sys_user_panel_searh_grid',
	    title: '查询结果',
	    scrollable:true,
	    border:false,
        scroll: true,
        region: 'center',
	    //forceFit:true,
	    stripeRows: true,
		emptyText:'没有查询到符合条件的数据 : ( ',
		store: sys_user_panel_searh_grid_store,
		viewConfig: {
            forceFit: true,
            stripeRows: true,
			enableTextSelection:true
		},
	    columns: [
            { text: 'ID',  dataIndex: 'id' ,hidden:true},
            { text: '用户账号',  dataIndex: 'name' },
            { text: '账号名称',  dataIndex: 'realName',width:150 },
            { text: '初始/重置密码',  dataIndex: 'initPassword',width:130},
            { text: '所属组织',  dataIndex: 'orgName',width:150},
        	{ text: '状态',  dataIndex: 'status',renderer: function(value, cellmeta, record, rowIndex, columnIndex, store) 
            	{
            		if('0' == value) return '冻结';
            		if('1' == value) return '正常';
        			return '未知';
        		} 
        	},
            { text: '手机',  dataIndex: 'telPhone' },
            { text: '固话',  dataIndex: 'fixedPhone' },
            { text: 'email',  dataIndex: 'email',width:150},
            { text: 'QQ',  dataIndex: 'qq' },
            { text: '地址',  dataIndex: 'address',width:200},
	        { text: '创建日期',  dataIndex: 'insertT',width:200},
	        { text: '创建人员',  dataIndex: 'insertU' },
	        { text: '更新日期',  dataIndex: 'updateT',width:200  },
	        { text: '更新人员',  dataIndex: 'updateU' }
	    ],
		dockedItems: [{
			xtype: 'pagingtoolbar',
			store: sys_user_panel_searh_grid_store,
			dock: 'bottom',
			displayInfo: true
		}]
	});
	sys_user_panel_searh_grid.getStore().on('beforeload',function (store, op, options){
		var params = {};
		var reg = /-/g;
		params.orgId = sys_user_panel_searh_form_org.getSubmitValue();
		params.name = sys_user_panel_searh_form_name.getValue();
		params.realName = sys_user_panel_searh_form_realName.getValue();
		params.status = sys_user_panel_searh_form_status.getValue();
		params.searchTS = sys_user_panel_searh_form_sdate.getRawValue().replace(reg,'');
		params.searchTE = sys_user_panel_searh_form_edate.getRawValue().replace(reg,'');
	    Ext.apply(store.proxy.extraParams, params);  
	});
	var sys_user_panel_right_main = Ext.create('Ext.panel.Panel', {
				id:'sys_user_panel_right_main',
				closable: false,
				border: false,
				title: '主面板',
				region: 'center',
				layout: 'border',
				items: [sys_user_panel_searh_form,sys_user_panel_searh_grid]
		});
	var sys_user_panel_right = new Ext.TabPanel({
		id:'sys_user_panel_right',
		region: 'center',
		layout: 'border',
        height: '100%',
        width: '100%',
		border:false,
		listeners: {
			beforetabchange: function(tabPanel, newTab, currentTab) {
				if (currentTab == null && newTab.title == '主面板') {
					return true;
				}else if (currentTab.title == '用户管理-操作'  ) {
					Ext.Msg.alert('提示', '请先关闭'+currentTab.title+'窗口！');
					return false;
				}else{
				}
				return true;
			},
			beforeremove: function(container, component) {
				component.setTitle('close');
			}
		}
	});
	sys_user_panel_right.add(sys_user_panel_right_main);
	sys_user_panel_right.setActiveTab(sys_user_panel_right_main);
	var sys_perm_panel = Ext.create('Ext.container.Viewport', {
			border: false,
			layout: 'border',
			items:[sys_user_panel_right]
	});
});
