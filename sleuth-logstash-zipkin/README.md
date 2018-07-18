### Spring Cloud Sleuth ###
分布式服务跟踪，跟踪一整条调用链路，在微服务的日志信息中添加跟踪信息的功能，通过分析系统将跟踪信息收集起来，实现对分布式系统的监控和分析功能。比如：预警延迟过长的请求链路、查询请求链路的调用明细等。  

---
#### ELK 平台整合 ####
* 是一个基于日志的分析系统
* 主要由 ElasticSearch、Logstash 和 Kibana 三个开源工具组成
* ElasticSearch 是一个分布式搜索引擎，对日志进行搜索
* Logstash 实现对日志进行收集、过滤，并将其存储供以后使用
* Kibana 为 Logstash 和 ElasticSearch 提供日志分析友好的 Web 界面，可以帮助汇总、分析和搜索重要数据日志

* Seuth 与 ELK 平台整合时，只需要实现与 Logstash 完成数据对接即可，需要为 Logstash 准备 JSON 格式的日志输出。由于 Spirng Boot 默认使用 logback 来记录日志，而 Logstash 自身也对 logback 日志工具的支持，所以我们可以直接通过在 logback 的配置中增加对 Logstash 的 Appender，就能方便地将日志转换成以 JSON 的格式存储和输出了。  

---
#### Zipkin 整合 ####
* ELK 平台中的数据分析维度缺少对请求链路中各阶段时间延迟的关注
* Zipkin 实现对分布式系统做延迟监控等与时间消化相关的操作
* 可以查询某个时间段内各个用户请求的处理时间等

---
#### 整合 zipkin ####

* zipkin 服务端需要加以下依赖  
	compile('org.springframework.boot:spring-boot-starter')  
	compile group: 'io.zipkin.java', name: 'zipkin-server', version: '2.10.2'  
	compile group: 'io.zipkin.java', name: 'zipkin-autoconfigure-ui', version: '2.10.2'  

* 微服务端需要加以下依赖  
	compile('org.springframework.cloud:spring-cloud-starter-sleuth')  
	compile('net.logstash.logback:logstash-logback-encoder:5.1')  
	compile('org.springframework.cloud:spring-cloud-starter-zipkin')  

* 要指定 base-url

---
#### 整合 rabbitMQ ####

* zipkin 服务端需要加以下依赖  
	compile('org.springframework.boot:spring-boot-starter')  
	compile group: 'io.zipkin.java', name: 'zipkin-server', version: '2.10.2'  
	compile group: 'io.zipkin.java', name: 'zipkin-autoconfigure-ui', version: '2.10.2'  
	compile group: 'io.zipkin.java', name: 'zipkin-autoconfigure-collector-rabbitmq', version: '2.10.2'

* 微服务端需要加以下依赖  
	compile('org.springframework.cloud:spring-cloud-starter-sleuth')  
	compile('net.logstash.logback:logstash-logback-encoder:5.1')  
	compile('org.springframework.cloud:spring-cloud-starter-zipkin')  
	compile('org.springframework.amqp:spring-rabbit') 

* 因为是通过中间件传递消息，所有微服务端不要指定 base-url，需要指定mq配置 

---
#### 区别 ####
唯一区别是 rabbitMQ 引入了 rabbit 依赖