Ext.onReady(function(){
	
	var sys_perm_panel_searh_form_pId =  Ext.create('Ext.ux.ComboBoxTree',{
		 fieldLabel: '上层权限',
	     labelAlign: 'right',
	     name:'pId',
	     id:"sys_perm_panel_searh_form_pId",
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
	           url: base + 'systemPermission/getSystemPermissionTreeVoForNotLow?showAll=true',
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
	
	var sys_perm_panel_searh_form_name = Ext.create('Ext.form.field.Text', {
		name: 'sys_perm_panel_searh_form_name',
        fieldLabel: '权限名称',
        labelAlign: 'right'
	});
	
	var sys_perm_panel_searh_form_perm = Ext.create('Ext.form.field.Text', {
		name: 'sys_perm_panel_searh_form_perm',
        fieldLabel: '权限',
        labelAlign: 'right'
	});
	
	var sys_perm_panel_searh_form_type = Ext.create('Ext.form.field.ComboBox',{
			fieldLabel: '级别',
			store: new Ext.data.SimpleStore({
				fields: ['value','text'],
				data: [['1','一级'],['2','二级'],['3','三级'],['4','四级'],['5','五级'],['','所有']]			
			}),
			value: '',
			mode: 'local',
            editable: false,
			triggerAction: "all",
			valueField: 'value',
			displayField: 'text',
			labelAlign: 'right'
		});
	
	var sys_perm_panel_searh_form_url = Ext.create('Ext.form.field.Text', {
		name: 'sys_perm_panel_searh_form_url',
        fieldLabel: 'URL',
        labelAlign: 'right'
	});
	
	var sys_perm_panel_searh_form_sdate = Ext.create('Ext.form.field.Date', {
		anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '起始日期',
        editable: false,
        name: 'sys_perm_panel_searh_form_sdate',
        format:'Y-m-d'
       // maxValue: new Date(),
       // value: Ext.Date.add(new Date(), Ext.Date.DAY, -1)
	});
	
	
	var sys_perm_panel_searh_form_edate = Ext.create('Ext.form.field.Date', {
		anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '起始日期',
        editable: false,
        name: 'sys_perm_panel_searh_form_edate',
        format:'Y-m-d'
       // maxValue: new Date(),
       // value: Ext.Date.add(new Date(), Ext.Date.DAY, -1)
	});
	
		
	
	var sys_perm_panel_searh_form  = Ext.create('Ext.form.Panel', {
		id:'sys_perm_panel_searh_form',
		title:'查询条件',
	    buttonAlign: 'left',
	    frame: true,
        region: 'north',
	    border: false,
	    layout: {
	    	type: 'table',
	    	columns: 4
	    },
	    items: [sys_perm_panel_searh_form_pId,
	    		sys_perm_panel_searh_form_name,
	    		sys_perm_panel_searh_form_perm,
	    		sys_perm_panel_searh_form_url,
	    		sys_perm_panel_searh_form_type,
	            sys_perm_panel_searh_form_sdate,
	            sys_perm_panel_searh_form_edate
	    ],
	    buttons : [{
	    	text : '查询',
	    	handler : function (){
	    		sys_perm_panel_searh_grid_store.removeAll(true); 
	    		sys_perm_panel_searh_grid_store.load();
	    	}
	    },{
	    	text : '新增',
	    	//hidden : true,
	    	id : 'system:perm:add',
	    	handler : function (){
	    		system_perm_oper('',sys_perm_panel_right,sys_perm_panel_searh_grid);
	    	}
	    },{
	    	text : '修改',
	    	//hidden : true,
	    	id : 'system:perm:update',
	    	handler : function (){
	    		var record = sys_perm_panel_searh_grid.getSelectionModel().getSelection();
	 		    if (record == null || record.length == 0) {
		 			Ext.MessageBox.alert("提示","请选择需要操作的记录！");
		 			return false;
	 		   }
	 		   var id= record[0].data.id;
	 		   system_perm_oper(id,sys_perm_panel_right,sys_perm_panel_searh_grid);
	    	}
	    }]
	});
	
	Ext.util.CSS.createStyleSheet('#sys_perm_panel_searh_form {border-left: none;}');
	
	Ext.define("elasticBg.model.SystemOrgVo", {
	    extend: "Ext.data.Model",
	    fields: [
	        { name: 'ID', type: 'string' },
	        { name: 'name', type: 'string' },
            { name: 'icon', type: 'string' },
	        { name: 'pName', type: 'string' },
	        { name: 'type', type: 'type' },
	        { name: 'url', type: 'url' },
	        { name: 'permission', type: 'permission' },
	        { name: 'icon', type: 'icon' },
	        { name: 'status', type: 'status' },
	        { name: 'insertT', type: 'string' },
	        { name: 'insertU', type: 'string' },
	        { name: 'updateT', type: 'string' },
	        { name: 'updateU', type: 'string' }
	    ]
	});
	
	var sys_perm_panel_searh_grid_store =  Ext.create("Ext.data.Store", {
	    model: "elasticBg.model.SystemOrgVo",
	    autoLoad: true,
	    pageSize: 20,
	    proxy: {
	        type: "ajax",
	        url: base + "systemPermission/getSystemPermissionPageVo",
	        actionMethods : {  
                read : 'POST' 
            },
	        reader: {
	            root: "list",
	           
	        }
	    }
	});
		
		
		
	
	var sys_perm_panel_searh_grid = Ext.create('Ext.grid.Panel', {
		id:'sys_perm_panel_searh_grid',
	    title: '查询结果',
        region: 'center',
	    scrollable:true,
	    border:false,
	   // forceFit:true,
	    stripeRows: true,
		emptyText:'没有查询到符合条件的数据 : ( ',
		store: sys_perm_panel_searh_grid_store,
		viewConfig: {
            forceFit: true,
            stripeRows: true,
            enableTextSelection:true
		},
	    columns: [
            { text: 'ID',  dataIndex: 'id' ,hidden:true},
            { text: '权限名称',  dataIndex: 'name' },
            { text: 'icon',  dataIndex: 'icon',renderer: function(value, cellmeta, record, rowIndex, columnIndex, store){
                return '<img  src="'+ base +'static/js/extjs/icon/'+value+'" />'
            	}
            },
            { text: '上级权限',  dataIndex: 'pName'},
            { text: '级别',  dataIndex: 'type',renderer: function(value, cellmeta, record, rowIndex, columnIndex, store) 
            	{
            		if('1' == value) return '一级';
            		if('2' == value) return '二级';
            		if('3' == value) return '三级';
            		if('4' == value) return '四级';
            		if('5' == value) return '五级';
        			return '未知';
        		} 
        	},
        	{ text: '权限',  dataIndex: 'permission',width:200 },
        	{ text: '状态',  dataIndex: 'status',renderer: function(value, cellmeta, record, rowIndex, columnIndex, store) 
            	{
            		if('0' == value) return '冻结';
            		if('1' == value) return '正常';
        			return '未知';
        		} 
        	},
        	{ text: 'URL',  dataIndex: 'url',width:200 },
            { text: '图标',  dataIndex: 'icon' ,hidden:true},
	        { text: '创建日期',  dataIndex: 'insertT',width:200},
	        { text: '创建人员',  dataIndex: 'insertU' },
	        { text: '更新日期',  dataIndex: 'updateT',width:200  },
	        { text: '更新人员',  dataIndex: 'updateU' }
	    ],
		dockedItems: [{
			xtype: 'pagingtoolbar',
			store: sys_perm_panel_searh_grid_store,
			dock: 'bottom',
			displayInfo: true
		}]
	});
	
	sys_perm_panel_searh_grid.getStore().on('beforeload',function (store, op, options){
            
		var params = {};
		var reg = /-/g;
		params.pId = sys_perm_panel_searh_form_pId.getSubmitValue();
		params.name = sys_perm_panel_searh_form_name.getValue();
		params.permission = sys_perm_panel_searh_form_perm.getValue();
		params.type = sys_perm_panel_searh_form_type.getValue();
		params.url = sys_perm_panel_searh_form_url.getValue();
		params.searchTS = sys_perm_panel_searh_form_sdate.getRawValue().replace(reg,'');
		params.searchTE = sys_perm_panel_searh_form_edate.getRawValue().replace(reg,'');
		
	    Ext.apply(store.proxy.extraParams, params);  
		
	});
	
           
	

	var sys_perm_panel_right_main = Ext.create('Ext.panel.Panel', {
				id:'sys_perm_panel_right_main',
				closable: false,
				border: false,
				title: '主面板',
				region: 'center',
				layout: 'border',
				items: [sys_perm_panel_searh_form,sys_perm_panel_searh_grid]
		});
	
	 	
	   
	sys_perm_panel_right = new Ext.TabPanel({
		id:'sys_perm_panel_right',
		region: 'center',
		layout: 'border',
        height: '100%',
        width: '100%',
		border:false,
		listeners: {
			beforetabchange: function(tabPanel, newTab, currentTab) {
				if (currentTab == null && newTab.title == '主面板') {
					return true;
				}else if (currentTab.title == '权限配置-操作'  ) {
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
	sys_perm_panel_right.add(sys_perm_panel_right_main);
	sys_perm_panel_right.setActiveTab(sys_perm_panel_right_main);
	
		
	var sys_perm_panel = Ext.create('Ext.container.Viewport', {
			border: false,
			layout: 'border',
			items:[sys_perm_panel_right]
	});
	
});
