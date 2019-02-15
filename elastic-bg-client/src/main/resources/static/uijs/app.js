/**
 * 定义ExtJS 遮罩
 * @param env
 * @returns {Ext.LoadMask}
 */
function appMask(env){
    if(env == null){
        env = Ext.getBody();
    }
    return new Ext.LoadMask(env, {
        msg: '正在执行，请稍后...',
        removeMask: true
    });
};

/**
 * 封装ExtJS Ajax
 * @param env 遮罩层，传null在当前body弹出
 * @param url 地址
 * @param param 参数
 * @param isAsync 是否同步
 * @param isAlert 执行前是否弹出确认框
 * @param alertMsg 确认框内容
 * @param _this this
 * @param callback 执行后回调函数
 */
function appDoAjaxRequest(env,url,param,isAsync,isAlert,alertMsg,_this,callback){
    //执行前是否提示
    if(isAlert){
        Ext.Msg.confirm("提示", alertMsg, function(btn) {
            if (btn == "yes") {
                var mask = appMask(env);
                mask.show();
                Ext.Ajax.request({
                    url : url,
                    params : param,
                    async : isAsync,
                    scope : _this,
                    timeout : 10000000000,
                    success : function(response) {
                        mask.hide();
                        callback(response, _this, param);
                    },
                    failure : function() {
                        mask.hide();
                        Ext.MessageBox.alert("提示", "系统繁忙,请稍后重试！");
                    }
                });
                mask.destroy();
            }
        }, this);
    }else{
        var mask = appMask(env);
        mask.show();
        Ext.Ajax.request({
            url : url,
            params : param,
            async : isAsync,
            scope : _this,
            timeout : 10000000000,
            success : function(response) {
                mask.hide();
                callback(response, _this, param);
            },
            failure : function() {
                mask.hide();
                Ext.MessageBox.alert("提示", "系统繁忙,请稍后重试！");
            }
        });
        mask.destroy();
    }

};




