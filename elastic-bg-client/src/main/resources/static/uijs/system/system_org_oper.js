function system_org_oper(id,sys_org_panel_right,sys_org_panel_searh_grid){
    var sys_org_panel_right_oper_url = base + 'systemOrg/execAddSystemOrg';
    if(id != null && id != ''){
        sys_org_panel_right_oper_url = base + 'systemOrg/execUpdateSystemOrg';
    }

    var sys_org_panel_right_oper_form_org = Ext.create('Ext.ux.ComboBoxTree',{
        fieldLabel: '上层组织',
        labelAlign: 'right',
        name:'pId',
        id:"sys_org_panel_right_oper_form_org",
        allowBlank:false,
        editable: false,
        multiCascade:false,
        treeHeight:150,
        rootVisible:true,
        store: Ext.create('Ext.data.TreeStore',{
            fields: ['id','text'],
            proxy: {
                type: 'ajax',
                url: base + 'systemOrg/getSystemOrgTreeVoForNotLow',
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

		var sys_org_panel_right_oper_form =  Ext.create('Ext.form.FormPanel', {
		  	buttonAlign: 'left',
   		  	frame: true,
   		    labelWidth:80,
   		    style:'padding:10px 0;',
   		    url:  sys_org_panel_right_oper_url,
		    layout: {
		    	type: 'table',
		    	columns: 3
		    },
		    defaultType: 'textfield',
		    defaults: {
	    		anchor: '100%',
	    		labelAlign: "right"
	    	},
		    items: [sys_org_panel_right_oper_form_org,
		    	{
	    			 fieldLabel: '组织名称',
	    			 name:'name',
	    			 id:"sys_org_panel_right_oper_form_name",
				     labelAlign: 'right',
			    	 allowBlank:false
	    		},{
	    			fieldLabel: '联系人',
	    			name:'linkMan',
	    			id:"sys_org_panel_right_oper_form_linkMan",
				    labelAlign: 'right',
			    	allowBlank:false
	    		},{
	    			fieldLabel: '联系电话',
	    			name:'fixedPhone',
	    			id:"sys_org_panel_right_oper_form_fixedPhone",
				    labelAlign: 'right'
	    		},{
	    			fieldLabel: '传真',
	    			name:'fax',
	    			id:"sys_org_panel_right_oper_form_fax",
				    labelAlign: 'right'
	    		},{
	    			fieldLabel: '地址',
	    			name:'address',
	    			id:"sys_org_panel_right_oper_form_address",
				    labelAlign: 'right'
	    		},{
	    			fieldLabel: '拼音编码',
	    			name:'pinYin',
	    			id:"sys_org_panel_right_oper_form_pinYin",
				    labelAlign: 'right'
	    		},{
	    			fieldLabel: '是否启用',
				    labelAlign: 'right',
				    name:'statusStr',
				    id:"sys_org_panel_right_oper_form_status",
				    xtype: 'checkboxfield',
				    checked:true
	    		},{
	    			fieldLabel: 'ID',
	    			name:'id',
	    			id:"sys_org_panel_right_oper_form_id",
				    labelAlign: 'right',
				    xtype:'hidden'
	    		}],
			buttons: [{
				text: '保存',
				handler: function() {
					if(sys_org_panel_right_oper_form.getForm().isValid()){
		        		var myMask = new Ext.LoadMask(Ext.getBody(), {
				            msg: '正在执行，请稍后...',
				            removeMask: true 
				        });
						myMask.show();
						sys_org_panel_right_oper_form.getForm().submit({
							success : function(form, data) {
								myMask.hide();
								sys_org_panel_right.remove(sys_org_panel_right_oper_panel, true);
								sys_org_panel_searh_grid.getStore().reload();
							},
							failure : function(form, data) {
								Ext.Msg.alert('提示', data.result.msg);
								myMask.hide();
							}
		        		});
		        	}
			  	}
			},{
				text: '取消',
				handler: function() {
					sys_org_panel_right.remove(sys_org_panel_right_oper_panel, true);			
				}
			}]
		});

		//修改，初始化数据
		initData(id);

		function initData(id){
			if(id == null || id == ''){
				return ;
			};
			var myMask = new Ext.LoadMask(Ext.getBody(), {
	            msg: '正在执行，请稍后...',
	            removeMask: true 
	        });
			myMask.show();
			Ext.Ajax.request({
					method :'post',
					timeout:2000000,
					params: {'id':id},
					url : base + 'systemOrg/preExecAddSystemOrg',
					success : function(response) {
						myMask.hide();
						var result = Ext.decode(response.responseText);
						if(result.success){
							var data = result.data;
							for(var h in data){
                                if(h == 'pId'){
                                    sys_org_panel_right_oper_form_org.setDefaultValue(data['pId'],data['pName']);
                                }
								var temp = Ext.getCmp('sys_org_panel_right_oper_form_' + h);
								if(temp != null){
									temp.setValue(data[h]);
									if(h == 'status'){
										if(data[h] == '1'){
											Ext.getCmp('sys_org_panel_right_oper_form_status').setValue(true);
										}else{
											Ext.getCmp('sys_org_panel_right_oper_form_status').setValue(false);
										}
									}
								}
							}
						}else{
							Ext.Msg.alert('提示',result.msg);
						}
					},failure : function() {
						Ext.Msg.alert('错误','系统繁忙,请稍后重试');
					}
			});
			myMask.destroy();
		}
		
		var sys_org_panel_right_oper_panel = new Ext.Panel({
			border: false,
			title: '组织管理-操作',
			region: 'center',
			layout: 'anchor',
			closable: true,
			items: [sys_org_panel_right_oper_form]
		});
		
		sys_org_panel_right.add(sys_org_panel_right_oper_panel);
		sys_org_panel_right.setActiveTab(sys_org_panel_right_oper_panel);
}