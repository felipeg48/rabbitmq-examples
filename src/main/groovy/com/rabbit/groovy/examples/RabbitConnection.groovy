/**
 * 
 */
package com.rabbit.groovy.examples

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

/**
 * @author fgutierrezcru
 *
 */
class RabbitConnection {

	protected ConnectionFactory factory
	protected Connection connection
	protected Channel channel
	
	RabbitConnection(){
		factory = new ConnectionFactory()
		factory.host = "localhost"

		connection = factory.newConnection()
		channel = connection.createChannel()
	}
	
	void shutdown(){
		channel.close()
		connection.close()
	}
}
