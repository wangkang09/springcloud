#### 将config-server加入总线
可以直接对config-server发送/actuator/bus-refresh请求，这样可以通知所有节点，刷新配置，而不是随机选一个节点地址发送请求，这样也降低了正常业务节点的压力  

#### /bus-refresh?destination=applicationName:port  
* appliationName和port都是具体的值时：表示只让这一个节点刷新配置
* port是*时：表示applicationName这个项目的所有节点刷新配置
* 都为*时：表示对所有节点刷新配置

#### kafka消息总线配置 ####
1. 启动zookeeper
2. 启动kafka
3. 把bus-amqp的依赖改成 bus-kafka
4. 将rabbitmq的配置注释掉

---
#### 遗留问题 ####
1. /bus-refresh?destination=applicationName:port并不能正常使用，总是会请求所有节点
2. 不管是/bus-refresh还是/refresh,发现只是让所有服务down并重新up，网上也有说这事，但说高版本已经出来好啦，但是我还是这样


