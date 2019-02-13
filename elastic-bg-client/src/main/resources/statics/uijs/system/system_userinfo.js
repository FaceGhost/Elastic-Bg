Ext.onReady(function(){
	var id = parent.document.getElementById('userInfoSetting').getAttribute("rid");
    var defaultOrgId,defaultOrgName,defaultRoleIds,defaultRoleNames;
    var sys_userinfo_panel_right_oper_form_systemOrgId = Ext.create('Ext.ux.ComboBoxTree',{
        fieldLabel: '所属组织',
        labelAlign: 'right',
        id:"sys_userinfo_panel_right_oper_form_systemOrgId",
        allowBlank:false,
        editable: false,
        disabled:true,
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
                sys_userinfo_panel_right_oper_form_systemRoleId_store.load({params: {orgId: record.submitValue}});
                if(defaultOrgId == record.submitValue){
                    sys_userinfo_panel_right_oper_form_systemRoleId.setDefaultValue(defaultRoleIds,defaultRoleNames);
                }else{
                    sys_userinfo_panel_right_oper_form_systemRoleId.clearValue();
                }
            }
        }
    });

    var sys_userinfo_panel_right_oper_form_systemRoleId_store = Ext.create('Ext.data.TreeStore',{
        autoLoad:false,
        fields: ['id','text'],
        proxy: {
            type: 'ajax',
            url: base + 'systemUser/getSystemUserRole?userId='+ id  +'&roleId='+sys_userinfo_panel_right_oper_form_systemOrgId.getValue(),
            reader: {
                type: 'json'
            }
        }

    })
    var sys_userinfo_panel_right_oper_form_systemRoleId = Ext.create('Ext.ux.ComboBoxTree',{
        fieldLabel: '所属角色',
        labelAlign: 'right',
        id:"sys_userinfo_panel_right_oper_form_systemRoleId",
        editable: false,
        multiSelect : true,
        disabled:true,
        allowBlank:true,
        treeHeight:150,
        store: sys_userinfo_panel_right_oper_form_systemRoleId_store,
        listeners: {
            change:function(){
                Ext.getCmp('sys_userinfo_panel_right_oper_form_userRoles').setValue(sys_userinfo_panel_right_oper_form_systemRoleId.getSubmitValue());
            }
        }
    });

    var sys_userinfo_panel_right_oper_form =  Ext.create('Ext.form.FormPanel', {
        buttonAlign: 'left',
        region: 'north',
        frame: true,
        border:false,
        labelWidth:80,
        style:'padding:10px 0;',
        url: base + 'systemUser/execUpdateUserInfo',
        layout: {
            type: 'table',
            columns: 4
        },
        defaultType: 'textfield',
        defaults: {
            anchor: '100%',
            labelAlign: "right"
        },
        items: [
        	sys_userinfo_panel_right_oper_form_systemOrgId,
            sys_userinfo_panel_right_oper_form_systemRoleId,
            {
                fieldLabel: '用户账号',
                id:"sys_userinfo_panel_right_oper_form_name",
                labelAlign: 'right',
                regex : /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_\-]{6,15}$/,
                disabled:true,
                regexText:'6到15位同时包含字母、数字',
                allowBlank:false
            },{
                fieldLabel: '账号名称',
                name:'realName',
                id:"sys_userinfo_panel_right_oper_form_realName",
                labelAlign: 'right',
                maxLength:15,
                allowBlank:false
            },{
                fieldLabel: '手机号',
                name:'telPhone',
                maxLength:11,
                id:"sys_userinfo_panel_right_oper_form_telPhone",
                labelAlign: 'right'
            },{
                fieldLabel: '固话',
                name:'fixedPhone',
                maxLength:15,
                id:"sys_userinfo_panel_right_oper_form_fixedPhone",
                labelAlign: 'right'
            },{
                fieldLabel: 'E-mail',
                name:'email',
                maxLength:30,
                id:"sys_userinfo_panel_right_oper_form_email",
                labelAlign: 'right'
            },{
                fieldLabel: 'QQ',
                name:'qq',
                maxLength:15,
                id:"sys_userinfo_panel_right_oper_form_qq",
                labelAlign: 'right'
            },{
                fieldLabel: '地址',
                name:'address',
                maxLength:50,
                id:"sys_userinfo_panel_right_oper_form_address",
                labelAlign: 'right'
            },{
                fieldLabel: '备注',
                name:'ps',
                maxLength:30,
                id:"sys_userinfo_panel_right_oper_form_ps",
                labelAlign: 'right'
            },{
                fieldLabel: 'ID',
                name:'id',
                id:"sys_userinfo_panel_right_oper_form_id",
                labelAlign: 'right',
                xtype:'hidden'
            }],
        buttons: [{
            text: '保存',
            handler: function() {
                if(sys_userinfo_panel_right_oper_form.getForm().isValid()){
                    var myMask = new Ext.LoadMask(Ext.getBody(), {
                        msg: '正在执行，请稍后...',
                        removeMask: true
                    });
                    myMask.show();
                    sys_userinfo_panel_right_oper_form.getForm().submit({
                        success : function(form, data) {
                            if(data.result.success){
                                myMask.hide();
								window.location.reload();
                            }else{
                                Ext.Msg.alert('提示', data.result.msg);
                                myMask.hide();
                            }
                        },
                        failure : function(form, data) {
                            Ext.Msg.alert('提示', data.result.msg);
                            myMask.hide();
                        }
                    });
                }
            }
        }]
    });

	Ext.create('Ext.container.Viewport', {
			border: false,
			layout: 'border',
			items:[sys_userinfo_panel_right_oper_form]
	});

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
                    var temp = Ext.getCmp('sys_userinfo_panel_right_oper_form_' + h);
                    if(h == 'roleIds'){
                        defaultOrgId = data['orgId'];
                        defaultRoleIds = data['roleIds'];
                        defaultRoleNames = data['roleNames'];
                    }else if(h == 'orgId'){
                        defaultOrgName = data['orgName'];
                    }else if(temp != null){
                        temp.setValue(data[h]);
                    }
                };
                sys_userinfo_panel_right_oper_form_systemOrgId.setDefaultValue(defaultOrgId,defaultOrgName);
            }else{
                Ext.Msg.alert('提示',result.msg);
            }
        });
    }

});
