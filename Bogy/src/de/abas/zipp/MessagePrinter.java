package de.abas.zipp;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessagePrinter {

	public static void main(String[] args)throws Exception {
		
		ConnectionFactory rmq_factory = new ConnectionFactory();
		rmq_factory.setHost("192.168.178.27");
		rmq_factory.setVirtualHost("Gripp3r");
		
		Connection rabbitMQconnection = rmq_factory.newConnection();
		Channel channel = rabbitMQconnection.createChannel();
		
		
		DefaultConsumer printMessage = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {

					System.err.println(new String(body));
			}
			
		};

		channel.basicConsume("transferData", printMessage);
		
		
		
		
		
	}

}
