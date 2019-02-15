Ext.onReady(function() {


    Ext.get('userInfoSetting').on('click',function(){
        var id = 'tab_id_userInfoSetting';
        var url = 'system/system_userinfo';
        var title = '个人设置';
        if(!Ext.isEmpty(url)) {
            var p = Ext.getCmp(id);
            if(typeof(p) != 'undefined') {
                layout_tabs.setActiveTab(p);
            } else {
                var cp = Ext.create('Ext.Component', {
                    title: '<div align="center">' + title + '</div>',
                    id: id,
                    closable: true,
                    layout: "fit",
                    html : '<iframe id="'+id+'_iframe" src="'+url+'" name="'+title+'" width="100%" height="100%" frameborder="0" ></iframe>'
                });


                layout_tabs.add(cp);
                layout_tabs.setActiveTab(cp);

            }
        }
    })

})