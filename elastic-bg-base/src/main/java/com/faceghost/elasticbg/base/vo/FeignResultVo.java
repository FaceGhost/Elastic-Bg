package com.faceghost.elasticbg.base.vo;


import com.faceghost.elasticbg.base.statics.ErrorMsgConst;
import lombok.Data;

import java.io.Serializable;

@Data
public class FeignResultVo implements Serializable{

    private Boolean success = Boolean.FALSE;
    private String  data = "" ;//成功返回，数据
    private String  msg = "" ;//返回失败，原因

    private FeignResultVo() {
    }

    /**
     * init Err
     * @return
     */
    public static FeignResultVo  initErr(){
        return new FeignResultVo(Boolean.FALSE,"", ErrorMsgConst.errSys);
    }
    public static FeignResultVo  initErr(String msg){
        return new FeignResultVo(Boolean.FALSE,"",msg);
    }

    /**
     * init suc
     * @return
     */
    public static FeignResultVo  initSuc(String data){
        return new FeignResultVo(Boolean.TRUE,data,"");
    }


    public FeignResultVo(Boolean success, String data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }
}
