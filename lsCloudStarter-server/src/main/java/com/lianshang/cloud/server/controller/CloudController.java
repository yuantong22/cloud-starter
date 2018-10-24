package com.lianshang.cloud.server.controller;

import com.lianshang.cloud.server.beans.BaseRequest;
import com.lianshang.cloud.server.config.ServerStarterConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/lsCloud")
@ConditionalOnMissingBean(CloudController.class)
public class CloudController {

  /**
   * 目标bean
   * @param baseRequest
   * @return
   */
  @ResponseBody
  @RequestMapping("/execute")
  public Object execute(@RequestBody BaseRequest baseRequest){
    if(null == baseRequest ||
        StringUtils.isEmpty (baseRequest.getMethodName ())
        || StringUtils.isEmpty (baseRequest.getInterfaceName ())) {
      return null;
    }else{
     Object target = ServerStarterConfig
         .execute (baseRequest.getInterfaceName (), baseRequest.getMethodName (), baseRequest.getParams ());
     return target;
    }
  }
}
