package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:53 2018/7/11
 * @Modified By:
 */
@Service
public class HelloService {
    private final static Logger logger = LoggerFactory.getLogger(HelloService.class);
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2")})
    public String helloService(){
//        long start = System.currentTimeMillis();
//        String result = restTemplate.getForEntity("http://hello-service/hello",String.class).getBody();
//        long end = System.currentTimeMillis();
//        logger.info("Spend time: "+(end - start));
//        return result;

        return restTemplate.getForEntity("http://api-gateway/service-a/hello",String.class).getBody();
    }

    public String helloFallback(){
        return "error!";
    }

    public Integer helloFallback1(Long id){
        return 404;
    }

    @CacheResult
    @HystrixCommand(commandKey = "commandKey1",fallbackMethod = "helloFallback1")
    public Integer cacheTest(Long id) {
        return restTemplate.getForEntity("http://api-gateway/service-a/cacheTest",Integer.class).getBody();
    }

    public String getCacheKey(Long id){
        return String.valueOf(id);
    }

    //要是有remove方法，则result方法中就必须加和remove中一样的commandKey，才能生效
    @CacheRemove(commandKey = "commandKey1")
    @HystrixCommand
    public void flushCacheByAnnotation1(Long id){
        logger.info("请求缓存已清空！");
        //这个@CacheRemove注解直接用在更新方法上效果更好
    }
}

/**
 *
 * @Description: 通过方法来指定缓存键值
 *
 * @auther: wangkang
 * @date: 17:36 2018/7/11
 * @param:
 * @return:
 *
 */
//    @CacheResult(cacheKeyMethod = "getCacheKey")
//    @HystrixCommand(commandKey = "commandKey1",fallbackMethod = "helloFallback1")
//    public Integer cacheTest(Long id) {
//        return restTemplate.getForEntity("http://hello-service/cacheTest",Integer.class).getBody();
//    }
//
//    public String getCacheKey(Long id){
//        return String.valueOf(id);
//    }
//
//    //要是有remove方法，则result方法中就必须加和remove中一样的commandKey，才能生效
//    @CacheRemove(commandKey = "commandKey1", cacheKeyMethod = "getCacheKey")
//    @HystrixCommand
//    public void flushCacheByAnnotation1(Long id){
//        logger.info("请求缓存已清空！");
//        //这个@CacheRemove注解直接用在更新方法上效果更好
//    }

/**
 *
 * @Description: 通过注解@CacheKey来指定缓存键值
 *
 * @auther: wangkang
 * @date: 17:37 2018/7/11
 * @param:
 * @return:
 *
 */
//    @CacheResult
//    @HystrixCommand(commandKey = "commandKey1",fallbackMethod = "helloFallback1")
//    //@CacheKey("id")出错，不能加id
//    public Integer cacheTest(@CacheKey Long id) {
//        return restTemplate.getForEntity("http://hello-service/cacheTest",Integer.class).getBody();
//    }
//
//    public String getCacheKey(Long id){
//        return String.valueOf(id);
//    }
//
//    //要是有remove方法，则result方法中就必须加和remove中一样的commandKey，才能生效
//    @CacheRemove(commandKey = "commandKey1")
//    @HystrixCommand
//    public void flushCacheByAnnotation1(@CacheKey Long id){
//        logger.info("请求缓存已清空！");
//        //这个@CacheRemove注解直接用在更新方法上效果更好
//    }


/**
 *
 * @Description: 不指定缓存键值，则键值是方法中的所有字段的组成
 *
 * @auther: wangkang
 * @date: \ 2018/7/11
 * @param:
 * @return:
 *
 */
//    @CacheResult
//    @HystrixCommand(commandKey = "commandKey1",fallbackMethod = "helloFallback1")
//    public Integer cacheTest(Long id) {
//        return restTemplate.getForEntity("http://hello-service/cacheTest",Integer.class).getBody();
//    }
//
//    public String getCacheKey(Long id){
//        return String.valueOf(id);
//    }
//
//    //要是有remove方法，则result方法中就必须加和remove中一样的commandKey，才能生效
//    @CacheRemove(commandKey = "commandKey1")
//    @HystrixCommand
//    public void flushCacheByAnnotation1(Long id){
//        logger.info("请求缓存已清空！");
//        //这个@CacheRemove注解直接用在更新方法上效果更好
//    }