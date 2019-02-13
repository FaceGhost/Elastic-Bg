Ext.define('TreeComboBox', {
    extend: 'Ext.form.field.ComboBox',
    url: '',
    tree: {},
    rootVisible:true,
    textProperty: 'text',
    valueProperty: '',

    initComponent: function () {
        Ext.apply(this, {
            editable: false,
            queryMode: 'local',
            select: Ext.emptyFn
        });

        this.displayField = this.displayField || 'text',
        this.treeid = Ext.String.format('tree-combobox-{0}', Ext.id());
        this.tpl = Ext.String.format('<div id="{0}"></div>', this.treeid);

        if (this.url) {
            var me = this;
            var store = Ext.create('Ext.data.TreeStore', {
                root: { expanded: true },
                proxy: { type: 'ajax', url: this.url }
            });
            this.tree = Ext.create('Ext.tree.Panel', {
                rootVisible: this.rootVisible,
                autoScroll: true,
                height: 200,
                store: store
            });
            this.tree.on('itemclick', function (view, record) {
                me.setValue(record);
                me.collapse();
            });
            me.on('expand', function () {
                if (!this.tree.rendered) {
                    this.tree.render(this.treeid);
                }
            });
        }
        this.callParent(arguments);
    }
});