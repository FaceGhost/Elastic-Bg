package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.controller.BaseController;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.vo.IconVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class IconController extends BaseController {

    /**
     * icon-分页显示
     * @return
     */
    @RequiresPermissions("system:icon:view")
    @RequestMapping(value="getIcons",method= RequestMethod.POST)
    @ResponseBody
    public Object getSystemOrgPageVo(){
        PageVo pageList = new PageVo();
        try {
            String path = "static" + File.separator + "js" + File.separator + "extjs" + File.separator +"icon";
            File file = ResourceUtils.getFile("classpath:" + path);
            if(file.exists()){
                File[] files = file.listFiles();
                if(files != null){
                    List<IconVo> iconList = new ArrayList<IconVo>();
                    for(File childFile:files){
                        IconVo vo = new IconVo();
                        vo.setName(childFile.getName());
                        iconList.add(vo);
                        vo = null;
                    }
                    pageList.setTotal((long)files.length);
                    pageList.setList(iconList);
                }
            }

        } catch (Exception e) {
            log.error(String.format("icon-分页显示,异常:%s", ExceptionUtil.getExDetail(e)));
            e.printStackTrace();
        }
        return pageList;
    }

}
