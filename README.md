# open-platform
## 我们要做什么
open-platform是由**share猿社区**发起的一个开放平台项目，社区旨在构建一个**简单、灵活、高可用**的企业级开放平台。社区真诚欢迎每一位爱好技术的极客朋友参与进来，和我们一起构建一个高可用的平台。
## 架构设计
### v1.0架构设计

![open-platform架构设计_v1.0](https://github.com/lywlefan/open-platform/blob/master/docs/designs/open-platform%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1_v1.0.png)

open-platform v1.0的架构设计是一套微服务架构，整体架构包含：基础服务、内部服务、聚合服务、业务服务、授权服务、技术中台、api网关、前台、后台等。

## 平台介绍

### 项目结构

```tree
open-platform
|──open-platform-api:开发平台api
|  |──open-platform-back-api：开放平台后台相关api
|  |──open-platform-front-api：开放平台前端api
|──open-platform-base:开放平台基础依赖
|  |──open-platform-atom：开放平台原子服务
|  |──open-platform-oauth：开放平台oauth认证
|──open-platform-gateway:开放平台网关层
|  |──open-platform-gateway-api：api网关层
|  |──open-platform-gateway-management：后台网关层
|──open-platform-parent:开放平台父pom依赖
|──open-platform-starters:开放平台stater（自己封装）
|  |──open-platform-limit：限流starter
|──open-platform-view：开发平台前端（前端小伙伴注意了！）
|  |──open-platform-view-management：开发平台后台页面
```

## 平台规划

## 文档

## 贡献

---
**↓↓↓↓↓↓↓↓扫描以下公众号关注share猿社区↓↓↓↓↓↓↓↓**

![](http://upload-images.jianshu.io/upload_images/3084894-e6e9a10cf3e08bba?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

更多内容请在**简书、掘金、CSDN**搜索**Share猿**找到社区哦！！！