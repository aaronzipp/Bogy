package de.abas.zipp;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import de.abas.zipp.behavior.BottleTopColorBehavior;
import de.abas.zipp.msg.MessageHandler;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class RecognizeBottleTop {

	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(MessageHandler.rbmq_host);
		factory.setVirtualHost(MessageHandler.rbmq_vhost);
	
		Connection connection = factory.newConnection();		
		Channel channel = connection.createChannel();
		channel.queueDeclare(MessageHandler.rbmq_queue, true, false, false, null);
		
		MessageHandler msgHandler = new MessageHandler(channel);
		
		Behavior bottleTopColorBehavior = new BottleTopColorBehavior(msgHandler);
		
		Behavior[] behaviorArray = { bottleTopColorBehavior};
		Arbitrator arbitrator = new Arbitrator(behaviorArray);
		
		arbitrator.start();
	}

}
