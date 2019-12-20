package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemOrgVo;
import com.faceghost.elasticbg.cons.AppCons;
import com.faceghost.elasticbg.service.SystemOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SystemOrgServiceController {

	@Autowired
	private SystemOrgService systemOrgService;

	/**
	 * 组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemOrgTreeVoListBean",method = RequestMethod.POST)
	public List<ExtjsTreeVo> getSystemOrgTreeVoListBean() throws Exception{
		List<ExtjsTreeVo>  R =   systemOrgService.getSystemOrgTreeVoListBean();
		if(R != null && !R.isEmpty()){
			for(ExtjsTreeVo bean : R){
				if(ValidateUtil.validateBlank(bean.getIcon())){
					bean.setIcon(AppCons.EXT_ICON_PREFIX + AppCons.EXT_ICON_DEFAULT);
				}else{
					bean.setIcon(AppCons.EXT_ICON_PREFIX + bean.getIcon());
				}
				if("1".equals(bean.getIsAutoExpand())){
					bean.setExpanded(true);
				}else{
					bean.setExpanded(false);
				}
			}
		}
		return R;
	}

	
	/**
	 * 组织管理分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemOrgPageVo",method = RequestMethod.POST)
	public PageVo getSystemOrgPageVo(@RequestBody SystemOrgVo searchvo) throws Exception{
		return systemOrgService.getSystemOrgPageVo(searchvo);
	}


	/**
	 * 新增或修改-上级组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemOrgTreeVoForNotLow",method = RequestMethod.POST)
	public List<ExtjsTreeVo> getSystemOrgTreeVoForNotLow() throws Exception{
		List<ExtjsTreeVo>  R =    systemOrgService.getSystemOrgTreeVoForNotLow();

		if(R != null && !R.isEmpty()){
			for(ExtjsTreeVo bean : R){
				if(ValidateUtil.validateBlank(bean.getIcon())){
					bean.setIcon(AppCons.EXT_ICON_PREFIX + AppCons.EXT_ICON_DEFAULT);
				}else{
					bean.setIcon(AppCons.EXT_ICON_PREFIX + bean.getIcon());
				}
				if("1".equals(bean.getIsAutoExpand())){
					bean.setExpanded(true);
				}else{
					bean.setExpanded(false);
				}
			}
		}
		return R;

	}


	/**
	 * 新增,更新组织
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemOrg",method = RequestMethod.POST)
	public SystemOrg execAddSystemOrg(@RequestBody SystemOrg bean) throws Exception{
		return systemOrgService.execAddSystemOrg(bean);
	}
	

	/**
	 * 修改组织 获取原数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getPreExecAddSystemOrgData",method = RequestMethod.POST)
	public SystemOrgVo getPreExecAddSystemOrgData(@RequestParam("id") Integer id) throws Exception{
		return systemOrgService.getPreExecAddSystemOrgData(id);
	}

}
