#### 消息驱动测试项目
主要测试输入输出通道是如何被监听的

---
#### 核心概念 ####
* **绑定器**  
　　Spring cloud stream 构建的应用程序与消息中间件之间是通过绑定器 Binder 相关联的，绑定器对于应用程序而言起到了隔离作用，使得不同消息中间件的实现细节对应用程序来说是透明的。  
　　绑定器通过向应用程序暴露统一的 Channel 通道，使得应用程序不需要再考虑各种不同的消息中间件的实现。当需要升级消息中间件，或更换其他消息中间件产品时，只需要更换对应的 Binder 而不需要修改任何的应用逻辑。  
　　Spring cloud stream 为主流的消息中间件产品 RabbitMQ 和 Kafka 提供了默认的 Binder 实现。

* **发布-订阅模式**  
　　Spring cloud stream 中的消息通信方式遵循了发布-订阅模式，当一条消息被投递到消息中间件后，他会通过共享的 Topic 主题进行广播，消息消费者在订阅的主题中收到它并触发自身的业务逻辑处理。  
　　通过发布-订阅模式将消息生产者和消费者做了很好的**解耦**，基于相同主题的消费者可以轻松地进行扩展。  

* **消费组**  
　　保证每个消息只被组内的一个实例消费。  
　　如果同一个主题上的应用启动了多个实例，可以通过 spring.cloud.stream.bindings.<channelName 如：input>.group 属性为应用指定一个组名，这样这个应用的多个实例在接收到消息的时候，只会有一个成员真正受到消息。  
　　默认情况下，会分配一个独立的匿名消费者，所以同一个应用的多个实例的组是不一样的，所以都能受到消息。

* **消息分区**  
　　保证拥有共同特征的消息数据始终有同一个消费者实例接收和处理。  　

#### @SteamListener 和 @ServiceActivator 注解的区别 ####
　　@SteamListener 内置了一系列的消息转换功能，可以直接实现 JSON 对象和 Java 类的转换。  
　　当 JSON 转 Java 类时，只需要在配置文件中增加 spring.cloud.stream.bindings.<channelName 如：input>.content-type=application/json 属性。　　

#### 相关配置文件属性 ####
* **消费组**  
spirng.cloud.stream.bindings.<channelName>.group=Group-A  
spring.cloud.bindings.<channelName>.destination=destination-A //设置通道目标为**destination-A主题**

* **消费者消息分区** 
spring.cloud.bindings.<channelName>.destination=wang 
spring.cloud.stream.bindings.<channelName>.consumer.partitioned=true //开启消费者分区功能  
spring.cloud.stream.instanceCount=2 //指定当前消费者的总实例数量  
spring.cloud.stream.instanceCount=0 //指定当前实例的索引号 
 
* **生成者消息分区**  
spring.cloud.bindings.<channelName>.destination=wang 
spring.cloud.stream.bindings.<channelName>.producer.partitionKeyExpression=payload //指定分区键的表达式  
spring.cloud.stream.bindings.<channelName>.producer.partitionCount=2  //指定分区数量

* **多绑定器配置**  
spring.cloud.stream.defaultBinder=rabbit //指定默认绑定器是rabbit  
spirng.cloud.stream.bindings.<channelName>.binder=kafka //指定特定的通道为kafka

* **设置不同环境的绑定器**  
spirng.cloud.stream.bindings.channel1.binder=rabbit1  
spirng.cloud.stream.bindings.channel2.binder=rabbit2  

　　spring.cloud.stream.binders.rabbit1.type=rabbit  
　　spring.cloud.stream.binders.rabbit1.environment.spring.rabbitmq.host=192.168.0.101  
　　spring.cloud.stream.binders.rabbit1.environment.spring.rabbitmq.port=5672  
　　spring.cloud.stream.binders.rabbit1.environment.spring.rabbitmq.username=wang  
　　spring.cloud.stream.binders.rabbit1.environment.spring.rabbitmq.passwore=wang  

　　spring.cloud.stream.binders.rabbit2.type=rabbit  
　　spring.cloud.stream.binders.rabbit2.environment.spring.rabbitmq.host=192.168.0.102  
　　spring.cloud.stream.binders.rabbit2.environment.spring.rabbitmq.port=5672  
　　spring.cloud.stream.binders.rabbit2.environment.spring.rabbitmq.username=wang  
　　spring.cloud.stream.binders.rabbit2.environment.spring.rabbitmq.passwore=wang  

　　**注：当绑定器使用了显示别名的配置方式时（binder=rabbit1），会自动禁用默认的绑定器配置，需要自行配置**  
　　　　**具体配置信息，可查阅官方文档或《Spring Cloud 微服务实战》P376页** 

#### 注意事项 ####
1. 当一个通道被监听后，此通道就不能被其他设置转发此通道
2. 同一个 @EnableBinding 注解中，多个类的通道标识符不能相同，否则包 bean 已经定义错误
