package com.example.redismq2;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedissonQueue {
    @Resource
    private ThreadPoolTaskExecutor executor;

    public static final String QUEUE = "delayQueue";

    // 默认超时时间，30秒
    public static final Integer DEFAULT_TIMEOUT = 30;

    @Resource
    private RedissonClient redissonClient;

    // 加入任务并设置到期时间
    public void offer(String taskId, Integer timeout) {
        RDelayedQueue<String> delayedQueue = delayedQueue();
        delayedQueue.offer(taskId, Objects.isNull(timeout) ? DEFAULT_TIMEOUT : timeout, TimeUnit.SECONDS);
    }

    // 移除任务
    public void remove(String taskId) {
        RDelayedQueue<String> delayedQueue = delayedQueue();
        delayedQueue.removeIf(messageId -> messageId.equals(taskId));
    }

    // 任务列表
    public RDelayedQueue<String> delayedQueue() {
        RBlockingDeque<String> blockingDeque = blockingDeque();
        return redissonClient.getDelayedQueue(blockingDeque);
    }

    public RBlockingDeque<String> blockingDeque() {
        return redissonClient.getBlockingDeque(QUEUE);
    }

    public boolean isShutdown() {
        return redissonClient.isShutdown();
    }

    public void shutdown() {
        redissonClient.shutdown();
    }

    /**
     *	服务启动时加载，开始消费延迟队列
     */
    @PostConstruct
    public void consumer() {
        System.out.println("服务启动时加载>>>>>>");
        //获取延迟队列
        RBlockingQueue<Object> delayedQueue = redissonClient.getBlockingQueue(QUEUE);

        //启用一个线程来消费这个延迟队列
        executor.execute(() ->{
            while (true){
                try {
//					System.out.println("while中的线程："+Thread.currentThread().getName());
                    //获取延迟队列中的任务
                    String value = (String) delayedQueue.poll();
                    if(value == null){
                        //如果没有任务就休眠1秒，休眠时间根据业务自己定义
                        Thread.sleep(1000);	//这里休眠时间越短，误差就越小
                        continue;
                    }
                    //异步处理延迟队列中的消息
                    log.info(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
