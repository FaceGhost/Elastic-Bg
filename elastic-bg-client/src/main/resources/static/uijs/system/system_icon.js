Ext.onReady(function(){
	Ext.define("elasticBg.model.SystemIconVo", {
	    extend: "Ext.data.Model",
	    fields: [
	        { name: 'name', type: 'string' }
	    ]
	});
	
	var sys_icon_panel_searh_grid_store =  Ext.create("Ext.data.Store", {
	    model: "elasticBg.model.SystemIconVo",
	    autoLoad: true,
	    pageSize: 10000,
	    proxy: {
	        type: "ajax",
	        url: base + "getIcons",
	        actionMethods : {  
                read : 'POST' 
            },
	        reader: {
	            root: "list",
	           
	        }
	    }
	});
		
	var sys_icon_panel_searh_grid = Ext.create('Ext.grid.Panel', {
		id:'sys_icon_panel_searh_grid',
	    scrollable:true,
        region: 'center',
	    border:false,
	    forceFit:true,
	    stripeRows: true,
		emptyText:'没有查询到符合条件的数据 : ( ',
		store: sys_icon_panel_searh_grid_store,
        viewConfig: {
            forceFit: true,
            stripeRows: true,
            enableTextSelection:true
        },
	    columns: [
            { text: '预览',  dataIndex: 'image',renderer: function(value, cellmeta, record, rowIndex, columnIndex, store){
					var name = record.data['name'];
					return '<img  src="'+ base +'static/js/extjs/icon/'+name+'" />'
				}
            },
            { text: '图标',  dataIndex: 'name',width : 150 },
            { text: '路径',  dataIndex: 'path',width : 250,renderer: function(value, cellmeta, record, rowIndex, columnIndex, store){
                var name = record.data['name'];
                return 'static/js/extjs/icon/'+ name;
           		 }
            }

	    ],
		dockedItems: [{
			xtype: 'pagingtoolbar',
			store: sys_icon_panel_searh_grid_store,
			dock: 'bottom',
			displayInfo: true
		}]
	});


	var sys_icon_panel_right_main = Ext.create('Ext.panel.Panel', {
				id:'sys_icon_panel_right_main',
				closable: false,
				border: false,
				title: '系统Icon资源',
				region: 'center',
				layout: 'border',
				items: [sys_icon_panel_searh_grid]
		});
	

	var sys_icon_panel_right = new Ext.TabPanel({
		id:'sys_icon_panel_right',
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
	sys_icon_panel_right.add(sys_icon_panel_right_main);
	sys_icon_panel_right.setActiveTab(sys_icon_panel_right_main);
	
		
	var sys_icon_panel = Ext.create('Ext.container.Viewport', {
			border: false,
			layout: 'border',
			items:[sys_icon_panel_right]
	});
	
});
