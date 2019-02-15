function system_user_oper(id,sys_user_panel_right,sys_user_panel_searh_grid){
		var sys_user_panel_right_oper_url = base + 'systemUser/execAddSystemUser';
		if(id != null && id != ''){
            sys_user_panel_right_oper_url = base + 'systemUser/execUpdateSystemUser';
    	}
		var defaultOrgId,defaultOrgName,defaultRoleIds,defaultRoleNames;
		var sys_user_panel_right_oper_form_systemOrgId = Ext.create('Ext.ux.ComboBoxTree',{
			 fieldLabel: '所属组织',
		     labelAlign: 'right',
		     name:'systemOrgId',
		     id:"sys_user_panel_right_oper_form_systemOrgId",
		     allowBlank:false,
		     editable: false,
		     multiCascade:false,
		     treeHeight:150,
		     rootVisible:true,
		     store: Ext.create('Ext.data.TreeStore',{
		         fields: ['id','text'],
		         proxy: {
		           type: 'ajax',
		           url: base + 'systemOrg/getSystemOrgTreeVo',
		           reader: {
		             type: 'json'
		           }
		         }
		       }),
               listeners: {
                   change:function(record){
                	   sys_user_panel_right_oper_form_systemRoleId_store.load({params: {orgId: record.submitValue}});
                	   if(defaultOrgId == record.submitValue){
                           sys_user_panel_right_oper_form_systemRoleId.setDefaultValue(defaultRoleIds,defaultRoleNames);
					   }else{
                           sys_user_panel_right_oper_form_systemRoleId.clearValue();
					   }
                   }
              }
		});
		
		var sys_user_panel_right_oper_form_systemRoleId_store = Ext.create('Ext.data.TreeStore',{
			 autoLoad:false,
	         fields: ['id','text'],
	         proxy: {
	           type: 'ajax',
	           url: base + 'systemUser/getSystemUserRole?userId='+ id  +'&roleId='+sys_user_panel_right_oper_form_systemOrgId.getValue(),
	           reader: {
	             type: 'json'
	           }
	         }
	         
	       })
		var sys_user_panel_right_oper_form_systemRoleId = Ext.create('Ext.ux.ComboBoxTree',{
			 fieldLabel: '所属角色',
		     labelAlign: 'right',
		     name:'systemRoleId',
		     id:"sys_user_panel_right_oper_form_systemRoleId",
		     editable: false,
		     multiSelect : true, 
		     allowBlank:true,
		     treeHeight:150,
		     store: sys_user_panel_right_oper_form_systemRoleId_store,
             listeners: {
                 change:function(){
                	Ext.getCmp('sys_user_panel_right_oper_form_userRoles').setValue(sys_user_panel_right_oper_form_systemRoleId.getSubmitValue()); 
                 }
            }
		});
		
		var sys_user_panel_right_oper_form =  Ext.create('Ext.form.FormPanel', {
		  	buttonAlign: 'left',
   		  	frame: true,
   		    labelWidth:80,
   		    style:'padding:10px 0;',
   		    url:  sys_user_panel_right_oper_url,
		    layout: {
		    	type: 'table',
		    	columns: 4
		    },
		    defaultType: 'textfield',
		    defaults: {
	    		anchor: '100%',
	    		labelAlign: "right"
	    	},
		    items: [sys_user_panel_right_oper_form_systemOrgId,
		    	sys_user_panel_right_oper_form_systemRoleId,
		    	{
	    			 fieldLabel: '用户账号',
	    			 name:'name',
	    			 id:"sys_user_panel_right_oper_form_name",
				     labelAlign: 'right',
				     regex : /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_\-]{6,15}$/,
				     regexText:'6到15位同时包含字母、数字',
			    	 allowBlank:false
		    	},{
	    			 fieldLabel: '账号名称',
	    			 name:'realName',
	    			 id:"sys_user_panel_right_oper_form_realName",
				     labelAlign: 'right',
				     maxLength:15,
			    	 allowBlank:false
		    	},{
	    			 fieldLabel: '手机号',
	    			 name:'telPhone',
	    			 maxLength:11,
	    			 id:"sys_user_panel_right_oper_form_telPhone",
				     labelAlign: 'right'
		    	},{
	    			 fieldLabel: '固话',
	    			 name:'fixedPhone',
	    			 maxLength:15,
	    			 id:"sys_user_panel_right_oper_form_fixedPhone",
				     labelAlign: 'right'
		    	},{
	    			 fieldLabel: 'E-mail',
	    			 name:'email',
	    			 maxLength:30,
	    			 id:"sys_user_panel_right_oper_form_email",
				     labelAlign: 'right'
		    	},{
	    			 fieldLabel: 'QQ',
	    			 name:'qq',
	    			 maxLength:15,
	    			 id:"sys_user_panel_right_oper_form_qq",
				     labelAlign: 'right'
		    	},{
	    			 fieldLabel: '地址',
	    			 name:'address',
	    			 maxLength:50,
	    			 id:"sys_user_panel_right_oper_form_address",
				     labelAlign: 'right'
		    	},{
	    			fieldLabel: '是否启用',
				    labelAlign: 'right',
				    name:'statusStr',
				    id:"sys_user_panel_right_oper_form_status",
				    xtype: 'checkboxfield',
				    checked:true
	    		},{
	    			fieldLabel: '备注',
	    			name:'ps',
	    			maxLength:30,
	    			id:"sys_user_panel_right_oper_form_ps",
				    labelAlign: 'right'
	    		},{
	    			fieldLabel: 'ID',
	    			name:'id',
	    			id:"sys_user_panel_right_oper_form_id",
				    labelAlign: 'right',
				    xtype:'hidden'
	    		},{
	    			fieldLabel: 'userRoles',
	    			name:'userRoles',
	    			id:"sys_user_panel_right_oper_form_userRoles",
				    labelAlign: 'right',
				    xtype:'hidden'
	    		}],
			buttons: [{
				text: '保存',
				handler: function() {
					if(sys_user_panel_right_oper_form.getForm().isValid()){
		        		var myMask = new Ext.LoadMask(Ext.get(sys_user_panel_right_oper_panel.getEl()), {
				            msg: '正在执行，请稍后...',
				            removeMask: true 
				        });
						myMask.show();
						sys_user_panel_right_oper_form.getForm().submit({
							success : function(form, data) {
								myMask.hide();
								sys_user_panel_right.remove(sys_user_panel_right_oper_panel, true);
								sys_user_panel_searh_grid.getStore().reload();
										
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
					sys_user_panel_right.remove(sys_user_panel_right_oper_panel, true);			
				}
			}]
		});

		var sys_user_panel_right_oper_panel = new Ext.Panel({
			border: false,
			title: '用户管理-操作',
			region: 'center',
			layout: 'anchor',
			closable: true,
			items: [sys_user_panel_right_oper_form]
		});
		
		sys_user_panel_right.add(sys_user_panel_right_oper_panel);
		sys_user_panel_right.setActiveTab(sys_user_panel_right_oper_panel);

		//修改，初始化数据
		initData(id);
		function initData(id){
			if(id == null || id == ''){
				return ;
			};
			var url = base + 'systemUser/preExecOperSystemUser';
			var params = {'id':id};
            appDoAjaxRequest(null,url,params,true,false,'',this,function(response){
                var result = Ext.decode(response.responseText);
                if(result.success){
                    var data = result.data;
                    for(var h in data){
                        var temp = Ext.getCmp('sys_user_panel_right_oper_form_' + h);
                        if(h == 'status'){
                            if(data[h] == '1'){
                                Ext.getCmp('sys_user_panel_right_oper_form_status').setValue(true);
                            }else{
                                Ext.getCmp('sys_user_panel_right_oper_form_status').setValue(false);
                            }
                        }else if(h == 'roleIds'){
                            defaultOrgId = data['orgId'];
							defaultRoleIds = data['roleIds'];
                            defaultRoleNames = data['roleNames'];
                        }else if(h == 'orgId'){
                            defaultOrgName = data['orgName'];
                        }else if(temp != null){
                            temp.setValue(data[h]);
                        }
                        if(h = 'name' && data[h] == 'super_admin'){
                            Ext.getCmp('sys_user_panel_right_oper_form_name').setDisabled(true);
                           // Ext.getCmp('sys_user_panel_right_oper_form_systemOrgId').setDisabled(true);
                            //Ext.getCmp('sys_user_panel_right_oper_form_systemRoleId').setDisabled(true);
                            Ext.getCmp('sys_user_panel_right_oper_form_realName').setDisabled(true);
						}
                    };
                    sys_user_panel_right_oper_form_systemOrgId.setDefaultValue(defaultOrgId,defaultOrgName);
                }else{
                    Ext.Msg.alert('提示',result.msg);
                }
			});
		}
}