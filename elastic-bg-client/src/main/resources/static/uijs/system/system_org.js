Ext.onReady(function(){
    var sys_org_panel_searh_form_org =  Ext.create('Ext.ux.ComboBoxTree',{
        fieldLabel: '上层组织',
        labelAlign: 'right',
        name:'systemOrgId',
        id:"sys_org_panel_searh_form_org",
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
                url: base + 'systemOrg/getSystemOrgTreeVoForNotLow?showAll=true',
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

	var sys_org_panel_searh_form_name = Ext.create('Ext.form.field.Text', {
		name: 'sys_org_panel_searh_form_name',
        fieldLabel: '组织名称',
        labelAlign: 'right'
	});

	var sys_org_panel_searh_form_sdate = Ext.create('Ext.form.field.Date', {
		anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '起始日期',
        editable: false,
        name: 'sys_org_panel_searh_form_sdate',
        format:'Y-m-d'
	});

	var sys_org_panel_searh_form_edate = Ext.create('Ext.form.field.Date', {
		anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '起始日期',
        editable: false,
        name: 'sys_org_panel_searh_form_edate',
        format:'Y-m-d'
	});

    var sys_org_panel_searh_form_status = Ext.create('Ext.form.field.ComboBox',{
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
	

	var sys_org_panel_searh_form  = Ext.create('Ext.form.Panel', {
		id:'sys_org_panel_searh_form',
		title:'查询条件',
	    buttonAlign: 'left',
        region: 'north',
	    frame: true,
	    border: false,
	    layout: {
	    	type: 'table',
	    	columns: 4
	    },
	    items: [sys_org_panel_searh_form_org,
	    		sys_org_panel_searh_form_name,
	            sys_org_panel_searh_form_sdate,
	            sys_org_panel_searh_form_edate,
            	sys_org_panel_searh_form_status
	    ],
	    buttons : [{
	    	text : '查询',
	    	handler : function (){
	    		sys_org_panel_searh_grid_store.removeAll(true); 
	    		sys_org_panel_searh_grid_store.load();
	    	}
	    },{
	    	text : '新增',
	    	hidden : true,
	    	id : 'system:org:add',
	    	handler : function (){
	    		system_org_oper('',sys_org_panel_right,sys_org_panel_searh_grid);
	    	}
	    },{
	    	text : '修改',
            hidden : true,
            id : 'system:org:update',
	    	handler : function (){
	    		var record = sys_org_panel_searh_grid.getSelectionModel().getSelection();
	 		    if (record == null || record.length == 0) {
		 			Ext.MessageBox.alert("提示","请选择需要操作的记录！");
		 			return false;
	 		   }
	 		   var id= record[0].data.id;
	 		   system_org_oper(id,sys_org_panel_right,sys_org_panel_searh_grid);
	    	}
	    }]
	});
	
	Ext.util.CSS.createStyleSheet('#sys_org_panel_searh_form {border-left: none;}');
	
	Ext.define("elasticBg.model.SystemOrgVo", {
	    extend: "Ext.data.Model",
	    fields: [
	        { name: 'ID', type: 'string' },
	        { name: 'name', type: 'string' },
	        { name: 'address', type: 'string' },
	        { name: 'pName', type: 'string' },
	        { name: 'linkMan', type: 'string' },
	        { name: 'pinYin', type: 'string' },
	        { name: 'fax', type: 'string' },
	        { name: 'fixedPhone', type: 'string' },
	        { name: 'insertT', type: 'string' },
	        { name: 'insertU', type: 'string' },
	        { name: 'updateT', type: 'string' },
	        { name: 'updateU', type: 'string' },
	        { name: 'status', type: 'string' }
	    ]
	});
	
	var sys_org_panel_searh_grid_store =  Ext.create("Ext.data.Store", {
	    model: "elasticBg.model.SystemOrgVo",
	    autoLoad: true,
	    pageSize: 20,
	    proxy: {
	        type: "ajax",
	        url: base + "systemOrg/getSystemOrgPageVo",
	        actionMethods : {  
                read : 'POST' 
            },
	        reader: {
	            root: "list",
	           
	        }
	    }
	});
		
	var sys_org_panel_searh_grid = Ext.create('Ext.grid.Panel', {
		id:'sys_org_panel_searh_grid',
	    title: '查询结果',
	    scrollable:true,
        region: 'center',
	    border:false,
	   // forceFit:true,
	    stripeRows: true,
		emptyText:'没有查询到符合条件的数据 : ( ',
		store: sys_org_panel_searh_grid_store,
        viewConfig: {
            forceFit: true,
            stripeRows: true,
            enableTextSelection:true
        },
	    columns: [
            { text: 'ID',  dataIndex: 'id' ,hidden:true},
            { text: '组织名称',  dataIndex: 'name' },
            { text: '上级组织',  dataIndex: 'pName'},
            { text: '状态',  dataIndex: 'status',renderer: function(value, cellmeta, record, rowIndex, columnIndex, store) 
            	{
            		if('1' == value) return '正常';
        			if('0' == value)return '冻结';
        		} 
        	},
        	{ text: '联系人',  dataIndex: 'linkMan' },
        	{ text: '联系电话',  dataIndex: 'fixedPhone' },
            { text: '地址',  dataIndex: 'address' },
            { text: '传真',  dataIndex: 'fax' },
            { text: '拼音',  dataIndex: 'pinYin' },
	        { text: '创建日期',  dataIndex: 'insertT',width:200},
	        { text: '创建人员',  dataIndex: 'insertU' },
	        { text: '更新日期',  dataIndex: 'updateT',width:200  },
	        { text: '更新人员',  dataIndex: 'updateU' }
	    ],
		dockedItems: [{
			xtype: 'pagingtoolbar',
			store: sys_org_panel_searh_grid_store,
			dock: 'bottom',
			displayInfo: true
		}]
	});
	
	sys_org_panel_searh_grid.getStore().on('beforeload',function (store, op, options){
		var params = {};
		var reg = /-/g;
        params.pId = sys_org_panel_searh_form_org.getSubmitValue();
		params.name = sys_org_panel_searh_form_name.getValue();
		params.searchTS = sys_org_panel_searh_form_sdate.getRawValue().replace(reg,'');
		params.searchTE = sys_org_panel_searh_form_edate.getRawValue().replace(reg,'');
		params.status = sys_org_panel_searh_form_status.getValue();
	    Ext.apply(store.proxy.extraParams, params);
	});

	var sys_org_panel_right_main = Ext.create('Ext.panel.Panel', {
				id:'sys_org_panel_right_main',
				closable: false,
				border: false,
				title: '主面板',
				region: 'center',
				layout: 'border',
				items: [sys_org_panel_searh_form,sys_org_panel_searh_grid]
		});
	

	var sys_org_panel_right = new Ext.TabPanel({
		id:'sys_org_panel_right',
		region: 'center',
		layout: 'border',
		border:false,
        height: '100%',
        width: '100%',
		listeners: {
			beforetabchange: function(tabPanel, newTab, currentTab) {
				if (currentTab == null && newTab.title == '主面板') {
					return true;
				}else if (currentTab.title == '组织管理-操作'  ) {
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
	sys_org_panel_right.add(sys_org_panel_right_main);
	sys_org_panel_right.setActiveTab(sys_org_panel_right_main);
	
		
	var sys_org_panel = Ext.create('Ext.container.Viewport', {
			border: false,
			layout: 'border',
			items:[sys_org_panel_right]
	});
	
});
