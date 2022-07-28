package com.zhou.Consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhouhelong
 * @creat 2022-07-28 15:49
 * @description:
 */
@SpringBootTest(classes = RibbitMqConsumer.class)
@RunWith(value = SpringRunner.class)
public class RibbitMqConsumer {
    private final static String QUEUE_NAME = "hello";

    private final static String Exchangl_NAME = "mrzhou11";

    private static ConnectionFactory factory = new ConnectionFactory();

    public static void main(String[] args) throws IOException {
        Channel channel = getChannel();
        String queue = channel.queueDeclare().getQueue();
        channel.exchangeDeclare(Exchangl_NAME, "direct");
        channel.queueBind(queue, Exchangl_NAME, "BLACK12");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String backMessage = new String(delivery.getBody(), "UTF-8");
            //设置ack  表示消息确认机制 防止消费者突然挂掉导致消息未被消费
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            System.out.println(Thread.currentThread().getName() + "接受到的消息为" + backMessage);
        };
        try {
            System.out.println("接受消息中=====");
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Test
    public void sendRibbit() throws IOException {
        Channel channel = getChannel();
        channel.exchangeDeclare(Exchangl_NAME, "direct");
        for (int i = 0; i < 5; i++) {
            String message = "Hello World!" + i;
            channel.basicPublish(Exchangl_NAME, "BLACK1", null, message.getBytes());
            System.out.println("发送消息成功" + message);
        }
    }




    public static Channel getChannel() {
        factory.setHost("192.168.47.129");
        factory.setPort(5672);
        Channel channel = null;
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }

    @Test
    public void Consumer() throws InterruptedException {
        Channel channel = getChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String backMessage = new String(delivery.getBody(), "UTF-8");
            System.out.println(Thread.currentThread().getName() + "接受到的消息为" + backMessage);
        };
        try {
            System.out.println("c1接受消息");

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
