# Elastic-Bg SpringCloud版

### Java前后端分离权限基础框架

[ GitHub地址 ](https://github.com/FaceGhost/Elastic-Bg/tree/2.0.0 )

[ Gitee地址 ](https://gitee.com/faceghost/Elastic-Bg/tree/2.0.0 )

[ 演示地址 ](http://elastic-bg.faceghost.com/elastic-bg-client )

- 账户名：super_admin

- 密码：bg2019

##### PS:演示系统为1.0.0版（2.0.0无演示系统），且为只读模式，同一账号最多允许20人同时访问（按照FIFO策略剔除）



#### 本机快速运行
[ 点击下载并创建数据库(elasticbg)，执行Sql ](https://github.com/FaceGhost/Elastic-Bg/tree/2.0.0/elastic-bg-server/src/main/resources/sql )

[ 点击下载并运行eureka(java -jar elastic-bg-eureka.jar) ](https://github.com/FaceGhost/Elastic-Bg/tree/2.0.0/demo/elastic-bg-eureka.jar )

[ 点击下载并运行server(java -jar elastic-bg-server.jar) ](https://github.com/FaceGhost/Elastic-Bg/tree/2.0.0/demo/elastic-bg-server.jar )

[ 点击下载并运行client(java -jar elastic-bg-client.jar) ](https://github.com/FaceGhost/Elastic-Bg/tree/2.0.0/demo/elastic-bg-client.jar )


##### 数据库默认localhost,用户名:root,密码：root,eureka端口:7000,server端口:8000,client端口:9000,访问地址:https://localhost:9000


#### 相关文章

[ IDEA本地运行Elastic-Bg框架 ](http://faceghost.com/article/478001)

##### 通用前后端分离权限管理系统,快速部署,应用于OA系统,客户关系管理系统，销售系统，公司内部系统等开发

#### V 2.0.0
##### 相关主要依赖版本
- JDK1.8
- Spring Boot 2.2
- spring-cloud Hoxton.RELEASE
- shiro 1.4
- Extjs 4.2
- Mysql 5.7

#### V 1.0.0
##### 相关主要依赖版本
- JDK1.8
- Spring Boot 2.1
- shiro 1.4
- Grpc
- Extjs 4.2
- Mysql 5.7
