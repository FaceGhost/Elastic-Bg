<%@ page pageEncoding="utf-8"%>
<script type="text/javascript">
 /**
  * 获取页面权限
  */
 Ext.onReady(function(){
     var iframes = parent.document.getElementsByTagName('iframe');
     if(undefined != iframes){
         for (var i = 0 ; i < iframes.length; i++) {
             if(location.href == iframes[i].src){
                 var tmp_id = iframes[i].id;
                 if(tmp_id != null){
                     var tmp_id_split = tmp_id.split('_');
                     if(tmp_id_split != null || tmp_id_split.length > 0 ){
                         Ext.Ajax.request({
                             params : {
                                 pId : tmp_id_split[0]
                             },
                             url : base + "systemPermission/getPermByPage",
                             timeout:3000000,
                             success : function(response) {
                                 var result = Ext.decode(response.responseText);
                                 if(result.success){
                                     var data = result.data;
                                     for (var i = 0; i < data.length; i++) {
                                         var vo = data[i];
                                         if (typeof(Ext.getCmp(vo.permission)) != 'undefined') {
                                             Ext.getCmp(vo.permission).setVisible(true);
                                         }
                                     }
                                 }
                             },
                             failure : function() {
                                 Ext.Msg.alert('错误', '系统繁忙,请稍后重试！');
                             }
                         });
                     }
                 }
             }
         }
     }
 })

var hideMask = function () {
    Ext.get('loading').remove();
    Ext.fly('loading-mask').animate({
        opacity:0,
        remove:true,
        callback: function(){}
    });
};
Ext.defer(hideMask, 250);
</script>