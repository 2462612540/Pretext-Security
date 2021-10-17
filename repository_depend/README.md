# 工程简介

#项目代码层结构

1.工程启动类(ApplicationServer.java)置于com.springboot.build包下

2.实体类(domain)置于com.springboot.domain

3.数据访问层(Dao)置于com.springboot.repository

4.数据服务层(Service)置于com,springboot.service,数据服务的实现接口(serviceImpl)至于com.springboot.service.impl

5.前端控制器(Controller)置于com.springboot.controller

6.工具类(utils)置于com.springboot.utils

7.常量接口类(constant)置于com.springboot.constant

8.配置信息类(config)置于com.springboot.config

9.数据传输类(vo)置于com.springboot.vo

------------------------------------------------------------------------------------------------------------------------

# 资源目录结构

根目录:src/main/resources

1.配置文件(.properties/.json等)置于config文件夹下

2.国际化(i18n))置于i18n文件夹下

3.spring.xml置于META-INF/spring文件夹下

4.页面以及js/css/image等置于static文件夹下的各自文件下

------------------------------------------------------------------------------------------------------------------------
.
│  mvnw
│  mvnw.cmd
│  pom.xml
│  README.md
│  
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─bajins
    │  │          └─api
    │  │              │  BajinsApiApplication.java
    │  │              │      
    │  │              ├─config
    │  │              │  │  QuartzJob.java
    │  │              │  │  Swagger2.java
    │  │              │  │  TaskExecutorConfig.java
    │  │              │  │  
    │  │              │  └─websocket
    │  │              │          WebSocketConfig.java
    │  │              │          WebSocketHandler.java
    │  │              │          WebSocketInterceptor.java │          
    │  │              ├─constants
    │  │              │      WeChatConstants.java
    │  │              │      
    │  │              ├─mapper
    │  │              │      WechatLoginLogMapper.java
    │  │              │      WechatMsgMapper.java
    │  │              │      WechatUserOpenidMapper.java
    │  │              │      
    │  │              ├─domain
    │  │              │      WechatLoginLog.java
    │  │              │      WechatMsg.java
    │  │              │      WechatUserOpenid.java
    │  │              │      
    │  │              ├─service
    │  │              │  │  UserSignatureService.java
    │  │              │  │  WechatService.java
    │  │              │  │  WxMsgService.java
    │  │              │  │  
    │  │              │  └─impl
    │  │              │          WechatServiceImpl.java
    │  │              │          WxMsgServiceImpl.java
    │  │              │          
    │  │              ├─utils
    │  │              │  │  EmailUtil.java
    │  │              │  │  EncryptUtil.java
    │  │              │  │  StringUtil.java
    │  │              │  │          
    │  │              │  └─wechat
    │  │              │          SHA1.java
    │  │              │          WXBizMsgCrypt.java
    │  │              │          XMLParse.java
    │  │              │          
    │  │              ├─vo
    │  │              │      TemplateLibraryVO.java
    │  │              │      TemplateMessageVO.java
    │  │              │      
    │  │              └─controller
    │  │                      WechatController.java
    │  │                      WxMsgController.java
    │  │                      
    │  └─resources
    │      │  application.properties
    │      │  ehcache3.xml
    │      │  logback-spring.xml
    │      │  
    │      ├─mappers
    │      │      WechatLoginLogMapper.xml
    │      │      WechatMsgMapper.xml
    │      │      WechatUserOpenidMapper.xml
    │      │      
    │      ├─static
    │      └─templates
    └─test
        └─java
            └─com
                └─bajins
                    └─api
                            BajinsApiApplicationTests.java

------------------------------------------------------------------------------------------------------------------------                         


# 延伸阅读

