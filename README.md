# JrRPC：一款简单易用的高性能RPC框架
## 项目介绍
一款基于 Java + Etcd + Vert.x + 类 Dubbo 协议。开发者可以引入 Spring Boot Starter，通过注解和配置文件快速使用框架；还支持通过 SPI 机制动态扩展序列化器、负载均衡器、重试和容错策略等。同时也是学习 RPC 的良好示例。
以下是 JRPC 的调用流程：
![image](https://github.com/user-attachments/assets/9cf4a2c7-7325-4ac7-bb2e-8d1711d446c7)

## 功能
### 目录结构
```
JrRPC 框架
├─jrpc-core	--rpc核心实现类
├─jrpc-spring-starter	--组件的spring-starter接入类
├─springboot-consumer	--[示例]消费者使用注解调用服务
└─springboot-provider	--[示例]提供者使用注解提供服务
```
### 核心模块结构
```
├── common                         -> 通用模块
├── config                         -> 项目配置（服务端、客户端属性配置）
├── constants                      -> 项目常量
├── exception                      -> 全局异常
├── fault                          -> 重试和容错方法
├── loadbalancer                   -> 负载均衡方法
├── protocol                       -> 自定义协议
├── proxy                          -> 动态代理
├── registry                       -> 注册中心
├── serialize                      -> 序列化与反序列化
├── server                         -> 服务端相关类（请求处理、启动加载）
├── spi                            -> SPI自定义加载类
└── utils                          -> 项目工具包
```
### 功能
- 基于 Vert.x 实现长连接通信，包括心跳检测、解决粘包半包等
- 基于 Etcd 实现分布式服务注册与发现
- 通过 JDK 动态代理为接口类生成返回模拟数据的 Mock 服务对象，便于开发者测试
- 实现了轮询、随机、一致性 Hash 等负载均衡算法，并通过 SPI 机制支持开发者自行扩展
- 基于 Guava Retrying 实现了包括 fixedWait 等多种重试策略，并通过 SPI 机制支持开发者自行扩展
- 设计实现了 FailOver、FailBack、FailSafe、FailFast 等多种重试策略，并通过 SPI 机制支持开发者自行扩展
- 支持Json、hessian、kryo 的序列化方式，并通过 SPI 机制支持开发者自行扩展
- 基于注解驱动的 Spring Boot Starter，可通过注解快速注册服务、调用服务
- 自定义网络传输协议
## 快速开始
### 环境准备
- JDK8 或以上
- Etcd 实例
### 启动示例
#### 方式一：使用本项目的测试用例
1. 将项目克隆到本地
2. IDEA 打开项目
3. 安装 Etcd[https://github.com/etcd-io/etcd/releases] 和 etcdkeeper[https://github.com/evildecay/etcdkeeper/] 并分别启动两个服务
4. 先启动 provider，再启动 consumer
5. 打开浏览器输入[http://127.0.0.1:8081/etcdkeeper/] ,可以查看注册的服务
   ![image](https://github.com/user-attachments/assets/753a4dbe-79e1-4817-964b-7e04ac9d97ef)
6. provider 和 consumer 的控制台都能看到 "zjr" 输出
#### 方式二：将该框架运用到自己项目中
1. 将项目克隆到本地
2. `mvn clean install` 编译安装 `jrrpc-core` 到本地仓库
3. 在项目中引入依赖
   ```
        <groupId>com.zjr</groupId>
        <artifactId>jr-rpc</artifactId>
        <version>1.0-SNAPSHOT</version>
   ```
4. 使用注解 `@RpcService` 注册一个服务接口，并在启动类上使用 `@EnableRpc` 注解，可在`springboot-provider`查看使用示例
5. 服务端通过`application.properties`自定义功能，可在 `config.RpcConfig `中查看全部配置
    ```
    rpc.name=jrrpc
    rpc.version=2.0
    rpc.mock=false
    rpc.serializer=jdk
    rpc.serverPort=8080
    rpc.serverHost=localhost
    rpc.loadBalancer=consistentHash
     ```
6. 使用注解`@RpcReference`自动注入服务端暴露的接口服务，可在`springboot-consumer`查看使用示例
7. 客户端同样通过`application.properties`自定义功能，注意序列化器需要和服务端一致。
8. 启动项目，先启动服务提供者，再启动服务消费者。
