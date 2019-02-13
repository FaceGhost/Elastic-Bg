//每一个列都会出现鼠标悬浮上去显示内容
/**
 * //适用于Extjs4.x
 * @class Ext.grid.GridView
 * @override Ext.grid.GridView
 * GridPanel单元格不能选中复制问题
 * 单元格数据显示不完整 ,增加title 浮动提示信息
 */
Ext.override(Ext.grid.GridPanel, {
    afterRender : Ext.Function.createSequence(Ext.grid.GridPanel.prototype.afterRender,
        function() {
            /* 默认显示提示
             if (!this.cellTip) {
             return;
             }*/

            var view = this.getView();

            this.tip = new Ext.ToolTip({
                target: view.el,
                delegate : '.x-grid-cell-inner',
                trackMouse: true,
                renderTo: Ext.getBody(),
                listeners: {
                    beforeshow: function updateTipBody(tip) {
                        //取cell的值
                        //fireFox  tip.triggerElement.textContent
                        //IE  tip.triggerElement.innerText
                        var tipText = (tip.triggerElement.innerText || tip.triggerElement.textContent);
                        if (Ext.isEmpty(tipText) || Ext.isEmpty(tipText.trim()) ) {
                            return false;
                        }

                        tip.update(tipText);
                    }
                }
            });
        })
});