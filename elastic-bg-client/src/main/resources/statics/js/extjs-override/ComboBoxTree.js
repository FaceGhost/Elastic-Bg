/**
 *  基于Extjs4的下拉树控件
 *  version:2012-10-29
 *  author: ZHANGLEI
 *  用法:
    
    实例化store作为数据源
    var store = Ext.create('Ext.data.TreeStore',{
		proxy:{
			type:'ajax',
			url:'${webRoot}/wip/ttttQueryAction.action'
		}
	});
	
	实例化ComboBoxTree
	var ctree =Ext.create("Ext.ux.ComboBoxTree", {
        id:'c_tree',
        name:'c_tree',
        store :store,
		editable: false,
		multiCascade:false,
		multiSelect : false,
        width : 300
	});
	
	说明：
	1)注意单选时加载的store的对象串中不要有checked字段，多选时加载的store的对象串中一定要有checked字段;
	2)multiSelect属性不写默认是false，是单选;设定为true时是多选;
	3)multiCascade属性不写默认是true,代表多选时是否级联选择;设定为false时不级联选择;
	4)treeHeight属性是指下拉树层的高度，不写默认是300;
	
	常用方法:
	
	清除当前值
	ctree.clearValue();
	
	获得当前选中的提交值
	ctree.getSubmitValue();
	
	获得当前选中的显示值
	ctree.getDisplayValue();
	
	单选时获得当前选中的对应树节点的JSON值
	ctree.getEleJson();
	
	设置控件默认值
	单选默认值('树节点id值','树节点text显示值')
	ctree.setDefaultValue('4100000000021119950','北京');
	多选默认值('逗号隔开的树节点id值','逗号隔开的树节点text显示值')
	ctree.setDefaultValue('4100000000021119950,4100000000021119951','北京,上海');
	
	设置控件自动展开定位或选中
	单选展开定位('树上的节点路径')
	ctree.setPathValue('/root/4100000000021119950');
	多选展开选中
	var pathArray = [];
	pathArray[0] = '/root/4100000000021119950';
	pathArray[1] = '/root/4100000000021119951';
	ctree.setPathArray(pathArray);
	
 */
Ext.define('Ext.ux.ComboBoxTree',{
	extend : 'Ext.form.field.Picker',
	requires : ['Ext.tree.Panel'],
	alias : ['widget.comboboxtree'],
	multiSelect : false,
	multiCascade:true,
	submitValue:'',
	pathValue:'',
	pathArray:[],
	initComponent : function(){
		var self = this;
		Ext.apply(self,{
			fieldLabel : self.fieldLabel,
			labelWidth : self.labelWidth     
		});
		self.callParent();
	},
	createPicker:function(){
		var self = this;
		self.picker = Ext.create('Ext.tree.Panel',{
			height : self.treeHeight==null?300:self.treeHeight,
			autoScroll : true,
			floating : true,
			focusOnToFront : false,
			shadow : true,
			ownerCt : this.ownerCt,
			useArrows : true,
			store : self.store,
			rootVisible : false,
			viewConfig: {
				onCheckboxChange: function(e,t) {
				if (self.multiSelect) {
					var item = e.getTarget(this.getItemSelector(),this.getTargetEl()), record;
					if (item) {
						record = this.getRecord(item);
						var check = !record.get('checked');
						record.set('checked',check);
						if(self.multiCascade){
							if (check) {
								record.bubble(function(parentNode) {
									if ('Root' != parentNode.get('text')) {
										parentNode.set('checked',true);
									}
								});
								record.cascadeBy(function(node) {
									node.set('checked',true);
									node.expand(true);
								});
							} else {
								record.cascadeBy(function(node) {
									node.set('checked',false);
								});
								record.bubble(function(parentNode) {
									if ('Root' != parentNode.get('text')) {
										var flag = true;
										for (var i = 0; i < parentNode.childNodes.length; i++) {
											var child = parentNode.childNodes[i];
											if(child.get('checked')){
												flag = false;
												continue;
											}
										}
										if(flag){
											parentNode.set('checked',false);
										}
									}
								});
							}
						}
					}
					var records = self.picker.getView().getChecked(), names = [], values = [];
			    	Ext.Array.each(records, function(rec) {
			    		names.push(rec.get('text'));
			    		values.push(rec.get('id'));
			    	});
			    	self.submitValue = values.join(',');
			    	self.setValue(names.join(','));
				}
				}
			}
		});
		self.picker.on({
			itemclick: function (view,recore,item,index,e,object) {
				if (!self.multiSelect) {
					self.submitValue = recore.get('id');
					self.setValue(recore.get('text')); 
					self.eleJson = Ext.encode(recore.raw);
					self.collapse();    
				}
			}
		});
		return self.picker;
	},
	listeners:{
		expand : function(field,eOpts){
			var picker = this.getPicker();
			if(!this.multiSelect){
				if(this.pathValue != ''){
					picker.expandPath(this.pathValue,'id','/',function(bSucess,oLastNode){
						picker.getSelectionModel().select(oLastNode);   
					});
				}
			}else{
				if(this.pathArray.length > 0){
			        for(var m=0;m<this.pathArray.length;m++){
			        	picker.expandPath(this.pathArray[m],'id','/',function(bSucess,oLastNode){
			        		oLastNode.set('checked',true); 
						});
					}
				}
			}
		}
	},
	clearValue : function() {
		this.setDefaultValue('','');
	},
	getEleJson : function() {
		if(this.eleJson == undefined){
			this.eleJson = [];
		}
		return this.eleJson;
	},
	getSubmitValue : function() {
		if(this.submitValue == undefined){
			this.submitValue = '';
		}
		return this.submitValue;
	},
	getDisplayValue:function(){
		if(this.value == undefined){
			this.value = '';
		}
		return this.value;
	},
	setPathValue:function(pathValue){
		this.pathValue = pathValue;
	},
	setPathArray:function(pathArray){
		this.pathArray = pathArray;
	},
	setDefaultValue:function(submitValue,displayValue){
		this.submitValue = submitValue;
		this.setValue(displayValue);
		this.eleJson = undefined;
		this.pathArray = [];
	},
	alignPicker:function(){
		var me = this, picker, isAbove, aboveSfx = '-above';
		if (this.isExpanded){
			picker = me.getPicker();
			if (me.matchFieldWidth){picker.setWidth(me.bodyEl.getWidth());}
			if (picker.isFloating()){
				picker.alignTo(me.inputEl, "", me.pickerOffset);// ""->tl
				isAbove = picker.el.getY() < me.inputEl.getY();
				me.bodyEl[isAbove ? 'addCls' : 'removeCls'](me.openCls+aboveSfx);
				picker.el[isAbove ? 'addCls' : 'removeCls'](picker.baseCls+aboveSfx);
			}
		}
	}
});