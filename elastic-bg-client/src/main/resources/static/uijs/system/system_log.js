Ext.onReady(function(){
    var sys_log_panel_searh_form_className = Ext.create('Ext.form.field.Text', {
        name: 'sys_log_panel_searh_form_className',
        fieldLabel: '类名',
        labelAlign: 'right'
    });
    var sys_log_panel_searh_form_method = Ext.create('Ext.form.field.Text', {
        name: 'sys_log_panel_searh_form_method',
        fieldLabel: '方法',
        labelAlign: 'right'
    });

    var sys_log_panel_searh_form_type = Ext.create('Ext.form.field.Text', {
        name: 'sys_log_panel_searh_form_type',
        fieldLabel: '类型',
        labelAlign: 'right'
    });
    var sys_log_panel_searh_form_oper = Ext.create('Ext.form.field.Text', {
        name: 'sys_log_panel_searh_form_oper',
        fieldLabel: '操作',
        labelAlign: 'right'
    });

    var sys_log_panel_searh_form_sdate = Ext.create('Ext.form.field.Date', {
        anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '起始日期',
        editable: false,
        name: 'sys_log_panel_searh_form_sdate',
        format:'Y-m-d'
    });

    var sys_log_panel_searh_form_edate = Ext.create('Ext.form.field.Date', {
        anchor: '100%',
        labelAlign: 'right',
        fieldLabel: '起始日期',
        editable: false,
        name: 'sys_log_panel_searh_form_edate',
        format:'Y-m-d'
    });

 

    var sys_log_panel_searh_form  = Ext.create('Ext.form.Panel', {
        id:'sys_log_panel_searh_form',
        title:'查询条件',
        buttonAlign: 'left',
        region: 'north',
        frame: true,
        border: false,
        layout: {
            type: 'table',
            columns: 4
        },
        items: [
            sys_log_panel_searh_form_className,
            sys_log_panel_searh_form_method,
            sys_log_panel_searh_form_type,
			sys_log_panel_searh_form_oper,
            sys_log_panel_searh_form_sdate,
            sys_log_panel_searh_form_edate
        ],
        buttons : [{
            text : '查询',
            handler : function (){
                sys_log_panel_searh_grid_store.removeAll(true);
                sys_log_panel_searh_grid_store.load();
            }
        }]
    });

    Ext.util.CSS.createStyleSheet('#sys_log_panel_searh_form {border-left: none;}');
    
	Ext.define("elasticBg.model.SystemLogVo", {
	    extend: "Ext.data.Model",
	    fields: [
	        { name: 'name', type: 'string' },
            { name: 'insertTStr', type: 'string' },
            { name: 'insertU', type: 'string' },
            { name: 'className', type: 'string' },
            { name: 'method', type: 'string' },
            { name: 'type', type: 'string' },
            { name: 'refId', type: 'string' },
            { name: 'oper', type: 'string' },
            { name: 'ip1Str', type: 'string' },
            { name: 'ip2Str', type: 'string' },
            { name: 'city', type: 'string' },
            { name: 'ps', type: 'string' }


	    ]
	});
	
	var sys_log_panel_searh_grid_store =  Ext.create("Ext.data.Store", {
	    model: "elasticBg.model.SystemLogVo",
	    autoLoad: true,
        pageSize: 20,
	    proxy: {
	        type: "ajax",
	        url: base + "systemLog/getSystemLogPageVo",
	        actionMethods : {  
                read : 'POST' 
            },
	        reader: {
	            root: "list",
	           
	        }
	    }
	});



		
	var sys_log_panel_searh_grid = Ext.create('Ext.grid.Panel', {
		id:'sys_log_panel_searh_grid',
	    scrollable:true,
        title:'查询结果',
        region: 'center',
	    border:false,
	    //forceFit:true,
	    stripeRows: true,
		emptyText:'没有查询到符合条件的数据 : ( ',
		store: sys_log_panel_searh_grid_store,
        viewConfig: {
            forceFit: true,
            stripeRows: true,
            enableTextSelection:true
        },
	    columns: [
            { text: 'ID',  dataIndex: 'id',hidden:true},
            { text: '操作人员',  dataIndex: 'insertU',width : 100 },
            { text: '内网IP',  dataIndex: 'ip1Str',width : 100 },
            { text: '外网IP',  dataIndex: 'ip2Str',width : 120 },
            { text: '城市',  dataIndex: 'city',width : 200 },
            { text: '操作时间',  dataIndex: 'insertTStr',width : 150 },
            { text: '类名',  dataIndex: 'className',width : 200 },
            { text: '方法',  dataIndex: 'method',width : 200 },
            { text: '类型',  dataIndex: 'type',width : 100 },
            { text: '参考ID',  dataIndex: 'refId',width : 100 },
            { text: '操作/异常',  dataIndex: 'oper',width : 200 },
            { text: '备注',  dataIndex: 'ps',width : 150 }


	    ],
		dockedItems: [{
			xtype: 'pagingtoolbar',
			store: sys_log_panel_searh_grid_store,
			dock: 'bottom',
			displayInfo: true
		}]
	});

    sys_log_panel_searh_grid.getStore().on('beforeload',function (store, op, options){
        var params = {};
        var reg = /-/g;
        params.className = sys_log_panel_searh_form_className.getValue();
        params.method = sys_log_panel_searh_form_method.getValue();
        params.type = sys_log_panel_searh_form_type.getValue();
        params.oper = sys_log_panel_searh_form_oper.getValue();
        params.searchTS = sys_log_panel_searh_form_sdate.getRawValue().replace(reg,'');
        params.searchTE = sys_log_panel_searh_form_edate.getRawValue().replace(reg,'');
        Ext.apply(store.proxy.extraParams, params);
    });

	var sys_log_panel_right_main = Ext.create('Ext.panel.Panel', {
				id:'sys_log_panel_right_main',
				closable: false,
				border: false,
				title: '主面板',
				region: 'center',
				layout: 'border',
				items: [sys_log_panel_searh_form,sys_log_panel_searh_grid]
		});
	

	var sys_log_panel_right = new Ext.TabPanel({
		id:'sys_log_panel_right',
		region: 'center',
		layout: 'border',
		border:false,
        height: '100%',
        width: '100%',
		listeners: {
			beforetabchange: function(tabPanel, newTab, currentTab) {
				return true;
			},
			beforeremove: function(container, component) {
				component.setTitle('close');
			}
		}
	});
	sys_log_panel_right.add(sys_log_panel_right_main);
	sys_log_panel_right.setActiveTab(sys_log_panel_right_main);
	
		
	var sys_log_panel = Ext.create('Ext.container.Viewport', {
			border: false,
			layout: 'border',
			items:[sys_log_panel_right]
	});
	
});
