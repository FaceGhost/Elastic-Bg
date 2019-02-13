Ext.onReady(function(){
	
	var sys_role_panel_searh_form_org =  Ext.create('Ext.ux.ComboBoxTree',{
		 fieldLabel: '所属组织',
	     labelAlign: 'right',
	     name:'pId',
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
	
	var sys_role_panel_searh_form_name = Ext.create('Ext.form.field.Text', {
		name: 'sys_role_panel_searh_form_name',
        fieldLabel: '角色名称',
        labelAlign: 'right'
	});
	
	var sys_role_panel_searh_form_status = Ext.create('Ext.form.field.ComboBox',{
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
	
	
	var sys_role_panel_searh_form_sdate = Ext.create('Ext.form.field.Date', {
		anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '起始日期',
        editable: false,
        name: 'sys_role_panel_searh_form_sdate',
        format:'Y-m-d'
       // maxValue: new Date(),
       // value: Ext.Date.add(new Date(), Ext.Date.DAY, -1)
	});
	
	
	var sys_role_panel_searh_form_edate = Ext.create('Ext.form.field.Date', {
		anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '起始日期',
        editable: false,
        name: 'sys_role_panel_searh_form_edate',
        format:'Y-m-d'
       // maxValue: new Date(),
       // value: Ext.Date.add(new Date(), Ext.Date.DAY, -1)
	});
	
		
	
	var sys_role_panel_searh_form  = Ext.create('Ext.form.Panel', {
		id:'sys_role_panel_searh_form',
		title:'查询条件',
	    buttonAlign: 'left',
	    frame: true,
        region: 'north',
	    border: false,
	    layout: {
	    	type: 'table',
	    	columns: 4
	    },
	    items: [sys_role_panel_searh_form_org,
	    		sys_role_panel_searh_form_name,
	    		sys_role_panel_searh_form_status,
	            sys_role_panel_searh_form_sdate,
	            sys_role_panel_searh_form_edate
	    ],
	    buttons : [{
	    	text : '查询',
	    	handler : function (){
	    		sys_role_panel_searh_grid_store.removeAll(true); 
	    		sys_role_panel_searh_grid_store.load();
	    	}
	    },{
	    	text : '新增',
	    	hidden : true,
	    	id : 'system:role:add',
	    	handler : function (){
	    		system_role_oper('',sys_role_panel_right,sys_role_panel_searh_grid);
	    	}
	    },{
	    	text : '修改',
	    	hidden : true,
	    	id : 'system:role:update',
	    	handler : function (){
	    		var record = sys_role_panel_searh_grid.getSelectionModel().getSelection();
	 		    if (record == null || record.length == 0) {
		 			Ext.MessageBox.alert("提示","请选择需要操作的记录！");
		 			return false;
	 		   }
	 		   var id= record[0].data.id;
	 		   system_role_oper(id,sys_role_panel_right,sys_role_panel_searh_grid);
	    	}
	    },{
	    	text : '权限配置',
	    	hidden : true,
			id : 'system:role:settingPerm',
	    	handler : function (){
	    		var record = sys_role_panel_searh_grid.getSelectionModel().getSelection();
	 		    if (record == null || record.length == 0) {
		 			Ext.MessageBox.alert("提示","请选择需要操作的记录！");
		 			return false;
	 		   }
	 		   var id= record[0].data.id;
	 		   var name= record[0].data.name;
	 		   system_role_perm_oper(id,name,sys_role_panel_right);
	    	}
	    }]
	});
	
	Ext.util.CSS.createStyleSheet('#sys_role_panel_searh_form {border-left: none;}');
	
	Ext.define("elasticBg.model.SystemRoleVo", {
	    extend: "Ext.data.Model",
	    fields: [
	        { name: 'ID', type: 'string' },
	        { name: 'name', type: 'string' },
	        { name: 'orgName', type: 'string' },
	        { name: 'pOrgName', type:'string'},
	        { name: 'status', type: 'status' },
	        { name: 'insertT', type: 'string' },
	        { name: 'insertU', type: 'string' },
	        { name: 'updateT', type: 'string' },
	        { name: 'updateU', type: 'string' }
	    ]
	});
	
	var sys_role_panel_searh_grid_store =  Ext.create("Ext.data.Store", {
	    model: "elasticBg.model.SystemRoleVo",
	    autoLoad: true,
	    pageSize: 20,
	    proxy: {
	        type: "ajax",
	        url: base + "systemRole/getSystemRolePageVo",
	        actionMethods : {  
                read : 'POST' 
            },
	        reader: {
	            root: "list",
	           
	        }
	    }
	});
		
		
		
	
	var sys_role_panel_searh_grid = Ext.create('Ext.grid.Panel', {
		id:'sys_role_panel_searh_grid',
	    title: '查询结果',
	    scrollable:true,
	    border:false,
	    forceFit:true,
	    stripeRows: true,
        region: 'center',
		emptyText:'没有查询到符合条件的数据 : ( ',
		store: sys_role_panel_searh_grid_store,
		viewConfig: {
            forceFit: true,
            stripeRows: true,
            enableTextSelection:true
		},
	    columns: [
            { text: 'ID',  dataIndex: 'id' ,hidden:true},
            { text: '角色名称',  dataIndex: 'name' },
            { text: '所属组织',  dataIndex: 'orgName'},
            { text: '所属上级组织',  dataIndex: 'pOrgName'},
        	{ text: '状态',  dataIndex: 'status',renderer: function(value, cellmeta, record, rowIndex, columnIndex, store) 
            	{
            		if('0' == value) return '冻结';
            		if('1' == value) return '正常';
        			return '未知';
        		} 
        	},
	        { text: '创建日期',  dataIndex: 'insertT',width:200},
	        { text: '创建人员',  dataIndex: 'insertU' },
	        { text: '更新日期',  dataIndex: 'updateT',width:200  },
	        { text: '更新人员',  dataIndex: 'updateU' }
	    ],
		dockedItems: [{
			xtype: 'pagingtoolbar',
			store: sys_role_panel_searh_grid_store,
			dock: 'bottom',
			displayInfo: true
		}]
	});
	
	sys_role_panel_searh_grid.getStore().on('beforeload',function (store, op, options){
            
		var params = {};
		var reg = /-/g;
		params.orgId = sys_role_panel_searh_form_org.getSubmitValue();
		params.name = sys_role_panel_searh_form_name.getValue();
		params.status = sys_role_panel_searh_form_status.getValue();
		params.searchTS = sys_role_panel_searh_form_sdate.getRawValue().replace(reg,'');
		params.searchTE = sys_role_panel_searh_form_edate.getRawValue().replace(reg,'');
		
	    Ext.apply(store.proxy.extraParams, params);  
		
	});
	
           
	

	var sys_role_panel_right_main = Ext.create('Ext.panel.Panel', {
				id:'sys_role_panel_right_main',
				closable: false,
				border: false,
				title: '主面板',
				region: 'center',
				layout: 'border',
				items: [sys_role_panel_searh_form,sys_role_panel_searh_grid]
		});
	
	 	
	   
	var sys_role_panel_right = new Ext.TabPanel({
		id:'sys_role_panel_right',
		region: 'center',
		layout: 'border',
		border:false,
        height: '100%',
        width: '100%',
		listeners: {
			beforetabchange: function(tabPanel, newTab, currentTab) {
				if (currentTab == null && newTab.title == '主面板') {
					return true;
				}else if (currentTab.title == '角色管理-操作'  ) {
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
	sys_role_panel_right.add(sys_role_panel_right_main);
	sys_role_panel_right.setActiveTab(sys_role_panel_right_main);
	
		
	var sys_perm_panel = Ext.create('Ext.container.Viewport', {
			border: false,
			layout: 'border',
			items:[sys_role_panel_right]
	});
	
});
