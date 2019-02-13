/*!
* 在原有的Date组件上添加【清除】按钮
*/
Ext.define('Js.ux.DatePicker', {
    override: 'Ext.picker.Date',
    renderTpl: [
        '<div id="{id}-innerEl" role="grid">',
            '<div role="presentation" class="{baseCls}-header">',
                 // the href attribute is required for the :hover selector to work in IE6/7/quirks
                '<a id="{id}-prevEl" class="{baseCls}-prev {baseCls}-arrow" href="#" role="button" title="{prevText}" hidefocus="on" ></a>',
                '<div class="{baseCls}-month" id="{id}-middleBtnEl">{%this.renderMonthBtn(values, out)%}</div>',
                 // the href attribute is required for the :hover selector to work in IE6/7/quirks
                '<a id="{id}-nextEl" class="{baseCls}-next {baseCls}-arrow" href="#" role="button" title="{nextText}" hidefocus="on" ></a>',
            '</div>',
            '<table id="{id}-eventEl" class="{baseCls}-inner" cellspacing="0" role="grid">',
                '<thead role="presentation"><tr role="row">',
                    '<tpl for="dayNames">',
                        '<th role="columnheader" class="{parent.baseCls}-column-header" title="{.}">',
                            '<div class="{parent.baseCls}-column-header-inner">{.:this.firstInitial}</div>',
                        '</th>',
                    '</tpl>',
                '</tr></thead>',
                '<tbody role="presentation"><tr role="row">',
                    '<tpl for="days">',
                        '{#:this.isEndOfWeek}',
                        '<td role="gridcell" id="{[Ext.id()]}">',
                            // the href attribute is required for the :hover selector to work in IE6/7/quirks
                            '<a role="presentation" hidefocus="on" class="{parent.baseCls}-date" href="#"></a>',
                        '</td>',
                    '</tpl>',
                '</tr></tbody>',
            '</table>',
            '<tpl if="showToday">',
                '<div id="{id}-footerEl" role="presentation" class="{baseCls}-footer">',
                    '{%this.renderTodayBtn(values, out)%}',
                    '{%this.renderClearBtn(values, out)%}',
                    '</div>',
            '</tpl>',
        '</div>',
        {
            firstInitial: function (value) {
                return Ext.picker.Date.prototype.getDayInitial(value);
            },
            isEndOfWeek: function (value) {
                // convert from 1 based index to 0 based
                // by decrementing value once.
                value--;
                var end = value % 7 === 0 && value !== 0;
                return end ? '</tr><tr role="row">' : '';
            },
            renderTodayBtn: function (values, out) {
                Ext.DomHelper.generateMarkup(values.$comp.todayBtn.getRenderTree(), out);
            },
            renderMonthBtn: function (values, out) {
                Ext.DomHelper.generateMarkup(values.$comp.monthBtn.getRenderTree(), out);
            },
            renderClearBtn: function (values, out) { // 清除按钮
                Ext.DomHelper.generateMarkup(values.$comp.clearBtn.getRenderTree(), out);
            }
        }
    ],
 
    beforeRender: function () {
        var me = this;
        me.callParent();
        // 创建按钮
        me.clearBtn = new Ext.button.Button({
            ownerCt: me,
            ownerLayout: me.getComponentLayout(),
            text: '清除',
            tooltip: '清除日期',
            tooltipType: 'title',
            handler: me.selectClear,
            scope: me
        });
    },
 
    // Do the job of a container layout at this point even though we are not a Container.
    // TODO: Refactor as a Container.
    finishRenderChildren: function () {
        var me = this;
        me.callParent();
        me.clearBtn.finishRender();
    },
 
    /**
    * Sets the value of the date field
    * @param {Date} value The date to set
    * @return {Ext.picker.Date} this
    */
    setValue: function (value) {
        if (value == '') {
            this.value = value;
        } else {
            this.value = Ext.Date.clearTime(value, true);
            return this.update(this.value);
        }
    },
 
    /**
    * 清除按钮点击
    */
    selectClear: function () {
        var me = this,
            btn = me.clearBtn,
            handler = me.handler;
 
        if (btn && !btn.disabled) {
            me.setValue('');
            me.fireEvent('select', me, me.value);
            if (handler) {
                handler.call(me.scope || me, me, me.value);
            }
            me.onSelect();
        }
        return me;
    },
 
    beforeDestroy: function () {
        var me = this;
        if (me.rendered) {
            Ext.destroy(
                me.clearBtn
            );
        }
        me.callParent();
    },
});