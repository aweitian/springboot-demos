package me.taw.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 七脉
 * 描述：这里主要讲解五种队列消息
 *  1.一对一普通队列（hello world）
 *  2.一对多工作队列
 *  3.fanout广播队列（发布订阅）
 *  4.direct定向队列（routing-key）
 *  5.topic通配符队列（*、#）
 */
@Configuration
public class RabbitmqConfig {

    /**hello world普通队列**/
    public static final String HELLO_WORLD_QUEUE = "hello_world_queue";

    /**work工作队列**/
    public static final String WORK_QUEUE = "work_queue";

    /**fanout使用队列 one**/
    public static final String FANOUT_QUEUE_ONE = "fanout_queue_one";

    /**fanout使用队列 two**/
    public static final String FANOUT_QUEUE_TWO = "fanout_queue_two";

    /**direct routing使用队列ONE**/
    public static final String DIRECT_QUEUE_ONE = "direct_queue_one";

    /**direct routing使用队列TWO**/
    public static final String DIRECT_QUEUE_TWO = "direct_queue_two";

    /**topic使用队列**/
    public static final String TOPIC_QUEUE_ONE = "topic_queue_one";

    /**topic使用队列**/
    public static final String TOPIC_QUEUE_TWO = "topic_queue_TWO";

    /**fanout交换机**/
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    /**direct routing交换机**/
    public static final String DIRECT_EXCHANGE = "direct_exchange";

    /**topic交换机**/
    public static final String TOPIC_EXCHANGE = "topic_exchange";


    /**定义routing-key提供给direct交换机使用**/
    public static final String ROUTING_KEY = "my_routing_key";

    /**定义topic通配符提供给topic交换机使用**/
    public static final String TOPICS_ONE = "my_topic.*";//*表示匹配任何一个单词
    /**定义topic通配符提供给topic交换机使用**/
    public static final String TOPICS_MORE = "my_topic.#";//#表示匹配任何多个单词

    /**
     * @author 七脉
     * 描述：hello world普通队列，不需要绑定交换机
     *     官方文档里，点对点， 一个生产者、一个队列、一个消费者。
     * @return
     */
    @Bean
    public Queue helloWorldQueue(){
        return new Queue(HELLO_WORLD_QUEUE, true, false, false);
        //return new Queue(HELLO_WORLD_QUEUE, true);
    }

    /**
     * @author 七脉
     * 描述：work工作队列，不需要绑定交换机
     *     官方文档里， 一个生产者、一个队列、多个消费者。
     *     多个消费者时，会均分接收消息。
     * @return
     */
    @Bean
    public Queue workQueue(){
        return new Queue(WORK_QUEUE, true);
    }

    /**
     * @author 七脉
     * 描述：第一个fanout广播队列，需要绑定Fanout交换机
     *     fanout交换机会把消息发送到每一个绑定的队列
     *           官方：发布订阅
     * @return
     */
    @Bean
    public Queue fanoutQueueOne(){
        return new Queue(FANOUT_QUEUE_ONE, true);
    }

    /**
     * @author 七脉
     * 描述：第二个fanout广播队列，需要绑定Fanout交换机
     *     fanout交换机会把消息发送到每一个绑定的队列
     *           官方：发布订阅
     * @return
     */
    @Bean
    public Queue fanoutQueueTwo(){
        return new Queue(FANOUT_QUEUE_TWO, true);
    }

    /**
     * @author 七脉
     * 描述：第一个direct定向队列，需要绑定Direct交换机，并指定routing-key
     *     direct交换机会把消息发送到每一个绑定且指定相同routing-key的队列，
     *           官方：Routing
     * @return
     */
    @Bean
    public Queue directQueueOne(){
        return new Queue(DIRECT_QUEUE_ONE, true);
    }

    /**
     * @author 七脉
     * 描述：第二个direct定向队列，需要绑定Direct交换机，并指定routing-key
     *     direct交换机会把消息发送到每一个绑定且指定相同routing-key的队列，
     *           官方：Routing
     * @return
     */
    @Bean
    public Queue directQueueTwo(){
        return new Queue(DIRECT_QUEUE_TWO, true);
    }


    /**
     * @author 七脉
     * 描述：第一个topic通配符匹配队列，需要绑定Topic交换机
     *     topic队列使用*、#的通配符进行匹配topic交换机的消息
     *           官方：Topics
     * @return
     */
    @Bean
    public Queue topicQueueOne(){
        return new Queue(TOPIC_QUEUE_ONE, true);
    }

    /**
     * @author 七脉
     * 描述：第二个topic通配符匹配队列，需要绑定Topic交换机
     *     topic队列使用*、#的通配符进行匹配topic交换机的消息
     *           官方：Topics
     * @return
     */
    @Bean
    public Queue topicQueueTwo(){
        return new Queue(TOPIC_QUEUE_TWO, true);
    }

    /**
     * @author 七脉
     * 描述：定义 FanoutExchange 交换机
     *     FanoutExchange交换机，将消息发送到每一个绑定的消息队列中
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE, true, true);
    }

    /**
     * @author 七脉
     * 描述：定义 DirectExchange 交换机
     *     DirectExchange交换机，将消息发送到每一个绑定且对应routing-key的队列中
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE, true, true);
    }

    /**
     * @author 七脉
     * 描述：定义 TopicExchange 交换机
     *     TopicExchange交换机，将消息发送到每一个绑定且匹配topic通配符的队列中
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE, true, true);
    }

    /**
     * @author 七脉
     * 描述：将第一个FanoutQueueOne队列绑定到FanoutExchange交换机
     * @param fanoutQueueOne
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding bindingFanoutQueueOne(Queue fanoutQueueOne, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueueOne).to(fanoutExchange);
    }

    /**
     * @author 七脉
     * 描述：将第二个FanoutQueueTwo队列绑定到FanoutExchange交换机
     * @param fanoutQueueTwo
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding bindingFanoutQueueTwo(Queue fanoutQueueTwo, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueueTwo).to(fanoutExchange);
    }

    /**
     * @author 七脉
     * 描述：将第一个DirectQueueOne队列绑定到DirectExchange交换机
     * @param directQueueOne
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindingDirectQueueOne(Queue directQueueOne, DirectExchange directExchange){
        return BindingBuilder.bind(directQueueOne).to(directExchange).with(ROUTING_KEY);
    }

    /**
     * @author 七脉
     * 描述：将第二个DirectQueueTwo队列绑定到DirectExchange交换机
     * @param directQueueTwo
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindingDirectQueueTwo(Queue directQueueTwo, DirectExchange directExchange){
        return BindingBuilder.bind(directQueueTwo).to(directExchange).with(ROUTING_KEY);
    }

    /**
     * @author 七脉
     * 描述：将第一个TopicQueueOne队列绑定到TopicExchange交换机
     * @param topicQueueOne
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding bindingTopicQueueOne(Queue topicQueueOne, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueueOne).to(topicExchange).with(TOPICS_ONE);
    }

    /**
     * @author 七脉
     * 描述：将第二个TopicQueueOne队列绑定到TopicExchange交换机
     * @param topicQueueTwo
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding bindingTopicQueueTwo(Queue topicQueueTwo, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueueTwo).to(topicExchange).with(TOPICS_MORE);
    }

}