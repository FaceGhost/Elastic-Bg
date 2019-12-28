Ext.QuickTips.init(); 
Ext.setGlyphFontFamily('FontAwesome');
var layout_tabs;
Ext.onReady(function() {

	var sys_nav = Ext.create('Ext.panel.Panel', {
		id: 'sys_nav',
		xtype: 'tab',
		border: false,
		animate: false,
		layout: 'accordion',
		loader: {},
		title: '<span >系统导航</span>'
	});


	loadLeftNavMenu();

	function loadLeftNavMenu() {

		Ext.Ajax.request({
			url: base + 'systemPermission/getSystemUserPermission',
			method: 'POST',
			success: function(response) {
				var result = eval('(' + response.responseText + ')');;
				for(var i = 0; i < result.length; i++) {
					var item = result[i];
					var title = "<div >" + item.text + "</div>";
					var node = Ext.create('Ext.tree.Panel', {
						title: title,
						rootVisible: false,
						lines: false,
						autoScroll: true,
						qtips: item.text,
						icon: item.icon,
						collapsed: true,
						root: {
							editable: false,
							expanded: true,
							text: item.text,
							draggable: false,
							children: item.children
						}
					});

					node.on('itemclick', function(view, record, item, index, e) {
						
						if(record.isExpanded()) {
							record.collapse();
						} else{
							record.expand();
						}
						
						var id = record.data.id;
						var url = record.raw.path;
						var title = record.data.text;
						if(!Ext.isEmpty(url)) {
							var p = Ext.getCmp(id);
							if(typeof(p) != 'undefined') {
								layout_tabs.setActiveTab(p);
							} else {
								//加载延迟
								/*
								var myMask = new Ext.LoadMask({
								       msg    : '正在加载，请稍后...',
								       target : layout_tabs
								}).show();
								*/
								var cp = Ext.create('Ext.Component', {
									title: '<div align="center">' + title + '</div>',
									id: id,
									closable: true,
									layout: "fit",
									html : '<iframe refid="'+id+'" id="'+id+'_iframe" src="'+url+'" name="'+title+'" width="100%" height="100%" frameborder="0" ></iframe>'
								});


								layout_tabs.add(cp);
								layout_tabs.setActiveTab(cp);
								
								/*
								 setTimeout(function(){
									myMask.hide();
								},250)
								*/
							}
						}

					});

					Ext.getCmp('sys_nav').add(node);
				}
			},
			failure: function() {
				Ext.Msg.alert("提示", "获取菜单失败！");
			}
		});
	}

	var layout_west = Ext.create('Ext.tab.Panel', {
		id: 'layout_west',
		xtype: 'tabpanel',
		region: 'west',
		width: 240,
		border: false,
		maxWidth: 300,
		minWidth: 220,
		split: true,
		collapsible: true,
		collapseMode: 'mini',
		header: false,
		tabPosition: 'top',
		tabBar: {
			height: 30,
			defaults: {
				height: 30 - 2
			}
		}
	});

	Ext.getCmp('layout_west').add(sys_nav);
	layout_west.setActiveTab('sys_nav');
	Ext.util.CSS.createStyleSheet('#layout_west-body {border-right-width: 1px !important;}');
	Ext.util.CSS.createStyleSheet('#layout_west-body {border-bottom-width: 1px !important;}');
	Ext.util.CSS.createStyleSheet('#layout_west-body {border-left-width: 1px !important;}');

	var layout_bottom = Ext.create('Ext.panel.Panel', {
		id: 'layout_bottom',
		xtype: 'panel',
		region: 'south',
		width: 200,
		height: 18,
		border: false,
		contentEl: 'layout_bottom_div',
		layout: 'fit',
		loader: {},
		bodyStyle: {
			backgroundColor: '#DFE8F6'
		},
		header: false
	});

	var layout_top = Ext.create('Ext.panel.Panel', {
		id: 'layout_top',
		xtype: 'panel',
		region: 'north',
		width: 200,
		height: 60,
		border: false,
		contentEl: 'layout_top_div',
		maxHeight: 60,
		minHeight: 60,
		layout: 'fit',
		split: true,
		loader: {},
		collapsible: false,
		collapseMode: 'mini',
		header: false
	});

	var id_tab_welcome = Ext.create('Ext.panel.Panel', {
		id: 'id_tab_welcome',
		xtype: 'panel',
		border: false,
        region: 'center',
		contentEl: 'div_center',
		layout: 'fit',
		title: '<span >欢迎</span>',
		reorderable: false
	});

	layout_tabs = Ext.create('Ext.tab.Panel', {
		id: 'layout_tabs',
		xtype: 'tabpanel',
		region: 'center',
		width: 200,
		plugins: [
			Ext.create('Ext.ux.TabCloseMenu', {
				//closeTabIcon: base + 'static/js/extjs/icon/page.png',
				//closeOtherslayout_tabsIcon: base + 'static/js/extjs/icon/page2.png',
				//closeAlllayout_tabsIcon: base + 'static/js/extjs/icon/page3.png',
               // icon:base + 'static/js/extjs/icon/refresh2.png',
				extraItemsTail: [
					{
						text: '刷新',
						handler: function(){
							
							//加载延迟
							var myMask = new Ext.LoadMask({
							       msg    : '正在加载，请稍后...',
							       target : layout_tabs
							}).show();
								
								
							var cur_tab_id = layout_tabs.getLayout().activeItem.id;
								if(cur_tab_id === 'id_tab_welcome'){
								Ext.get('iframe_main').dom.contentWindow.location.reload();
							}else{
								document.getElementById(cur_tab_id + '_iframe').contentWindow.location.reload(); 
							}
							
							setTimeout(function(){
									myMask.hide();
								},250)
						}
					} 
				]
			}),
			Ext.create('Ext.ux.TabReorderer')
		],
		plain: true,
		tabPosition: 'top',
		tabBar: {
			height: 30,
			defaults: {
				height: 30 - 2
			}
		}
	});
	Ext.getCmp('layout_tabs').add(id_tab_welcome);
	layout_tabs.setActiveTab(0);
	Ext.util.CSS.createStyleSheet('.x-tab-default-top-active {border-top: 2px solid #0099FF;}', 'layout_tabs_style');
	Ext.util.CSS.createStyleSheet('#layout_tabs-body {border-top-width: 0px !important;}', '_id_6ba0d0aa');
	Ext.util.CSS.createStyleSheet('#layout_tabs-body {border-right-width: 1px !important;}', '_id_c3429386');
	Ext.util.CSS.createStyleSheet('#layout_tabs-body {border-bottom-width: 1px !important;}', '_id_bafea1f7');
	Ext.util.CSS.createStyleSheet('#layout_tabs-body {border-left-width: 1px !important;}', '_id_d686c903');


	var vp = Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items: [layout_top, layout_west, layout_tabs, layout_bottom]
	});
	
	var hideMask = function () {
        Ext.get('loading').remove();
        Ext.fly('loading-mask').animate({
            opacity:0,
            remove:true,
            callback: function(){}
        });
    };

    Ext.defer(hideMask, 250);
    /**
     * welcome iframe height
     */
    var  welcomeIframe =document.getElementById('iframe_main');
    welcomeIframe.height=document.getElementById("body").offsetHeight-100;

});