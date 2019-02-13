function system_role_perm_oper(id,name,sys_role_panel_right){
		var sys_role_panel_right_prem_oper_form_systemOrgId = Ext.create('Ext.tree.Panel',{
			 fieldLabel: '权限配置',
		     labelAlign: 'right',
		     xtype: 'treepanel',
		     name:'systemOrgId',
		     id:"sys_role_panel_right_prem_oper_form_systemOrgId",
		     displayField: 'text',
		     value: '1',
		     allowBlank:true,
		     minPickerHeight: 20,
		     collapsible: true,
		     animate: false,
		     useArrows: true,
		     rootVisible:true,
		     store: Ext.create('Ext.data.TreeStore',{
		         fields: ['id','text'],
		         root: {
		           text: '权限',
		           expanded: true,
		           checked:false
		         },
		         proxy: {
		           type: 'ajax',
		           url: base + 'systemRole/systemRolePermOperPre?roleId='+id,
		           reader: {
		             type: 'json'
		           }
		         }
		       })
		});
	
		function CheckChild(node, checked) {
			//选择所有子节点
			node.eachChild(function (child) {
            child.set("checked", checked);
	            CheckChild(child, checked);
	        });
	    }
	    //选择所有父节点
	    function CheckParent(node, checked) {
	        if (node.parentNode) {
	            if (checked == true) {
	                node.parentNode.set("checked", checked);
	                CheckParent(node.parentNode, checked)
	                return;
	            }
	            var ist = true;
	            node.parentNode.eachChild(function (child) {
	                if (child.get("checked") != false)
	                    ist = false;
	            });
	            if (ist) {
	                node.parentNode.set("checked", checked);
	                CheckParent(node.parentNode, checked)
	            }
	        }
	    }
    
		sys_role_panel_right_prem_oper_form_systemOrgId.on('itemclick', function (view, record, item, index, event, eOpts) {
            var expand = record.get('expanded')
            if (expand) {
                view.collapse(record);
            }
            else {
                view.expand(record);
            }
            var checked = record.get("checked");
            record.set("checked", !checked);
            CheckChild(record, !checked);
            CheckParent(record, !checked);
            var datas = sys_role_panel_right_prem_oper_form_systemOrgId.getChecked();
            var submitIDs = new Array();
            for(var i=0;i<datas.length;i++){
            	var data = datas[i].data;
            	var selId = data.id;
            	if(selId != 'root'){
            		submitIDs.push(selId);
            	}
            }
            Ext.getCmp('sys_role_panel_right_prem_oper_permIds').setValue(submitIDs);
            
        }, sys_role_panel_right_prem_oper_form_systemOrgId);
		
		var sys_role_panel_right_prem_oper_form =  Ext.create('Ext.form.FormPanel', {
		  	buttonAlign: 'left',
   		  	frame: true,
   		    labelWidth:80,
   		    style:'padding:10px 0;',
   		    url: base + 'systemRole/execAddSystemRolePerm',
		    layout: {
		    	type: 'table',
		    	columns: 1
		    },
		    defaultType: 'textfield',
		    defaults: {
	    		anchor: '100%',
	    		labelAlign: "right"
	    	},
		    items: [{
    			fieldLabel: 'permIds',
    			name:'permIds',
    			id:"sys_role_panel_right_prem_oper_permIds",
			    labelAlign: 'right',
			    value:id,
			    xtype:'hidden'
    		},{
    			fieldLabel: 'roleId',
    			name:'systemRoleId',
    			id:"sys_role_panel_right_prem_oper_roleId",
			    labelAlign: 'right',
			    value:id,
			    xtype:'hidden'
    		}],
			buttons: [{
				text: '保存',
				handler: function() {
					if(sys_role_panel_right_prem_oper_form.getForm().isValid()){
		        		var myMask = new Ext.LoadMask(Ext.get(sys_role_panel_right_prem_oper_panel.getEl()), {
				            msg: '正在执行，请稍后...',
				            removeMask: true 
				        });
						myMask.show();
						sys_role_panel_right_prem_oper_form.getForm().submit({
							success : function(form, data) {
								Ext.Msg.alert('提示', '执行成功');
                                myMask.hide();
                                setTimeout(function(){
                                    sys_role_panel_right_prem_oper_form_systemOrgId.getStore().reload();
                                },300);

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
					sys_role_panel_right.remove(sys_role_panel_right_prem_oper_panel, true);			
				}
			}]
		});
		
		sys_role_panel_right_prem_oper_panel = new Ext.Panel({
			border: false,
			title: '角色管理-操作',
			region: 'center',
			layout: 'anchor',
			closable: true,
			items: [sys_role_panel_right_prem_oper_form_systemOrgId,sys_role_panel_right_prem_oper_form]
		});
		
		sys_role_panel_right.add(sys_role_panel_right_prem_oper_panel);
		sys_role_panel_right.setActiveTab(sys_role_panel_right_prem_oper_panel);
}