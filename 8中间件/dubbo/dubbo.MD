# dubbo学习之路

官网：https://dubbo.apache.org/zh/overview/what/architecture/



## 1简介

现在的分布式越来越普及，我们需要将多个服务都分开部署打包，且基于网络建立起相关的联系。



## 2RPC

在如今的微服务之间，服务需要进行互相调用，于是我们基于socket连接进行传输，例如A服务调取B服务的某个方法，那么就需要A服务搭建连接并将请求参数序列化传输给B服务的**小助手**进行通信。

## 3架构

![architecture](C:\Users\一号线\Desktop\architecture.png)

上图描述为容器初始化启动->然后服务提供者进行注册到注册中心（zookeeper等）再然后提交给消费者 ，消费者通过注册中心得到对应的服务提供者。然后将提供者和消费者一起进行监控。



## 4dubbo监控页面

本质上是一个springboot项目

github地址：https://github.com/apache/dubbo-admin/blob/develop/README_ZH.md





