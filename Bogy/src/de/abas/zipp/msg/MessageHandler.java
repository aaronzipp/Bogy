package de.abas.zipp.msg;

import java.io.IOException;

import com.rabbitmq.client.Channel;

public class MessageHandler {
	
	public static final String rbmq_queue = "transferData";
	public static final String rbmq_vhost = "Gripp3r";
	// The host of rabbit mq on the erpdemo-dev
	public static final String rbmq_host = "192.168.178.27";
	private Channel channel;

	public MessageHandler(Channel channel) {
		this.channel = channel;
	}

	public void sendMessage(String message) throws IOException {
		channel.basicPublish("", rbmq_queue, null, message.getBytes());
	}

}
