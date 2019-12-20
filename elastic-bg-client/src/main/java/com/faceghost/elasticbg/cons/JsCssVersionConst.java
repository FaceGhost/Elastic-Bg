package com.faceghost.elasticbg.cons;

import com.faceghost.elasticbg.base.utils.DateStyleEnum;
import com.faceghost.elasticbg.base.utils.DateUtil;
import com.faceghost.elasticbg.conf.PropConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JsCssVersionConst {

    private static PropConf propConf;

    @Autowired
    public  void setPropConf(PropConf propConf) {
        this.propConf = propConf;
    }

    /**
     * 获取js版本号
     * @return
     */
    public static String getJsVersion(){
        if(propConf.getEnvJsCssVersionEnabled()){
            return propConf.getEnvJsVersion();
        }else{
            return DateUtil.dateFormat(new Date(), DateStyleEnum.yyyymmddhhmmss);
        }
    }

    /**
     * 获取css版本号
     * @return
     */
    public static String getCssVersion(){
        if(propConf.getEnvJsCssVersionEnabled()){
            return propConf.getEnvCssVersion();
        }else{
            return DateUtil.dateFormat(new Date(), DateStyleEnum.yyyymmddhhmmss);
        }
    }
}
