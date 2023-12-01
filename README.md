# 管理平台

[API文档](http://127.0.0.1:8081/social-admin-api/doc.html#/home)
## 基础环境

jdk 1.8
mysql 5.7

redis 5.0

实体生成 运行utils/MybatisGen

## 项目目录结构

com
    └─social
        │  SocialAdminApiApplication.java
        │  
        ├─common
        │  ├─cache
        │  │      RedisCache.java
        │  │      RedisKeys.java
        │  │      
        │  ├─config
        │  │      CorsConfig.java
        │  │      MybatisPlusConfig.java
        │  │      RedisConfig.java
        │  │      SwaggerConfig.java
        │  │      
        │  ├─constant
        │  │      Constant.java
        │  │      
        │  ├─exception
        │  │      ErrorCode.java
        │  │      ServerException.java
        │  │      ServerExceptionHandler.java
        │  │      
        │  ├─handler
        │  │      FieldMetaObjectHandler.java
        │  │      
        │  ├─model
        │  │      BaseServiceImpl.java
        │  │      Query.java
        │  │      TreeNode.java
        │  │      
        │  └─result
        │          PageResult.java
        │          Result.java
        │          
        ├─controller
        │      
        ├─convert
        ├─entity
        │      
        ├─enums
        │      
        ├─mapper
        ├─query
        │      
        ├─security
        │  ├─cache
        │  │      TokenStoreCache.java
        │  │      
        │  ├─config
        │  │      PasswordConfig.java
        │  │      PermitResource.java
        │  │      SecurityConfig.java
        │  │      
        │  ├─exception
        │  │      SecurityAuthenticationEntryPoint.java
        │  │      
        │  ├─filter
        │  │      AuthenticationTokenFilter.java
        │  │      MyRequestWrapper.java
        │  │      
        │  ├─service
        │  │      UserDetailsServiceImpl.java
        │  │      
        │  ├─user
        │  │      ManagerDetail.java
        │  │      SecurityUser.java
        │  │      
        │  └─utils
        │          TokenUtils.java
        │          
        ├─service
        │  │  
        │  └─impl
        ├─utils
        │      AssertUtils.java
        │      DateUtils.java
        │      ExcelUtils.java
        │      HttpContextUtils.java
        │      MybatisGen.java
        │      OssClientConfig.java
        │      TreeUtils.java
        │      
        └─vo

## 项目运行

本工程运行环境使用 maven profile 进行管理切换 

## 项目打包

idea 窗口右侧边栏菜单 maven -> Profiles 中选择相应环境后，在 root 节点下 clean + package 