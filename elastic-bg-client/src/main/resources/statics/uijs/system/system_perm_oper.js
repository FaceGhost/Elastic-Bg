function system_perm_oper(id,sys_perm_panel_right,sys_perm_panel_searh_grid){
		var sys_perm_panel_right_oper_url = base + 'systemPermission/execAddSystemPerm';
		if(id != null && id != ''){
            sys_perm_panel_right_oper_url = base + 'systemPermission/execUpdateSystemPerm';
		}
		var sys_perm_panel_right_oper_form_pId = Ext.create('Ext.ux.ComboBoxTree',{
             fieldLabel: '上层权限',
             labelAlign: 'right',
             name:'pId',
             id:"sys_perm_panel_right_oper_form_pId",
             allowBlank:false,
             editable: false,
             multiCascade:false,
             treeHeight:150,
             rootVisible:true,
		     store: Ext.create('Ext.data.TreeStore',{
		         fields: ['id','text'],
		         proxy: {
		           type: 'ajax',
		           url: base + 'systemPermission/getSystemPermissionTreeVoForNotLow',
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
		var sys_perm_panel_right_oper_form =  Ext.create('Ext.form.FormPanel', {
		  	buttonAlign: 'left',
   		  	frame: true,
   		    labelWidth:80,
   		    style:'padding:10px 0;',
   		    url: sys_perm_panel_right_oper_url,
		    layout: {
		    	type: 'table',
		    	columns: 4
		    },
		    defaultType: 'textfield',
		    defaults: {
	    		anchor: '100%',
	    		labelAlign: "right"
	    	},
		    items: [sys_perm_panel_right_oper_form_pId,
		    	{
	    			 fieldLabel: '权限名称',
	    			 name:'name',
	    			 id:"sys_perm_panel_right_oper_form_name",
				     labelAlign: 'right',
			    	 allowBlank:false
	    		},{
	    			fieldLabel: '权限',
	    			name:'permission',
	    			id:"sys_perm_panel_right_oper_form_permission",
				    labelAlign: 'right',
			    	allowBlank:true
	    		},{
	    			fieldLabel: 'URL',
	    			name:'url',
	    			id:"sys_perm_panel_right_oper_form_url",
				    labelAlign: 'right'
	    		},{
                    fieldLabel: 'Icon',
                    name:'icon',
                    id:"sys_perm_panel_right_oper_form_icon",
                    labelAlign: 'right'
                },{
	    			fieldLabel: '是否启用',
				    labelAlign: 'right',
				    name:'statusStr',
				    id:"sys_perm_panel_right_oper_form_status",
				    xtype: 'checkboxfield',
				    checked:true
	    		},{
	    			fieldLabel: '排序',
	    			name:'position',
	    			id:"sys_perm_panel_right_oper_form_position",
				    labelAlign: 'right',
				    xtype:'numberfield'
	    		},{
	    			fieldLabel: '备注',
	    			name:'ps',
	    			id:"sys_perm_panel_right_oper_form_ps",
				    labelAlign: 'right'
	    		},{
	    			fieldLabel: 'ID',
	    			name:'id',
	    			id:"sys_perm_panel_right_oper_form_id",
				    labelAlign: 'right',
				    xtype:'hidden'
	    		}],
			buttons: [{
				text: '保存',
				handler: function() {
					if(sys_perm_panel_right_oper_form.getForm().isValid()){
		        		var myMask = new Ext.LoadMask(Ext.get(sys_perm_panel_right_oper_panel.getEl()), {
				            msg: '正在执行，请稍后...',
				            removeMask: true 
				        });
						myMask.show();
						sys_perm_panel_right_oper_form.getForm().submit({
							success : function(form, data) {
								myMask.hide();
								sys_perm_panel_right.remove(sys_perm_panel_right_oper_panel, true);
								sys_perm_panel_searh_grid.getStore().reload();
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
					sys_perm_panel_right.remove(sys_perm_panel_right_oper_panel, true);			
				}
			}]
		});

		//修改，初始化数据
		initData(id);

		//数据初始化
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
					url : base + 'systemPermission/preExecAddSystemPerm',
					success : function(response) {
						myMask.hide();
						var result = Ext.decode(response.responseText);
						if(result.success){
							var data = result.data;
							for(var h in data){
                                if(h == 'pId'){
                                    sys_perm_panel_right_oper_form_pId.setDefaultValue(data['pId'],data['pName']);
                                }else{
                                    var temp = Ext.getCmp('sys_perm_panel_right_oper_form_' + h);
                                    if(temp != null){
                                        temp.setValue(data[h]);
                                    }
                                    if(h == 'status'){
                                        if(data[h] == '1'){
                                            Ext.getCmp('sys_perm_panel_right_oper_form_status').setValue(true);
                                        }else{
                                            Ext.getCmp('sys_perm_panel_right_oper_form_status').setValue(false);
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
		
		var sys_perm_panel_right_oper_panel = new Ext.Panel({
			border: false,
			title: '权限配置-操作',
			region: 'center',
			layout: 'anchor',
			closable: true,
			items: [sys_perm_panel_right_oper_form]
		});
		
		sys_perm_panel_right.add(sys_perm_panel_right_oper_panel);
		sys_perm_panel_right.setActiveTab(sys_perm_panel_right_oper_panel);
}