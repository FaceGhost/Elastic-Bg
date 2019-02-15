Ext.onReady(function(){

    var changePwdWin_form = Ext.create('Ext.form.FormPanel',{
        labelWidth:80,
        closable:false,
        frame: true,
        url: base + 'systemUser/changePwd',
        style:'padding-top:50px;',
        buttonAlign: 'center',
        defaultType: 'textfield',
        defaults: {
            anchor: '80%',
            labelAlign: "right",
            allowBlank: false
        },
        items: [{
            name: 'oldPwd',
            fieldLabel:'旧密码',
            inputType: 'password',
            allowBlank:false
        },{
            name: 'pwd',
            fieldLabel:'新密码',
            allowBlank:false,
            regex : /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_\-]{6,15}$/,
            regexText:'6到15位同时包含字母、数字',
            inputType: 'password'
        },{
            name: 'newPwd',
            fieldLabel:'确认密码',
            allowBlank:false,
            regex : /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_\-]{6,15}$/,
            regexText:'6到15位同时包含字母、数字',
            inputType: 'password'
        }],
        buttons: [{
            text: '保存',
            handler: function() {
                if(changePwdWin_form.getForm().isValid()){
                    var myMask = new Ext.LoadMask(Ext.getBody(), {
                        msg: '正在执行，请稍后...',
                        removeMask: true
                    });
                    myMask.show();
                    changePwdWin_form.getForm().submit({
                        success : function(form, data) {
                            if(data.result.success){
                                myMask.hide();
                                window.top.location.href = base + 'logout';
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
        },{
            text: '取消',
            handler: function() {
                changePwdWin.hide();
            }
        }]
    });

    var changePwdWin = Ext.create("Ext.window.Window", {
        id:"ext_changePwdWin",
        title:"修改密码",
        width:350,
        height:250,
        plain: true,
        closable: false,	//是否可关闭
        resizable: false,	//是否可调整大小
        frame: true,
        layout: 'fit',
        closeAction: 'hide',
        border: false,
        modal: true,
        draggable:false,	//是否可拖拉
        autoShow:false,		//默认是否显示
        closeAction:'hide',//点击右上角的关闭是，默认执行的“destroy”，改为“hide”,当执行destory时，是无法再显示的。
        items:changePwdWin_form
    });
    Ext.get('changePwd').on('click',function(){
        changePwdWin.show();
    })
})
