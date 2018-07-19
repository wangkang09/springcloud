#### 将 config-server 加入总线
可以直接对 config-server 发送 /actuator/bus-refresh 请求，这样可以通知所有节点，刷新配置，而不是随机选一个节点地址发送请求，这样也降低了正常业务节点的压力  

#### /bus-refresh?destination=applicationName:port  
* appliationName 和 port 都是具体的值时：表示只让这一个节点刷新配置
* port 是 * 时：表示 applicationName 这个项目的所有节点刷新配置
* 都为 * 时：表示对所有节点刷新配置

#### kafka 消息总线配置 ####
1. 启动 zookeeper
2. 启动 kafka
3. 把 bus-amqp 的依赖改成 bus-kafka
4. 将 rabbitmq 的配置注释掉

---
#### 遗留问题 ####
1. /bus-refresh?destination=applicationName:port 并不能正常使用，总是会请求所有节点
2. 不管是 /bus-refresh 还是/refresh,发现只是让所有服务 down 并重新 up，网上也有说这事，但说高版本已经出来好啦，但是我还是这样


